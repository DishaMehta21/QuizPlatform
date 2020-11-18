package com.contestCreation.contestCreation.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.contestCreation.contestCreation.Entity.Contest;

@Repository
public interface ContestRepository extends MongoRepository<Contest, String> {

}
