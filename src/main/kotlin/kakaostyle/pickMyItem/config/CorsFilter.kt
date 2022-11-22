package kakaostyle.pickMyItem.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("http://localhost")
        config.addAllowedOrigin("http://pick-item-client.s3-website.ap-northeast-2.amazonaws.com")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        config.allowedOriginPatterns = listOf("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}