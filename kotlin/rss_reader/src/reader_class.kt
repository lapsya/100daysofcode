class RSSReader(val url: String) {
    init {
        println("Reader initialized!")
    }

    val feedBuffer: String = ""

    fun getFeed() {
        println("about to request the feed")
    }
}


fun main(args: Array<String>) {
    println("Hello!")
    val a = RSSReader("http://androidpolice.com/feed")
    a.getFeed()
}
