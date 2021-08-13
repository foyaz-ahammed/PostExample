package life.league.challenge.kotlin.model

data class PostDetail(
    val Id: Int? = null,
    val userId: Int,
    val userName: String,
    var photoUrl: String,
    val title: String,
    val description: String,
) {
    constructor(post: Post, user: User): this(post.Id, post.userId, user.name, user.avatar.medium, post.title, post.body)
}