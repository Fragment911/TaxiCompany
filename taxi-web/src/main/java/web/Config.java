package web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages={"dao.repository"})
@ComponentScan(basePackages = {"api", "dao", "services"})
@PropertySource("classpath:application.properties")
public class Config {

    @Resource
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        Properties prop = new Properties();
        prop.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernat.dialect"));
        prop.put("hibernate.connection.url", environment.getRequiredProperty("spring.datasource.url"));
        prop.put("hibernate.connection.username", environment.getRequiredProperty("spring.datasource.username"));
        prop.put("hibernate.connection.password", environment.getRequiredProperty("spring.datasource.password"));
        prop.put("hibernate.connection.driver_class", environment.getRequiredProperty("spring.datasource.driver"));
        prop.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(prop);
        em.setPackagesToScan("api.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
}

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
