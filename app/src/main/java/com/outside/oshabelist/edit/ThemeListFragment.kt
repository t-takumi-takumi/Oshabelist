package com.outside.oshabelist.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.outside.oshabelist.R
import com.outside.oshabelist.data.TalkThemeModel
import com.outside.oshabelist.databinding.FragmentThemeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeListFragment : Fragment() {

    private var _binding: FragmentThemeListBinding? = null
    private val binding get() = _binding!!

    private val themeListViewModel by viewModels<ThemeListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThemeListBinding.inflate(inflater, container, false).apply {
//            viewModel = themeListViewModel
//            lifecycleOwner = this@ThemeListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = ThemeListAdapter(themeListViewModel.themeList).apply {
            setOnItemClickListener(object : ThemeListAdapter.OnItemClickListener {
                override fun onClick(view: View, talkThemeModel: TalkThemeModel) {
                    parentFragmentManager.beginTransaction().add(
                        R.id.content,
                        NetaEditFragment.newInstance(talkThemeModel.id.toString())
                    ).commit()
                }
            })
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ThemeListFragment()
    }
}