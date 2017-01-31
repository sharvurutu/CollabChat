package com.niit.collaborationbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationbackend.model.Blog;
import com.niit.collaborationbackend.model.Chat;
import com.niit.collaborationbackend.model.Event;
import com.niit.collaborationbackend.model.Forum;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.JobApplication;
import com.niit.collaborationbackend.model.User;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement

public class ApplicationContextConfig {

	// create an instance
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder lsf = new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		Class classes[] = { User.class, Blog.class, Job.class, JobApplication.class, Friend.class, Event.class,
				Chat.class, Forum.class };
		return lsf.addAnnotatedClasses(classes)

				.buildSessionFactory();
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("COLLAB");// Schema name
		dataSource.setPassword("12345");
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager hibTransManagement() {
		return new HibernateTransactionManager(sessionFactory());
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		return sessionBuilder.buildSessionFactory();
	}

}
