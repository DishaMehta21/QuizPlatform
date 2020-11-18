package com.contestCreation.contestCreation.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contestCreation.contestCreation.Dto.ContestCreateDto;
import com.contestCreation.contestCreation.Entity.Contest;
import com.contestCreation.contestCreation.Entity.Question;
import com.contestCreation.contestCreation.Repository.ContestRepository;
import com.contestCreation.contestCreation.Repository.QuestionRepository;

@Service
public class ContestIngestionService {

	@Autowired
	QuestionRepository QuestionRepo;

	@Autowired
	ContestRepository contestRepository;

	private static final String filePath = "F:\\contestCreation\\src\\main\\resources\\scrapingQues.csv";

	public List<Question> storeQuestionCSV() throws IOException {
		List<Question> questionList = new ArrayList<Question>();
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			for (CSVRecord csvRecord : csvParser) {
				// Accessing values by Header names
				Question question = new Question();
				question.setQuestionText(csvRecord.get("Question"));
				question.setOptionA(csvRecord.get("A"));
				question.setOptionB(csvRecord.get("B"));
				question.setOptionC(csvRecord.get("C"));
				question.setOptionD(csvRecord.get("D"));
				question.setRightAnswer(csvRecord.get("RightAnswer"));
				question.setQuestionType(csvRecord.get("QuesionType"));
				question.setPath(csvRecord.get("Link"));
				question.setDifficultyLevel(Integer.parseInt(csvRecord.get("DificultyLevel")));

				QuestionRepo.save(question);

				questionList.add(question);
			}
		}
		System.out.println(questionList);
		return questionList;
	}

	public Contest createContest(ContestCreateDto contestCreateDto) {
		HashMap<String, Integer> diffLevelEasy = new HashMap<String, Integer>();
		HashMap<String, Integer> diffLevelMid = new HashMap<String, Integer>();
		HashMap<String, Integer> diffLevelHard = new HashMap<String, Integer>();
		HashMap<String, Integer> questionType = new HashMap<String, Integer>();

		List<Question> easyText = new ArrayList<>();
		List<Question> midText = new ArrayList<>();
		List<Question> hardText = new ArrayList<>();
		List<Question> easyVideo = new ArrayList<>();
		List<Question> midVideo = new ArrayList<>();
		List<Question> hardVideo = new ArrayList<>();
		List<Question> easyAudio = new ArrayList<>();
		List<Question> midAudio = new ArrayList<>();
		List<Question> hardAudio = new ArrayList<>();
		List<Question> easyImage = new ArrayList<>();
		List<Question> midImage = new ArrayList<>();
		List<Question> hardImage = new ArrayList<>();

		// Define Threshold for difficulty level;
		diffLevelEasy.put("easy", 50);
		diffLevelEasy.put("mid", 30);
		diffLevelEasy.put("hard", 20);

		diffLevelMid.put("easy", 30);
		diffLevelMid.put("mid", 50);
		diffLevelMid.put("hard", 20);

		diffLevelHard.put("easy", 20);
		diffLevelHard.put("mid", 30);
		diffLevelHard.put("hard", 50);

		questionType.put("Text", 40);
		questionType.put("Image", 30);
		questionType.put("Audio", 20);
		questionType.put("Video", 10);

		List<Question> questionList = QuestionRepo.findAll(); // fetch all ques
		System.out.println(questionList.size());

		String contestName = contestCreateDto.getContestName();
		int numOfQue = contestCreateDto.getNumOfQue();
		int diffLevel = contestCreateDto.getDiffLevel();

		for (Question que : questionList) {

			if (que.getDifficultyLevel() == 1 && que.getQuestionType().equals("Text")) {
				easyText.add(que);
			} else if (que.getDifficultyLevel() == 3 && que.getQuestionType().equals("Text")) {
				midText.add(que);
			} else if (que.getDifficultyLevel() == 5 && que.getQuestionType().equals("Text")) {
				hardText.add(que);
			} else if (que.getDifficultyLevel() == 1 && que.getQuestionType().equals("Image")) {
				easyImage.add(que);
			} else if (que.getDifficultyLevel() == 3 && que.getQuestionType().equals("Image")) {
				midImage.add(que);
			} else if (que.getDifficultyLevel() == 5 && que.getQuestionType().equals("Image")) {
				hardImage.add(que);
			} else if (que.getDifficultyLevel() == 1 && que.getQuestionType().equals("Video")) {
				easyVideo.add(que);
			} else if (que.getDifficultyLevel() == 3 && que.getQuestionType().equals("Video")) {
				midVideo.add(que);
			} else if (que.getDifficultyLevel() == 5 && que.getQuestionType().equals("Video")) {
				hardVideo.add(que);
			} else if (que.getDifficultyLevel() == 1 && que.getQuestionType().equals("Audio")) {
				easyAudio.add(que);
			} else if (que.getDifficultyLevel() == 3 && que.getQuestionType().equals("Audio")) {
				midAudio.add(que);
			} else if (que.getDifficultyLevel() == 5 && que.getQuestionType().equals("Audio")) {
				hardAudio.add(que);
			}
		}
		HashMap<String, List<Question>> allTypeQuestion = new HashMap<String, List<Question>>();
		allTypeQuestion.put("easyText", easyText);
		allTypeQuestion.put("midText", midText);
		allTypeQuestion.put("hardText", hardText);
		allTypeQuestion.put("easyImage", easyImage);
		allTypeQuestion.put("midImage", midImage);
		allTypeQuestion.put("hardImage", hardImage);
		allTypeQuestion.put("easyVideo", easyVideo);
		allTypeQuestion.put("midVideo", midVideo);
		allTypeQuestion.put("hardVideo", hardVideo);
		allTypeQuestion.put("easyAudio", easyAudio);
		allTypeQuestion.put("midAudio", midAudio);
		allTypeQuestion.put("hardAudio", hardAudio);

		HashMap<String, Integer> finalQueMap = new HashMap<String, Integer>();
		int queCount = 0;

		
		List<Question> contestQueSet = new ArrayList<Question>();
		int textCount = 0;

		for (Map.Entry<String, Integer> qType : questionType.entrySet()) {
			textCount = Math.round((numOfQue * qType.getValue()) / 100);
				//			 System.out.println("Qtype:"+qType.getKey());
				//			 System.out.println("Qtype Value:"+qType.getValue());
				//			 System.out.println("TextCount:"+textCount);

			HashMap<String, Integer> level;
			if (diffLevel == 1) {
				level = diffLevelEasy;
			} else if (diffLevel == 3) {
				level = diffLevelMid;
			} else {
				level = diffLevelHard;
			}

			for (Map.Entry<String, Integer> lev : level.entrySet()) {
				if (queCount >= numOfQue) {
					System.out.println("QCount Before:" + queCount);
					queCount = queCount - (queCount - numOfQue);
					System.out.println("QCount After:" + queCount);
					break;
				}
				//				System.out.println("Level:"+lev.getKey());
				//   		 	System.out.println("lev Value:"+lev.getValue());
				//				System.out.println("TCount:"+textCount+"\nTcount * levValue /
				//				100:"+(int)Math.ceil((double)textCount*lev.getValue()/(double)100));
				int ratio = (int) Math.ceil((double) (textCount * lev.getValue()) / (double) 100);

				
				System.out.println("Ratio:" + ratio);
				if (queCount + ratio >= numOfQue) {
					ratio = Math.abs(queCount - numOfQue);
					System.out.println("Ratio After:" + ratio);
				}
				queCount = queCount + ratio;
				finalQueMap.put(lev.getKey() + qType.getKey(), ratio);
				 //System.out.println("final:"+finalQueMap.toString());
//				 queCount=queCount+ratio;
				System.out.println("QueCount:" + queCount);
			}
		}

		for (Map.Entry<String, Integer> item : finalQueMap.entrySet()) {
			HashSet<Integer> hs = new HashSet<>();
			if (item.getValue() != 0) {

				while (hs.size() != item.getValue()) {

					// int queIndex=(int)(Math.floor(Math.random()*item.getValue()));
					String key = item.getKey();
					int queIndex = new Random().nextInt(allTypeQuestion.get(key).size());

					// 				System.out.println("Size:"+allTypeQuestion.get(key).size());
					// 				System.out.println("key:"+item.getKey());
					// 				System.out.println("value:"+item.getValue());
					// 				System.out.println("QueIndex:"+queIndex);
					if (!hs.contains(queIndex)) {
						hs.add(queIndex);
						contestQueSet.add(allTypeQuestion.get(key).get(queIndex));
						// System.out.println("item Key:"+allTypeQuestion.get(key));
						// System.out.println("Final
						// Contest:"+allTypeQuestion.get(item.getKey()).get(queIndex));
					}

				}
			}
		}
//		System.out.println("Contest Set:" + contestQueSet.toString());
//		System.out.println("Contest ques size:" + contestQueSet.size());

		// save contest in db
		Contest contest = new Contest();
		contest.setContestName(contestCreateDto.getContestName());
		contest.setDiffLevel(contestCreateDto.getDiffLevel());

		contest.setNumOfQue(contestCreateDto.getNumOfQue());
		contest.setQuestionSet(contestQueSet);
		contestRepository.save(contest);
		return contest;
	}

	public List<Contest> getAllContestList() {
		return contestRepository.findAll();
	}
}
