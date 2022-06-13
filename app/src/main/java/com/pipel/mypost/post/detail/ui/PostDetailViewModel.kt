package com.pipel.mypost.post.detail.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pipel.mypost.post.detail.mapper.PostDetailMapper
import com.pipel.mypost.post.detail.useCase.GetPostDetailUseCase
import com.pipel.mypost.post.detail.view.PostDetailModel
import com.pipel.mypost.route.RouteNavigator
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel @AssistedInject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase,
    @Assisted private val postId: Int,
    private val routerNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routerNavigator {

    private val _postDetailFlow: MutableStateFlow<PostDetailModel?> = MutableStateFlow(null)
    val postDetailFlow: Flow<PostDetailModel?>
        get() = _postDetailFlow.asStateFlow()

    init {
        Log.d("FELIPE", "PostDetailViewModel init")
        viewModelScope.launch {
            _postDetailFlow.value =
                PostDetailMapper.mapFromDomainToView(getPostDetailUseCase.execute(postId))
        }
    }

}