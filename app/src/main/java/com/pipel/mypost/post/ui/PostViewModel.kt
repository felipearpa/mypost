package com.pipel.mypost.post.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pipel.mypost.cache.Cache
import com.pipel.mypost.cache.DailyCache
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