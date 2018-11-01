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

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squareup.moshi.Moshi;

import cn.lhfei.drill.config.EnvironmentProperties;
import cn.lhfei.drill.config.constant.DrillOperationEnum;
import cn.lhfei.drill.web.model.QueryModel;
import cn.lhfei.drill.web.model.QueryResult;
import cn.lhfei.drill.wrapper.QueryWrapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created on Jun 27, 2018
 */
@RestController
@RequestMapping(value = "/")
public class DrillResource extends AbstractResource {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final Moshi MOSHI = new Moshi.Builder().build();
	
	
	@RequestMapping(value = "/query2", method = POST)
	public QueryResult query2(@RequestBody QueryModel queryModel) throws ClassNotFoundException, SQLException {
		
		LOG.info("Query: {}", queryModel);
		
		QueryResult result = queryWrapper.query(queryModel.getQuery(), queryModel.getParams());
		
		return result;
	}
	
	@RequestMapping(value = "/query", method = POST)
	public String query(@RequestBody QueryModel queryModel) throws ClassNotFoundException, SQLException, IOException {
		final String json = "{\"queryType\": \"SQL\", \"query\": \"" + queryModel.getQuery() + "\"}";
		okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
		
		OkHttpClient client = buildHttpClient();
		
		LOG.info("SQL: {}", queryModel.getQuery());
		
	    // Create request for Drill remote resource.
	    Request request = new Request.Builder()
	        .url(getRandomServer(DrillOperationEnum.QUERY))
	        .post(body)
	        .build();

	    // Execute the request and retrieve the response.
		try (Response response = client.newCall(request).execute()) {
			// Deserialize HTTP response to concrete type.
			return response.body().string();
		}
	}
	
	@RequestMapping(value = "/databases", method = GET)
	public String getDatabases() throws ClassNotFoundException, SQLException, IOException {
		final String json = "{\"queryType\": \"SQL\", \"query\": \"SHOW DATABASES\"}";
		okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
		
		OkHttpClient client = new OkHttpClient();

	    // Create request for Drill remote resource.
	    Request request = new Request.Builder()
	        .url(getRandomServer(DrillOperationEnum.QUERY))
	        .post(body)
	        .build();

	    // Execute the request and retrieve the response.
		try (Response response = client.newCall(request).execute()) {
			// Deserialize HTTP response to concrete type.
			return response.body().string();
		}
	}
	
	@RequestMapping(value = "/database/{database}/tables", method = GET)
	public String getTables(@PathVariable("") String database) throws ClassNotFoundException, SQLException, IOException {
		final String json = "{\"queryType\": \"SQL\", \"query\": \"SHOW TABLES IN " + database + "\"}";
		okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
		
		OkHttpClient client = buildHttpClient();

	    // Create request for Drill remote resource.
	    Request request = new Request.Builder()
	        .url(getRandomServer(DrillOperationEnum.QUERY))
	        .post(body)
	        .build();

	    // Execute the request and retrieve the response.
		try (Response response = client.newCall(request).execute()) {
			// Deserialize HTTP response to concrete type.
			return response.body().string();
		}
	}
	
	
	@RequestMapping(value = "/database/{database}/table/{tableName}", method = GET)
	public String getTable(@PathVariable("") String database, @PathVariable("") String tableName)
			throws ClassNotFoundException, SQLException, IOException {
		String fullName = database + "." + tableName;
		final String json = "{\"queryType\": \"SQL\", \"query\": \"DESC " + fullName + "\"}";
		okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
		
		OkHttpClient client = buildHttpClient();

	    // Create request for Drill remote resource.
	    Request request = new Request.Builder()
	        .url(getRandomServer(DrillOperationEnum.QUERY))
	        .post(body)
	        .build();

	    // Execute the request and retrieve the response.
		try (Response response = client.newCall(request).execute()) {
			// Deserialize HTTP response to concrete type.
			return response.body().string();
		}
	}
	
	public String getRandomServer(DrillOperationEnum type) {
		String endpoint = "";
		int min = 0, max = envParams.getDrillServers().length - 1;
		Random random = new Random();
		StringBuilder sb = new StringBuilder(envParams.getDrillServerProtocol());
		sb.append("://");
		
		int serverIndex = random.nextInt(max - min + 1) + min;
		
		sb.append(envParams.getDrillServers()[serverIndex]);
		
		// append port number
		sb.append(":");
		sb.append(envParams.getDrillServerPort());
		
		// append drill RESTful end-point
		sb.append("/");
		sb.append(type.getType());
		
		endpoint = sb.toString();
		
		LOG.info("Query: dispatch to : {}", endpoint);
		
		return endpoint;
	}
	
	private OkHttpClient buildHttpClient() {
		OkHttpClient client = new OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.MINUTES)
		        .writeTimeout(10, TimeUnit.MINUTES)
		        .readTimeout(10, TimeUnit.MINUTES)
		        .build();
		
		return client;
	}
	
	@Autowired
	private QueryWrapper queryWrapper;
	
	@Autowired
	private EnvironmentProperties envParams;
	
	
}
