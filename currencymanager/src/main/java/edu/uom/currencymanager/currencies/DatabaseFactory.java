package edu.uom.currencymanager.currencies;

public class DatabaseFactory {
    public static ICurrencyDatabase GetCurrencyDatabase() throws Exception{
        return new CurrencyDatabase();
    }

    public static ICurrencyDatabase GetTestCurrencyDatabase() throws Exception {
        return new TestCurrencyDatabase();
    }
}
