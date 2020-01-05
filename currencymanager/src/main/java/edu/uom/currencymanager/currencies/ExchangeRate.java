package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.time.IUtilTime;

import javax.swing.text.NumberFormatter;

public class ExchangeRate {

    public Currency sourceCurrency;
    public Currency destinationCurrency;
    public double rate;
    public long lastChecked;

    public ExchangeRate(Currency sourceCurrency, Currency destinationCurrency, double rate, IUtilTime utilTime) {
        this.sourceCurrency =sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.rate =rate;
        lastChecked = utilTime.getTimeInMilliSeconds();
    }

    public String toString() {
        return sourceCurrency.code + " 1 = " + destinationCurrency.code + " " + Util.formatAmount(rate);
    }

}
