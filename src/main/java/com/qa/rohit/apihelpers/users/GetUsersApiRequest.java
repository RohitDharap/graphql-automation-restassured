package com.qa.rohit.apihelpers.users;

import com.qa.rohit.restclient.Cookie;
import com.qa.rohit.restclient.GetRequest;
import com.qa.rohit.restclient.QueryParams;
import com.qa.rohit.restclient.Response;
import com.qa.rohit.enums.ApiBase;
import com.qa.rohit.restclient.pojomodels.Datum;
import com.qa.rohit.restclient.pojomodels.Support;

import java.util.List;

public class GetUsersApiRequest extends GetRequest {
    public static final int DEFAULT_PER_PAGE_USER_COUNT = 6;
    public GetUsersApiRequest() {
        resource = "/users";
    }

    public GetUsersApiRequest(QueryParams queryParams) {
        this();
        this.queryParams = queryParams;
    }

    @Override
    public String base() {
        return ApiBase.REQ_RES_SERVER.getHostName();
    }

    @Override
    public String resource() {
        return resource;
    }

    @Override
    public QueryParams queryParams() {
        return queryParams;
    }

    @Override
    public Cookie cookie() {
        return null;
    }

    @Override
    public <T extends Response> Class<T> getResponsePojoClass() {
        return (Class<T>) ResponsePojo.class;
    }

    /*========================================Response Body Pojo Below========================================*/

    public static class ResponsePojo implements Response {
        private Integer page, per_page, total, total_pages;
        private List<Datum> data;
        private Support support;

        public Integer getPage() {
            return page;
        }

        public Integer getPer_page() {
            return per_page;
        }

        public Integer getTotal() {
            return total;
        }

        public Integer getTotal_pages() {
            return total_pages;
        }

        public List<Datum> getData() {
            return data;
        }

        public Support getSupport() {
            return support;
        }

        public int indexOfId(Integer id) {
            if (id == null || id < 0) return -1;
            for (int i = 0; i < data.size(); i++)
                if (data.get(i).id.equals(id)) return i;
            return -1;
        }
    }

    /*========================================Abstracted Support methods Below========================================*/

    /**
     * @param page page num
     * @param perPage limit records to
     * @param id id to search for
     * @return Datum object if given id found in page/page num constrains, else null
     */
    public Datum getDataHavingId(Integer page, Integer perPage, Integer id) {
        ResponsePojo resp = new GetUsersApiRequest(new QueryParams().append("per_page", String.valueOf(perPage)).append("page", String.valueOf(page))).
                hitAndGetPojoResponse();
        int index = resp.indexOfId(id);
        return index == -1 ? null : resp.data.get(index);
    }
}
