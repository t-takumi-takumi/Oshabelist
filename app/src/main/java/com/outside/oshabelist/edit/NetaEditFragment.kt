package com.outside.oshabelist.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.outside.oshabelist.R

class NetaEditFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_neta_edit, container, false)
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