package com.felipearpa.mypost.post.detail.mapper

import com.felipearpa.mypost.comment.mapper.CommentMapper
import com.felipearpa.mypost.post.detail.domain.PostDetail
import com.felipearpa.mypost.post.detail.view.PostDetailModel
import com.felipearpa.mypost.post.mapper.PostMapper
import com.felipearpa.mypost.user.mapper.UserMapper

object PostDetailMapper {

    fun mapFromDomainToView(postDetail: PostDetail): PostDetailModel =
        PostDetailModel(
            post = PostMapper.mapFromDomainToView(postDetail.post),
            author = UserMapper.mapFromDomainToView(postDetail.author),
            comments = postDetail.comments.map(CommentMapper::mapFromDomainToView)
        )

}