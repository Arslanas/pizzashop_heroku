package pizzaShop.config.mvcConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"pizzaShop"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"pizzaShop"})
@PropertySource(value = "classpath:prod.properties")
public class RootConfig {

    @Autowired
    Environment env;

    @Bean(name = "dataSource")
    @Profile("prod")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setUrl(env.getProperty("db.url"));
        return dataSource;
    }
    @Bean(name = "dataSource", destroyMethod = "shutdown")
    @Profile("test")
    public DataSource dataSourceForTest(){
        return  new EmbeddedDatabaseBuilder().
                generateUniqueName(true).
                setType(EmbeddedDatabaseType.H2).
                setScriptEncoding("UTF-8").
                ignoreFailedDrops(true).
                addScript("my-schema.sql").
                addScripts("my-data.sql").
                build();
    }

    @Bean(name = "jpaVendorAdapter")
    @Profile("prod")
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setShowSql(true);
        return adapter;
    }
    @Bean(name = "jpaVendorAdapter")
    @Profile("test")
    public JpaVendorAdapter jpaVendorAdapterForTest(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        adapter.setShowSql(true);
        return adapter;
    }

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory( JpaVendorAdapter adapter, DataSource dataSource){
        Properties properties = new Properties();
        properties.setProperty("hibernate.format_sql", String.valueOf(true));
        properties.setProperty("hibernate.connection.CharSet","utf8");
        properties.setProperty("hibernate.connection.characterEncoding","utf8");
        properties.setProperty("hibernate.connection.useUnicode", String.valueOf(true));
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaProperties(properties);
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("pizzaShop");
        return emf;
    }


    @Bean(name = "transactionManager")
    @Profile("prod")
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }

    @Bean(name = "transactionManager")
    @Profile("test")
    public PlatformTransactionManager transactionManagerForTest(){
        return new DataSourceTransactionManager(dataSourceForTest());
    }

    @Bean
    public BeanPostProcessor persistenceTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
