package com.web.dacn.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "nvarchar(2000) NOT NULL", unique=true)
	private String slug;
	private String fullname;
	private Date birthday;
	@Column(columnDefinition = "nvarchar(1000)")
	private String address;
	@Column(columnDefinition = "nvarchar(20)")
	private String phone;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String description;
	private Boolean status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date mod_time;
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="mod_user_id")
	private User user;
}
