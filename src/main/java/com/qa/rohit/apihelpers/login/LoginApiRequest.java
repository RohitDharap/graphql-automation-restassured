package com.qa.rohit.apihelpers.login;

import com.qa.rohit.restclient.*;
import com.qa.rohit.enums.ApiBase;
import com.qa.rohit.enums.ApiStatus;

public class LoginApiRequest extends PostRequest {
    RequestBody body;

    public LoginApiRequest(String username, String email, String password) {
        body = new RequestBodyPojo(username, email, password);
    }

    @Override
    public String base() {
        return ApiBase.REQ_RES_SERVER.getHostName();
    }

    @Override
    public String resource() {
        return "/login";
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
        return (Class<T>) ResponsePojo.class;
    }

    @Override
    public RequestBody body() {
        return body.body();
    }

    /**
     * Ideally for post request successful request should return 201. However,
     * This particular server does not allow successful post call to avoid multiple data entries from being created.
     * Hence, for demo purpose, below method is overriden to actually return 400
     * @return 400
     */
    @Override
    public int getStatusCodeForHappyPath(){
        return ApiStatus._400_BAD_REQUEST.getStatusCode();
    }

    /*========================================Request Body Pojo Below========================================*/

    public static class RequestBodyPojo implements RequestBody {
        private String username, email, password;

        public RequestBodyPojo(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        @Override
        public RequestBody body() {
            return this;
        }

    }

    /*========================================Response Body Pojo Below========================================*/

    public static class ResponsePojo implements Response {
        public String getToken() {
            return token;
        }

        private String token;
    }

}
