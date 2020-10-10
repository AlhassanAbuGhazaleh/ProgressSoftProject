package com.progresssoft.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@Data
//TODO add lombok
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, length = 255)
	private Long id;

	@Column(name = "CREATE_DATE", unique = false, nullable = true, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	private Date createDate;

	@Column(name = "UPDATE_DATE", unique = false, nullable = true, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}