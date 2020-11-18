package com.contestCreation.contestCreation.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contest {

	@Id
	String contestId;
	String contestName;
	int numOfQue;
	int diffLevel; //Easy-1,Medium-3,Hard-5
	int timeLimit;
	int skipLimit;
	List<Question> questionSet;
	
	public Contest() {
	
	}

	public Contest(String contestId, String contestName, int numOfQue, int diffLevel, int timeLimit, int skipLimit,
			List<Question> questionSet) {
		super();
		this.contestId = contestId;
		this.contestName = contestName;
		this.numOfQue = numOfQue;
		this.diffLevel = diffLevel;
		this.timeLimit = timeLimit;
		this.skipLimit = skipLimit;
		this.questionSet = questionSet;
	}

	public String getContestId() {
		return contestId;
	}

	public void setContestId(String contestId) {
		this.contestId = contestId;
	}

	public String getContestName() {
		return contestName;
	}

	public void setContestName(String contestName) {
		this.contestName = contestName;
	}

	public int getNumOfQue() {
		return numOfQue;
	}

	public void setNumOfQue(int numOfQue) {
		this.numOfQue = numOfQue;
	}

	public int getDiffLevel() {
		return diffLevel;
	}

	public void setDiffLevel(int diffLevel) {
		this.diffLevel = diffLevel;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getSkipLimit() {
		return skipLimit;
	}

	public void setSkipLimit(int skipLimit) {
		this.skipLimit = skipLimit;
	}

	public List<Question> getQuestionSet() {
		return questionSet;
	}

	public void setQuestionSet(List<Question> questionSet) {
		this.questionSet = questionSet;
	}
	
	
}
