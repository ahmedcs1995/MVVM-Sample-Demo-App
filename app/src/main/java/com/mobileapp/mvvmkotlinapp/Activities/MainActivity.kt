package com.mobileapp.mvvmkotlinapp.Activities

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.mobileapp.mvvm_sample_kotlin_app.ViewModel.ProjectListViewModel
import com.mobileapp.mvvmkotlinapp.Adapters.ProjectAdapter
import com.mobileapp.mvvmkotlinapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object {
        val TAG = MainActivity::class.java.simpleName
    }


    //adapter
    private var projectAdapter: ProjectAdapter? = null


    //view Model
    lateinit var viewModel : ProjectListViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //init the adapter
        projectAdapter = ProjectAdapter(this)


        //set the adapter and layout manager
        project_list_recycler_view.adapter = projectAdapter
        project_list_recycler_view.layoutManager = LinearLayoutManager(this)


        /*
         init viewModel and then observe the changes
        */


        // set refreshing true
        swipe_refresh_layout.isRefreshing = true


        initViewModel()


        addOnSwipeRefresh()
    }


    private fun addOnSwipeRefresh(){
        swipe_refresh_layout.setOnRefreshListener {
            Log.i(TAG,"Pull To refresh called")
            viewModel.loadData()
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
        observeViewModel()
    }


    private fun observeViewModel(){
        // Update the list when the data changes
        viewModel.projectListObservable.observe(this, Observer {
            if(it !=null){
                swipe_refresh_layout.isRefreshing = false
                Log.i(TAG,"Observer Data Change")
                projectAdapter?.setProjectList(it)
            }
        })
    }

}
