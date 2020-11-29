package com.example.youtubecollector.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoController {

    @Autowired
    VideoService service;

    // 1. client does not give pageNo as well as pageSize - first 10 videos
    // 2. client is sending pageNo but not the pageSize = [(x - 1) * 10  - (x*10))
    // 3. client is not sending pageNo but it is sending pageSize = first x videos
    // 4. client is sending both - pageSize - x, pageNo - n = [(n-1)*x to n*x))

    @GetMapping("/get_videos")
    public List<Video> getVideos(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize){
        return service.getVideosFromDB(pageNo, pageSize);
    }

    @PostMapping("/load")
    public void loadVideosFromYoutube(@RequestParam("search_key") String key) throws Exception{
        service.loadVideosFromYoutube(key);
    }
}
