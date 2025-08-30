package com.example.projectn01.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectn01.ui.screen.Home
import com.example.projectn01.ui.screen.UniteConverter


@Composable
fun NavigationGraph(navController : NavHostController) {

    NavHost(navController = navController ,  startDestination = routs.HOME){

        composable(routs.HOME) {
            Home(navController)
        }


        composable(routs.UNIT_CONVERTER) {
            UniteConverter(navController)
        }

    }

}
