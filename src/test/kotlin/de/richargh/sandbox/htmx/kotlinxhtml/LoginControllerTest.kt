package de.richargh.sandbox.htmx.kotlinxhtml

import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.login.web.LoginFormData
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@ApplicationTest
class LoginControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should login standard user`() {
        mockMvc
            .post(Paths.Login.INDEX) {
                with(csrf())
                param(LoginFormData::user.name, "alex")
                param(LoginFormData::pass.name, "alex")
            }
            .andExpect {
                status { is3xxRedirection() }
                header { string("Location", "/") }
            }
    }

    @Test
    fun `should not login any user`() {
        mockMvc
            .post(Paths.Login.INDEX) {
                with(csrf())
                param(LoginFormData::user.name, "fira")
                param(LoginFormData::pass.name, "fira")
            }
            .andExpect {
                status { is3xxRedirection() }
                header { string("Location", "${Paths.Login.INDEX}?error") }
            }
    }

}