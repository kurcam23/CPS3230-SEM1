package edu.uom.currencymanager.currencies.currencies;

import edu.uom.currencymanager.ExchangeRateStatic;
import edu.uom.currencymanager.TimeStatic;
import edu.uom.currencymanager.currencies.*;
import edu.uom.currencymanager.currencyserver.CurrencyServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CurrencyOperationsTest {

    private ICurrencyDatabase currencyDatabase;
    private TimeStatic timeStatic;
    private CurrencyServer currencyServer;
    private CurrencyOperations currencyOperations;

    @Before
    public void setUp() throws Exception {
        currencyDatabase = DatabaseFactory.GetTestCurrencyDatabase();
        timeStatic = new TimeStatic();
        currencyServer = new ExchangeRateStatic();
        currencyOperations = new CurrencyOperations(currencyDatabase, currencyServer, timeStatic);
    }

    @After
    public void tearDown() throws Exception {
        currencyDatabase = null;
        timeStatic = null;
        currencyServer = null;
        currencyOperations = null;
    }

    @Test
    public void getMajorCurrencies() throws Exception {
        Currency majorCurrency = new Currency("NCR","new Currency", true);
        currencyDatabase.addCurrency(majorCurrency);
        List<Currency> currencies =  currencyOperations.getMajorCurrencies();
        assertEquals(1, currencies.size());
    }

    @Test
    public void getCurrencyByCode() throws Exception {
        Currency currency = new Currency("NCR","new Currency", false);
        currencyDatabase.addCurrency(currency);
        Currency result = CurrencyOperations.getCurrencyByCode(currencyDatabase.getCurrencies(), "NCR");
        assertSame(currency, result);
    }

    @Test
    public void testGetExchangeRateNotExists() throws Exception {
        // Adding initial currencies in database
        Currency fromCurrency = new Currency("FCR","First Currency", false);
        Currency toCurrency = new Currency("SCR","Second Currency", false);
        currencyDatabase.addCurrency(fromCurrency);
        currencyDatabase.addCurrency(toCurrency);
        ExchangeRate exchangeRate = currencyOperations.getExchangeRate("FCR", "SCR");
        ExchangeRate expectedExchangeRate = new ExchangeRate(fromCurrency, toCurrency, currencyServer.getExchangeRate("FCR", "SCR"), timeStatic);

        assertEquals(expectedExchangeRate.sourceCurrency, exchangeRate.sourceCurrency);
        assertEquals(expectedExchangeRate.destinationCurrency, exchangeRate.destinationCurrency);
        assertEquals(expectedExchangeRate.rate, exchangeRate.rate, 0);
        assertEquals(expectedExchangeRate.lastChecked, exchangeRate.lastChecked);
    }

    @Test
    public void currencyExists() throws Exception {
        Currency currency = new Currency("NCR","new Currency", false);
        currencyDatabase.addCurrency(currency);
        boolean result = CurrencyOperations.currencyExists(currencyDatabase.getCurrencies(), "NCR");
        assertTrue(result);
    }
}