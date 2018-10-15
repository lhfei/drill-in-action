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

package cn.lhfei.drill.web.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lhfei.drill.web.model.QueryModel;
import cn.lhfei.drill.web.model.QueryResult;
import cn.lhfei.drill.wrapper.QueryWrapper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created on Jun 27, 2018
 */
@RestController
@RequestMapping("/")
public class DrillResource extends AbstractResource {
	
	@RequestMapping(value = "/query", method = POST)
	public QueryResult query(@RequestBody QueryModel queryModel) throws ClassNotFoundException, SQLException {
		
		LOG.info("Query: {}", queryModel);
		
		QueryResult result = queryWrapper.query(queryModel.getQuery(), queryModel.getParams());
		
		return result;
	}
	
	@RequestMapping(value = "/databases", method = GET)
	public QueryResult getDatabases() throws ClassNotFoundException, SQLException {
		final String sql = "SHOW DATABASES";
		
		return queryWrapper.query(sql, null);
	}
	
	@RequestMapping(value = "/database/{database}/tables", method = GET)
	public QueryResult getTables(@PathVariable("") String database) throws ClassNotFoundException, SQLException {
		final String sql = "SHOW TABLES IN ?";
		
		return queryWrapper.query(sql, new Object[] {database});
	}
	
	@Autowired
	private QueryWrapper queryWrapper;
}