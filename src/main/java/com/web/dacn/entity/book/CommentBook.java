package com.web.dacn.entity.book;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.dacn.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="commentbook")
public class CommentBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "nvarchar(MAX)")
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mod_time")
	private Date mod_time;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = CommentBook.class)
	@JoinColumn(name="parent_id")
	private CommentBook commentBook;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
	@JoinColumn(name="user_id")
	private User user; 
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Book.class)
	@JoinColumn(name="book_id")
	private Book book;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commentBook", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<CommentBook> commentBooks;

	@Override
    public int hashCode() {
		 return Objects.hash(getId());
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommentBook)) {
            return false;
        }
        CommentBook that = (CommentBook) obj;
        return  Objects.equals(getId(),that.getId());
    }
}
