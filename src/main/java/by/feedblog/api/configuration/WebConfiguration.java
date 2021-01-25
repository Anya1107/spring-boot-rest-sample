package by.feedblog.api.configuration;

import by.feedblog.api.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private TokenInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/post/**")
                .addPathPatterns("/tag/**")
                .addPathPatterns("/category/**")
                .addPathPatterns("/reaction/**")
                .addPathPatterns("/like/**")
                .addPathPatterns("/dislike/**")
                .addPathPatterns("/comment/**")
                .addPathPatterns("/bookmark/**");
    }
}
