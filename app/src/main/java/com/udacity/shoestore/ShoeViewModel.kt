package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {

    private val _shoeList = MutableLiveData(mutableListOf<Shoe>())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name
    var shoeName = ""

    private val _company = MutableLiveData<String>()
    val company: LiveData<String>
        get() = _company
    var shoeCompany = ""


    private val _size = MutableLiveData<String>()
    val size: LiveData<String>
        get() = _size
    var shoeSize = ""

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description
    var shoeDescription = ""

    private val _eventNavigateBackToShoeList  = MutableLiveData<Boolean>()
    val eventNavigateBackToShoeList: LiveData<Boolean>
    get() = _eventNavigateBackToShoeList

    private val _eventShowToast = MutableLiveData<Boolean>()
    val eventShowToast: LiveData<Boolean>
    get() = _eventShowToast

    init {
        _shoeList.value = mutableListOf()
    }

fun onNewShoe(){
    if (shoeSize == ""){
        shoeSize = "0"
    }

    _name.value = shoeName
    _size.value = shoeSize
    _company.value = shoeCompany
    _description.value = shoeDescription


    if(!_name.value.isNullOrEmpty() && !_company.value.isNullOrEmpty() && !_size.value.isNullOrEmpty() && !_description.value.isNullOrEmpty()) {
        val newShoe = Shoe(_name.value!!, _size.value!!.toDouble(), _company.value!!, _description.value!!)
        _shoeList.value?.add(newShoe)
        _eventNavigateBackToShoeList.value = true
    } else {
        _eventShowToast.value = true
    }
}
    fun onNavigateBackToShoeListComplete(){
        _eventNavigateBackToShoeList.value = false
        clearEditTexts()
    }

    fun onShowedToastComplete(){
        _eventShowToast.value = false
    }

    fun clearEditTexts(){
        shoeName = ""
        shoeSize = ""
        shoeCompany = ""
        shoeDescription = ""
    }
}