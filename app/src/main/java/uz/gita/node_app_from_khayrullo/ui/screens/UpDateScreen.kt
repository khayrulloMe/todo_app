package uz.gita.node_app_from_khayrullo.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.awesomedialog.*
import kotlinx.coroutines.flow.collect
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity
import uz.gita.node_app_from_khayrullo.databinding.FragmentAddScreenBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentUpDateScreenBinding
import uz.gita.node_app_from_khayrullo.utils.DateChange
import uz.gita.node_app_from_khayrullo.view_model.AddNoteViewModel
import uz.gita.node_app_from_khayrullo.view_model.UpdateNoteScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.AddNoteViewModelImpl
import uz.gita.node_app_from_khayrullo.view_model.impl.UpdateNoteScreenViewModelImpl

class UpDateScreen : Fragment(R.layout.fragment_up_date_screen) {
    private val viewBinding: FragmentUpDateScreenBinding by viewBinding()
    val viewModel: UpdateNoteScreenViewModel by viewModels<UpdateNoteScreenViewModelImpl>()
    private val args by navArgs<UpDateScreenArgs>()
    private val date = DateChange()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.backButton.collect {
                findNavController().navigate(UpDateScreenDirections.actionUpDateScreenToMainScreen())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.buttonDelete.visibility = View.VISIBLE
        viewBinding.buttonDelete.setOnClickListener {
            showDialog()
        }
        viewBinding.editTextTitle.setText(args.note.title)
        viewBinding.editTextBody.setText(args.note.body)
        val labelType = viewBinding.spLabel
        val a: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.labels,
            android.R.layout.simple_spinner_item
        )
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        labelType.adapter = a
        when (args.note.label) {
            1 -> {
                viewBinding.spLabel.setSelection(a.getPosition("Personal"))
            }
            2 -> {
                viewBinding.spLabel.setSelection(a.getPosition("Work"))
            }
            3 -> {
                viewBinding.spLabel.setSelection(a.getPosition("Study"))

            }
        }

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
                val note =
                    NoteEntity(args.note.id, title, type, date.getToday(), date.getTime(), body, 1)
                viewModel.update(note)
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

    private fun showDialog() {
//        Log.d("MMM", "ochldi")
        AwesomeDialog.build(requireActivity())
            .position(AwesomeDialog.POSITIONS.CENTER)
            .body(
                "The note will be permanently deleted.",
                color = ContextCompat.getColor(requireContext(), R.color.colorTitle)
            )
            .background(R.drawable.background_dialog)
            .icon(R.drawable.logo)
            .onPositive(
                "Yes, delete",
                buttonBackgroundColor = android.R.color.transparent,
                textColor = ContextCompat.getColor(requireContext(), R.color.colorTitle)
            ) {
                deleteNote()

                findNavController().navigate(UpDateScreenDirections.actionUpDateScreenToMainScreen())
            }
            .onNegative(
                "Cancel",
                buttonBackgroundColor = R.drawable.bg_btn_black,
                textColor = ContextCompat.getColor(requireContext(), R.color.background)
            ) {

            }

    }

    private fun deleteNote() {
        viewModel.delete(args.note)
    }

    override fun onDestroy() {
        viewBinding to null
        super.onDestroy()
    }

}