package uz.gita.node_app_from_khayrullo.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.databinding.FragmentAddScreenBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentMainScreenBinding
import uz.gita.node_app_from_khayrullo.ui.adapters.SectionsPagerAdapter
import uz.gita.node_app_from_khayrullo.view_model.AddNoteViewModel
import uz.gita.node_app_from_khayrullo.view_model.MainScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.AddNoteViewModelImpl
import uz.gita.node_app_from_khayrullo.view_model.impl.MainScreenViewModelImpl

class MainScreen : Fragment(R.layout.fragment_main_screen) {
    private val viewBinding: FragmentMainScreenBinding by viewBinding()
    val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val navigator by lazy { findNavController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.addButtonLiveData.collect {
                navigator.navigate(MainScreenDirections.actionMainScreenToAddScreen())
            }
        }
        lifecycleScope.launchWhenCreated {
        viewModel.searchButtonLiveData.collect{
            navigator.navigate(MainScreenDirections.actionMainScreenToSearchScreen())
        }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.floatingActionButton.setOnClickListener {
            viewModel.addButtonClicked()
        }
        viewBinding.toolbar.ibSearch.setOnClickListener {
            viewModel.searchButtonClicked()
        }
        initView()

    }
    private fun initView() {

        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(),childFragmentManager)
        viewBinding.viewPager.adapter = sectionsPagerAdapter
        viewBinding.tabs.setupWithViewPager(viewBinding.viewPager)

    }

    override fun onDestroy() {
        viewBinding to null
        super.onDestroy()
    }

}
