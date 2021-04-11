package com.example.androidtermproject;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest3 {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splashActivityTest3() {
        //Type id to login
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_id),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("k111"), closeSoftKeyboard());

        //Type password to login
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edit_password),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("kkkk11"), closeSoftKeyboard());

        //Press the button login to use the application
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_login), withText("로그인"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        //Press the cody button to watch cody screen
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.btn_cody),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        //Press the button with '코디 보기' and set the cody list on the screen
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.change_btn), withText("코디 보기"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        //Press the button to go back
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.change_btn), withText("돌아가기"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        //Show the image view with cody list
        DataInteraction imageView = onData(anything())
                .inAdapterView(allOf(withId(R.id.cloth_grid),
                        childAtPosition(
                                withId(R.id.codyImg_layout),
                                0)))
                .atPosition(0);
        imageView.perform(click());

        //Press the button to select one cody with the avatar
        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.change_btn), withText("코디 보기"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton4.perform(click());

        //Press the button to move back
        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.go_back_state), withText("되돌리기"),
                        childAtPosition(
                                allOf(withId(R.id.cloth_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());

        //Press the spinner to choose type of cody
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.category_spinner),
                        childAtPosition(
                                allOf(withId(R.id.category_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        //Show the spinner with types
        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        //Press the next spinner
        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.detail_spinner),
                        childAtPosition(
                                allOf(withId(R.id.detail_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        //Show the spinner with types
        DataInteraction checkedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        checkedTextView.perform(click());

        //Press the back button to move back
        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.change_btn), withText("돌아가기"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton6.perform(click());

        //Press the button to change the avatar with selected clothes
        ViewInteraction button = onView(
                allOf(withId(R.id.change_btn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    //Enable to testing can move the position
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {

            //Move the position to do next things automatically
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            //Show the view with changed position
            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
