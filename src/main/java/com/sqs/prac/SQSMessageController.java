package com.sqs.prac;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.model.Message;

@RestController
@RequestMapping("sqs")
public class SQSMessageController {

	@Autowired
	AWSSQSUtil awsSqsConfig;
	
	@PostMapping("produce/{message}")
	public String sendMessageToSqs(@PathVariable String message) {
		String messageId = awsSqsConfig.produceMessageToSQS(message);
		return "Message pushed to sqs: " + messageId;
	}
	
	@GetMapping("consume")
	public List<Message> retrieveMessageFromSqs() {
		return awsSqsConfig.consumeMessageFromSQS();
		
	}
}
