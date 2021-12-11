package com.outside.oshabelist.home

import com.outside.oshabelist.data.TalkThemeModel
import com.outside.oshabelist.data.TalkThemeRepository
import java.util.Collections.shuffle
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: TalkThemeRepository) {
    fun getShuffledTalkThemeList(): List<TalkThemeModel> {
        val shuffledList = repository.talkThemeList
        shuffle(shuffledList)
        return shuffledList
    }
}