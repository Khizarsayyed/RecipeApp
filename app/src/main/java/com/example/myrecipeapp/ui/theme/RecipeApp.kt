package com.example.myrecipeapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


@Composable
fun RecipeApp(navController: NavHostController){
val recipeViewModel: MainViewModel= viewModel()
    val viewstate by recipeViewModel.categoriesState

 NavHost(navController= navController, startDestination= Screen.RecipeScreen.route){
     composable(route = Screen.RecipeScreen.route){
         RecipeScreen( viewstate = viewstate,navigateToDetail ={
//This part is responsible for passing the data from the current screen to details screen
         // It utilizes the savedStateHandle , which is a component of the compose navigation framework
             navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
             navController.navigate(Screen.DetailScreen.route)
         } )
     }
     composable(route = Screen.DetailScreen.route){
         val category= navController.previousBackStackEntry?.savedStateHandle?.
         //cat is the key
     get<Category>("cat") ?:Category("","","","")
         CategoryDetailScreen(category = category)
     }
 }

}