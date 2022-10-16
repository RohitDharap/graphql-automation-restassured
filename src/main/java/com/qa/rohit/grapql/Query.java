package com.qa.rohit.grapql;

import com.qa.rohit.enums.QueryFilePath;
import com.qa.rohit.exceptions.NoSuchQueryException;

import java.io.IOException;

public class Query extends QueryBuilder {
    private static final String DEFAULT_QUERY_ID = "1";

    /**
     *
     * @param path relative file path to json file containing query for this object
     * @throws IOException if no file found in above path
     * @throws NoSuchQueryException if no query found in json file specified by above path
     */
    public Query(QueryFilePath path) throws IOException, NoSuchQueryException {
        this(path, DEFAULT_QUERY_ID);
    }

    /**
     *
     * @param path relative file path to json file containing query for this object
     * @param queryId unique id of query in json file specified by above path
     * @throws IOException if no file found in above path
     * @throws NoSuchQueryException if no query found in json file specified by above path
     */
    public Query(QueryFilePath path, String queryId) throws IOException, NoSuchQueryException {
        super(path, queryId);
    }

    /**
     *
     * @param key name of variable
     * @param value value of variable
     * @return instance of object (for method-chaining)
     */
    public Query addVariable(String key, Object value){
        ((RequestBodyPojo) body()).addVariable(key, value);
        return this;
    }

}
