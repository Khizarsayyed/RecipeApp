package com.example.myrecipeapp.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel :  ViewModel() {

private val _categorieState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categorieState

    init {
        fetchCategories()
    }
// This is called when MainViewModel is loaded
    private fun fetchCategories(){
        viewModelScope.launch{
            try {
                val response = recipeservice.getcategories()
                _categorieState.value  = _categorieState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }catch (e: Exception){
                _categorieState.value= _categorieState.value.copy(
                    loading = false,
                    error= "Error Fetching Categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading : Boolean = true,
        val list: List<Category> = emptyList(),
        // ? - thiS allows our string to be nullable
        val error: String? = null
    )

}