package life.league.challenge.kotlin.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Path

data class User (
    @SerializedName("id") val Id: Int? = null,
    @Path("name") val name: String,
    @Path("avatar") val avatar: Avatar
)

data class Avatar(
    @Path("large") val large: String,
    @Path("medium") val medium: String,
    @Path("thumbnail") val thumbnail: String
)