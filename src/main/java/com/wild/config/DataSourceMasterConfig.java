//package com.wild.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "com.wild.mapper.master", sqlSessionFactoryRef = "masterSqlSessionFactory")
//public class DataSourceMasterConfig {
//
//    @Primary
//    @Bean(name = "masterDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource masterDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
////    @Value("mybatis.mapper-locations")
////    private String mapperLocations;
////    @Value("${mybatis.type-aliases-package}")
////    private String typeAliasesPackage;
//
//    @Primary
//    @Bean(name = "masterSqlSessionFactory")
//    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(masterDataSource);
//        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
//        sessionFactory.setMapperLocations(resourcePatternResolver.getResources("classpath: **/**/mapper/**/*.xml"));
////        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
//
//        return sessionFactory.getObject();
//    }
//
//    @Primary
//    @Bean(name = "masterTransactionManager")
//    public DataSourceTransactionManager masterTransactionManager() {
//
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(); //定义一个某个框架平台的TransactionManager，如JDBC、Hibernate
//        dataSourceTransactionManager.setDataSource(masterDataSource()); // 设置数据源
//        DefaultTransactionDefinition transDef = new DefaultTransactionDefinition(); // 定义事务属性
//        transDef.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED); // 设置传播行为属性
//        TransactionStatus status = dataSourceTransactionManager.getTransaction(transDef); // 获得事务状态
//        try {
//            // 数据库操作
//            dataSourceTransactionManager.commit(status);// 提交
//        } catch (Exception e) {
//            dataSourceTransactionManager.rollback(status);// 回滚
//        }
//
//        return dataSourceTransactionManager;
//    }
//
//
//
//}
