package com.android.rahul.movies.viewmodel

import com.android.rahul.movies.BaseTest
import com.android.rahul.movies.database.AppDatabase
import com.android.rahul.movies.model.Result
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTest : BaseTest() {
    var mainViewModel = MainViewModel()
    @Test
    fun `fetch movies`() {
        mainViewModel.fetchMovieResults({
            mainViewModel?.movieResults?.value?.isNotEmpty()?.let { Assert.assertTrue(it) }
        }, {assert(false)})
    }

    @Test
    fun addDataToLocalDatabase(){

        val appDatabase= Mockito.mock(AppDatabase::class.java)

        Assert.assertNotNull(mainViewModel.addDataToLocalDatabase(appDatabase))
    }

    @Test
    fun getDataFromLocalDataBase(){

        val appDatabase= Mockito.mock(AppDatabase::class.java)

        Assert.assertNotNull(mainViewModel.getDataFromLocalDataBase(appDatabase))
    }

    @Test
    fun addDataToLocalDatabase2(){

        val appDatabase= null

        Assert.assertNotNull(mainViewModel.addDataToLocalDatabase(appDatabase))
    }

    @Test
    fun getDataFromLocalDataBase2(){

        val appDatabase= null

        Assert.assertNotNull(mainViewModel.getDataFromLocalDataBase(appDatabase))
    }

    @Test
    fun `sort the results`(){
        var actual = listOf<Result>(Result(id=0,vote_average = "9"),
            Result(id=0,vote_average = "4"),Result(id=0,vote_average = "5"),Result(id=0,vote_average = "6"))
        var expected = listOf<Result>(Result(id=0,vote_average = "9"),
            Result(id=0,vote_average = "6"),Result(id=0,vote_average = "5"),Result(id=0,vote_average = "4"))

        assertEquals(mainViewModel?.sortByRating(actual)?.get(1)!!.vote_average, expected[1].vote_average)
        assertEquals(mainViewModel?.sortByRating(actual)?.get(3)!!.vote_average, expected[3].vote_average)
    }
    @Test
    fun `sort the empty results`(){
        var actual = listOf<Result>()
        var expected = listOf<Result>()

        assertEquals(mainViewModel?.sortByRating(actual)?.size, expected.size)
        assertNotNull(mainViewModel?.sortByRating(actual))
    }


}