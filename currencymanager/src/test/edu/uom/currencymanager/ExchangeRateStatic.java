package edu.uom.currencymanager;

import edu.uom.currencymanager.currencyserver.CurrencyServer;

public class ExchangeRateStatic implements CurrencyServer {
    @Override
    public double getExchangeRate(String sourceCurrency, String destinationCurrency) {
        return 1;
    }
}
