package com.example.projectn01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.projectn01.navigation.NavigationGraph
import com.example.projectn01.ui.theme.ProjectN01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectN01Theme {
                val navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectN01Theme {
        val navController = rememberNavController()
        NavigationGraph(navController)
    }
}