package uz.gita.node_app_from_khayrullo.ui.screens

import android.os.Bundle
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
import uz.gita.node_app_from_khayrullo.databinding.FragmentMainScreenBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentPersonalNotesScreenBinding
import uz.gita.node_app_from_khayrullo.ui.adapters.NoteAdapter
import uz.gita.node_app_from_khayrullo.view_model.MainScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.PersonalNotesScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.MainScreenViewModelImpl
import uz.gita.node_app_from_khayrullo.view_model.impl.PersonalNotesScreenViewModelImpl


class PersonalNotesScreen : Fragment(R.layout.fragment_personal_notes_screen) {
    private val viewBinding: FragmentPersonalNotesScreenBinding by viewBinding()
    val viewModel: PersonalNotesScreenViewModel by viewModels<PersonalNotesScreenViewModelImpl>()
    private val adapter: NoteAdapter by lazy { NoteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.rvPersonal.setHasFixedSize(true)
        viewBinding.rvPersonal.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        viewBinding.rvPersonal.adapter = adapter
        val empty = viewBinding.textViewNoteEmpty

        lifecycleScope.launchWhenCreated {
            viewModel.personalNoteFlow.collect {
                if (it.isEmpty()) {
                    empty.visibility = View.VISIBLE
                } else {
                    empty.visibility = View.GONE
                }
                adapter.setData(it)
            }
        }
//        viewModel.personalNoteLiveData.observe(viewLifecycleOwner) {
//            if (it.isEmpty()) {
//                viewBinding.textViewNoteEmpty.visibility = View.VISIBLE
//            }else{
//                viewBinding.textViewNoteEmpty.visibility = View.GONE
//
//            }
//            adapter.setData(it)
//        }
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