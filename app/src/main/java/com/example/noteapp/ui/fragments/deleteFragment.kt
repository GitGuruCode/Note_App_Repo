package com.example.noteapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.noteapp.MVVM.entity
import com.example.noteapp.MVVM.viewModel
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentDeleteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class deleteFragment : Fragment() {
       lateinit var binding:FragmentDeleteBinding
    val viewModel: viewModel by viewModels()
    var priority:String="1"
       val allNotes by navArgs<deleteFragmentArgs>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
// for hiding toolbar from fragment     binding=FragmentDeleteBinding.inflate(layoutInflater,container,false)
        binding= FragmentDeleteBinding.inflate(layoutInflater);
        binding.delfbtn.setOnClickListener{
            updateNotes(it)

        }
       binding.deltitle.setText(allNotes.data.title)
        binding.delsubtitle.setText(allNotes.data.subtitle)
        binding.delwriteNotes.setText(allNotes.data.notes)
        when(allNotes.data.priority){
            "1"->{
                binding.delbluei.setImageResource(R.drawable.ic_baseline_done_24)
                binding.delredi.setImageResource(0)
                binding.delyellowi.setImageResource(0)
            }
            "2"->{
                binding.delyellowi.setImageResource(R.drawable.ic_baseline_done_24)
                binding.delredi.setImageResource(0)
                binding.delbluei.setImageResource(0)
            }
            "3"->{
                binding.delredi.setImageResource(R.drawable.ic_baseline_done_24)
                binding.delyellowi.setImageResource(0)
                binding.delbluei.setImageResource(0)
            }
        }
        binding.delredi.setOnClickListener{
            priority="3"
            binding.delredi.setImageResource(R.drawable.ic_baseline_done_24)
            binding.delyellowi.setImageResource(0)
            binding.delbluei.setImageResource(0)
        }
        binding.delyellowi.setOnClickListener{
            priority="2"
            binding.delyellowi.setImageResource(R.drawable.ic_baseline_done_24)
            binding.delredi.setImageResource(0)
            binding.delbluei.setImageResource(0)
        }
        binding.delbluei.setOnClickListener{
            binding.delbluei.setImageResource(R.drawable.ic_baseline_done_24)
            binding.delredi.setImageResource(0)
            binding.delyellowi.setImageResource(0)
        }
        return binding.root///halke mein mt le ise
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateNotes(it:View?){
        val title=binding.deltitle.text.toString()
        val subTitile=binding.delsubtitle.text.toString()
        val notes=binding.delwriteNotes.text.toString()
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MMMM, d")
        val currentDate = current.format(formatter)
        val data= entity(allNotes.data.id,title,subTitile,notes,currentDate,priority)
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(),"notes updated successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_deleteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.deletemenu){
            val bottomSheetDialog:BottomSheetDialog=BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheetDialog.setContentView(R.layout.dialogbar)
            bottomSheetDialog.show()
            val textViewYes=bottomSheetDialog.findViewById<TextView>(R.id.dialogYes)
            val textViewNo=bottomSheetDialog.findViewById<TextView>(R.id.dialogNo)
            textViewYes?.setOnClickListener{
                viewModel.deleteNotes(allNotes.data.id!!)
                bottomSheetDialog.dismiss()
            }
            textViewNo?.setOnClickListener{
                bottomSheetDialog.dismiss()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}