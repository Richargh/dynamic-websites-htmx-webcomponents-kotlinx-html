package de.richargh.sandbox.htmx.kotlinxhtml

import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ApplicationTest
class GreetingControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should greet anonymous user`() {
        mockMvc
            .get(Paths.Greeting.INDEX) { }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.TEXT_HTML)
                    xpath("//p[@data-testid='message']") {
                        string("Hello Anonymous")
                    }
                }
            }
    }

    @Test
    fun `should greet logged in user`() {
        mockMvc
            .get(Paths.Greeting.INDEX) { with(vin()) }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.TEXT_HTML)
                    xpath("//p[@data-testid='message']") {
                        string("Hello vin")
                    }
                }
            }
    }
}
