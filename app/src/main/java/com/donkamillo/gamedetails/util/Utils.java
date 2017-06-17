package com.donkamillo.gamedetails.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DonKamillo on 17.06.2017.
 */

public class Utils {

    public static final String GAME_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"; // 2015-01-25T20:20:30+01:00
    public static final String LAST_LOGIN_DATE_FORMAT = "dd/MM/yyyy'T'HH:mm"; // 04/05/2016T16:45

    public static String getFormattedCurrency(String currencyCode, long balance) {
        NumberFormat format = NumberFormat.getCurrencyInstance(getLocale(currencyCode));
        return format.format(balance);
    }

    public static String getFormattedCurrency(long balance) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(balance);
    }


    public static Locale getLocale(String strCode) {
        if (strCode == null || strCode.isEmpty()) {
            return null;
        }
        for (Locale locale : NumberFormat.getAvailableLocales()) {
            String code = NumberFormat.getCurrencyInstance(locale).getCurrency().getCurrencyCode();
            if (strCode.equals(code)) {
                return locale;
            }
        }
        return null;
    }

    public static String getFormattedDate(String dateStr, String datePattern) {
        return getFormattedDateByLocale(dateStr, datePattern, Locale.getDefault());
    }

    public static String getFormattedDateByLocale(String dateStr, String inputPattern, Locale locale) {
        String str;

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, locale);
        SimpleDateFormat outputFormat = ((SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale));

        try {
            Date date = inputFormat.parse(dateStr);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;
        }

        return str;
    }

}
