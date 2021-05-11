package com.waracle.cakemgr.entity;
import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "cakeEntity")
public class CakeEntity implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer employeeId;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "FIRST_NAME",nullable = false, length = 100)
    private String desc;

    @Column(name = "LAST_NAME", nullable = false, length = 300)
    private String image;
           
    public CakeEntity() {
		
	}

	public CakeEntity(String title, String desc, String image) {	
		this.title = title;
		this.desc = desc;
		this.image = image;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	@Override
	public String toString() {
		return "CakeEntity [employeeId=" + employeeId + ", title=" + title + ", desc=" + desc + ", image=" + image
				+ "]";
	}
    
    
}

