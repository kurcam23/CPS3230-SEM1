package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyDatabase implements ICurrencyDatabase {

    CurrencyServer currencyServer;
    List<Currency> currencies = new ArrayList<Currency>();
    HashMap<String, ExchangeRate> exchangeRates = new HashMap<String, ExchangeRate>();

    String currenciesFile = "target" + File.separator + "classes" + File.separator + "currencies.txt";

    public CurrencyDatabase() throws Exception {
        init();
    }

    public void init() throws Exception {
        //Initialise currency server
        currencyServer = new DefaultCurrencyServer();

        //Read in supported currencies from text file
        BufferedReader reader = new BufferedReader(new FileReader(currenciesFile));

        //skip the first line to avoid header
        String firstLine = reader.readLine();
        if (!firstLine.equals("code,name,major")) {
            throw new Exception("Parsing error when reading currencies file.");
        }

        while (reader.ready()) {
            String  nextLine = reader.readLine();

            //Check if line has 2 commas
            int numCommas = 0;
            char[] chars = nextLine.toCharArray();
            for (char c : chars) {
                if (c == ',') numCommas++;
            }

            if (numCommas != 2) {
                throw new Exception("Parsing error: expected two commas in line " + nextLine);
            }

            Currency currency = Currency.fromString(nextLine);

            if (currency.code.length() == 3) {
                if (!CurrencyOperations.currencyExists(currencies,currency.code)) {
                    currencies.add(currency);
                }
            } else {
                System.err.println("Invalid currency code detected: " + currency.code);
            }
        }
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void addCurrency(Currency currency) throws Exception {

        //Save to list
        currencies.add(currency);

        //Persist
        persist();
    }

    public void deleteCurrency(String code) throws Exception {

        //Save to list
        currencies.remove(CurrencyOperations.getCurrencyByCode(currencies,code));

        //Persist
        persist();
    }

    public void persist() throws Exception {

        //Persist list
        BufferedWriter writer = new BufferedWriter(new FileWriter(currenciesFile));

        writer.write("code,name,major\n");
        for (Currency currency : currencies) {
            writer.write(currency.code + "," + currency.name + "," + (currency.major ? "yes" : "no"));
            writer.newLine();
        }

        writer.flush();
        writer.close();
    }

}
