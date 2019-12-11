package com.ashish.highlight.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashish.highlight.dao.HighlightDao;
import com.ashish.highlight.model.Highlight;
import com.ashish.highlight.model.HighlightEntity;

@Service
public class HighlightServiceImpl implements HighlightService {

	@Autowired
	HighlightDao highlightDao;
	
	@Autowired
	HighlightMergeService highlightMergeService;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(HighlightServiceImpl.class);
	
	@Override
	public List<Highlight> getHighlights(String bookID, String userID) throws Exception {
		
		Set<Highlight> result;
		
		try {
			result = highlightDao.retrieveHighlight(bookID, userID);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		if(null!=result && !result.isEmpty())
			return result.stream().collect(Collectors.toList());
		else 
			return new ArrayList<Highlight>();
	}
	@Override
	public void updateHighlight(HighlightEntity entity) throws Exception {
		
		Set<Highlight> existingHighlights;
		try {
			existingHighlights = highlightDao.retrieveHighlight(entity.getBookId(), entity.getUserId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		if(null!=existingHighlights&&!existingHighlights.isEmpty()){
			logger.debug("Highlights will be merged");
			highlightMergeService.mergeHighlights(existingHighlights, entity);
		}
		
		highlightDao.updateHighlight(entity);
		logger.debug("Highlights updated");
		
	}



}
