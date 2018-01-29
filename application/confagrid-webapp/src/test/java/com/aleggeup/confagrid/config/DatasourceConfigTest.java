/*
 * DatasourceConfigTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

@RunWith(MockitoJUnitRunner.class)
public class DatasourceConfigTest {

    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String URL = "url";

    @Mock
    private DataSourceProperties mockDataSourceProperties;

    private DatasourceConfig datasourceConfig;

    @Before
    public void setUp() {
        datasourceConfig = new DatasourceConfig();
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(mockDataSourceProperties);
    }

    @Test
    public void test() {
        Mockito.when(mockDataSourceProperties.getDriverClassName()).thenReturn(DRIVER_CLASS_NAME);
        Mockito.when(mockDataSourceProperties.getUsername()).thenReturn(USERNAME);
        Mockito.when(mockDataSourceProperties.getPassword()).thenReturn(PASSWORD);
        Mockito.when(mockDataSourceProperties.getUrl()).thenReturn(URL);
        datasourceConfig.datasource(mockDataSourceProperties);

        Mockito.verify(mockDataSourceProperties).getDriverClassName();
        Mockito.verify(mockDataSourceProperties).getUsername();
        Mockito.verify(mockDataSourceProperties).getPassword();
        Mockito.verify(mockDataSourceProperties).getUrl();
    }
}
