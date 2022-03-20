/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htm.configs;

import java.sql.DriverManager;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author Manax
 */

@Configuration
@PropertySource("classpath:database.properties")
public class HibernateConfig {
    @Autowired
    private Environment env;
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factory

                                 = new LocalSessionFactoryBean();
    factory.setPackagesToScan(new String[] {
            "com.htm.pojo"
    });
        factory.setDataSource(dataSource());
//        factory.setHibernateProperties(hibernateProperties());
    return factory;
    
    }
    
    @Bean
    public  DataSource dataSource(){
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName(env.getPropety("hibernate.connection.driverClass"));
        d.setUsername(env.getProperty("hibernate.connection.username"));
        d.setPassword(env.getProperty("hibernate.connection.password"));
        d.setUrl(env.getProperty("hibernate.connection.url"));
        
        return d;
    }
    
        private Properties hibernateProperties() {
            Properties props = new Properties();
            props.put(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.showSql"));
            props.put(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
        
                    return props;
        }
}
         
          

