package com.weebkun.api.net;

import com.weebkun.api.Activity;

import java.util.List;

/**
 * json response for a GET /activities call.
 */
public class ActivityResponse {
    public String kind;
    public String etag;
    public String nextPageToken;
    public String prevPageToken;
    public PageInfo pageInfo;
    public List<Activity> items;

    static class PageInfo {
        public int totalResults;
        public int resultsPerPage;
    }
}
