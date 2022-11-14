package com.example.springjpatest.conf;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.example.springjpatest.*"})
@Profile(value = {"!test"})
public class JpaConfig {

    private final static Logger log = LoggerFactory.getLogger(JpaConfig.class);
    private final Environment environment;

    @Autowired
    public JpaConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("dataSource") DataSource dataSource,
            @NotNull EntityManagerFactoryBuilder builder) {

        log.info("init entityManagerFactory");

           /* LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            return sessionFactory;*/

        return builder.dataSource(dataSource)
                .packages("com.example.springjpatest.*")
                .build();
    }


    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        log.info("init dataSource");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String url = environment.getProperty("spring.datasource.url");
        String username = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");
        String driverClassName = environment.getProperty("spring.datasource.driverClassName", "org.postgresql.Driver");

        if (username == null || username.isBlank()) {
            throw new RuntimeException("Error Property spring.datasource.username isEmpty");
        } else if (password == null || password.isBlank()) {
            throw new RuntimeException("Error Property spring.datasource.password isEmpty");
        } else if (url == null || url.isBlank()) {
            throw new RuntimeException("Error Property spring.datasource.url isEmpty");
        }

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(username);

        return dataSource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory")
            @NotNull LocalContainerEntityManagerFactoryBean entityManagerFactory) {

        log.info("init transactionManager");
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }

}