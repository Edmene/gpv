package app.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class Db {
    private static DataSource dataSource = null;

    private Db(){
        if(dataSource == null) {
            BasicDataSource bds = new BasicDataSource();
            bds.setDriverClassName("org.postgresql.Driver");
            bds.setUrl("jdbc:postgresql://172.17.0.3:5432/gpv");
            bds.setUsername("postgres");
            bds.setPassword("postgres");
            dataSource = bds;
        }
    }

    public static DataSource getInstance(){
        if(dataSource == null) {
            new Db();
            return dataSource;
        }
        else{
            return dataSource;
        }
    }
}
