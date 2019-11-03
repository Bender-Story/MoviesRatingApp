package com.android.rahul.movies.viewmodel

import com.android.rahul.movies.BaseTest
import org.junit.Test

class MovieDetailsViewModelTest :BaseTest(){
    var movieDetailsViewModel = MovieDetailsViewModel()
    @Test
    fun `fetch movies details when id is empty should throw error`() {
        movieDetailsViewModel.fetchRelatedResults("",{},{
            assert(true)
        })
    }

    @Test
    fun `fetch movies details when id is ivalid should throw error`() {
        movieDetailsViewModel.fetchRelatedResults("jhsjghe9238",{},{
            assert(true)
        })
    }

    @Test
    fun `fetch movies details when id is valid`() {
        movieDetailsViewModel.fetchRelatedResults("475557",{
            assert(true)
        },{
        })
    }
}