package com.mobileapp.mvvm_sample_kotlin_app.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.mobileapp.mvvm_sample_kotlin_app.Model.Project
import com.mobileapp.mvvm_sample_kotlin_app.Repository.ProjectRepository

class ProjectListViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    lateinit var projectListObservable: LiveData<List<Project>>

    init {

        // If any transformation is needed, this can be simply done by Transformations class ...
     //   projectListObservable = ProjectRepository.getInstance().getProjectList("Google")
        loadData()
    }

    fun loadData(){
        projectListObservable = ProjectRepository.getInstance().getProjectList("Google")
    }

}
