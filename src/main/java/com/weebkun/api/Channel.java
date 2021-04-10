package com.weebkun.api;

import java.util.List;
import java.util.Map;

public class Channel {
    public String kind;
    public String etag;
    public String id;
    public Snippet snippet;
    public Content contentDetails;
    public Statistics statistics;
    public Topics topicDetails;
    public Status status;
    public Branding brandingSettings;
    public Audit auditDetails;


    static class Snippet {
        public String title;
        public String description;
        public String customUrl;
        public String publishedAt;
        public Map<String, Thumbnail> thumbnails;
        public String defaultLanguage;
        public LocalisedLanguage localized;
        public String country;

        static class LocalisedLanguage {
            public String title;
            public String description;
        }
    }

    static class Content {
        public RelatedPlaylists relatedPlaylists;

        static class RelatedPlaylists {
            public String likes;
            public String favorites;
            public String uploads;
        }
    }

    static class Statistics {
        public long viewCount;
        public long subscriberCount;
        public boolean hiddenSubscriberCount;
        public long videoCount;
    }

    static class Topics {
        public List<String> topicIds;
        public List<String> topicCategories;
    }

    static class Status {
        public String privacyStatus;
        public boolean isLinked;
        public String longUploadsStatus;
        public boolean madeForKids;
        public boolean selfDeclaredMadeForKids;
    }

    static class Branding {
        public ChannelBranding channel;

        static class ChannelBranding {
            public String title;
            public String description;
            public String keywords;
            public String defaultTab;
            public String trackingAnalyticsAccountId;
            public boolean moderateComments;
            public boolean showRelatedChannels;
            public boolean showBrowseView;
            public String featuredChannelsTitle;
            public List<String> featuredChannelUrls;
            public String unsubscribedTrailer;
            public String profileColor;
            public String defaultLanguage;
            public String country;
        }
    }

    static class Audit {
        public boolean overallGoodStanding;
        public boolean communityGuidelinesGoodStanding;
        public boolean copyrightStrikesGoodStanding;
        public boolean contentIdClaimsGoodStanding;
    }

    static class ContentOwner {
        public String contentOwner;
        public String timeLinked;
        public Map<String, Localisation> localizations;

        static class Localisation {
            public String title;
            public String description;
        }
    }
}
