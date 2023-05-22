package com.example.noteapp.ui.fragments

import android.content.res.Resources.Theme
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.noteapp.MVVM.entity
import com.example.noteapp.MVVM.viewModel
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentEditBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class EditFragment : Fragment() {
private lateinit var binding: FragmentEditBinding
  val viewModel: viewModel by viewModels()
    var priority:String="1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
   //taking edit fragment as a view   val v:View=LayoutInflater.from(context).inflate(R.layout.fragment_edit,container,false)

        binding=FragmentEditBinding.inflate(layoutInflater,container,false)
        binding.fbtn.setOnClickListener{
            createNotes(it)

        }
        binding.bluei.setImageResource(R.drawable.ic_baseline_done_24)
        binding.redi.setOnClickListener{
            priority="3"
            binding.redi.setImageResource(R.drawable.ic_baseline_done_24)
            binding.yellowi.setImageResource(0)
            binding.bluei.setImageResource(0)
        }
        binding.yellowi.setOnClickListener{
            priority="2"
            binding.yellowi.setImageResource(R.drawable.ic_baseline_done_24)
            binding.redi.setImageResource(0)
            binding.bluei.setImageResource(0)
        }
        binding.bluei.setOnClickListener{
            binding.bluei.setImageResource(R.drawable.ic_baseline_done_24)
            binding.redi.setImageResource(0)
            binding.yellowi.setImageResource(0)
        }
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotes(it:View?){
        val title=binding.Title.text.toString()
        val subTitile=binding.subtitle.text.toString()
        val notes=binding.writeNotes.text.toString()
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MMMM, d")
        val currentDate = current.format(formatter)
       val data=entity(null,title,subTitile,notes,currentDate,priority)
       viewModel.addNotes(data)
        Toast.makeText(requireContext(),"notes created successfully",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)
    }
}