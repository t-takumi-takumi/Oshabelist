package com.outside.oshabelist.edit

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.room.Room
import com.outside.oshabelist.R
import com.outside.oshabelist.dao.NetaDao
import com.outside.oshabelist.data.Neta
import com.outside.oshabelist.db.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NetaEditFragment : Fragment() {
    private var savedNeta: Neta? = null
    private lateinit var userDao: NetaDao
    private lateinit var id: String

    private val netaEditViewModel by viewModels<NetaEditViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val database =
            Room.databaseBuilder(requireContext(), AppDatabase::class.java, "neta").build()
        userDao = database.netaDao()
        id = arguments?.getString("BUNDLE_KEY_ID") ?: ""
        savedNeta = if (id.isEmpty()) {
            null
        } else {
            userDao.getNeta(id)
        }
        return inflater.inflate(R.layout.fragment_neta_edit, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_neta_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveButton) {
            Toast.makeText(requireContext(), "save", Toast.LENGTH_SHORT).show()
            GlobalScope.launch {
                val editedNeta = Neta(id, "test")
                if (savedNeta == null) {
                    userDao.insert(editedNeta)
                } else {
                    userDao.update(editedNeta)
                }
            }
            return true
        }
        return false
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