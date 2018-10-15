/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.drill.config;

import java.beans.PropertyVetoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 10月 15, 2018
 */
@Configuration
public class DatasourceConfig {

private Logger LOG = LoggerFactory.getLogger(DatasourceConfig.class);
	
	@Bean	
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource("ck-111");

        try {
            dataSource.setDriverClass(dsPros.getDriverClassName());
        } catch (PropertyVetoException pve){
			LOG.debug("Cannot load datasource driver ({)} : {}", dsPros.getJdbcUrl(), pve.getMessage());
            return null;
        }
        dataSource.setJdbcUrl(dsPros.getJdbcUrl());
        dataSource.setUser(dsPros.getUsername());
        dataSource.setPassword(dsPros.getPassword());
        dataSource.setMinPoolSize(dsPros.getC3p0MinPoolSize());
        dataSource.setMaxPoolSize(dsPros.getC3p0MaxPoolSize());
        dataSource.setInitialPoolSize(dsPros.getInitialPoolSize());
        dataSource.setMaxIdleTime(dsPros.getMaxIdleTime());
        dataSource.setAcquireIncrement(dsPros.getAcquireIncrement());
        dataSource.setMaxStatements(dsPros.getMaxStatements());
        dataSource.setMaxStatementsPerConnection(dsPros.getMaxStatementsPerConnection());
        dataSource.setNumHelperThreads(dsPros.getNumHelperThreads());
        dataSource.setPropertyCycle(dsPros.getPropertyCycle());

        return dataSource;
    }
	
	@Autowired
	private DatasourceProperties dsPros;
}
