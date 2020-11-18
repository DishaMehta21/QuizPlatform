package com.contestCreation.contestCreation.Dto;

public class ContestCreateDto {
	String contestName;
	int numOfQue;
	int diffLevel;
	
	
	public ContestCreateDto() {
		
	}
	public ContestCreateDto(String contestName, int numOfQue, int diffLevel) {
		this.contestName = contestName;
		this.numOfQue = numOfQue;
		this.diffLevel = diffLevel;
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
	@Override
	public String toString() {
		return "ContestCreateDto [contestName=" + contestName + ", numOfQue=" + numOfQue + ", diffLevel=" + diffLevel
				+ "]";
	}
	
	
}

