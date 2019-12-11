package com.ashish.highlight.service;

import java.util.List;

import com.ashish.highlight.model.Highlight;
import com.ashish.highlight.model.HighlightEntity;

public interface HighlightService {
	
	public List<Highlight> getHighlights(String bookID, String UserId) throws Exception;
	
	public void updateHighlight(HighlightEntity entity) throws Exception;
}
