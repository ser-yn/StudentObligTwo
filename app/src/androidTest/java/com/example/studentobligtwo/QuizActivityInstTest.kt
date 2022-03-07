package com.example.studentobligtwo

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import androidx.test.rule.ActivityTestRule
import org.junit.Assert


class QuizActivityInstTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(QuizActivity::class.java)

    @get:Rule
    var mainActivityTestRule = ActivityTestRule(QuizActivity::class.java)


    @Test
    fun insertRightAnswer(){
        Espresso.onView(ViewMatchers.withId(mainActivityTestRule.activity.viewModel.rightButtonId)).perform(ViewActions.click())
        Assert.assertEquals(mainActivityTestRule.activity.viewModel.rightCounter, 1)
    }

    @Test
    fun insertWrongAnswer(){
        Espresso.onView(ViewMatchers.withId(mainActivityTestRule.activity.viewModel.wrongButtonId)).perform(ViewActions.click())
        Assert.assertEquals(mainActivityTestRule.activity.viewModel.wrongCounter, 1)
    }

}