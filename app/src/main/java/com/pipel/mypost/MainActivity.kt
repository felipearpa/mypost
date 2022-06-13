package com.pipel.mypost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pipel.mypost.post.detail.route.PostDetailRoute
import com.pipel.mypost.post.detail.ui.PostDetailViewModelFactory
import com.pipel.mypost.post.route.PostRoute
import com.pipel.mypost.ui.theme.MyPostTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun postDetailViewModelFactory(): PostDetailViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPostTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    Outlet()

    /*Scaffold(
        topBar = { AppTopBar(title = { Text(text = "MyPost") }) },
        content = { Outlet() }
    )*/
}

@Composable
fun AppTopBar(title: @Composable () -> Unit) {
    TopAppBar(title = title, elevation = 0.dp)
}

@Composable
fun Outlet() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PostRoute.route) {
        PostRoute.composable(this, navController)
        PostDetailRoute.composable(this, navController)
    }
}