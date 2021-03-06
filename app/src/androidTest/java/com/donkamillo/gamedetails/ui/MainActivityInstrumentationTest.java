package com.donkamillo.gamedetails.ui;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.RestServiceTestHelper;
import com.donkamillo.gamedetails.data.local.SharedPreferencesManager;
import com.donkamillo.gamedetails.di.module.ApiModule;
import com.donkamillo.gamedetails.util.Utils;
import com.google.gson.Gson;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by DonKamillo on 18.06.2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest extends InstrumentationTestCase {

    private SharedPreferencesManager preferencesManager;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Context app = InstrumentationRegistry.getTargetContext();
        this.preferencesManager = new SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(app), new Gson());

        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        ApiModule.API_URL = server.url("/").toString();

        clearCache();
        mockServer();
    }

    @Test
    public void testHeaderView_GamesListDisplay() throws Exception {
        Thread.sleep(5000);
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(ViewMatchers.withId(R.id.last_login_layout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withText("PlayerName2")).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.item_name), withText("Game abc"), childAtPosition(allOf(withId(R.id.main_layout), childAtPosition(withId(R.id.list_view), 0)), 0), isDisplayed()));
    }

    @Test
    public void testHeaderView_GameDetailDisplay() throws Exception {
        // Espresso sometimes stops at the first view, probably waiting for the end of the progressbar animation
        // 5000 sleep time allow pass first view
        Thread.sleep(5000);

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        ViewInteraction appCompatTextView = onView(allOf(withId(R.id.item_name), childAtPosition(allOf(withId(R.id.main_layout), childAtPosition(withId(R.id.list_view), 0)), 0), isDisplayed()));
        appCompatTextView.perform(click());

        onView(withText("PlayerName2")).check(matches(isDisplayed()));

        if (isTablet()) {
            onView(ViewMatchers.withId(R.id.last_login_layout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        } else {
            onView(ViewMatchers.withId(R.id.last_login_layout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        }
    }


    @Test
    public void testDetailView() throws Exception {
        Thread.sleep(5000);
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        ViewInteraction appCompatTextView = onView(allOf(withId(R.id.item_name), childAtPosition(allOf(withId(R.id.main_layout), childAtPosition(withId(R.id.list_view), 0)), 0), isDisplayed()));
        appCompatTextView.perform(click());

        onView(withText(Utils.getFormattedCurrency(34000000))).check(matches(isDisplayed()));
        onView(withText("Game date:")).check(matches(isDisplayed()));
        onView(withId(R.id.date)).check(matches(isDisplayed()));
    }

    private void mockServer() throws Exception {
        String playerInfoMockFile = "player_info_ok_response.json";
        String gameDateMockFile = "game_data_ok_response.json";
        server.enqueue(new MockResponse().setResponseCode(200).setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), playerInfoMockFile)));
        server.enqueue(new MockResponse().setResponseCode(200).setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), gameDateMockFile)));
    }

    private void clearCache() {
        preferencesManager.saveCacheDate(0);
    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


    private boolean isTablet() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        return appContext.getResources().getString(R.string.screen_type).equals("tablet");
    }
}