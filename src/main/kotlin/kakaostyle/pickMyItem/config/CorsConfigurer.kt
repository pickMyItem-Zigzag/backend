package kakaostyle.pickMyItem.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost", "http://pick-item-client.s3-website.ap-northeast-2.amazonaws.com")
            .allowedMethods(HttpMethod.POST.name, HttpMethod.GET.name)
            .allowedOriginPatterns("*")
            .allowCredentials(true)
    }
}
