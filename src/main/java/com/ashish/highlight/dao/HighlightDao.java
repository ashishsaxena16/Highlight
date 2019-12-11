package com.ashish.highlight.dao;

import java.util.Set;

import com.ashish.highlight.model.Highlight;
import com.ashish.highlight.model.HighlightEntity;

public interface HighlightDao {
	public Set<Highlight> retrieveHighlight(String bookId, String userId) throws Exception;
	public void updateHighlight(HighlightEntity entity) throws Exception;
}
