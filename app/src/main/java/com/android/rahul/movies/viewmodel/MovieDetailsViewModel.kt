package com.android.rahul.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rahul.movies.model.Result
import com.android.rahul.movies.network.AppServiceRepo

class MovieDetailsViewModel : ViewModel() {

    val movieResults: MutableLiveData<List<Result>?> = MutableLiveData()
    // result to show details on the screen
    val result: MutableLiveData<Result> = MutableLiveData()
    private val appServiceRepo = AppServiceRepo()

    // call service and fetch related movie list update the movie results mutable list.
    fun fetchRelatedResults(
        id: String, onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        appServiceRepo.getRelatedList(id, { response ->
            movieResults.postValue(response.results)
            onSuccess.invoke()
        }, {
            onError.invoke(it)
        })
    }

}