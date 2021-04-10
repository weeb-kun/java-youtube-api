package com.weebkun.api;

import com.weebkun.api.net.ActivityResponse;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * represents an activity.<br>
 * an activity is an action that a channel or user has taken on Youtube.
 */
public class Activity {
    public String kind;
    public String etag;
    public String id;
    public Snippet snippet;
    public Content contentDetails;

    public static List<Activity> getActivities(String channelId) {
        return getActivities(channelId, new String[]{"contentDetails", "id", "snippet"});
    }

    public static List<Activity> getActivities(String channelId, String[] part) {
        Request req = new Request.Builder().url(Client.root + "/activities?" +
                String.format("channelId=%s&" +
                        "part=%s", channelId, String.join("%2C", part))).build();
        try(Response response = Client.getClient().newCall(req).execute()) {
            ActivityResponse resp = Client.moshi.adapter(ActivityResponse.class).fromJson(response.body().source());
            return resp.items;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Activity> getActivities(String channelId, Content.Optional params) {
        return getActivities(channelId, new String[]{"contentDetails", "id", "snippet"}, params);
    }

    public static List<Activity> getActivities(String channelId, String[] part, Content.Optional params) {
        Request req = new Request.Builder().url(Client.root + "/activities?" +
                String.format("channelId=%s&" +
                        "part=%s"
                        .concat(params.maxResults > 0 ? "&maxResults=" + params.maxResults : "")
                        .concat(params.pageToken != null ? "&pageToken=" + params.pageToken : "")
                        .concat(params.publishedAfter != null ? "&publishedAfter=" + params.publishedAfter.replaceAll(":", "%3A") : "")
                        .concat(params.publishedBefore != null ? "&publishedBefore=" + params.publishedBefore.replaceAll(":", "%3A") : "")
                        .concat(params.regionCode != null ? "&regionCode=" + params.regionCode : "")
                        , channelId, String.join("%2C", part))).build();
        try(Response response = Client.getClient().newCall(req).execute()) {
            ActivityResponse resp = Client.moshi.adapter(ActivityResponse.class).fromJson(response.body().source());
            return resp.items;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Activity> getMyActivities(){
        return getMyActivities(new String[]{"contentDetails", "id", "snippet"});
    }

    public static List<Activity> getMyActivities(String[] part) {
        Request req = new Request.Builder().url(Client.root + "/activities?mine=true&" +
                String.format("part=%s", String.join("%2C", part))).build();
        try(Response response = Client.getClient().newCall(req).execute()) {
            ActivityResponse resp = Client.moshi.adapter(ActivityResponse.class).fromJson(response.body().source());
            return resp.items;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Activity> getMyActivities(Content.Optional params) {
        return getMyActivities(new String[]{"contentDetails", "id", "snippet"}, params);
    }

    public static List<Activity> getMyActivities(String[] part, Content.Optional params){
        Request req = new Request.Builder().url(Client.root + "/activities?mine=true&" +
                String.format("part=%s"
                        .concat(params.maxResults > 0 ? "&maxResults=" + params.maxResults : "")
                        .concat(params.pageToken != null ? "&pageToken=" + params.pageToken : "")
                        .concat(params.publishedAfter != null ? "&publishedAfter=" + params.publishedAfter.replaceAll(":", "%3A") : "")
                        .concat(params.publishedBefore != null ? "&publishedBefore=" + params.publishedBefore.replaceAll(":", "%3A") : "")
                        .concat(params.regionCode != null ? "&regionCode=" + params.regionCode : ""), String.join("%2C", part))).build();
        try(Response response = Client.getClient().newCall(req).execute()) {
            ActivityResponse resp = Client.moshi.adapter(ActivityResponse.class).fromJson(response.body().source());
            return resp.items;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class Snippet {
        public String publishedAt;
        public String channelId;
        public String title;
        public String description;
        public Map<String, Thumbnail> thumbnails;
        public String channelTitle;
        public String type;
        public String groupId;
    }

    static class Content {
        public Upload upload;
        public Like like;
        public Favourite favorite;
        public Comment comment;
        public Subscription subscription;
        public PlaylistItem playlistItem;
        public Recommendation recommendation;
        public Social social;
        public ChannelItem channelItem;

        static class Upload {
            public String videoId;
        }

        static class Like {
            public Resource resourceId;
        }

        static class Favourite {
            public Resource resourceId;
        }

        static class Comment {
            public Resource resourceId;
        }

        static class Subscription {
            public Resource resourceId;
        }

        static class PlaylistItem {
            public Resource resourceId;
            public String playlistId;
            public String playlistItemId;
        }

        static class Recommendation {
            public Resource resourceId;
            public String reason;
            public Resource seedResourceId;
        }

        static class Social {
            public String type;
            public Resource resourceId;
            public String author;
            public String referenceUrl;
            public String imageUrl;
        }

        static class ChannelItem {
            public Resource resourceId;
        }

        static class Resource {
            public String kind;
            public String videoId;
            public String channelId;
            public String playlistId;
        }

        /**
         * optional query params for get /activities
         */
        static class Optional {
            public int maxResults;
            public String pageToken;
            public String publishedAfter;
            public String publishedBefore;
            public String regionCode;
        }
    }
}
