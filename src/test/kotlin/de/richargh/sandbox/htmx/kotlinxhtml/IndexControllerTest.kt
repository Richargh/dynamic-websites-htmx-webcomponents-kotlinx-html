package de.richargh.sandbox.htmx.kotlinxhtml

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ApplicationTest
class IndexControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun shouldRedirectToGreeting() {
        mockMvc
            .get("/")
            .andExpect {
                status { is3xxRedirection() }
                header { string("Location", "greeting") }
            }
    }
}

