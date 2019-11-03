package com.android.rahul.movies

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
open class BaseTest {
 var context:Context?= ApplicationProvider.getApplicationContext()

 @Before
 open fun beforeEach() {
  MockitoAnnotations.initMocks(this)
 }

}