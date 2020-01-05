package edu.uom.currencymanager.currencies.currencies;

import edu.uom.currencymanager.currencies.Currency;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyTest {

    private Currency currency;

    @Before
    public void setUp() throws Exception {
        currency = new Currency("CUR", "Currency", true);
    }

    @After
    public void tearDown() throws Exception {
        currency = null;
    }

    @Test
    public void testFromStringName() throws Exception {
        Currency fromStringCurrency = Currency.fromString("CUR,Currency,yes");
        assertEquals("Currency", fromStringCurrency.name);
    }

    @Test
    public void testFromStringCode() throws Exception {
        Currency fromStringCurrency = Currency.fromString("CUR,Currency,yes");
        assertEquals("CUR", fromStringCurrency.code);
    }

    @Test
    public void testFromStringMajorT() throws Exception {
        Currency fromStringCurrency = Currency.fromString("CUR,Currency,yes");
        assertTrue(fromStringCurrency.major);
    }

    @Test
    public void testFromStringMajorF() throws Exception {
        Currency fromStringCurrency = Currency.fromString("CUR,Currency,no");
        assertFalse(fromStringCurrency.major);
    }

    @Test
    public void testToString() {
        assertEquals("CUR - Currency", currency.toString());
    }
}