package com.web.dacn.entity.quote;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class CommentQuote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date mod_time;
	
	@ManyToOne(targetEntity = CommentQuote.class)
	@JoinColumn(name="parent_id")
	private CommentQuote commentQuote;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="user_id")
	private User user;
	
	
	@ManyToOne(targetEntity = Quote.class)
	@JoinColumn(name="quote_id")
	private Quote quote;
	
	@OneToMany(mappedBy = "commentQuote", cascade = CascadeType.ALL)
	private Set<CommentQuote> commentQuotes = new HashSet<>();
}
