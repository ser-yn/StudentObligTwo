package com.example.studentobligtwo

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class AddActivityInstTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(AddActivity::class.java)

    @Test
    fun clickButton(){
        Intents.init()

        Espresso.onView(ViewMatchers.withId(R.id.add_imageView)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(DatabaseActivity::class.java.name))

    }
}