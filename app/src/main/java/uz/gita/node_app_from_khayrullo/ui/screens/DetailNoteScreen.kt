package uz.gita.node_app_from_khayrullo.ui.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.awesomedialog.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.databinding.BottomsheetNoteBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentAllNotesScreenBinding
import uz.gita.node_app_from_khayrullo.databinding.FragmentDetailNoteScreenBinding
import uz.gita.node_app_from_khayrullo.view_model.AllNotesScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.DetailNoteScreenViewModel
import uz.gita.node_app_from_khayrullo.view_model.impl.AllNotesScreenViewModelImpl
import uz.gita.node_app_from_khayrullo.view_model.impl.DetailNoteScreenViewModelImpl

class DetailNoteScreen : Fragment(R.layout.fragment_detail_note_screen) {
    private val viewBinding: FragmentDetailNoteScreenBinding by viewBinding()
    val viewModel: DetailNoteScreenViewModel by viewModels<DetailNoteScreenViewModelImpl>()
    private val args by navArgs<DetailNoteScreenArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.backButton.collect {
                findNavController().popBackStack()
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.openEditNoteButton.collect {
                findNavController().navigate(
                    DetailNoteScreenDirections.actionDetailNoteScreenToUpDateScreen(args.note)
                )
            }
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.tvTitle.text = args.note.title
        viewBinding.tvBody.text = args.note.body
        viewBinding.tvDate.text = args.note.date
        viewBinding.nibBack.setOnClickListener {
            viewModel.back()
        }
        viewBinding.btnSave.setOnClickListener {
            viewModel.editNoteButton()
        }
        if (args.note.edit == 1) {
            viewBinding.tvEdited.text = "Last edited ${args.note.date}, ${args.note.time}"
        } else {
            viewBinding.tvEdited.text = "Added ${args.note.date}, ${args.note.time}"

        }
        viewBinding.ibMenu.setOnClickListener {
            showBottomSheet()
        }


    }

    private fun deleteNote() {
        viewModel.delete(args.note)
    }

    private fun showBottomSheet() {
        val views: View =
            LayoutInflater.from(requireContext())
                .inflate(R.layout.bottomsheet_note, null)

        val bindingBottom = BottomsheetNoteBinding.bind(views)

        val dialog = BottomSheetDialog(requireContext())
        dialog.setCancelable(true)

        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(views)
        dialog.show()
        bindingBottom.clDelete.setOnClickListener {
            showDialog()
            dialog.cancel()
        }
        bindingBottom.clShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "This app create from Khayrullo\ncontact me: khayrullo.me@gmail.com\n" +
                            "instagram:@kyayrullo.me\n\n\n\n" +
                            "Your note:\nTitle: ${args.note.title}\nBody: ${args.note.body}\n\ndate: ${args.note.date}, ${args.note.time}"
                )
                type = "text/plain"

            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
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
                findNavController().popBackStack()
            }
            .onNegative(
                "Cancel",
                buttonBackgroundColor = R.drawable.bg_btn_black,
                textColor = ContextCompat.getColor(requireContext(), R.color.background)
            ) {

            }

    }


    override fun onDestroy() {
        viewBinding to null
        super.onDestroy()
    }

}