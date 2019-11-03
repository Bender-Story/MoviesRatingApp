package com.android.rahul.movies.view


import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.android.rahul.movies.utils.NetworkUtils
import com.android.rahul.movies.R
import com.android.rahul.movies.components.ActivityUIExt
import com.android.rahul.movies.controller.Navigator
import com.android.rahul.movies.database.AppDatabase
import com.android.rahul.movies.foundation.BaseActivity
import com.android.rahul.movies.model.Result
import com.android.rahul.movies.viewmodel.MainRowViewModel
import com.android.rahul.movies.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : BaseActivity() {
    var viewModel: MainViewModel? = null
    private var rowViewModels: ArrayList<MainRowViewModel>? = arrayListOf()
    private var database: AppDatabase?=null
    private var isOffline:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initDataBase()
        observeList()
        callService()


    }

    // Observers the mutable list to update recycler view
    private fun observeList() {
        viewModel?.movieResults?.observe(this,
            Observer {
                initRecyclerView(it)
            })
        supportActionBar?.title = getString(R.string.main_list_head)
    }

    // Add data to the recycler view
    private fun initRecyclerView(data: List<Result>?) {
    doAsync {

        data?.map {
           var mainViewModel  =MainRowViewModel(it) { result ->
               var bundle = Bundle()
               bundle.putSerializable("result", result)
               bundle.putBoolean("isOffline", isOffline)
               Navigator.goToActivity(this@MainActivity, MovieDetailActivity::class.java, bundle = bundle)
           }
                rowViewModels?.add(mainViewModel)
        }
        // Add list to adapter using UI Thread
        uiThread {
            mainRecyclerView.adapter = MainAdapter(rowViewModels)
            mainRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        }


    }

    // fetch movie list from the service
    private fun callService() {
        if(!networkAvailable()) {
            createErrorDilaog(getString(R.string.checkNetwork))
            return
        }

        progressbar.isVisible = true
        viewModel?.fetchMovieResults({
            viewModel?.addDataToLocalDatabase(database)
            isOffline=false
            stopProgressBar()
        }, {
            stopProgressBar()
            createErrorDilaog(it)
        })
    }

    private fun networkAvailable(): Boolean {
      return NetworkUtils.isNetworkAvailable(this)
    }

    private fun createErrorDilaog(error:String){
        ActivityUIExt(this).buildDialog(error, {
            callService()
        },{
            isOffline=true
            viewModel?.getDataFromLocalDataBase(database)
        })
    }
    private fun stopProgressBar(){
        progressbar.isVisible = false
    }

    private fun initDataBase(){
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }


}
