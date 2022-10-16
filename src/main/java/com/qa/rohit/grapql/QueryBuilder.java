package com.qa.rohit.grapql;

import com.qa.rohit.enums.ApiBase;
import com.qa.rohit.enums.ApiStatus;
import com.qa.rohit.enums.QueryFilePath;
import com.qa.rohit.exceptions.NoSuchQueryException;
import com.qa.rohit.grapql.pojomodels.GraphQLResponse;
import com.qa.rohit.restclient.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QueryBuilder extends PostRequest {
    protected RequestBodyPojo body;

    /**
     *
     * @param path relative file path to json file containing query for this object
     * @param queryId unique id of query in json file specified by above path
     * @throws IOException if no file found in above path
     * @throws NoSuchQueryException if no query found in json file specified by above path
     */
    public QueryBuilder(QueryFilePath path, String queryId) throws IOException, NoSuchQueryException {
        JSONObject query = new JSONObject(Files.readString(Paths.get(System.getProperty("user.dir") + path.getRelPath())));
        if (!query.has(queryId)) throw new NoSuchQueryException(queryId, path.getRelPath());
        body = new RequestBodyPojo(query.getString(queryId));
    }

    @Override
    public String base() {
        return ApiBase.RICK_MORTY_SERVER.getHostName();
    }

    @Override
    public String resource() {
        return "/";
    }

    @Override
    public QueryParams queryParams() {
        return null;
    }

    @Override
    public Cookie cookie() {
        return null;
    }

    @Override
    public <T extends Response> Class<T> getResponsePojoClass() {
        return (Class<T>) GraphQLResponse.class;
    }

    @Override
    public RequestBody body() {
        return body;
    }

    @Override
    public int getStatusCodeForHappyPath(){
        return ApiStatus._200_OK.getStatusCode();
    }

    /*========================================Request Body Pojo Below========================================*/

    public static class RequestBodyPojo implements RequestBody  {

        private final String query;
        private final Map<String,Object> variables;

        /**
         *
         * @param query graphql query to fire
         */
        RequestBodyPojo(String query) {
            this.query = query;
            this.variables = new HashMap<>();
        }

        /**
         *
         * @param key name of variable
         * @param value value of variable
         */
        public void addVariable(String key, Object value) {
            variables.put(key, value);
        }

        @Override
        public RequestBody body() {
            return this;
        }
    }

}
