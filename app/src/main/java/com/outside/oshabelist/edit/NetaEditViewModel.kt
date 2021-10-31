package com.outside.oshabelist.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.outside.oshabelist.dao.NetaDao
import com.outside.oshabelist.data.Neta
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetaEditViewModel @Inject constructor() : ViewModel() {
    var netaId = ""
    var userDao: NetaDao? = null

    private val _savedNeta = MutableLiveData(userDao?.getNeta(netaId)?.neta ?: "")
    val savedNeta: LiveData<String>
        get() = _savedNeta

    //返却値は保存成功可否
    fun insertNetaText(netaText: String): Boolean {
        val dao = userDao ?: return false
        if (netaId.isEmpty()) return false
        dao.insert(Neta(netaId, netaText))
        return true
    }

    //返却値は保存成功可否
    fun updateNetaText(netaText: String): Boolean {
        val dao = userDao ?: return false
        if (netaId.isEmpty()) return false
        dao.update(Neta(netaId, netaText))
        return true
    }
}