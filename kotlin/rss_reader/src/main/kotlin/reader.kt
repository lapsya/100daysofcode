package reader

import com.github.magneticflux.rss.createRssPersister
import com.github.magneticflux.rss.namespaces.standard.elements.Guid
import com.github.magneticflux.rss.namespaces.standard.elements.Rss
import khttp.get
import org.threeten.bp.ZonedDateTime


data class Post(
        val id: Guid,
        val pubDate: ZonedDateTime?,
        val title: String,
        val author: String,
        val link: String
)

typealias Feed = MutableList<Post>

class RSSReader(val url: String) {
    var feedBuffer: Feed? = null

    init {
        println("Reader initialized!")
    }

    fun getFeed(): Feed {
        val response = get(url)
        println(response.text)
        val persister = createRssPersister()
        val rssFeed = persister.read(Rss::class.java, response.text)

        val posts = mutableListOf<Post>()
        for (item in rssFeed.channel.items) {
            val post_item = Post(
                    item.guid ?: Guid("false", "null"),
                    item.pubDate,
                    item.title ?: "Uknown title",
                    item.author ?: "Unknown author",
                    item.link.toString()
            )
            posts.add(post_item)
        }
        return posts

    }

    fun update() {
        val newFeed = getFeed()

        val ids = feedBuffer?.map { it.id } ?: emptyList()
        val newPosts = mutableListOf<Post>()

        for (post in newFeed) {
            if (ids.isEmpty()) {
                newPosts.add(post)
                continue
            }
            if ((post.id !in ids) or (post.pubDate != (feedBuffer!!.filter { it.id == post.id }[0]).pubDate)) {
                newPosts.add(post)
            }
        }

        for (post in newPosts) {
            println("'${post.title}'")
        }

        feedBuffer = newFeed
    }
}