package de.richargh.sandbox.htmx.kotlinxhtml.commons.context.web

import org.springframework.core.MethodParameter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


class PageContextArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        return methodParameter.getParameterAnnotation(Context::class.java) != null
                && methodParameter.parameterType == PageContext::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): PageContext {
        val token = webRequest.getAttribute(
            CsrfToken::class.java.getName(), RequestAttributes.SCOPE_REQUEST) as CsrfToken
        val principal = webRequest.userPrincipal as? UsernamePasswordAuthenticationToken
        val user = principal?.principal as? UserDetails
        return userCtx(user, token)
    }

    private fun userCtx(userDetails: UserDetails?, csrfToken: CsrfToken) = PageContext(
        userDetails?.let { PageUser(it.username) },
        CsrfFormToken(csrfToken.token)
    )

}