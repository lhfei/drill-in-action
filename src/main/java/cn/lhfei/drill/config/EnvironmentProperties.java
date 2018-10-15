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
 * @Created Oct. 15, 2018
 */
public class EnvironmentProperties {

	public ParameterProperties getParams() {
		return params;
	}

	public void setParams(ParameterProperties params) {
		this.params = params;
	}

	public DatasourceProperties getDatasource() {
		return datasource;
	}

	public void setDatasource(DatasourceProperties datasource) {
		this.datasource = datasource;
	}

	public int getDrillServerPort() {
		return drillServerPort;
	}

	public void setDrillServerPort(int drillServerPort) {
		this.drillServerPort = drillServerPort;
	}

	public String getDrillServerProtocol() {
		return drillServerProtocol;
	}

	public void setDrillServerProtocol(String drillServerProtocol) {
		this.drillServerProtocol = drillServerProtocol;
	}

	public String[] getDrillServers() {
		return drillServers;
	}

	public void setDrillServers(String[] drillServers) {
		this.drillServers = drillServers;
	}

	private ParameterProperties params;
	private DatasourceProperties datasource;
	private int drillServerPort;
	private String drillServerProtocol;
	private String[] drillServers;

}
