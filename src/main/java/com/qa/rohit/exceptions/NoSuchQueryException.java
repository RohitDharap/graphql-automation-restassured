package com.qa.rohit.exceptions;

public class NoSuchQueryException extends Exception {

    /**
     * Thrown when given qeury id does not exist as key in json file specified by given file path
     * @param queryId queryId which is unique for given file (but not required to be unique across different files)
     * @param filePath path to json file having actual query for above id
     */

    public NoSuchQueryException(String queryId, String filePath){
        super("query id : " + queryId + " does not exist in file : " + filePath);
    }
}
