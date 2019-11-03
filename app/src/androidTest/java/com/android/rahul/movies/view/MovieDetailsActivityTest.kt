package com.android.rahul.movies.view


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.android.rahul.movies.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MovieDetailsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun movieDetailsActivityTest() {
        Thread.sleep(3000)

        onView(withId(R.id.mainRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(3000)

        val textView7 = onView(
            allOf(
                withText("Movie Details"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("Movie Details")))

        val textView = onView(
            allOf(
                withText("Title :"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Title :")))

        val textView2 = onView(
            allOf(
                withText("Released On :"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Released On :")))

        val textView3 = onView(
            allOf(
                withText("Rating :"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Rating :")))

        val textView4 = onView(
            allOf(
                withText("Language :"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        3
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Language :")))

        val textView5 = onView(
            allOf(
                withText("Overview :"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Overview :")))
        onView(withId(R.id.relatedMoviesTitle)).check(matches(withText("Related Movies")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
