package com.ashish.highlight.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import com.ashish.highlight.model.Highlight;
import com.ashish.highlight.model.HighlightEntity;

@Repository
public class HighlightDaoImpl implements HighlightDao{
	
	static final Map<String, Set<Highlight>>highlightMap = new ConcurrentHashMap<String, Set<Highlight>>();
	
	@Override
	public Set<Highlight> retrieveHighlight(String bookId, String userId) {
		
		return highlightMap.get(bookId+":"+userId);
	}

	@Override
	public void updateHighlight(HighlightEntity entity) throws Exception {
		
		String entityKey = entity.getBookId()+":"+entity.getUserId();
		
		highlightMap.put(entityKey, new HashSet<>(entity.getHighlights()));
	}

}
