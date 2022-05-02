package com.web.dacn.entity.quote;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="quotecategory")
public class QuoteCategory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "nvarchar(2000) NOT NULL", unique=true)
	private String slug;
	private String name;
	@ColumnDefault(value = "0")
	private int view;
	@Column(name="meta_title") 
	private String metaTitle;
	@Column(name="meta_description")
	private String metaDescription;
	@ColumnDefault(value="1")
	private Boolean status;
	@ManyToOne(targetEntity = QuoteCategory.class)
	@JoinColumn(name="parent_id")
	@ColumnDefault(value = "1")
	private QuoteCategory quoteCategory;
	
}
