import greet.Greeter
import org.junit.Assert.assertEquals
import org.junit.Test

class GreeterTest {
    @Test fun testGreeter() {
        assertEquals("Meredith", Greeter("Meredith").name)
    }
}
