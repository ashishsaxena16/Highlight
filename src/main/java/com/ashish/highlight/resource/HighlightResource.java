package com.ashish.highlight.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ashish.highlight.model.Highlight;
import com.ashish.highlight.model.HighlightEntity;
import com.ashish.highlight.service.HighlightService;

@RestController
public class HighlightResource {
	
	@Autowired
	HighlightService highlightService;
	
	private static final Logger logger = LoggerFactory.getLogger(HighlightResource.class);
	
	@GetMapping("/highlight/book/{bookId}/user/{userId}")
	public ResponseEntity<List<Highlight>> getBookHighlight(@PathVariable String bookId, @PathVariable String userId){
		
		List<Highlight> result;
		try {
			result = highlightService.getHighlights(bookId,userId);
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		if(!result.isEmpty())
			return ResponseEntity.ok(result);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/highlight")
	public ResponseEntity<?> updateHighlight(@RequestBody HighlightEntity highlightentity){
		try {
			
			highlightService.updateHighlight(highlightentity);
			logger.debug("Highlights updated");
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
