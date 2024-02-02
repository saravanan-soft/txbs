package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="extsystems")
public class ExtSystems {
	
	@Id
	@Column(name="extsystemsid",nullable=false,unique=true)
	private String extSystemsId;
	
	

}
