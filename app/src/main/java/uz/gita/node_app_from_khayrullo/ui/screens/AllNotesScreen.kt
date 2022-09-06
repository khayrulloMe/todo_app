package uz.gita.node_app_from_khayrullo.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.databinding.FragmentAddScreenBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentAllNotesScreenBinding
import uz.gita.node_app_from_khayrullo.ui.adapters.NoteAdapter
import uz.gita.node_app_from_khayrullo.view_model.AddNoteViewModel
import uz.gita.node_app_from_khayrullo.view_model.AllNotesScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.AddNoteViewModelImpl
import uz.gita.node_app_from_khayrullo.view_model.impl.AllNotesScreenViewModelImpl

class AllNotesScreen : Fragment(R.layout.fragment_all_notes_screen) {
    private val viewBinding: FragmentAllNotesScreenBinding by viewBinding()
    val viewModel: AllNotesScreenViewModel by viewModels<AllNotesScreenViewModelImpl>()
    private val adapter: NoteAdapter by lazy { NoteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.rvAll.setHasFixedSize(true)
        val empty = viewBinding.textViewNoteEmpty

        viewBinding.rvAll.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        viewBinding.rvAll.adapter = adapter
        lifecycleScope.launchWhenCreated {
            viewModel.allNote.collect {
                if (it.isEmpty()) {
                    empty.visibility = View.VISIBLE
                } else {
                    empty.visibility = View.GONE
                }
                adapter.setData(it)

            }
        }

        adapter.setOnClicked {
            val action = MainScreenDirections.actionMainScreenToDetailNoteScreen(it)
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        viewBinding to null
        super.onDestroy()
    }

}