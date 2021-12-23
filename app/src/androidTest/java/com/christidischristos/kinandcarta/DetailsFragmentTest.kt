package com.christidischristos.kinandcarta

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.christidischristos.kinandcarta.ui.details.DetailsFragment
import com.christidischristos.kinandcarta.ui.details.DetailsFragmentArgs
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    @Test
    fun basicCheck() {
        val applicationContext = ApplicationProvider.getApplicationContext<MyApplication>()
        val fakeStudy = FakeData.caseStudies.first()
        val bundle = DetailsFragmentArgs(fakeStudy).toBundle()
        launchFragmentInContainer<DetailsFragment>(bundle, R.style.Theme_KinAndCarta)
        val expectedText = applicationContext.getString(R.string.category_x, fakeStudy.category)
        onView(withId(R.id.category_tv)).check(
            matches(allOf(isDisplayed(), withText(expectedText)))
        )
        Thread.sleep(1000)
    }
}
