package com.donkamillo.gamedetails;

import com.donkamillo.gamedetails.util.Utils;

import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by DonKamillo on 17.06.2017.
 */

public class UtilsTest {

    @Test
    public void getLocale_null_input() {
        assertNull(Utils.getLocale(null));
    }

    @Test
    public void getLocale_empty_input() {
        assertNull(Utils.getLocale(""));
    }

    @Test
    public void getLocale_wrong_input() {
        assertNull(Utils.getLocale("ABC"));
    }

    @Test
    public void getLocale_correct_input() {
        assertThat(Utils.getLocale("GBP"), is(Locale.UK));
    }

    @Test
    public void getFormattedDateByLocale_wrong_format_input() {
        assertThat(Utils.getFormattedDateByLocale("04-05-2016", Utils.LAST_LOGIN_DATE_FORMAT, Locale.UK), is("04-05-2016"));
    }

    @Test
    public void getFormattedDateByLocale_correct_input() {
        assertThat(Utils.getFormattedDateByLocale("04/05/2016T16:45", Utils.LAST_LOGIN_DATE_FORMAT, Locale.UK), is("04-May-2016 16:45:00"));
    }

    @Test
    public void getFormattedCurrencyByLocal_positive_input() {
        assertThat(Utils.getFormattedCurrencyByLocal(1000000, Locale.UK), is("£1,000,000.00"));
        assertThat(Utils.getFormattedCurrencyByLocal(1000000, Locale.US), is("$1,000,000.00"));
        assertThat(Utils.getFormattedCurrencyByLocal(1000000, Locale.FRANCE), is("1 000 000,00 €"));
    }

    @Test
    public void getFormattedCurrencyByLocal_negative_input() {
        assertThat(Utils.getFormattedCurrencyByLocal(-50000, Locale.UK), is("-£50,000.00"));
    }

    @Test
    public void getFormattedCurrencyByLocal_zero_input() {
        assertThat(Utils.getFormattedCurrencyByLocal(0, Locale.UK), is("£0.00"));
    }

}
