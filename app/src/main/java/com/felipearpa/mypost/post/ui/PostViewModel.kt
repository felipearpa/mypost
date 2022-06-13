package com.felipearpa.mypost.post.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipearpa.mypost.cache.Cache
import com.felipearpa.mypost.cache.DailyCache
import com.felipearpa.mypost.post.database.useCase.AddPostsDatabaseUseCase
import com.felipearpa.mypost.post.database.useCase.GetAllPostsDatabaseUseCase
import com.felipearpa.mypost.post.database.useCase.RemoveAllPostsDatabaseUseCase
import com.felipearpa.mypost.post.detail.route.PostDetailRoute
import com.felipearpa.mypost.post.domain.Post
import com.felipearpa.mypost.post.mapper.PostMapper
import com.felipearpa.mypost.post.useCase.GetPostsUseCase
import com.felipearpa.mypost.post.useCase.RemovePostUseCase
import com.felipearpa.mypost.post.useCase.RemovePostsUseCase
import com.felipearpa.mypost.post.view.PostModel
import com.felipearpa.mypost.route.RouteNavigator
import com.felipearpa.mypost.store.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
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

    private val cache: Cache<List<Post>>

    init {
        cache = DailyCache(
            onLoad = { posts ->
                _postsFlow.value = posts.map(PostMapper::mapFromDomainToView)
            },
            cacheDateTimeFlow = store.getCacheDateTimeFlow().distinctUntilChanged(),
            numberOfDays = 1,
            loadFromService = {
                getPostsUseCase.execute()
            },
            loadFromDatabase = {
                getAllPostsDatabaseUseCase.execute()
            },
            cache = { posts ->
                cachePosts(posts)
            }
        )

        viewModelScope.launch {
            cache.load()
        }
    }

    fun navigateToPostDetail(postId: Int) {
        navigate(PostDetailRoute.getRoute(postId))
    }

    fun refresh() {
        viewModelScope.launch {
            cache.reload()
        }
    }

    private suspend fun cachePosts(posts: List<Post>) {
        removeAllPostsDatabaseUseCase.execute()
        addPostsToDatabaseUseCase.execute(posts)
        store.putCacheDateTime(Date())
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