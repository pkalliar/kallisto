package com.pankal.commons.service;

import com.pankal.commons.DBUtilities;
import com.pankal.service.ServiceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
@EnableConfigurationProperties(DBServiceProperties.class)
public class DBService {

    private final DBServiceProperties serviceProperties;

    public DBService(DBServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public String message() {
        return this.serviceProperties.getUrl();
    }

    public boolean connect() throws SQLException, IOException, ClassNotFoundException {

        return DBUtilities.connect(this.serviceProperties.getUrl(),
                this.serviceProperties.getUsername(), this.serviceProperties.getPassword());
    }
}
