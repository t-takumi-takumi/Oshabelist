package com.outside.oshabelist.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.outside.oshabelist.databinding.FragmentHomeBinding
import com.outside.oshabelist.edit.ThemeListActivity
import com.outside.oshabelist.remoteconfig.RemoteConfigKey
import com.outside.oshabelist.remoteconfig.RemoteConfigUtils

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.themeTitle.text = RemoteConfigUtils.getString(RemoteConfigKey.TALK_THEME_LIST)

        binding.gatyaButton.setOnClickListener {
            Toast.makeText(requireContext(), "comming soon!", Toast.LENGTH_SHORT).show()
        }
        binding.homeFab.setOnClickListener {
            startActivity(ThemeListActivity.createIntent(requireContext()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}