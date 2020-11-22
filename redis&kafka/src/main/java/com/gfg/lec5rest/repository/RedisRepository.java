package com.gfg.lec5rest.repository;
import com.gfg.lec5rest.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<Rating,Long> {
}
