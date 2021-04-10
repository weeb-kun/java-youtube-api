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

    /**
     * gets a channel's activities.<br>
     * same as {@link #getActivities(String, String[])} with all parts returned.
     * @param channelId the channel id
     * @return the list of activities of the specified channel
     */
    public static List<Activity> getActivities(String channelId) {
        return getActivities(channelId, new String[]{"contentDetails", "id", "snippet"});
    }

    /**
     * gets a channel's activities.<br>
     * the part parameter indicates the parts of the activities that the api will return.
     * the parts you can request are: contentDetails, id, and snippet
     * @param channelId the channel id
     * @param part the parts of the activity you are requesting
     * @return the list of activities of the specified channel
     */
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

    /**
     * gets a channel's activities.<br>
     * same as {@link #getActivities(String, String[], Optional)} with all parts returned.
     * @param channelId the channel id
     * @param params the optional params
     * @return the list of activities
     */
    public static List<Activity> getActivities(String channelId, Optional params) {
        return getActivities(channelId, new String[]{"contentDetails", "id", "snippet"}, params);
    }

    /**
     * gets a channel's activities.<br>
     * the parts and optional parameters are passed to this method.
     * see {@link Optional} for more details.
     * @param channelId the channel id
     * @param part the parts to be returned
     * @param params the optional params
     * @return the list of activities
     */
    public static List<Activity> getActivities(String channelId, String[] part, Optional params) {
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

    /**
     * get the authenticated user's activities
     * @return the list of activities
     */
    public static List<Activity> getMyActivities(){
        return getMyActivities(new String[]{"contentDetails", "id", "snippet"});
    }

    /**
     * get the authenticated user's activities
     * @param part the parts to return
     * @return the list of activities
     */
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

    /**
     * get the authenticated user's activities.<br>
     * same as {@link #getMyActivities(String[], Optional)} with all parts returned.
     * @param params the optional params
     * @return the list of activities
     */
    public static List<Activity> getMyActivities(Optional params) {
        return getMyActivities(new String[]{"contentDetails", "id", "snippet"}, params);
    }

    /**
     * get the authenticated user's activities.<br>
     * specify the parts of the activities to return and the optional params.
     * @param part the parts to return
     * @param params the optional params
     * @return the list of activities
     */
    public static List<Activity> getMyActivities(String[] part, Optional params){
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
        /**
         * the user uploaded a video
         */
        public Upload upload;
        /**
         * a resource received a like
         */
        public Like like;
        /**
         * a video was marked as favorite
         */
        public Favourite favorite;
        /**
         * the user commented on a video or channel
         */
        public Comment comment;
        /**
         * the user subscribed to a channel
         */
        public Subscription subscription;
        /**
         * a new item was added to a playlist
         */
        public PlaylistItem playlistItem;
        /**
         * a video or channel was recommended
         */
        public Recommendation recommendation;
        /**
         * a social network post was posted
         */
        public Social social;
        /**
         * a resource was added to the channel
         */
        public ChannelItem channelItem;

        static class Upload {
            /**
             * the uploaded video id
             */
            public String videoId;
        }

        static class Like {
            /**
             * the associated resource information
             */
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
