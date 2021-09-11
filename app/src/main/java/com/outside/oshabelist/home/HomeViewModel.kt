package com.outside.oshabelist.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: HomeUseCase) : ViewModel() {
    var navigator: HomeNavigator? = null

    private val _talkTheme = MutableLiveData(useCase.getTalkThemeRandomly())
    val talkTheme: LiveData<String>
        get() = _talkTheme

    fun onClickNextButton() {
        _talkTheme.value = useCase.getTalkThemeRandomly()
    }

    fun onClickFab() {
        navigator?.showNextPage()
    }
}