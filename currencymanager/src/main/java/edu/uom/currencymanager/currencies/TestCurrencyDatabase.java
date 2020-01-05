package edu.uom.currencymanager.currencies;

import java.util.ArrayList;
import java.util.List;

public class TestCurrencyDatabase implements ICurrencyDatabase {

    List<Currency> currencies;

    public TestCurrencyDatabase() throws Exception {
        init();
    }

    @Override
    public void init() throws Exception {
        currencies = new ArrayList<>();
    }

    @Override
    public List<Currency> getCurrencies() {
        return currencies;
    }

    @Override
    public void addCurrency(Currency currency) throws Exception {
        currencies.add(currency);
    }

    @Override
    public void deleteCurrency(String code) throws Exception {
        currencies.remove(CurrencyOperations.getCurrencyByCode(currencies, code));
    }

    @Override
    public void persist() throws Exception {

    }
}