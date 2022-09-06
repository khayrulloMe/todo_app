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
import uz.gita.node_app_from_khayrullo.databinding.FragmentStudyNotesScreenBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentWorkNotesScreenBinding
import uz.gita.node_app_from_khayrullo.ui.adapters.NoteAdapter
import uz.gita.node_app_from_khayrullo.view_model.StudyNoteScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.WorkNotesScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.StudyNoteScreenViewModelImpl
import uz.gita.node_app_from_khayrullo.view_model.impl.WorkNotesScreenViewModelImpl


class WorkNotesScreen : Fragment(R.layout.fragment_work_notes_screen) {

    private val viewBinding: FragmentWorkNotesScreenBinding by viewBinding()
    val viewModel: WorkNotesScreenViewModel by viewModels<WorkNotesScreenViewModelImpl>()
    private val adapter: NoteAdapter by lazy { NoteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.rvWork.setHasFixedSize(true)
        viewBinding.rvWork.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        viewBinding.rvWork.adapter = adapter
        val empty = viewBinding.textViewNoteEmpty

        lifecycleScope.launchWhenCreated {
            viewModel.workNoteFlow.collect {
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