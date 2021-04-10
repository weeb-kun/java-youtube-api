package com.weebkun.api;

import java.util.List;
import java.util.Map;

/**
 * represents a youtube channel.
 * see the
 * <a href="https://developers.google.com/youtube/v3/docs/channels">youtube docs</a> for more info.
 */
public class Channel {
    /**
     * usually youtube#channel
     */
    public String kind;
    /**
     * the etag of this channel.
     */
    public String etag;
    /**
     * the unique id of this channel.
     */
    public String id;
    public Snippet snippet;
    public Content contentDetails;
    public Statistics statistics;
    public Topics topicDetails;
    public Status status;
    public Branding brandingSettings;
    public Audit auditDetails;

    /**
     * contains details about the channel.<br>
     * e.g. the channel name, description, etc
     */
    static class Snippet {
        /**
         * the title of the channel. i.e. the name
         */
        public String title;
        public String description;
        /**
         * the channel's custom url
         */
        public String customUrl;
        /**
         * when the channel was created
         */
        public String publishedAt;
        /**
         * a map containing thumbnails.
         * the keys are the thumbnail names and the values are the {@link Thumbnail} objects.
         */
        public Map<String, Thumbnail> thumbnails;
        /**
         * default language of the channel's title and description
         */
        public String defaultLanguage;
        public LocalisedLanguage localized;
        /**
         * the channel's associated country
         */
        public String country;

        /**
         * localised metadata for the channel
         */
        static class LocalisedLanguage {
            public String title;
            public String description;
        }
    }

    /**
     * contains details about the channel's content
     */
    static class Content {
        public RelatedPlaylists relatedPlaylists;

        static class RelatedPlaylists {
            /**
             * id of the channel's likes playlist
             */
            public String likes;
            /**
             * id of the channel's favorites playlist
             */
            public String favorites;
            /**
             * id of the playlist that has the channel's uploaded videos
             */
            public String uploads;
        }
    }

    static class Statistics {
        /**
         * number of times this channel has been viewed
         */
        public long viewCount;
        /**
         * number of subscribers. this value is rounded down to 3 sig. fig.
         */
        public long subscriberCount;
        /**
         * true if the channel's subscriber count is hidden
         */
        public boolean hiddenSubscriberCount;
        /**
         * number of public videos uploaded
         */
        public long videoCount;
    }

    /**
     * contains info about the topics related to this channel
     */
    static class Topics {
        /**
         * list of topic ids related to this channel
         */
        public List<String> topicIds;
        /**
         * list of wikipedia urls that describe this channel's content
         */
        public List<String> topicCategories;
    }

    /**
     * encapsulates info about the privacy settings of this channel
     */
    static class Status {
        /**
         * privacy status of this channel.<br>
         * valid values are:<br>
         * private<br>
         * public<br>
         * unlisted
         */
        public String privacyStatus;
        /**
         * indicates whether this channel is linked to a youtube username or google+ account
         */
        public boolean isLinked;
        /**
         * indicates whether this channel can upload videos more than 15 minutes long.<br>
         * the values for this property are:<br>
         * allowed<br>
         * disallowed<br>
         * eligible<br>
         */
        public String longUploadsStatus;
        /**
         * true if this channel is child-directed
         */
        public boolean madeForKids;
        /**
         * same as madeForKids. used in the update call to update the designation.
         * only returned if the channel owner authorises the request.
         */
        public boolean selfDeclaredMadeForKids;
    }

    /**
     * the branding details of the channel
     */
    static class Branding {
        public ChannelBranding channel;

        static class ChannelBranding {
            public String title;
            public String description;
            /**
             * space separated list of keywords associated with this channel
             */
            public String keywords;
            /**
             * default content tab when viewers visit your channel
             */
            public String defaultTab;
            /**
             * google analytics account id. used for tracking and measuring traffic
             */
            public String trackingAnalyticsAccountId;
            /**
             * indicates whether the comments on this channel need to be approved by the owner
             */
            public boolean moderateComments;
            /**
             * determines if youtube will show a list of related channels on your channel
             */
            public boolean showRelatedChannels;
            /**
             * determines of youtube will show content in a browse or feed view.<br>
             * true is browse view and false is feed view.
             */
            public boolean showBrowseView;
            /**
             * the section title for the featured channels
             */
            public String featuredChannelsTitle;
            /**
             * list of featured channel ids
             */
            public List<String> featuredChannelUrls;
            /**
             * video id of channel trailer
             */
            public String unsubscribedTrailer;
            /**
             * prominent channel color
             */
            public String profileColor;
            /**
             * same as {@link Snippet#defaultLanguage}
             */
            public String defaultLanguage;
            /**
             * same as {@link Snippet#country}. update this value to change the country.
             */
            public String country;
        }
    }

    /**
     * used for auditing of channels. MCNs will evaluate the audit to decide whether to accept this channel.<br>
     * any requests that retrieve this property need to have the {@link com.weebkun.api.auth.Scopes#PARTNER_CHANNEL_AUDIT} scope.
     */
    static class Audit {
        /**
         * overall standing of the channel.
         * logical and of {@link #communityGuidelinesGoodStanding}, {@link #copyrightStrikesGoodStanding} and {@link #contentIdClaimsGoodStanding}.
         */
        public boolean overallGoodStanding;
        /**
         * whether the channel respects community guidelines
         */
        public boolean communityGuidelinesGoodStanding;
        /**
         * whether the channel has any copyright strikes
         */
        public boolean copyrightStrikesGoodStanding;
        /**
         * whether the channel has any content id claims against it
         */
        public boolean contentIdClaimsGoodStanding;
    }

    /**
     * contains data relevant to youtube partners linked to this channel
     */
    static class ContentOwner {
        /**
         * id of the content owner linked to this channel
         */
        public String contentOwner;
        /**
         * the date and time when the channel was linked to the content owner
         */
        public String timeLinked;
        /**
         * map of the channel's translation data.
         * the key is the <a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt">BCP-47</a> code of the language.
         */
        public Map<String, Localisation> localizations;

        /**
         * encapsulates translation data for the channel
         */
        static class Localisation {
            public String title;
            public String description;
        }
    }
}
