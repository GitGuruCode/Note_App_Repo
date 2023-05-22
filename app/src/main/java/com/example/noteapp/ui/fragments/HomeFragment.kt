package com.example.noteapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.GridLayout
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.MVVM.entity
import com.example.noteapp.MVVM.viewModel
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.ui.adapter.myAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: viewModel by viewModels()
    lateinit var myOldList:ArrayList<entity>
    lateinit var adapter: myAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getNotes().observe(viewLifecycleOwner) { userList ->
            myOldList= userList as ArrayList<entity>
//        for(i in userList){
//            Log.e("@@@@@", "onCreateView: ${i.title}" )
//        }
            setHasOptionsMenu(true)
            //now we got userList and now we have to adapt it in the recycler view so create kar bhai
            val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            binding.rcvL.layoutManager=staggeredGridLayoutManager
          //  binding.rcvL.layoutManager=GridLayoutManager(requireContext(),2)
            adapter=myAdapter(requireContext(),userList)
            binding.rcvL.adapter=adapter
        }
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment)
        }
        binding.highbtn.setOnClickListener{
            viewModel.getHighNotes().observe(viewLifecycleOwner) { userList ->
                myOldList= userList as ArrayList<entity>
                val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvL.layoutManager=staggeredGridLayoutManager
              //  binding.rcvL.layoutManager=GridLayoutManager(requireContext(),2)
                adapter=myAdapter(requireContext(),userList)
                binding.rcvL.adapter=adapter}
        }
        binding.mediumbtn.setOnClickListener{
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { userList ->
                myOldList= userList as ArrayList<entity>
                val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvL.layoutManager=staggeredGridLayoutManager
               // binding.rcvL.layoutManager=GridLayoutManager(requireContext(),2)
                adapter=myAdapter(requireContext(),userList)
                binding.rcvL.adapter=adapter}
        }
        binding.lowbtn.setOnClickListener{
            viewModel.getLowNotes().observe(viewLifecycleOwner) { userList ->
                myOldList= userList as ArrayList<entity>
                val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvL.layoutManager=staggeredGridLayoutManager
            //    binding.rcvL.layoutManager=GridLayoutManager(requireContext(),2)
                adapter=myAdapter(requireContext(),userList)
                binding.rcvL.adapter=adapter}
        }
        binding.filterbtn.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner) { userList ->
                myOldList= userList as ArrayList<entity>
                val staggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvL.layoutManager=staggeredGridLayoutManager
             //   binding.rcvL.layoutManager=GridLayoutManager(requireContext(),2)
                adapter=myAdapter(requireContext(),userList)
                binding.rcvL.adapter=adapter}
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item=menu.findItem(R.id.searchmenu)
        val searchView=item.actionView as SearchView
        searchView.queryHint="Enter your notes..."
        searchView.setOnQueryTextListener(object :OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(p0: String?) {
     //   Log.d("@@@@", "vikasFiltering: $p0")
        val newFilteredList= ArrayList<entity>()
        for (i in myOldList){
            if(i.title.contains(p0!!) || i.subtitle.contains(p0!!)){
                  newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}