package com.mybaits.test.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {

        return new DruidDataSource();
    }

    // 配置Druid的监控
    // 1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initparames = new HashMap<>();
        initparames.put("loginUsername", "admin");
        initparames.put("loginPassword", "111111");
        initparames.put("allow", "");//默认就是允许所有访问
        bean.setInitParameters(initparames);
        return bean;
    }

    // 2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webstatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initparams = new HashMap<>();
        initparams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initparams);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
