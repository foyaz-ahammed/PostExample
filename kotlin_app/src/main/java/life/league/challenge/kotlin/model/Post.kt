package life.league.challenge.kotlin.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Path

data class Post(
    @SerializedName("id") val Id: Int? = null,
    @Path("userId") val userId: Int,
    @Path("title") val title: String,
    @Path("body") val body: String
)
