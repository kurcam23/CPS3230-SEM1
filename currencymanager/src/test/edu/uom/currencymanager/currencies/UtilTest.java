package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void testFormatAmount() {
        assertEquals("1.00", Util.formatAmount(1));
    }

    @Test
    public void testFormatAmountMultipleDecimalPoint() {
        assertEquals("0.12", Util.formatAmount(0.123));
    }

    @Test
    public void testFormatAmountDecimalPoint() {
        assertEquals("0.10", Util.formatAmount(0.1));
    }

    @Test
    public void testFormatAmountLargeNumber() {
        assertEquals("1,000,000,000.00", Util.formatAmount(1000000000));
    }
}