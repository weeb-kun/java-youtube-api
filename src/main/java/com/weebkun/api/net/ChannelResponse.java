package com.weebkun.api.net;

import com.weebkun.api.Channel;

import java.util.List;

/**
 * the http response returned from GET /channels
 */
public class ChannelResponse {
    public String kind;
    public String etag;
    public String nextPageToken;
    public String prevPageToken;
    public PageInfo pageInfo;
    public List<Channel> items;
}
