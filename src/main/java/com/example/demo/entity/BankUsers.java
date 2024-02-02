package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Bankusers")
public class BankUsers {
	
	@Id
	@NotNull(message="user id is mandatory")
	private String userId;
	
	
	@NotNull(message="user entity is mandatory")
	private String entityCode;
	private String userName;
	
	@NotNull(message="user status is mandatory")
	private char status;
	
	
	@NotNull(message="user role is mandatory")
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "USERROLES", joinColumns = { 
    		@JoinColumn(name = "UID") }, inverseJoinColumns = { 
    				@JoinColumn(name = "RID") })
	
	private Set<BankRoles> roleCode;
	private String branchCode;
	private String customerCode;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate Dob;
	@Email(message="invalid email id")
	private String emailId;
	private String mobileNumber;
	private String designation;
	private char PwdDeliveryChoice;
	private String firstPin;
	private String firstPinSalt;
	private String userActivationReqd;
	@NotNull(message="Crated by should not be null")
	private String crBy;
	@NotNull(message="Crated on should not be null")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime crOn;
	private String modBy;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime modOn;


	
	
	
}
