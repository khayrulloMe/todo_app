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
import kotlinx.coroutines.flow.collectLatest
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.databinding.FragmentSearchScreenBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentStudyNotesScreenBinding
import uz.gita.node_app_from_khayrullo.ui.adapters.NoteAdapter
import uz.gita.node_app_from_khayrullo.view_model.SearchNoteScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.StudyNoteScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.SearchNoteScreenViewModelImpl
import uz.gita.node_app_from_khayrullo.view_model.impl.StudyNoteScreenViewModelImpl


class StudyNotesScreen : Fragment(R.layout.fragment_study_notes_screen) {

    private val viewBinding: FragmentStudyNotesScreenBinding by viewBinding()
    val viewModel: StudyNoteScreenViewModel by viewModels<StudyNoteScreenViewModelImpl>()
    private val adapter: NoteAdapter by lazy { NoteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.rvStudy.setHasFixedSize(true)
        viewBinding.rvStudy.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        viewBinding.rvStudy.adapter = adapter
        val empty = viewBinding.textViewNoteEmpty

        lifecycleScope.launchWhenCreated {
            viewModel.studyNoteFlow.collectLatest {
                if (it.isEmpty()) {
                    empty.visibility = View.VISIBLE
                } else {
                    empty.visibility = View.GONE

                }
                adapter.setData(it)
            }

        }

//        viewModel.studyNoteLiveData.observe(viewLifecycleOwner) {
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
