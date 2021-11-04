package com.outside.oshabelist.edit

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.outside.oshabelist.R
import com.outside.oshabelist.databinding.FragmentNetaEditBinding
import com.outside.oshabelist.db.AppDatabase
import com.outside.oshabelist.utils.AppUiUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NetaEditFragment : Fragment() {
    private val netaEditViewModel by viewModels<NetaEditViewModel>()
    private var _binding: FragmentNetaEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        netaEditViewModel.apply {
            netaId = arguments?.getString(BUNDLE_KEY_ID) ?: ""
            netaDao =
                Room.databaseBuilder(requireContext(), AppDatabase::class.java, "neta").build()
                    .netaDao()
            setSavedNeta()
        }
        _binding = FragmentNetaEditBinding.inflate(inflater, container, false).apply {
            viewModel = netaEditViewModel
            lifecycleOwner = this@NetaEditFragment
            if (netaEditViewModel.savedNeta.value.isNullOrEmpty()) {
                editNetaText.requestFocus()
                AppUiUtils.openInputMethodManager(editNetaText, 100)
            } else {
                editNetaText.clearFocus()
            }
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_neta_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveButton) {
            val netaText = binding.editNetaText.text.toString()
            val isSuccessSave =
                if (netaEditViewModel.savedNeta.value.isNullOrEmpty() && netaText.isNotBlank()) {
                    netaEditViewModel.insertNetaText(netaText)
                } else if (!netaEditViewModel.savedNeta.value.isNullOrEmpty() && netaText.isNotBlank()) {
                    netaEditViewModel.updateNetaText(netaText)
                } else {
                    false
                }
            if (isSuccessSave) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.success_save),
                    Toast.LENGTH_SHORT
                ).show()
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.failure_save),
                    Toast.LENGTH_SHORT
                ).show()
            }
            return true
        }
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val BUNDLE_KEY_ID = "id"
        fun newInstance(id: String) =
            NetaEditFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_KEY_ID, id)
                }
            }
    }
}