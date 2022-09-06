package uz.gita.node_app_from_khayrullo.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.databinding.FragmentPersonalNotesScreenBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentSearchScreenBinding
import uz.gita.node_app_from_khayrullo.ui.adapters.NoteAdapter
import uz.gita.node_app_from_khayrullo.view_model.PersonalNotesScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.SearchNoteScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.PersonalNotesScreenViewModelImpl
import uz.gita.node_app_from_khayrullo.view_model.impl.SearchNoteScreenViewModelImpl


class SearchScreen : Fragment(R.layout.fragment_search_screen) {

    private val viewBinding: FragmentSearchScreenBinding by viewBinding()
    val viewModel: SearchNoteScreenViewModel by viewModels<SearchNoteScreenViewModelImpl>()
    private val adapter: NoteAdapter by lazy { NoteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.openBackStack.collect {
                findNavController().popBackStack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.rvNotes.setHasFixedSize(true)
        viewBinding.rvNotes.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        viewBinding.rvNotes.adapter = adapter
        val empty = viewBinding.tvNoteEmpty
        lifecycleScope.launchWhenCreated {
            viewModel.searchedPage.collect {
                if (it.isEmpty()) {
                    empty.visibility = View.VISIBLE
                } else {
                    empty.visibility = View.GONE

                }
                adapter.setData(it)
            }
        }
        viewBinding.toolbar.searchNotes.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.searcher(p0!!)
                return true
            }

        })
        viewBinding.toolbar.nibBack.setOnClickListener {
            viewModel.back()
        }
        adapter.setOnClicked {
            val action = SearchScreenDirections.actionSearchScreenToUpDateScreen(it)
            findNavController().navigate(action)
        }


    }


    override fun onDestroy() {
        viewBinding to null
        super.onDestroy()
    }

}
