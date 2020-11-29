package com.example.youtubecollector.video;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class VideoService {

    private static Logger logger = LoggerFactory.getLogger(VideoService.class);

    private String token;

    @Autowired
    VideoRepository videoRepository;
    YouTube youTube;

    VideoService(){
        logger.warn("In VideoService Constructor");
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        this.youTube = new YouTube.Builder(transport, jsonFactory, new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {

            }
        }).setApplicationName("youtube_video_collector").build();

    }

    public void loadVideosFromYoutube(String search_key) throws IOException, ParseException, InterruptedException {

        YouTube.Search.List search = this.youTube.search().list(Arrays.asList("id", "snippet"));
        search.setKey("AIzaSyAvV1sYwVc0WmykBZYPs6LAyOIYJ7S3fUc");
        search.setQ(search_key);
        search.setMaxResults(3l);

        int count = 0;
        while(count < 3) {
            logger.warn("token is {}", this.token);

            search.setPageToken(token);

            SearchListResponse response = search.execute();

            this.token = response.getNextPageToken();

            logger.warn("search results are {}", response.getItems());

            List<SearchResult> items = response.getItems();

            List<Video> videosToStore = new ArrayList<>();

            for (SearchResult item : items) {

                SearchResultSnippet snippet = item.getSnippet();

                String title = snippet.getTitle();
                String description = snippet.getDescription();
                String channelId = snippet.getChannelId();
                String channelTitle = snippet.getChannelTitle();
                JSONObject thumbnails = (JSONObject) JSONValue.parseWithException(snippet.getThumbnails().toString());
//                Date publishedAt = new Date(snippet.getPublishedAt());

                String videoId = item.getId().getVideoId();

                Video video = new Video(description, title, videoId, thumbnails, channelId, channelTitle);

                videosToStore.add(video);

                // 5 videos - 1mb of memory
                // 500 - 100 mb of memory

            }

            videoRepository.insertMultipleVideos(videosToStore);

            Thread.sleep(10000);

            count++;
        }

    }

    public List<Video> getVideosFromDB(int pageNo, int pageSize){
        return videoRepository.getVideos(pageNo, pageSize);
    }
}
