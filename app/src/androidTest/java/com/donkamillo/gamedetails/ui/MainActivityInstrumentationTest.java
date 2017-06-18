package com.donkamillo.gamedetails.ui;

import android.content.Context;
import android.content.Intent;
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
import com.donkamillo.gamedetails.data.remote.DropBoxService;
import com.donkamillo.gamedetails.util.Utils;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        DropBoxService.API_URL = server.url("/").toString();

        clearCache();
        mockServer();
    }

    @Test
    public void testHeaderView_GamesListDisplay() throws Exception {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(ViewMatchers.withId(R.id.last_login_layout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withText("PlayerName2")).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.name), withText("Game abc"), childAtPosition(allOf(withId(R.id.main_layout), childAtPosition(withId(R.id.list_view), 0)), 0), isDisplayed()));
    }

    @Test
    public void testHeaderView_GameDetailDisplay() throws Exception {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        ViewInteraction appCompatTextView = onView(allOf(withId(R.id.name), withText("Game abc"), childAtPosition(allOf(withId(R.id.main_layout), childAtPosition(withId(R.id.list_view), 0)), 0), isDisplayed()));
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
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        ViewInteraction appCompatTextView = onView(allOf(withId(R.id.name), childAtPosition(allOf(withId(R.id.main_layout), childAtPosition(withId(R.id.list_view), 0)), 0), isDisplayed()));
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
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedPreferencesManager.saveCacheDate(0, appContext);
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