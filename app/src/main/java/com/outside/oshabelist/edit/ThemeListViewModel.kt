package com.outside.oshabelist.edit

import androidx.lifecycle.ViewModel
import com.outside.oshabelist.data.TalkThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeListViewModel @Inject constructor(repository: TalkThemeRepository) :
    ViewModel() {
    val themeList = repository.talkThemeList
}