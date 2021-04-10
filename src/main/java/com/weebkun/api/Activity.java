package com.weebkun.api;

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

    static class Snippet {
        public String publishedAt;
        public String channelId;
        public String title;
        public String description;
        public Map<String, Thumbnail> thumbnails;
        public String channelTitle;
        public String type;
        public String groupId;

        static class Thumbnail {
            public String url;
            public int width;
            public int height;
        }
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
    }
}
