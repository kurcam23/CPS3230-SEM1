package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExchangeRateTest {

    private Currency fromCurrency;
    private Currency toCurrency;
    private ExchangeRate exchangeRate;

    @Before
    public void setUp() {
        fromCurrency = new Currency("FCR", "From Currency", true);
        toCurrency = new Currency("TCR", "To Currency", true);
        exchangeRate = new ExchangeRate(fromCurrency, toCurrency, 0.90);
    }

    @After
    public void tearDown() {
        toCurrency = null;
        fromCurrency = null;
        exchangeRate = null;
    }

    @Test
    public void testToString() {
        assertEquals("FCR 1 = TCR 0.90", exchangeRate.toString());
    }
}