package com.ashish.highlight.model;

public class Highlight {
	int startIndex;
	int endIndex;
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	
	public Highlight() {
		super();
	}
	public Highlight(int startIndex, int endIndex) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	@Override
	public int hashCode() {
		return 31*startIndex + endIndex;
	}
	@Override
	public String toString() {
		return "Highlight [startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(null==obj)
			return false;
		
		if(this==obj)
			return true;
		
		if(obj instanceof Highlight){
			Highlight a = (Highlight) obj;
			return this.startIndex==a.startIndex && this.endIndex == a.endIndex;
		}
		
		return false;
	}
	
	
	
}
