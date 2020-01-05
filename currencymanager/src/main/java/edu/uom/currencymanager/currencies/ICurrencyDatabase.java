package edu.uom.currencymanager.currencies;

import java.util.List;

public interface ICurrencyDatabase {
    void init() throws Exception;
    List<Currency> getCurrencies();
    void addCurrency(Currency currency) throws Exception;
    void deleteCurrency(String code) throws Exception;
    void persist() throws Exception;
}
