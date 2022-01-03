package com.api.astrarest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.api.astrarest.generic.ViewFilter;
import com.fasterxml.jackson.annotation.JsonView;

@MappedSuperclass
public class DateRange {
	
	@Temporal(TemporalType.DATE)
	@JsonView(ViewFilter.Minimum.class)
	@Column(name="from_date")
	@Id
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	@JsonView(ViewFilter.Minimum.class)
	@Column(name="to_date")
	private Date toDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
}
