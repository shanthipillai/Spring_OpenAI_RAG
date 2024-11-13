package spring.ai.rag.budgetlatest.config;

import java.io.File;
import java.util.List;


import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class Config {
	@Value("classpath:/Budget_Speech.txt")
	private Resource budget;

	@Bean
	SimpleVectorStore simpleVectorStore(EmbeddingModel emebeddingModel)
	{
		SimpleVectorStore vectorStore=new SimpleVectorStore(emebeddingModel);
		File vectorStoreFile=new File("E:/SpringSecurityConcepts/SpringRAG_AI_Implementation/src/main/resources/vectorstore.txt");
		
		TextReader textReader=new TextReader(budget);
		
		if(vectorStoreFile.exists())
		{
			System.out.println("Loaded Vector Store File");
			vectorStore.load(vectorStoreFile);
		}
		else
		{
			System.out.println("Create Vector File");
		textReader.getCustomMetadata().put("filename", "Budget_Speech.txt");
		List<org.springframework.ai.document.Document> documents =textReader.get();
		TextSplitter textSplitter=new TokenTextSplitter();
		textSplitter.apply(documents);
		vectorStore.add(documents);
		vectorStore.save(vectorStoreFile);
		}
		return vectorStore; 
	}
}
