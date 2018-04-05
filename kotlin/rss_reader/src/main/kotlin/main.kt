import reader.RSSReader

fun main(args: Array<String>) {
    println("Hello!")
    val a = RSSReader("http://androidpolice.com/feed")
    a.update()

    println(a.feedBuffer?.etag)
}
