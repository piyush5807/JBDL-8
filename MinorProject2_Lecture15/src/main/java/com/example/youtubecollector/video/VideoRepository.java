package com.example.youtubecollector.video;

import com.mongodb.MongoBulkWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VideoRepository {

    VideoRepository(){
        mongoOperations.indexOps(Video.class).ensureIndex(new Index().on("videoId", Sort.Direction.ASC).unique());
    }

    @Autowired
    MongoOperations mongoOperations;

    public void insertVideo(Video video){
        mongoOperations.insert(video);
    }

    public List<Video> getVideos(int pageNo, int pageSize){
        Query query = new Query();
        query.skip((pageNo - 1) * pageSize).limit(pageSize);
        return mongoOperations.find(query, Video.class);
    }

    public void insertMultipleVideos(List<Video> videos) {
        try {
            mongoOperations.insertAll(videos);
        }catch (MongoBulkWriteException e){
            e.printStackTrace();
        }
    }

    public void createIndex(){
        mongoOperations.indexOps(Video.class).ensureIndex(new Index().on("videoId", Sort.Direction.ASC).unique());
    }
}
