package com.contestCreation.contestCreation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contestCreation.contestCreation.Dto.ContestCreateDto;
import com.contestCreation.contestCreation.Entity.Contest;
import com.contestCreation.contestCreation.Service.ContestIngestionService;

@RestController
@RequestMapping("/contest")
public class ContestCreateController {
	@Autowired
	ContestIngestionService contestService;

	@PostMapping("/create")
	public Contest createContest(@RequestBody ContestCreateDto contestCreateDto) {
		System.out.println(contestCreateDto);
		Contest contest=contestService.createContest(contestCreateDto);
		return contest;
	}
	
	@GetMapping("/getAllContest")
	public List<Contest> getAllContestList(){
		return contestService.getAllContestList();
		
	}
}
