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

package cn.lhfei.drill.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import cn.lhfei.drill.web.model.QueryResult;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 10月 15, 2018
 */
@Service
public class QueryWrapper {

	public QueryResult query(String sql, Object[] params) throws ClassNotFoundException, SQLException {
		QueryResult result = new QueryResult();

		boolean[] isFirstRow = { true };
		jdbcTemplate.query(sql, params, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				int colsCount = rs.getMetaData().getColumnCount();
				if (isFirstRow[0]) {
					for (int i = 1; i <= colsCount; i++) {
						result.getColumns().add(rs.getMetaData().getColumnName(i));
						result.getMetaData().getColumnLabels().add(rs.getMetaData().getColumnLabel(i));
					}
				}

				List<String> row = new ArrayList<>();
				for(int i = 1; i <= colsCount; i++) {// eval all columns
					row.add(rs.getString(i));
				}
				
				result.getRows().add(row);

				isFirstRow[0] = false;
			}
		});

		return result;
	}
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
}
