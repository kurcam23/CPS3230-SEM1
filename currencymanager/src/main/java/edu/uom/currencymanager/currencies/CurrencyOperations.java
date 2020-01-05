package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;
import edu.uom.currencymanager.time.IUtilTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyOperations {

    private ICurrencyDatabase currencyDatabase;
    private CurrencyServer currencyServer;
    public IUtilTime utilTime;
    private HashMap<String, ExchangeRate> exchangeRates = new HashMap<>();

    public CurrencyOperations(ICurrencyDatabase currencyDatabase){
        this.currencyDatabase = currencyDatabase;
        this.currencyServer = new DefaultCurrencyServer();
    }

    public CurrencyOperations(ICurrencyDatabase currencyDatabase, CurrencyServer currencyServer, IUtilTime utilTime){
        this.currencyDatabase = currencyDatabase;
        this.currencyServer = currencyServer;
        this.utilTime = utilTime;
    }

    public List<Currency> getMajorCurrencies() {
        List<Currency> result = new ArrayList<Currency>();
        List<Currency> currencies = currencyDatabase.getCurrencies();

        for (Currency currency : currencies) {
            if (currency.major) {
                result.add(currency);
            }
        }
        return result;
    }

    public static Currency getCurrencyByCode(List<Currency> currencies,String code) {
        for (Currency currency : currencies) {
            if (currency.code.equalsIgnoreCase(code)) {
                return currency;
            }
        }
        return null;
    }

    public ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode) throws  Exception {
        long FIVE_MINUTES_IN_MILLIS = 300000;  //5*60*100

        ExchangeRate result = null;

        Currency sourceCurrency = getCurrencyByCode(currencyDatabase.getCurrencies(), sourceCurrencyCode);
        if (sourceCurrency == null) {
            throw new Exception("Unkown currency: " + sourceCurrencyCode);
        }

        Currency destinationCurrency = getCurrencyByCode(currencyDatabase.getCurrencies(), destinationCurrencyCode);
        if (destinationCurrency == null) {
            throw new Exception("Unkown currency: " + destinationCurrencyCode);
        }

        //Check if exchange rate exists in database
        String key = sourceCurrencyCode + destinationCurrencyCode;
        if (exchangeRates.containsKey(key)) {
            result = exchangeRates.get(key);
            if (utilTime.getTimeInMilliSeconds() - result.lastChecked > FIVE_MINUTES_IN_MILLIS) {
                result = null;
            }
        }

        if (result == null) {
            double rate = currencyServer.getExchangeRate(sourceCurrencyCode, destinationCurrencyCode);
            result = new ExchangeRate(sourceCurrency,destinationCurrency, rate, utilTime);

            //Cache exchange rate
            exchangeRates.put(key, result);

            //Cache inverse exchange rate
            String inverseKey = destinationCurrencyCode+sourceCurrencyCode;
            exchangeRates.put(inverseKey, new ExchangeRate(destinationCurrency, sourceCurrency, 1/rate, utilTime));
        }

        return result;
    }

    public static boolean currencyExists(List<Currency> currencies, String code) {
        return getCurrencyByCode(currencies, code) != null;
    }
}
