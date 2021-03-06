package com.bibleit.questionkeywordcomparer.controller;

import com.bibleit.questionkeywordcomparer.connections.ConnectionsService;
import com.bibleit.questionkeywordcomparer.model.QuestionAnswer;
import com.bibleit.questionkeywordcomparer.model.QuestionType;
import com.bibleit.questionkeywordcomparer.model.Response;
import com.bibleit.questionkeywordcomparer.responses.ResponseService;
import com.bibleit.questionkeywordcomparer.utils.comparer.QuestionComparer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class QuestionKeywordController {

    @Autowired
    private ResponseService responseService;
    @Autowired
    private QuestionComparer comparer;
    @Autowired
    private ConnectionsService connection;

    @GetMapping("/")
    public Response index(){
        return responseService.retrieveHardCodedResponse();
    }

    @GetMapping("/matchedAnswers")
    public List<QuestionAnswer> getMatchedAnswers(@RequestParam String userInput){

        List<QuestionAnswer> answers = comparer.getBestMatched(userInput,QuestionType.ANSWER);
        return answers;
    }
    
    @GetMapping("/matchedQuestions")
    public QuestionAnswer[] getMatchedQuestions(@RequestParam String userInput){
        List<QuestionAnswer> questions = comparer.getBestMatched(userInput, QuestionType.QUESTION);
        QuestionAnswer[] sortedQuestion = connection.sortListByInput(questions, userInput);
        return sortedQuestion;
    }

}
