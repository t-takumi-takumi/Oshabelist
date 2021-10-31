package com.outside.oshabelist.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outside.oshabelist.dao.NetaDao
import com.outside.oshabelist.data.Neta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetaEditViewModel @Inject constructor() : ViewModel() {
    var netaId = ""
    var userDao: NetaDao? = null

    private val _savedNeta = MutableLiveData("")
    val savedNeta: LiveData<String>
        get() = _savedNeta

    fun setSavedNeta() {
        viewModelScope.launch(Dispatchers.IO) {
            _savedNeta.postValue(userDao?.getNeta(netaId)?.neta)
        }
    }

    //返却値は保存成功可否
    fun insertNetaText(netaText: String): Boolean {
        val dao = userDao ?: return false
        if (netaId.isEmpty()) return false
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(Neta(netaId, netaText))
        }
        return true
    }

    //返却値は保存成功可否
    fun updateNetaText(netaText: String): Boolean {
        val dao = userDao ?: return false
        if (netaId.isEmpty()) return false
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(Neta(netaId, netaText))
        }
        return true
    }
}