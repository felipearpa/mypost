package com.pipel.mypost.post.ui

import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.post.mapper.PostMapper
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.type.Identifier
import com.pipel.mypost.type.NonEmptyString

fun postForPreview(): List<Post> = listOf(
    Post(
        userId = Identifier(1),
        id = Identifier(1),
        title = NonEmptyString("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"),
        body = NonEmptyString("quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto")
    ),
    Post(
        userId = Identifier(1),
        id = Identifier(2),
        title = NonEmptyString("qui est esse"),
        body = NonEmptyString("est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla")
    ),
    Post(
        userId = Identifier(1),
        id = Identifier(3),
        title = NonEmptyString("ea molestias quasi exercitationem repellat qui ipsa sit aut"),
        body = NonEmptyString("et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut")
    ),
)

fun postsModelsForPreview(): List<PostModel> =
    postForPreview().map(PostMapper::mapFromDomainToView)