package com.affey;

import java.io.IOException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@ComponentScan("com.affey")
@PropertySource("classpath:local.properties")
@EnableAutoConfiguration(exclude = VelocityAutoConfiguration.class)
@EnableTransactionManagement
@EnableSwagger2
public class AffeyServer {
	
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
	    SpringApplication.run(AffeyServer.class, args);
	  }

	  @Bean
	  public Docket affeyApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .paths(PathSelectors.ant("/v1/api/**"))
	        .build()
	        .apiInfo(
	            new ApiInfo("Affey APIs", "APIs to book tickets in theatres", "v1", "",
	                "saugupta@adobe.com", "", ""));
	  }

	  @Bean
	  public SessionFactory sessionFactory() throws IOException {
	    PropertiesFactoryBean propertiesFactory = new PropertiesFactoryBean();
	    propertiesFactory
	        .setLocation(new ClassPathResource("hibernate.properties"));
	    propertiesFactory.afterPropertiesSet();
	    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
	    sessionFactoryBean.setPackagesToScan("com.affey.model");
	    sessionFactoryBean.setDataSource(dataSource());
	    sessionFactoryBean.setHibernateProperties(propertiesFactory.getObject());
	    sessionFactoryBean.afterPropertiesSet();
	    return sessionFactoryBean.getObject();
	  }

	  @Bean
	  public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("affey.db.driver"));
	    dataSource.setUrl("jdbc:mysql://" + env.getProperty("affey.db.host") + ":"
	        + env.getProperty("affey.db.port") + "/"
	        + env.getProperty("affey.db.name"));
	    dataSource.setUsername(env.getProperty("affey.db.username"));
	    dataSource.setPassword(env.getProperty("affey.db.password"));
	    return dataSource;
	  }

	  @Bean
	  public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	  }
	  /*
	   * This bean is used to create a session, No need to create a session anymore for transactions
	   * Use sessionFactory.getCurrentSession(); instead of sessionFactory.openSession();
	   * i.e now if we use @Transactional for any function, it automatically opens a session and close at the end. 
	   *So, for using @Transactional, we need to use this HibernateTransactionalManager which replaces our code
	   *public void doSomething() {
	    Session sess = factory.openSession();
	    Transaction tx = null;
	    try {
	        tx = sess.beginTransaction();
	
	        // do some work
	        ...
	
	        tx.commit();
	    }
	    catch (RuntimeException e) {
	        if (tx != null) tx.rollback();
	        throw e; // or display error message
	    }
	    finally {
	        sess.close();
	    }
	}
	with simple: @Transactional
	public void doSomething() {
	    // do some work
	}
	   */
	  @Bean
	  @Autowired
	  public PlatformTransactionManager transactionManager(
	      SessionFactory sessionFactory) {
	    return new HibernateTransactionManager(sessionFactory);
	  }
}
