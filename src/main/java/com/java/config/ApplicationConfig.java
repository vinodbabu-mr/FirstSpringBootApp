package com.java.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@EnableWebMvc
@ComponentScan(basePackages = "com.java")
class ApplicationConfig {
	
  @Autowired
  Environment envv;

  @Bean
  public DataSource dataSource() {
	DriverManagerDataSource ds = new DriverManagerDataSource();
	ds.setUrl(envv.getProperty("url"));
	ds.setUsername(envv.getProperty("usernme"));
	ds.setPassword(envv.getProperty("password"));
	ds.setDriverClassName(envv.getProperty("driver"));
    return ds;
  }
  
  private Properties hibernateProp() {
	  Properties prop = new Properties();
	  prop.setProperty("hibernate.dialect", envv.getProperty("hibernate.dialect"));
	  prop.setProperty("hibernate.show_sql", envv.getProperty("hibernate.show_sql"));
	  return prop;
  }

  @SuppressWarnings("deprecation")
@Bean
  public EntityManagerFactory entityManagerFactory() {

//    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    //factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.java");
    factory.setDataSource(dataSource());
    factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    factory.setJpaProperties(hibernateProp());
    factory.afterPropertiesSet();

    return factory.getObject();
  }

  @Bean
  public JpaTransactionManager transactionManager() {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }
}
