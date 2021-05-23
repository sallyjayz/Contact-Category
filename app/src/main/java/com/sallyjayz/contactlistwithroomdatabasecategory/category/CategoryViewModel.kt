package com.sallyjayz.contactlistwithroomdatabasecategory.category

import androidx.lifecycle.*
import com.sallyjayz.contactlistwithroomdatabasecategory.database.CategoryRepository
import com.sallyjayz.contactlistwithroomdatabasecategory.model.ContactCategory

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    private val _properties = MutableLiveData<List<ContactCategory>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<ContactCategory>>
        get() = _properties

   val allCategory: LiveData<List<ContactCategory>> = repository.allCategory.asLiveData()

}

class CategoryViewModelFactory(private val repository: CategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}