package reader

data class Post(
        val id: String,
        val updated: String,
        val title: String,
        val author: String,
        val link: String
)

data class Feed(val posts: List<Post>, val etag: String)

class RSSReader(val url: String) {
    var feedBuffer: Feed? = null

    init {
        println("Reader initialized!")
    }

    fun getFeed(): Feed {
        // make a request and get the feed
        return Feed(
                listOf(Post(
                        "482320",
                        "Mon, 26 Mar 2018 22:39:27 +0000",
                        "Start the week with 30 temporarily free and 43 on-sale apps",
                        "Jordan Palmer",
                        "https://www.androidpolice.com/2018/03/26/app-sales-mar-26-2018/"
                )),
                "bc800c70980104f41809d3a294f55d55"
        )

    }

    fun update() {
        val newFeed = getFeed()

        val ids = feedBuffer?.posts?.map { it.id } ?: emptyList()
        val newPosts = mutableListOf<Post>()

        for (post in newFeed.posts) {
            if (ids.isEmpty()) {
                newPosts.add(post)
                continue
            }
            if ((post.id !in ids) or (post.updated != (feedBuffer!!.posts.filter { it.id == post.id }[0]).updated)) {
                newPosts.add(post)
            }
        }

        for (post in newPosts) {
            println("'${post.title}'")
        }

        feedBuffer = newFeed
    }
}