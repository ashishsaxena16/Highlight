package com.ashish.highlight.service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.ashish.highlight.model.Highlight;
import com.ashish.highlight.model.HighlightEntity;

@Service
public class HighlightMergeServiceImpl implements HighlightMergeService{

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(HighlightServiceImpl.class);
	
	/* The merge highlight method converts the existing highlights to a treeset. The element to be added
	 * is then looked up as per its position in treeset and the prev and next are looked in for possible 
	 * rearrangement 
	 * @Param : exisitingHighights, and updatedEntity object
	 * @Output : updatedEntity object highlight list is updated
	 * */
	@Override
	public void mergeHighlights(Set<Highlight> existingHighlightsSet, HighlightEntity updatedEntity) throws Exception{
		
		logger.debug("Highlight Merge started");
		
		TreeSet<Highlight> exisitingHighlights = new TreeSet<>
					(Comparator.comparing(Highlight::getStartIndex)
					.thenComparing(Highlight::getEndIndex));
		
		exisitingHighlights.addAll(existingHighlightsSet);
		
		for(Highlight ht : updatedEntity.getHighlights()){
				if(!exisitingHighlights.contains(ht)){
					Highlight next = exisitingHighlights.higher(ht);
					Highlight prev = exisitingHighlights.lower(ht);
					
					if(null!=prev && ht.getStartIndex()<=prev.getEndIndex()){
							ht.setStartIndex(prev.getStartIndex());
							exisitingHighlights.remove(prev);
					}
					
					if(null!=next && ht.getEndIndex()>=next.getStartIndex()){
							if(ht.getEndIndex()<next.getEndIndex()){
								ht.setEndIndex(next.getEndIndex());
								
							}
							exisitingHighlights.remove(next);
					}
						
					exisitingHighlights.add(ht);
				}
		}
		
		updatedEntity.setHighlights(exisitingHighlights.stream().collect(Collectors.toList()));
		logger.debug("Highlights updated");
	}

}
