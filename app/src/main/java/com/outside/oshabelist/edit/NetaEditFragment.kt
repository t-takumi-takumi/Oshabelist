package com.outside.oshabelist.edit

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.outside.oshabelist.R

class NetaEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_neta_edit, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_neta_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveButton) {
            Toast.makeText(requireContext(), "save", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    companion object {
        private const val ID = "id"
        fun newInstance(id: String) =
            NetaEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ID, id)
                }
            }
    }
}