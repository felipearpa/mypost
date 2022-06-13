package com.pipel.mypost.post.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pipel.mypost.post.detail.route.PostDetailRoute
import com.pipel.mypost.post.mapper.PostMapper
import com.pipel.mypost.post.useCase.GetPostsUseCase
import com.pipel.mypost.post.useCase.RemovePostUseCase
import com.pipel.mypost.post.useCase.RemovePostsUseCase
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
    private val removePostUseCase: RemovePostUseCase,
    private val removePostsUseCase: RemovePostsUseCase,
    private val routerNavigator: RouteNavigator
) :
    ViewModel(), RouteNavigator by routerNavigator {

    private val _postsFlow: MutableStateFlow<List<PostModel>> = MutableStateFlow(emptyList())
    val postsFlow: Flow<List<PostModel>>
        get() = _postsFlow.asStateFlow()

    private val _isLoadingFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingFlow: Flow<Boolean>
        get() = _isLoadingFlow.asStateFlow()

    init {
        viewModelScope.launch {
            load()
        }
    }

    fun navigateToPostDetail(postId: Int) {
        navigate(PostDetailRoute.getRoute(postId))
    }

    fun refresh() {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() {
        _postsFlow.value = getPostsUseCase.execute().map(PostMapper::mapFromDomainToView)
    }

    fun removePost(postId: Int) {
        viewModelScope.launch {
            removePostUseCase.execute(postId)
            _postsFlow.value = _postsFlow.value.filter { post -> post.id != postId }
        }
    }

    fun removeAllPosts() {
        viewModelScope.launch {
            _isLoadingFlow.value = true
            removePostsUseCase.execute(_postsFlow.value.map { post -> post.id })
            _postsFlow.value = emptyList()
            _isLoadingFlow.value = false
        }
    }

}