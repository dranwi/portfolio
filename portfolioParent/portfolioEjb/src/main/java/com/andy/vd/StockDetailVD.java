package com.andy.vd;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class StockDetailVD  implements Serializable{
	private static final long serialVersionUID = -2424347628034527789L;
	private String name;
	
	public StockDetailVD() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
