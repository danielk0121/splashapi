package dev.danielk0121.test.kakaopay.splash.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

	private @Autowired ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties("spring.datasource.hikari")
	public DataSource dataSource() {

		return DataSourceBuilder.create()
				.type(HikariDataSource.class)
				.build() ;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {

		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setTypeAliasesPackage("dev.danielk0121.test.kakaopay.splash.domain");
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mapper/*.xml"));
		return factoryBean;
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {

		return new SqlSessionTemplate(sqlSessionFactory);
	}

}