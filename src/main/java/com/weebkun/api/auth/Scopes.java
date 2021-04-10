package com.weebkun.api.auth;

/**
 * provides flags for OAuth scopes.
 * see the OAuth 2.0 scopes for the <a href="https://developers.google.com/identity/protocols/oauth2/scopes#youtube">Youtube Data API</a> for more info.
 */
public final class Scopes {
    /**
     * Manage your YouTube account
     */
    public static final String ACCOUNT = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube";
    /**
     * See a list of your current active channel members, their current level, and when they became a member
     */
    public static final String CHANNEL_MEMBERSHIPS_CREATOR = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube.channel-memberships.creator";
    /**
     * See, edit, and permanently delete your YouTube videos, ratings, comments and captions
     */
    public static final String FORCE_SSL = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube.force-ssl";
    /**
     * View your YouTube account
     */
    public static final String READONLY = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube.readonly";
    /**
     * Manage your YouTube videos
     */
    public static final String UPLOAD = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube.upload";
    /**
     * View and manage your assets and associated content on YouTube
     */
    public static final String PARTNER = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutubepartner";
    /**
     * View private information of your YouTube channel relevant during the audit process with a YouTube partner
     */
    public static final String PARTNER_CHANNEL_AUDIT = "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutubepartner-channel-audit";
}
