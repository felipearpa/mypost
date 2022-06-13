package com.pipel.mypost.post.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pipel.mypost.post.detail.route.PostDetailRoute
import com.pipel.mypost.post.mapper.PostMapper
import com.pipel.mypost.post.useCase.GetPostsUseCase
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.route.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val routerNavigator: RouteNavigator
) :
    ViewModel(), RouteNavigator by routerNavigator {

    private val _postsFlow: MutableStateFlow<List<PostModel>> = MutableStateFlow(emptyList())
    val postsFlow: Flow<List<PostModel>>
        get() = _postsFlow.asStateFlow()

    init {
        load()
    }

    fun navigateToPostDetail(postId: Int) {
        navigate(PostDetailRoute.getRoute(postId))
    }

    fun refresh() = load()

    private fun load() {
        viewModelScope.launch {
            _postsFlow.value = getPostsUseCase.execute().map(PostMapper::mapFromDomainToView)
        }
    }

}