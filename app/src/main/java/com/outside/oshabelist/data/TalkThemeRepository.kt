package com.outside.oshabelist.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.outside.oshabelist.remoteconfig.RemoteConfigKey
import com.outside.oshabelist.remoteconfig.RemoteConfigUtils
import javax.inject.Inject

class TalkThemeRepository @Inject constructor() {
    val talkThemeList: List<TalkThemeModel>
        get() {
            val jsonString = RemoteConfigUtils.getString(RemoteConfigKey.TALK_THEME_LIST)
            if (jsonString == "EmptyThemeList") return emptyList()
            val listType = object : TypeToken<List<TalkThemeModel>>() {}.type
            return Gson().fromJson(jsonString, listType)
        }
}