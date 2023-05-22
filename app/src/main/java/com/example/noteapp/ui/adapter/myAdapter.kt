package com.example.noteapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.MVVM.entity
import com.example.noteapp.R
import com.example.noteapp.databinding.SmallpartBinding
import com.example.noteapp.ui.fragments.HomeFragment
import com.example.noteapp.ui.fragments.HomeFragmentDirections
import java.util.ArrayList


class myAdapter(val context: Context,var userList:List<entity>)
    :RecyclerView.Adapter<myAdapter.UserViewHolder>(){
    class UserViewHolder(val binding: SmallpartBinding):RecyclerView.ViewHolder(binding.root)
      fun filtering(filteredList:ArrayList<entity>){
              userList=filteredList
          notifyDataSetChanged()
      }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(SmallpartBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser=userList[position]
        holder.binding.second.text=currentUser.title
        holder.binding.third.text=currentUser.subtitle
        holder.binding.fourth.text=currentUser.date
        when(currentUser.priority){
            "1"->{
                holder.binding.first.setBackgroundResource(R.drawable.blue)
            }
            "2"->{
                holder.binding.first.setBackgroundResource(R.drawable.yellow)
            }
            "3"->{
                holder.binding.first.setBackgroundResource(R.drawable.red)
            }
        }
       holder.binding.root.setOnClickListener{
                val action=HomeFragmentDirections.actionHomeFragmentToDeleteFragment(currentUser)
                    Navigation.findNavController(it).navigate(action)

       }
    }
    override fun getItemCount(): Int {
        return userList.size
    }
}