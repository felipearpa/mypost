package com.pipel.mypost.post.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.pipel.mypost.post.ui.PostView
import com.pipel.mypost.post.ui.PostViewModel
import com.pipel.mypost.route.NavRoute

object PostRoute : NavRoute<PostViewModel> {

    override val route: String = "post"

    override val arguments: List<NamedNavArgument> = emptyList()

    @Composable
    override fun Content(viewModel: PostViewModel) = PostView(viewModel = viewModel)

    @Composable
    override fun viewModel(navBackStackEntry: NavBackStackEntry): PostViewModel = hiltViewModel()

}