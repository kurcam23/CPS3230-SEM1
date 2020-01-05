package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.DatabaseFactory;
import edu.uom.currencymanager.currencies.ExchangeRate;
import edu.uom.currencymanager.currencies.ICurrencyDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class CurrencyManagerTest {

    private CurrencyManager currencyManager;
    private ICurrencyDatabase currencyDatabase;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        currencyDatabase = DatabaseFactory.GetTestCurrencyDatabase();
        currencyManager = new CurrencyManager(currencyDatabase, new ExchangeRateStatic(), new TimeStatic());
    }

    @After
    public void tearDown() throws Exception {
        currencyDatabase = null;
        currencyManager = null;
    }

    @Test
    public void getMajorCurrencyRates() throws Exception {
        Currency firstCurrency = new Currency("FCR","First Currency", true);
        Currency secondCurrency = new Currency("SCR","Second Currency", true);
        currencyDatabase.addCurrency(firstCurrency);
        currencyDatabase.addCurrency(secondCurrency);

        List<ExchangeRate> exchangeRates =  currencyManager.getMajorCurrencyRates();
        assertEquals(2, exchangeRates.size());
    }

    @Test
    public void getExchangeRate() throws Exception {
        Currency firstCurrency = new Currency("FCR","First Currency", true);
        Currency secondCurrency = new Currency("SCR","Second Currency", true);
        currencyDatabase.addCurrency(firstCurrency);
        currencyDatabase.addCurrency(secondCurrency);

        ExchangeRate exchangeRate = currencyManager.getExchangeRate("FCR", "SCR");
        assertNotNull(exchangeRate);
    }

    @Test
    public void addCurrency() throws Exception {
        currencyManager.addCurrency("NCR", "new Currency", true);
        assertEquals(1, currencyDatabase.getCurrencies().size());
    }

    @Test
    public void addCurrencyExists() throws Exception {
        Currency currency = new Currency("NCR","new Currency", false);
        currencyDatabase.addCurrency(currency);
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("The currency NCR already exists.");
        currencyManager.addCurrency("NCR", "new Currency", true);
    }

    @Test
    public void addCurrencyCodeShort() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("A currency code should have 3 characters.");
        currencyManager.addCurrency("SC", "Currency", true);
    }

    @Test
    public void addCurrencyCodeLong() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("A currency code should have 3 characters.");
        currencyManager.addCurrency("LNGC", "Currency", true);
    }

    @Test
    public void addCurrencyNameShort() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("A currency's name should be at least 4 characters long.");
        currencyManager.addCurrency("CNS", "nms", true);
    }

    @Test
    public void deleteCurrencyWithCode() throws Exception {
        Currency currency = new Currency("NCR","new Currency", false);
        currencyDatabase.addCurrency(currency);
        currencyManager.deleteCurrencyWithCode("NCR");
        assertEquals(0, currencyDatabase.getCurrencies().size());
    }
}