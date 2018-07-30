package com.ncuhome.QandA.api.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

import com.ncuhome.QandA.api.models.*;
import com.ncuhome.QandA.api.utils;

@RestController
@RequestMapping("/api")
public class Question {
	
	public static int QUESTION_AMOUNT = 4;
	
	@PostMapping("/v3/rand_question")
	public Map<String, QuestionModel> rand_question(@RequestBody QuestionRequestModel request) throws SQLException {
		Map<String, QuestionModel> questionMap = new LinkedHashMap<String, QuestionModel>();
		List<String> categories;
		if(request.categories != null) {
			categories = new ArrayList<String>(Arrays.asList(request.categories));
		}
		else {
			categories = QuestionModel.getAllCategories();
		}
		while(categories.size() < QUESTION_AMOUNT) {
			categories.add(categories.get(utils.randIntRanged(categories.size())));
		}
		utils.shuffle((categories));
		for(int a = 0; a < QUESTION_AMOUNT; ++a) {
			QuestionModel question = new QuestionModel();
			question.randQuestion(categories.get(a));
			questionMap.put("ques" + Integer.toString(a + 1), question);
		}
		return questionMap;
	}
	
	@GetMapping("/api/v3/rand_question")
	public String rand_question_get() {
		return "success";
	}
}

class QuestionRequestModel {

	public String[] categories;
}

