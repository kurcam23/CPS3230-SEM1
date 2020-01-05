package edu.uom.currencymanager.time;

public class UtilTime implements IUtilTime{
    @Override
    public long getTimeInMilliSeconds() {
        return System.currentTimeMillis();
    }
}
