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


class AuthPageContextArgumentResolver(
    private val storeFacade: StoreFacade,
    private val mapper: ObjectMapper
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        return methodParameter.getParameterAnnotation(AuthContext::class.java) != null
                && methodParameter.parameterType == AuthPageContext::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): PageContext {
        val token = webRequest.getAttribute(
            CsrfToken::class.java.getName(), RequestAttributes.SCOPE_REQUEST
        ) as CsrfToken
        val principal = webRequest.userPrincipal as? UsernamePasswordAuthenticationToken
        val user = principal?.principal as? UserDetails
        if(user == null)
            throw IllegalArgumentException("Cannot resolve anonymous user. Please use AnonContext instead")
        return userCtx(user, token)
    }

    private fun userCtx(userDetails: UserDetails, csrfToken: CsrfToken): PageContext {
        val user = PageUser(UserName(userDetails.username))
        val userData = PageUserData(storeFacade.countBasketItems(user.userName))

        return AuthPageContext(
            user,
            userData,
            CsrfFormToken(csrfToken.token),
            PageContextServices(
                mapper
            )
        )
    }

}