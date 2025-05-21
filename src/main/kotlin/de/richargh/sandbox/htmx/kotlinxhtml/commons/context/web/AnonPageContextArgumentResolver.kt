package de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web

import com.fasterxml.jackson.databind.ObjectMapper
import de.richargh.sandbox.htmx.kotlinxhtml.store.domain.StoreFacade
import org.springframework.core.MethodParameter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


class AnonPageContextArgumentResolver(
    private val storeFacade: StoreFacade,
    private val mapper: ObjectMapper
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        return methodParameter.getParameterAnnotation(AnonContext::class.java) != null
                && methodParameter.parameterType == AnonPageContext::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): AnonPageContext {
        val token = webRequest.getAttribute(
            CsrfToken::class.java.getName(), RequestAttributes.SCOPE_REQUEST
        ) as CsrfToken
        val principal = webRequest.userPrincipal as? UsernamePasswordAuthenticationToken
        val user = principal?.principal as? UserDetails
        return anonCtx(user, token)
    }

    private fun anonCtx(userDetails: UserDetails?, csrfToken: CsrfToken): AnonPageContext {
        val user = userDetails?.let { PageUser(UserName(it.username)) }
        val userData = user?.let { PageUserData(storeFacade.countBasketItems(it.userName)) }

        return AnonPageContext(
            user,
            userData,
            CsrfFormToken(csrfToken.token),
            PageContextServices(
                mapper
            )
        )
    }

}