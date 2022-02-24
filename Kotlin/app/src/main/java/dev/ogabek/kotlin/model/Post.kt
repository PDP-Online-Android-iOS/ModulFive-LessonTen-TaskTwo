package dev.ogabek.kotlin.model

class Post {
    val profile: Int
    var photo = 0
    val fullName: String
    var photos: List<Photos> = ArrayList()

    constructor(profile: Int, photo: Int, fullName: String) {
        this.profile = profile
        this.photo = photo
        this.fullName = fullName
    }

    constructor(profile: Int, photos: List<Photos>, fullName: String) {
        this.profile = profile
        this.photos = photos
        this.fullName = fullName
    }
}
