package com.web.dacn.entity.quote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.web.dacn.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="commentquote")
public class CommentQuote implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date mod_time; 
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="parent_id")
	private CommentQuote commentQuote;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; 
	
	@ManyToOne
	@JoinColumn(name="quote_id")
	private Quote quote; 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commentQuote", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<CommentQuote> commentQuotes = new ArrayList<>();
}
