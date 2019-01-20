package com.mobileapp.mvvm_sample_kotlin_app.Repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.mobileapp.mvvm_sample_kotlin_app.Model.Project
import com.mobileapp.mvvm_sample_kotlin_app.Service.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectRepository {


    private var githubService : GithubService

    var gson = Gson()

    var   data = MutableLiveData<List<Project>>()

            private constructor(){
        val retrofit = Retrofit.Builder()
                .baseUrl(GithubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        githubService = retrofit.create(GithubService::class.java)
    }

    companion object {
        val TAG = ProjectRepository::class.java.simpleName
        private val INSTANCE: ProjectRepository = ProjectRepository()
        @Synchronized
        fun getInstance(): ProjectRepository {
            return INSTANCE
        }
    }

    fun getProjectList(userId : String) : LiveData<List<Project>>{


        githubService.getProjectList(userId).enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                Log.e(TAG,"" + gson.toJson(response.body()))
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                // TODO better error handling in part #2 ...
                data.value = null
            }
        })
        return data
    }
}