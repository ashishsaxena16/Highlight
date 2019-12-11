package com.ashish.highlight.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ashish.highlight.model.Highlight;
import com.ashish.highlight.model.HighlightEntity;
import static org.junit.Assert.*;

public class HighlightMergeServiceImplTest {

	private HighlightMergeService highlightMergeService;
	
	private static final Logger logger = LoggerFactory.getLogger(HighlightMergeServiceImplTest.class);
	
	@Before
	public void setUp(){
		highlightMergeService = new HighlightMergeServiceImpl();
	}

	@Test
	public void test_Highlight_Merged_Before(){
		
		Set<Highlight> existingHighlights = new HashSet<>();
		existingHighlights.add(new Highlight(500, 600));  existingHighlights.add(new Highlight(700, 800));
		existingHighlights.add(new Highlight(300, 400));
		
		HighlightEntity updatedEntity = new HighlightEntity();
		List<Highlight> highlights = new ArrayList<>();
		highlights.add(new Highlight(150,250));
		updatedEntity.setHighlights(highlights );
		
		List<Highlight> expectedHighlights = new ArrayList<>();
		expectedHighlights.add( new Highlight(150,250)); expectedHighlights.add(new Highlight(300, 400)); 
		expectedHighlights.add(new Highlight(500, 600)); expectedHighlights.add(new Highlight(700, 800));
		 		
		try {
			highlightMergeService.mergeHighlights(existingHighlights , updatedEntity );
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		assertEquals(expectedHighlights, updatedEntity.getHighlights());
	}
	
	@Test
	public void test_Highlight_Merged_After(){
		
		
		Set<Highlight> existingHighlights = new HashSet<>();
		existingHighlights.add(new Highlight(500, 600));  existingHighlights.add(new Highlight(700, 800));
		existingHighlights.add(new Highlight(300, 400));
		
		HighlightEntity updatedEntity = new HighlightEntity();
		List<Highlight> highlights = new ArrayList<>();
		highlights.add(new Highlight(850,950));
		updatedEntity.setHighlights(highlights );
		
		List<Highlight> expectedHighlights = new ArrayList<>();
		expectedHighlights.add(new Highlight(300, 400));expectedHighlights.add(new Highlight(500, 600));
		expectedHighlights.add(new Highlight(700, 800));expectedHighlights.add( new Highlight(850,950));
		
		try {
			highlightMergeService.mergeHighlights(existingHighlights , updatedEntity );
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		assertEquals(expectedHighlights, updatedEntity.getHighlights());
		
	}
	
	@Test
	public void test_Highlight_Merged_Intermediate(){
		
		Set<Highlight> existingHighlights = new HashSet<>();
		existingHighlights.add(new Highlight(300, 400));
		existingHighlights.add(new Highlight(500, 600));  existingHighlights.add(new Highlight(700, 800));
		
		HighlightEntity updatedEntity = new HighlightEntity();
		List<Highlight> highlights = new ArrayList<>();
		highlights.add(new Highlight(575,625));
		highlights.add(new Highlight(650,750));

		updatedEntity.setHighlights(highlights );
		
		List<Highlight> expectedHighlights = new ArrayList<>();
		expectedHighlights.add(new Highlight(300, 400));expectedHighlights.add(new Highlight(500, 625));
		expectedHighlights.add(new Highlight(650, 800));
		
		try {
			highlightMergeService.mergeHighlights(existingHighlights , updatedEntity );
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		assertEquals(expectedHighlights, updatedEntity.getHighlights());
		
	}
	
	@Test
	public void test_Highlight_Reject_Duplicate(){
		
		Set<Highlight> existingHighlights = new HashSet<>();
		existingHighlights.add(new Highlight(300, 400));
		existingHighlights.add(new Highlight(500, 600));  existingHighlights.add(new Highlight(700, 800));
		
		HighlightEntity updatedEntity = new HighlightEntity();
		List<Highlight> highlights = new ArrayList<>();
		highlights.add(new Highlight(500,600));

		updatedEntity.setHighlights(highlights );
		
		List<Highlight> expectedHighlights = new ArrayList<>();
		expectedHighlights.add(new Highlight(300, 400));expectedHighlights.add(new Highlight(500, 600));
		expectedHighlights.add(new Highlight(700, 800));
		
		try {
			highlightMergeService.mergeHighlights(existingHighlights , updatedEntity );
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		assertEquals(expectedHighlights, updatedEntity.getHighlights());
		
	}
	
	@Test
	public void test_Highlight_Merge_Entries(){
		
		Set<Highlight> existingHighlights = new HashSet<>();
		existingHighlights.add(new Highlight(300, 400));
		existingHighlights.add(new Highlight(500, 600));  existingHighlights.add(new Highlight(700, 800));
			
		HighlightEntity updatedEntity = new HighlightEntity();
		List<Highlight> highlights = new ArrayList<>();
		highlights.add(new Highlight(250,300)); highlights.add(new Highlight(600,650));

		updatedEntity.setHighlights(highlights );
		
		List<Highlight> expectedHighlights = new ArrayList<>();
		expectedHighlights.add(new Highlight(250, 400));expectedHighlights.add(new Highlight(500, 650));
		expectedHighlights.add(new Highlight(700, 800));
		
		try {
			highlightMergeService.mergeHighlights(existingHighlights , updatedEntity );
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		assertEquals(expectedHighlights, updatedEntity.getHighlights());
		
	}
	
	@Test
	public void test_Highlight_Merge_Unification(){
		
		Set<Highlight> existingHighlights = new HashSet<>();
		existingHighlights.add(new Highlight(300, 400));
		existingHighlights.add(new Highlight(500, 600));  existingHighlights.add(new Highlight(700, 800));
				
		HighlightEntity updatedEntity = new HighlightEntity();
		List<Highlight> highlights = new ArrayList<>();
		highlights.add(new Highlight(250,300)); highlights.add(new Highlight(600,700));
		highlights.add(new Highlight(400,500));

		updatedEntity.setHighlights(highlights );
		
		List<Highlight> expectedHighlights = new ArrayList<>();
		expectedHighlights.add(new Highlight(250, 800));
		
		try {
			highlightMergeService.mergeHighlights(existingHighlights , updatedEntity );
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		assertEquals(expectedHighlights, updatedEntity.getHighlights());
		
	}
}
