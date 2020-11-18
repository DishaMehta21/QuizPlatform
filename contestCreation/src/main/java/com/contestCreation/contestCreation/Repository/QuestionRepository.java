package com.contestCreation.contestCreation.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.contestCreation.contestCreation.Entity.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String>{

}
