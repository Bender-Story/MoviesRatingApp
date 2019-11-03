package com.android.rahul.movies.components


import com.android.rahul.movies.BaseTest
import com.android.rahul.movies.network.AppServiceRepo
import org.junit.Assert
import org.junit.Test

class AppServiceRepoTest: BaseTest(){

    @Test
    fun `get Movie list for page 1 from server positive scenario`(){
        AppServiceRepo().getMovieList(1,{
            Assert.assertTrue(it?.results != null)
        },{ assert(false)})
    }
    @Test
    fun `get Movie list for page 2 from server positive scenario`(){
        AppServiceRepo().getMovieList(2,{
            Assert.assertTrue(it?.results != null)
        },{ assert(false)})
    }

    @Test
    fun `service should not fail even if page is -1`(){
        AppServiceRepo().getMovieList(-1,{
            Assert.assertTrue(it?.results != null)
        },{ assert(false)})
    }
}