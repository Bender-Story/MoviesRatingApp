package com.android.rahul.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rahul.movies.database.AppDatabase
import com.android.rahul.movies.model.NowPlayingData
import com.android.rahul.movies.model.Result
import com.android.rahul.movies.network.AppServiceRepo
import org.jetbrains.anko.doAsync

class MainViewModel :   ViewModel() {
    val movieResults: MutableLiveData<List<Result>?> = MutableLiveData()
    private val appServiceRepo = AppServiceRepo()
    private var nowPlayingData:NowPlayingData?=null
    var fetchPage:Int=1

    // call service to fetch movie list and update the mutable list.
    fun fetchMovieResults(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        appServiceRepo.getMovieList(fetchPage,{ response ->
            nowPlayingData=response
            movieResults.postValue(sortByRating(nowPlayingData?.results))
            onSuccess.invoke()
        }, {
            onError.invoke(it)
        })
    }

     fun sortByRating(result: List<Result>?): List<Result>? {
       return result?.sortedByDescending { it.vote_average }
    }

    fun addDataToLocalDatabase(db: AppDatabase?){
        doAsync {
            db?.resultDao()?.deleteAll()
            movieResults.value?.forEach {
                db?.resultDao()?.insertAll(it)
            }
        }
    }

    fun getDataFromLocalDataBase(db: AppDatabase?){
        doAsync {
            var data = db?.resultDao()?.getAll()
            movieResults.postValue(sortByRating(data))
        }
    }

}