package newsaggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by admin on 13.03.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan({"newsaggregator.config", "newsaggregator.controller", "newsaggregator.repository", "newsaggregator.service"})
public class WebConfig extends WebMvcConfigurerAdapter{
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    @Bean
    ViewResolver tilesViewResolver() {
        TilesViewResolver viewResolver = new TilesViewResolver();
        viewResolver.setOrder(0);
        return viewResolver;
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setContentType("text/html; charset-utf-8");
//        viewResolver.setOrder(1);
//        return viewResolver;
//    }

    /**
     * Configure ViewResolvers to deliver preferred views.
     */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        TilesViewResolver viewResolver = new TilesViewResolver();
//        registry.viewResolver(viewResolver);
//    }

    @Bean
    TilesConfigurer tilesConfigurer() {
        TilesConfigurer tiles = new TilesConfigurer();
        tiles.setDefinitions("/WEB-INF/defs/general.xml");
        tiles.setCheckRefresh(true);
        return tiles;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/WEB-INF/views/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/views/fonts/");
        registry.addResourceHandler("/font-awesome/**").addResourceLocations("/WEB-INF/views/font-awesome/");
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/views/img/");
    }
}
