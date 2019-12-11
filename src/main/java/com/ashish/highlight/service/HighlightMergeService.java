package com.ashish.highlight.service;

import java.util.Set;

import com.ashish.highlight.model.Highlight;
import com.ashish.highlight.model.HighlightEntity;

public interface HighlightMergeService {
	
	public void mergeHighlights(Set<Highlight> existingHighlights, HighlightEntity updatedEntity) throws Exception;
	
}
