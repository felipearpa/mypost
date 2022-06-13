package com.pipel.mypost.post.detail.mapper

import com.pipel.mypost.comment.mapper.CommentMapper
import com.pipel.mypost.post.detail.domain.PostDetail
import com.pipel.mypost.post.detail.view.PostDetailModel
import com.pipel.mypost.post.mapper.PostMapper
import com.pipel.mypost.user.mapper.UserMapper

object PostDetailMapper {

    fun mapFromDomainToView(postDetail: PostDetail): PostDetailModel =
        PostDetailModel(
            post = PostMapper.mapFromDomainToView(postDetail.post),
            author = UserMapper.mapFromDomainToView(postDetail.author),
            comments = postDetail.comments.map(CommentMapper::mapFromDomainToView)
        )

}