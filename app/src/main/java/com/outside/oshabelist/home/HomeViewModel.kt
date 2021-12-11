package com.outside.oshabelist.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: HomeUseCase) : ViewModel() {
    var navigator: HomeNavigator? = null
    var themeListIndex = 1
    var themeList = useCase.getShuffledTalkThemeList()

    private val _talkTheme = MutableLiveData(themeList[0].talkTheme)
    val talkTheme: LiveData<String>
        get() = _talkTheme

    fun onClickNextButton() {
        //トークテーマリスト全部使い切ったらリセット
        if (themeListIndex >= themeList.size) {
            themeListIndex = 0
            _talkTheme.value = themeList[themeListIndex].talkTheme
            return
        }
        _talkTheme.value = themeList[themeListIndex].talkTheme
        themeListIndex++
    }

    fun onClickFab() {
        navigator?.showNextPage()
    }
}