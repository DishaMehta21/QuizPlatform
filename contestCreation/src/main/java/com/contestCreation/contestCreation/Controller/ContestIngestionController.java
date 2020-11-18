package com.contestCreation.contestCreation.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.contestCreation.contestCreation.Entity.Question;

import com.contestCreation.contestCreation.Service.ContestIngestionService;

@RestController
public class ContestIngestionController {
	
	@Autowired
	ContestIngestionService service;
	
	@PostMapping("/contestIngestion")
	public List<Question> insertContest() throws IOException{
		return service.storeQuestionCSV();
	}
}
