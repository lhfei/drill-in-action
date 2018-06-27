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

package cn.lhfei.drill.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created on Jun 27, 2018
 */
@RestController
@RequestMapping("/drill")
public class DrillResource extends AbstractResource {
	
	@RequestMapping(value = "/files", method = GET)
	public String getFiles() throws ClassNotFoundException, SQLException {
		LOG.debug(MARKER, "getFiles method execute ...");
		
		Class.forName("org.apache.drill.jdbc.Driver");
		Connection connection =DriverManager.getConnection("jdbc:drill:zk=10.182.60.8:2181,10.182.60.113:2181,10.182.60.142:2181,10.182.60.149:2181/drill/drillbits-li");
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM dfs.`/user/druid/benchmark/data/lineitem.tbl` LIMIT 20");
		while(rs.next()){
			LOG.debug(MARKER, rs.getString(1));
		}
		
		return "{}";
	}
}
