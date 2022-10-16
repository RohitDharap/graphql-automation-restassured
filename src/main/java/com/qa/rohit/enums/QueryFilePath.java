package com.qa.rohit.enums;

public enum QueryFilePath {
    //Add your graphql queries related to episode data in below file
    QUERY_EPISODE_DATA("src/main/java/com/qa/rohit/grapql/queries/QUERY_EPISODE_DATA.json");
    private String relPath;
    QueryFilePath(String relPath) {
        this.relPath = relPath;
    }

    public String getRelPath(){
        return relPath.startsWith("/") ? relPath : "/" + relPath;
    }
}
