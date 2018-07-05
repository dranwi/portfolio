package com.andy.portfolioModel;



import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Table(name="TIMELINE")
public class Timeline {
	
	@Id
	@GeneratedValue
	@Column(name="TIMELINE_ID")	
	long id;
	
	
	Map<String,Double> map;
	
	
	
	public void TimeLine() {		
		//map = Collections.synchronizedMap(new HashMap<Date,Double>());
	}
	
	public void put(String date, Double value) {
		map.put(date, value);
	}
	
	public Double get (String date) {
		return map.get(date);
	}
	
	public Set<String> keySet() {
		return map.keySet();
	}
	

}
