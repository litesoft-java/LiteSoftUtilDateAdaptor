// This Source Code is in the Public Domain per: http://unlicense.org
package org.litesoft.deprecated;

import java.util.*;

@SuppressWarnings({"UnusedDeclaration", "deprecation"})
public class LL_UtilDateAdaptor
{
    protected final int mUTCtoWallOffsetMinutes; // 0 = UTC, -600 = HT (Hawaii Time or more formally: HST = Alaska-Hawaii Standard Time)
    protected final int mTemporalFields; // 0 = Year, 1 = Year & Month, 2 = Year - Day, 3 = Year - Hour, 4 = Year - Min, 5 = Year - Sec, 6 = Year - MilliSec
    protected final int mYear, mMonth, mDay, mHour, mMin, mSec, mMilliSec;

    protected LL_UtilDateAdaptor( int pUTCtoWallOffsetMinutes, int pTemporalFields, //
                                  int pYear, int pMonth, int pDay, int pHour, int pMin, int pSec, int pMilliSec )
    {
        mUTCtoWallOffsetMinutes = pUTCtoWallOffsetMinutes;
        mTemporalFields = pTemporalFields;
        mYear = pYear;
        mMonth = (pMonth != 0) ? pMonth : 1;
        mDay = (pDay != 0) ? pDay : 1;
        mHour = pHour;
        mMin = pMin;
        mSec = pSec;
        mMilliSec = pMilliSec;
    }

    protected LL_UtilDateAdaptor( Date pWallDate, int pTemporalFields )
    {
        mUTCtoWallOffsetMinutes = -pWallDate.getTimezoneOffset();
        int dMonth = 1;
        int dDay = 1;
        int dHour = 0;
        int dMin = 0;
        int dSec = 0;
        int dMilliSec = 0;
        switch ( mTemporalFields = pTemporalFields )
        {
            case 6:
                long zMillisSinceEpoch = pWallDate.getTime();
                int zRawMilliSec = (int) (zMillisSinceEpoch % 1000L);
                dMilliSec = (zRawMilliSec >= 0) ? zRawMilliSec : (1000 + zRawMilliSec);
                // Fall Thru
            case 5:
                dSec = pWallDate.getSeconds();
                // Fall Thru
            case 4:
                dMin = pWallDate.getMinutes();
                // Fall Thru
            case 3:
                dHour = pWallDate.getHours();
                // Fall Thru
            case 2:
                dDay = pWallDate.getDate();
                // Fall Thru
            case 1:
                dMonth = pWallDate.getMonth() + 1;
                // Fall Thru
            default:
                break;
        }
        mYear = pWallDate.getYear() + 1900;
        mMonth = dMonth;
        mDay = dDay;
        mHour = dHour;
        mMin = dMin;
        mSec = dSec;
        mMilliSec = dMilliSec;
    }

    public static int dayOfWeek( Date pWallDate )
    {
        return pWallDate.getDay();
    }

    public static long parseUtilDate( String pUtilDateToString )
    {
        return Date.parse( pUtilDateToString );
    }

    public static long UTC( int pYear, int pMonth, int pDay, int pHour, int pMin, int pSec, int pMilliSec )
    {
        return Date.UTC( pYear - 1900, pMonth - 1, pDay, pHour, pMin, pSec ) + pMilliSec;
    }
}
