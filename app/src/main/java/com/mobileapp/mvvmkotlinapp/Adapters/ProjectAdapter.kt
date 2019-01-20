package com.mobileapp.mvvmkotlinapp.Adapters

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobileapp.mvvm_sample_kotlin_app.Model.Project
import com.mobileapp.mvvmkotlinapp.R
import kotlinx.android.synthetic.main.project_list_item.view.*


class ProjectAdapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    internal var projectList: List<Project>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.project_list_item, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.name.text = projectList!![p1].name
    }


    override fun getItemCount(): Int {
        if(projectList == null){
            return 0
        }
        return projectList!!.size
    }

    fun setProjectList(projectList: List<Project>){
        /*
        * set the entire data
        * */
        if(this.projectList == null){
            this.projectList = projectList
            notifyDataSetChanged()
            return
        }
        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return this@ProjectAdapter.projectList!!.size
            }

            override fun getNewListSize(): Int {
                return projectList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return this@ProjectAdapter.projectList!!.get(oldItemPosition).id === projectList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val project = projectList[newItemPosition]
                val old = projectList[oldItemPosition]
                return project.id === old.id && project.git_url == old.git_url
            }
        })
        this.projectList = projectList
        result.dispatchUpdatesTo(this)
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val name = view.name
}