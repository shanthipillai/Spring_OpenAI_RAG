package spring.ai.rag.budgetlatest.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrabBudgetDetails {

	
	private ChatClient chatClient;
	
	public GrabBudgetDetails(ChatClient.Builder builder,VectorStore vectorStore)
	{
		this.chatClient=builder
				.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore,SearchRequest.defaults()))
				.build();
	}
	
	@GetMapping("/budgetrecent")
	public String budgetQandA(@RequestParam(value="message",defaultValue="What is the highligh of the budge 2924-25") String message)
	{
	/*	return chatClient
				.prompt()
				.user(message)
				.call()
				.content();
				*/
		
		return chatClient
				.prompt()
				.user(message)
				.call()
				.content();
	}
}