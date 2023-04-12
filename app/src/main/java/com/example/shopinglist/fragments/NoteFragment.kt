package com.example.shopinglist.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.shopinglist.R
import com.example.shopinglist.activities.MainApp
import com.example.shopinglist.activities.NewNoteActivity
import com.example.shopinglist.databinding.FragmentNoteBinding
import com.example.shopinglist.db.MainViewModel

class NoteFragment : BaseFragment() {

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }
    private lateinit var binding: FragmentNoteBinding

    override fun onClickNew() {
        startActivity(Intent(activity, NewNoteActivity::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NoteFragment()
    }
}