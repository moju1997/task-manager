package com.jazasoft.taskmanager

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.sql.Sql
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.FileSystemResourceAccessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class BaseISpec extends Specification {
    @Autowired
    protected MockMvc mvc;

    @Shared
    ObjectMapper mapper = new ObjectMapper();

//    @Autowired
//    RestTemplate restTemplate;

    protected String contentTypeJson = "application/json;charset=UTF-8";

    @Shared
    Sql sql

    def setupSpec() {

//        def url = "jdbc:h2:mem:task_manager;DB_CLOSE_ON_EXIT=FALSE"
//        def user = "sa"
//        def password = ""
//        def driverClass = "org.h2.Driver"
//        def dataSource = new DriverManagerDataSource(url, user, password)
//        dataSource.setDriverClassName(driverClass);
//        def liquibase = new Liquibase("src/test/resources/db/changelog-tenant-h2.xml", new FileSystemResourceAccessor(), new JdbcConnection(dataSource.getConnection()));
//        // def liquibase = new Liquibase("src/test/resources/db.db/db-tenant-h2.xml", new FileSystemResourceAccessor(), new JdbcConnection(dataSource.getConnection()));
//
//        liquibase.update("")
    }
}
