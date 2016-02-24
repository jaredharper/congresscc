package com.none.controller;

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

@Configuration
@MapperScan("com.none.mapper")
public class Config extends WebMvcConfigurerAdapter
{

	URI dbUri;

	SqlSessionFactory f;

	// Will map to the JSP page: "WEB-INF/views/accounts/list.jsp"
	@Bean
	public ViewResolver getJspViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public DataSource getDataSource()
	{
		try
		{
			
			// This returns null
			String prop = System.getenv("HEROKU_POSTGRESQL_CONGRESS");
			
			// This doesn't
			Map<String,String> p = System.getenv();
			prop = p.get("HEROKU_POSTGRESQL_CONGRESS");
			
			// I am confused.
	        URI dbUri = new URI(prop);

	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	        BasicDataSource basicDataSource = new BasicDataSource();
	        basicDataSource.setUrl(dbUrl);
	        basicDataSource.setUsername(username);
	        basicDataSource.setPassword(password);
			
			return basicDataSource;

		} catch (Exception ex)
		{
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
			return null;
		}
	}


	@Bean
	public SqlSessionFactory getSqlSessionFactory()
	{
		try
		{
			SqlSessionFactoryBean b = new SqlSessionFactoryBean();
			b.setDataSource(getDataSource());
			return b.getObject();
		} catch (Exception ex)
		{
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
			return null;
		}
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}
}