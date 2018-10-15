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

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 10月 15, 2018
 */
public class DatasourceProperties {

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public int getC3p0MaxPoolSize() {
		return c3p0MaxPoolSize;
	}

	public void setC3p0MaxPoolSize(int c3p0MaxPoolSize) {
		this.c3p0MaxPoolSize = c3p0MaxPoolSize;
	}

	public int getC3p0MinPoolSize() {
		return c3p0MinPoolSize;
	}

	public void setC3p0MinPoolSize(int c3p0MinPoolSize) {
		this.c3p0MinPoolSize = c3p0MinPoolSize;
	}

	public int getPoolIdlePeriod() {
		return poolIdlePeriod;
	}

	public void setPoolIdlePeriod(int poolIdlePeriod) {
		this.poolIdlePeriod = poolIdlePeriod;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

	public int getAcquireIncrement() {
		return acquireIncrement;
	}

	public void setAcquireIncrement(int acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}

	public int getMaxStatements() {
		return maxStatements;
	}

	public void setMaxStatements(int maxStatements) {
		this.maxStatements = maxStatements;
	}

	public int getMaxStatementsPerConnection() {
		return maxStatementsPerConnection;
	}

	public void setMaxStatementsPerConnection(int maxStatementsPerConnection) {
		this.maxStatementsPerConnection = maxStatementsPerConnection;
	}

	public int getNumHelperThreads() {
		return numHelperThreads;
	}

	public void setNumHelperThreads(int numHelperThreads) {
		this.numHelperThreads = numHelperThreads;
	}

	public int getPropertyCycle() {
		return propertyCycle;
	}

	public void setPropertyCycle(int propertyCycle) {
		this.propertyCycle = propertyCycle;
	}

	private String jdbcUrl;
	private String username;
	private String password;
	private String driverClassName;
	private int c3p0MaxPoolSize;
	private int c3p0MinPoolSize;
	private int poolIdlePeriod;
	private int maxIdleTime;
	private int initialPoolSize;
	private int acquireIncrement;
	private int maxStatements;
	private int maxStatementsPerConnection;
	private int numHelperThreads;
	private int propertyCycle;
	
}
