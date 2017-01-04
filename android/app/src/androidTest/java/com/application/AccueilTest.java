package com.application;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import com.soprasteria.movalysmdk.espresso.action.SpoonScreenshotAction;
import com.application.Accueil;
import com.adoliveira.gestionclub.R;
import com.squareup.spoon.Spoon;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AccueilTest {

    @Rule
    public ActivityTestRule<Accueil> mActivityRule = new ActivityTestRule<>(Accueil.class);

    @Test
    public void test() {
   		//@non-generated-start[test][X]
			assertThat(mActivityRule.getActivity(), is(notNullValue()));
			SpoonScreenshotAction.perform("Accueil");
			
		//@non-generated-end
        
    }
}
