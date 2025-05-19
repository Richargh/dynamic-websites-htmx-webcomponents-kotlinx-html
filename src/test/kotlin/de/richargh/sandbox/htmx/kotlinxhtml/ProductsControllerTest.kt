package de.richargh.sandbox.htmx.kotlinxhtml

import de.richargh.sandbox.htmx.kotlinxhtml.commons.routes.web.Paths
import de.richargh.sandbox.htmx.kotlinxhtml.product.web.ProductFormData
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@ApplicationTest
class ProductsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should redirect anonymous users to login`() {
        mockMvc
            .get(Paths.Products.INDEX) { }
            .andExpect {
                status { is3xxRedirection() }
                header { string("Location", "http://localhost${Paths.Login.INDEX}") }
            }
    }

    @Test
    fun `should show product table to logged in user`() {
        mockMvc
            .get(Paths.Products.INDEX) { with(vin()) }
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.TEXT_HTML)
                    xpath("//table[@data-testid='products-table']") {
                        exists()
                    }
                }
            }
    }

    @Test
    fun `should redirect after adding a valid product`() {
        mockMvc
            .post(Paths.Products.INDEX) {
                with(vin())
                with(csrf())
                param(ProductFormData::id.name, "1")
                param(ProductFormData::name.name, "Belt")
                param(ProductFormData::priceInCents.name, "2000")
            }
            .andExpect {
                status { is3xxRedirection() }
                header { string("Location", Paths.Products.INDEX) }
            }
    }

    @Test
    fun `should show errors when adding an invalid product`() {
        mockMvc
            .post(Paths.Products.INDEX) {
                with(vin())
                with(csrf())
                param(ProductFormData::id.name, "1")
                param(ProductFormData::name.name, "Belt")
                param(ProductFormData::priceInCents.name, "0")
            }
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.TEXT_HTML)
                    xpath("//form[@data-testid='put-product-form']") {
                        exists()
                    }
                }
            }
    }
}