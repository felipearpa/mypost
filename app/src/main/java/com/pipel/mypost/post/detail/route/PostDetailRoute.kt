package com.pipel.mypost.post.detail.route

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.pipel.mypost.MainActivity
import com.pipel.mypost.post.detail.ui.PostDetailView
import com.pipel.mypost.post.detail.ui.PostDetailViewModel
import com.pipel.mypost.post.detail.ui.providePostDetailViewModelFactory
import com.pipel.mypost.route.NavRoute
import dagger.hilt.android.EntryPointAccessors

object PostDetailRoute : NavRoute<PostDetailViewModel> {

    override val route: String = "post/{postId}"

    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(name = "postId") { type = NavType.IntType }
    )

    @Composable
    override fun Content(viewModel: PostDetailViewModel) = PostDetailView(viewModel = viewModel)

    @Composable
    override fun viewModel(navBackStackEntry: NavBackStackEntry): PostDetailViewModel {
        val factory = EntryPointAccessors.fromActivity(
            LocalContext.current as Activity,
            MainActivity.ViewModelFactoryProvider::class.java
        ).postDetailViewModelFactory()
        return androidx.lifecycle.viewmodel.compose.viewModel(
            factory = providePostDetailViewModelFactory(
                assistedFactory = factory,
                postId = navBackStackEntry.arguments!!.getInt("postId")
            )
        )
    }

    fun getRoute(postId: Int): String = "post/${postId}"

}