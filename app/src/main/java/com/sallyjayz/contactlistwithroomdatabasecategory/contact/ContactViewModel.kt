package com.sallyjayz.contactlistwithroomdatabasecategory.contact

import android.util.Log
import androidx.lifecycle.*
import com.sallyjayz.contactlistwithroomdatabasecategory.database.ContactRepository
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactDetail
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository):
    ViewModel() {

    private var _category = MutableLiveData<List<ContactDetail>>()
    val category: LiveData<List<ContactDetail>>
        get() = _category



    fun findByCategory(category:String) =
        viewModelScope.launch {
            _category.value = repository.findByCategory(category)
            Log.d("Userviewmodel", "findFilledEmailPassword: ${_category.value.toString()}")
        }


    fun insert(contact: ContactDetail) = viewModelScope.launch {
        repository.insert(contact)
    }
}

class ContactViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}