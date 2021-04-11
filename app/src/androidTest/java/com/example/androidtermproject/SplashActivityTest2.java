package com.example.androidtermproject;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest2 {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    //The authority to access to external storage like the camera and album
    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_EXTERNAL_STORAGE");

    //The code to testing automatically
    @Test
    public void splashActivityTest2() {
        //Click the button to sign up
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_register), withText("회원가입"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        //move to sign up page and input the name with '이이'
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.name_input),
                        childAtPosition(
                                allOf(withId(R.id.name_layout),
                                        childAtPosition(
                                                withId(R.id.sign_in),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("이이"), closeSoftKeyboard());

        // input the id what you want with 'kk11'
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.id_input),
                        childAtPosition(
                                allOf(withId(R.id.id_layout),
                                        childAtPosition(
                                                withId(R.id.sign_in),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("kk11"), closeSoftKeyboard());

        //click the button to check the id already have
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.duplication_btn), withText("중복 확인"),
                        childAtPosition(
                                allOf(withId(R.id.id_layout),
                                        childAtPosition(
                                                withId(R.id.sign_in),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        //Show that kk11 already has in the database
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.id_input), withText("kk11"),
                        childAtPosition(
                                allOf(withId(R.id.id_layout),
                                        childAtPosition(
                                                withId(R.id.sign_in),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        //replace the id_input from kk11 to k111
        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.id_input), withText("kk11"),
                        childAtPosition(
                                allOf(withId(R.id.id_layout),
                                        childAtPosition(
                                                withId(R.id.sign_in),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("k111"));

        //Type the other id with 'k111'
        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.id_input), withText("k111"),
                        childAtPosition(
                                allOf(withId(R.id.id_layout),
                                        childAtPosition(
                                                withId(R.id.sign_in),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        //Type the password
        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.password_input),
                        childAtPosition(
                                allOf(withId(R.id.password_layout),
                                        childAtPosition(
                                                withId(R.id.sign_in),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("kkkk11"), closeSoftKeyboard());

        //Type the password onemore time to check it is correct
        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.password_commit_input),
                        childAtPosition(
                                allOf(withId(R.id.password_layout),
                                        childAtPosition(
                                                withId(R.id.sign_in),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("kkkk11"), closeSoftKeyboard());

        //Type the email address
        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.email_input),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.sign_in),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("kk@naver.com"), closeSoftKeyboard());

        //Click the button to finish the sign up
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.register), withText("가입하기"),
                        childAtPosition(
                                allOf(withId(R.id.sign_in),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                4),
                        isDisplayed()));
        appCompatButton3.perform(click());

        //Type the id at the home page to login
        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.edit_id),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("k111"), closeSoftKeyboard());

        //Type the password at the home page to login
        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.edit_password),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("kkkk11"), closeSoftKeyboard());

        //Click the button login and check the id and password are right
        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_login), withText("로그인"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        //Success to login

        //Click the closet button and move to closet page
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.btn_closet),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        //Click the cloth to show
        DataInteraction imageView = onData(anything())
                .inAdapterView(allOf(withId(R.id.cloth_grid),
                        childAtPosition(
                                withId(R.id.codyImg_layout),
                                0)))
                .atPosition(3);
        imageView.perform(scrollTo(), click());

        //Press the favorite about the cliked cloth and update database
        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.information_favorite_register_btn), withText("등록"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0)));
        appCompatButton5.perform(scrollTo(), click());

        //press the favortie button to release the favortie
        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.information_favorite_release_btn), withText("해제"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                1)));
        appCompatButton6.perform(scrollTo(), click());

        //press the button to go back to the closet page
        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.information_close_btn), withText("닫기"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatButton7.perform(scrollTo(), click());

        //Press the '상의' to choose the type of clothes to watch
        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.btn_top), withText("상의"),
                        childAtPosition(
                                allOf(withId(R.id.category_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton8.perform(click());

        //Press the spinner to choose the type of ordering
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_type_top),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.page_top),
                                        0),
                                1),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        //Show the spinner
        DataInteraction textView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        textView.perform(click());

        //Show the screen with the type
        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.check_top), withText("확인"),
                        childAtPosition(
                                allOf(withId(R.id.page_top),
                                        childAtPosition(
                                                withId(R.id.container),
                                                4)),
                                3),
                        isDisplayed()));
        appCompatButton9.perform(click());

        //Press the button to add clothes
        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.btn_register_cloth), withText("옷 추가"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cody_layout),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton10.perform(click());

        //Press the imageView to add clothes
        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.register_picture), withText("사진 추가"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatButton11.perform(scrollTo(), click());

        //Press button cancle to cancle the adding
        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.btn_cancel), withText("취소"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.select_picture),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton12.perform(click());

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
