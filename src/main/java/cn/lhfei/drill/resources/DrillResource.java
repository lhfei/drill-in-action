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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.netty.buffer.ByteBuf;

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
	public ArrayNode getFiles(@RequestParam Integer limit) throws ClassNotFoundException, SQLException {
		LOG.debug(MARKER, "getFiles method execute ...");
		//String sql = "SELECT * FROM dfs.`/user/druid/benchmark/data/lineitem.tbl` LIMIT ?";
		String sql = "SELECT * FROM hive.benchmark.lineitem LIMIT ?";
		
		List<String> result = new ArrayList<>();
		
		Class.forName("org.apache.drill.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:drill:zk=host-10-182-60-8:2181,host-10-182-60-113:2181,host-10-182-60-142:2181,host-10-182-60-149:2181/drill/drillbits-li");
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, limit);
		
		ResultSet rs = ps.executeQuery(sql);
		
		ObjectMapper mapper = new ObjectMapper();

		ArrayNode items = mapper.createArrayNode();
		
		
		while(rs.next()){
			LOG.debug(MARKER, rs.getString(1));
			
			ObjectNode item = mapper.createObjectNode();
			
			item.put("l_orderkey"     , rs.getString("l_orderkey"));     
			item.put("l_partkey"      , rs.getString("l_partkey"));      
			item.put("l_suppkey"      , rs.getString("l_suppkey"));      
			item.put("l_linenumber"   , rs.getString("l_linenumber"));   
			item.put("l_quantity"     , rs.getString("l_quantity"));     
			item.put("l_extendedprice", rs.getString("l_extendedprice"));
			item.put("l_discount"     , rs.getString("l_discount"));     
			item.put("l_tax"          , rs.getString("l_tax"));          
			item.put("l_returnflag"   , rs.getString("l_returnflag"));   
			item.put("l_linestatus"   , rs.getString("l_linestatus"));   
			item.put("l_shipdate"     , rs.getString("l_shipdate"));     
			item.put("l_commitdate"   , rs.getString("l_commitdate"));   
			item.put("l_receiptdate"  , rs.getString("l_receiptdate"));  
			item.put("l_shipinstruct" , rs.getString("l_shipinstruct")); 
			item.put("l_shipmode"     , rs.getString("l_shipmode"));     
			item.put("l_comment"      , rs.getString("l_comment"));      
			
			items.add(item);
		}
		
		/*jdbcTemplate.query(sql, new Object[] {limit}, new RowMapper<List<String>>() {

			@Override
			public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				while(rs.next()){
					result.add(rs.getString(1));
				}
				return result;
			}
		});*/
		
		return items;
	}
	
//	@Autowired
	private JdbcTemplate jdbcTemplate;
}
