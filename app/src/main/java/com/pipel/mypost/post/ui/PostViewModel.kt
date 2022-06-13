package com.pipel.mypost.post.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pipel.mypost.post.database.useCase.AddPostsDatabaseUseCase
import com.pipel.mypost.post.database.useCase.GetAllPostsDatabaseUseCase
import com.pipel.mypost.post.database.useCase.RemoveAllPostsDatabaseUseCase
import com.pipel.mypost.post.detail.route.PostDetailRoute
import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.post.mapper.PostMapper
import com.pipel.mypost.post.useCase.GetPostsUseCase
import com.pipel.mypost.post.useCase.RemovePostUseCase
import com.pipel.mypost.post.useCase.RemovePostsUseCase
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.route.RouteNavigator
import com.pipel.mypost.store.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val removePostUseCase: RemovePostUseCase,
    private val removePostsUseCase: RemovePostsUseCase,
    private val addPostsToDatabaseUseCase: AddPostsDatabaseUseCase,
    private val removeAllPostsDatabaseUseCase: RemoveAllPostsDatabaseUseCase,
    private val getAllPostsDatabaseUseCase: GetAllPostsDatabaseUseCase,
    private val store: DataStore,
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
            store.getCacheDateTimeFlow().distinctUntilChanged().collectLatest { cacheDateTime ->
                if (mustLoadFromService(cacheDateTime)) {
                    loadFromServiceAndCache()
                } else {
                    loadFromDatabase()
                }
            }
        }
    }

    private fun mustLoadFromService(cacheDateTime: Date?): Boolean =
        cacheDateTime == null || isCacheDateTimeGreaterThanOneDay(cacheDateTime)

    private fun isCacheDateTimeGreaterThanOneDay(cacheDateTime: Date) =
        (Date().time - cacheDateTime.time) / 1000 / 60 / 60 / 24 > 1L

    fun navigateToPostDetail(postId: Int) {
        navigate(PostDetailRoute.getRoute(postId))
    }

    fun refresh() {
        viewModelScope.launch {
            loadFromServiceAndCache()
        }
    }

    private suspend fun loadFromServiceAndCache() {
        val posts = getPostsUseCase.execute()
        _postsFlow.value = posts.map(PostMapper::mapFromDomainToView)
        cachePosts(posts)
    }

    private suspend fun cachePosts(posts: List<Post>) {
        removeAllPostsDatabaseUseCase.execute()
        addPostsToDatabaseUseCase.execute(posts)
        store.putCacheDateTime(Date())
    }

    private suspend fun loadFromDatabase() {
        _postsFlow.value = getAllPostsDatabaseUseCase.execute().map(PostMapper::mapFromDomainToView)
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