package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class EndpointTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testGetJoke() {
        Espresso.onView(withId(R.id.joke_button)).perform(click());
        Espresso.onView(withId(R.id.tv_joke)).check(matches(not(withText(""))));
    }

    @Test
    public void testAsyncTask(){

        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask(new EndpointAsyncTask.EndpointListener() {
            @Override
            public void onTaskCompleted(String obtainedJoke) {
                Assert.assertNotNull(obtainedJoke);
                Assert.assertFalse(obtainedJoke.isEmpty());
            }
        });

        endpointAsyncTask.execute(activityTestRule.getActivity());
    }
}