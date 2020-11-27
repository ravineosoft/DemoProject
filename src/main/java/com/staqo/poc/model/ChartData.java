package com.staqo.poc.model;

import java.io.Serializable;

public class ChartData implements Serializable {

	private String month;
	private Integer chartCount;
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getChartCount() {
		return chartCount;
	}
	public void setChartCount(Integer chartCount) {
		this.chartCount = chartCount;
	}
	@Override
	public String toString() {
		return "ChartData [month=" + month + ", chartCount=" + chartCount + "]";
	}

	
	
}
