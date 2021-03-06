package com.zx.myapp

/**
 *  description :xxxx 描述 <br/>
 *  author:ZhaoXiang <br/>
 *  create:2021/1/20 14:25 <br/>
 *  version:1.0 <br/>
 *  update:2021/1/20 14:25 <br/>
 */
data class ImageBean(
    val alt_description: String,
    val blur_hash: String,
    val categories: List<Any>,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: Any,
    val downloads: Int,
    val exif: Exif,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val location: Location,
    val promoted_at: String,
    val sponsorship: Any,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val views: Int,
    val width: Int
)

data class Exif(
    val aperture: String,
    val exposure_time: String,
    val focal_length: String,
    val iso: Int,
    val make: String,
    val model: String
)

data class Links(

    val followers: String,
    val following: String,
    val html: String,
    val likes: String,
    val photos: String,
    val portfolio: String,
    val self: String,

    val download: String,
    val download_location: String
)

data class Location(
    val city: Any,
    val country: Any,
    val name: Any,
    val position: Position,
    val title: Any
)

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class User(
    val accepted_tos: Boolean,
    val bio: String,
    val first_name: String,
    val id: String,
    val instagram_username: Any,
    val last_name: String,
    val links: Links,
    val location: String,
    val name: String,
    val portfolio_url: String,
    val profile_image: ProfileImage,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: Any,
    val updated_at: String,
    val username: String
)

data class Position(
    val latitude: Any,
    val longitude: Any
)

data class ProfileImage(
    val large: String,
    val medium: String,
    val small: String
)