package com.none.main;

import java.net.URI;
import java.sql.Driver;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Class to create beans for Spring Boot's automagic configuration logic
 * 
 * @author jthomas
 *
 */
@Configuration
@MapperScan("com.none.mapper")
public class Config extends WebMvcConfigurerAdapter
{

	/**
	 * Map all views in the view directory
	 */
	@Bean
	public ViewResolver getJspViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * Configures the data source.
	 * 
	 * In order for the Spring automagic to work and connect to the database an
	 * environment variable named HEROKU_POSTGRESQL_CONGRESS must exist with a
	 * correctly formatted postgres connection URL. It should look like this:
	 * 
	 * postgres://username:password@localhost:5432/databasename
	 * 
	 */
	@Bean
	public DataSource getDataSource()
	{
		try
		{

			// Get connection info from environment
			Map<String, String> p = System.getenv();
			String prop = p.get("HEROKU_POSTGRESQL_CONGRESS");
			URI dbUri = new URI(prop);

			// Reformat the URL because the JDBC driver wants "postgresql"
			// instead of "postgres"
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

			// This is not WebScale(tm). Should probably change this to a PooledDataSource.
			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setUrl(dbUrl);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);
			return basicDataSource;

		}
		catch (Exception ex)
		{
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
			return null;
		}
	}

	/**
	 * Spring Boot needs this as part of setting up the database. By convention
	 * it needs both a DataSource and a SqlSessionFactory.
	 * 
	 */
	@Bean
	public SqlSessionFactory getSqlSessionFactory()
	{
		try
		{
			SqlSessionFactoryBean b = new SqlSessionFactoryBean();
			b.setDataSource(getDataSource());
			return b.getObject();
		}
		catch (Exception ex)
		{
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
			return null;
		}
	}

	/**
	 * Basic servlet mapping config
	 * 
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}
}