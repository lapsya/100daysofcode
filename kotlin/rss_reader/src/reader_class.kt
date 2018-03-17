package reader

class RSSReader(val url: String) {
    init {
        println("Reader initialized!")
    }

    val feedBuffer: String = ""

    fun getFeed() {
        println("about to request the feed")
    }


}