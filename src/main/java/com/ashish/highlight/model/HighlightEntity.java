package com.ashish.highlight.model;

import java.util.List;

public class HighlightEntity {

	String bookId;
	String userId;
	List<Highlight> highlights;
	
	public HighlightEntity() {
		super();
	}
	public void setHighlights(List<Highlight> highlights) {
		this.highlights = highlights;
	}

	public String getBookId() {
		return bookId;
	}
	
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	
	public List<Highlight> getHighlights() {
		return highlights;
	}
	
}
