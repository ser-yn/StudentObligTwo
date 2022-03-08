package com.example.studentobligtwo

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent


@RunWith(AndroidJUnit4::class)
class MainActivityInstTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickButton(){
        Intents.init()

        Espresso.onView(withId(R.id.DataButton)).perform(click())
        intended(hasComponent(DatabaseActivity::class.java.name))
    }
}