package com.example.studentobligtwo

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class AddActivityInstTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(AddActivity::class.java)

    @get:Rule
    var activityTestRule = ActivityTestRule(AddActivity::class.java)

    @Test
    fun clickButton(){
        Intents.init()

        val resultData = Intent()
        resultData.putExtra("wolf", "wolfImage")
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

        Espresso.onView(ViewMatchers.withId(R.id.add_imageView)).perform(ViewActions.click())
        intending(hasAction(Intent.ACTION_CHOOSER)).respondWith(result)

        Espresso.onView(ViewMatchers.withId(R.id.add_AddButton)).perform(ViewActions.click())
    }
}