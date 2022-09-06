package uz.gita.node_app_from_khayrullo.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity
import uz.gita.node_app_from_khayrullo.databinding.FragmentAddScreenBinding
import uz.gita.node_app_from_khayrullo.utils.DateChange
import uz.gita.node_app_from_khayrullo.view_model.AddNoteViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.AddNoteViewModelImpl

class AddScreen : Fragment(R.layout.fragment_add_screen) {
    private val viewBinding: FragmentAddScreenBinding by viewBinding()
    private val date = DateChange()

    val viewModel: AddNoteViewModel by viewModels<AddNoteViewModelImpl>()
    private val navigator by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.backButton.observe(this) {
//            navigator.popBackStack()
//        }
        lifecycleScope.launchWhenCreated {
            viewModel.backButton.collect {
                navigator.popBackStack()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val labelType = viewBinding.spLabel
        val a: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.labels,
            android.R.layout.simple_spinner_item
        )
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        labelType.adapter = a

        viewBinding.btnSave.setOnClickListener {

            val label = labelType.selectedItem.toString()
            var type = 1
            type = when (label) {
                "Personal" -> {
                    1
                }
                "Work" -> {
                    2
                }
                else -> {
                    3
                }
            }
            val title = viewBinding.editTextTitle.text.toString()
            val body = viewBinding.editTextBody.text.toString()
            if (title.isNotEmpty() && body.isNotEmpty()) {
                val note = NoteEntity(0, title, type, date.getToday(), date.getTime(), body, 0)
                viewModel.add(note)
                viewModel.back()
            } else {
                Toast.makeText(requireContext(), "Please enter title and body", Toast.LENGTH_LONG)
                    .show()
            }


        }
        viewBinding.nibBack.setOnClickListener {
            viewModel.back()
        }
    }

    override fun onDestroy() {
        viewBinding to null
        super.onDestroy()
    }

}

