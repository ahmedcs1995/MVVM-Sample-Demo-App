package com.mobileapp.mvvm_sample_kotlin_app.Service

import com.mobileapp.mvvm_sample_kotlin_app.Model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    companion object {
        val HTTPS_API_GITHUB_URL = "https://api.github.com/"
    }
        @GET("users/{user}/repos")
        abstract fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    abstract fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>

}