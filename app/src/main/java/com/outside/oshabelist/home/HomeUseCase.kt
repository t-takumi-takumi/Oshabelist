package com.outside.oshabelist.home

import com.outside.oshabelist.data.TalkThemeRepository
import java.util.*
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: TalkThemeRepository) {
    fun getTalkThemeRandomly(): String {
        val index = Random().nextInt(repository.talkThemeList.size)
        return repository.talkThemeList[index].talkTheme
    }
}