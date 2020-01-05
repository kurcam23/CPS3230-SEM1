package edu.uom.currencymanager;

import edu.uom.currencymanager.time.IUtilTime;

public class TimeStatic implements IUtilTime {
    @Override
    public long getTimeInMilliSeconds() {
        return 600000;
    }
}
