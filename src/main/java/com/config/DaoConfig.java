package com.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "mapper")
public class DaoConfig {
    @Value("${mybatis.config-location:classpath:mybatis-config-dao.xml}")
    private String configLocation;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        bean.setVfs(SpringBootVFS.class);
        bean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

        return bean.getObject();
    }
}
