package com.outside.oshabelist.edit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.outside.oshabelist.R
import com.outside.oshabelist.databinding.FragmentNetaEditBinding
import com.outside.oshabelist.db.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        val database =
            Room.databaseBuilder(requireContext(), AppDatabase::class.java, "neta").build()
        netaEditViewModel.apply {
            netaId = arguments?.getString(BUNDLE_KEY_ID) ?: ""
            userDao = database.netaDao()
        }
        _binding = FragmentNetaEditBinding.inflate(inflater, container, false).apply {
            viewModel = netaEditViewModel
            lifecycleOwner = this@NetaEditFragment
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_neta_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveButton) {
            var isSuccesssSave = false
            GlobalScope.launch {
                if (netaEditViewModel.savedNeta.value.isNullOrEmpty()) {
                    isSuccesssSave =
                        netaEditViewModel.insertNetaText(binding.editNetaText.text.toString())
                } else {
                    isSuccesssSave =
                        netaEditViewModel.updateNetaText(binding.editNetaText.text.toString())
                }
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