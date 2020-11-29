package com.example.youtubecollector.video;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Video {

    private String description;
    private String title;

    @Indexed(name = "videoId", unique = true, background = true)
    private String videoId;

    private String channelId;
    private String channel_title;

    private JSONObject thumbnailUrl;

    public Video(String description, String title, String videoId, JSONObject thumbnailUrl, String channelId, String channel_title) {
        this.description = description;
        this.title = title;
        this.videoId = videoId;
        this.thumbnailUrl = thumbnailUrl;
        this.channelId = channelId;
        this.channel_title = channel_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public JSONObject getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(JSONObject thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannel_title() {
        return channel_title;
    }

    public void setChannel_title(String channel_title) {
        this.channel_title = channel_title;
    }
}
