package com.java.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subject {
	@Id
	@Column(name = "subjectid")
	private long subjectid;
	@Column(name = "subtitle")
	private String subtitle;
	@Column(name = "durationInHours")
	private int durationInHours;
	@OneToOne
	@JoinColumn(name = "bookid")
	private Book book;
	private static long count=0;
	
	public Subject(String subtitle, int durationInHours, Book book) {
		this.subtitle = subtitle;
		this.durationInHours = durationInHours;
		this.book = book;
	}
	
	public Subject() {
		// TODO Auto-generated constructor stub
	}
	public long getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "Subject [subjectid=" + subjectid + ", subtitle=" + subtitle + ", durationInHours=" + durationInHours
				+ ", references=" + book + "]";
	}
	
	
	
}
