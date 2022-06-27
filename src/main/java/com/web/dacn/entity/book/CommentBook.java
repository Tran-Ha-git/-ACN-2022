package com.web.dacn.entity.book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
	private Date modTime;
	
	@ManyToOne(targetEntity = CommentBook.class)
	@JoinColumn(name="parent_id")
	@JsonIgnore
	private CommentBook commentBook;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user; 
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Book.class)
	@JoinColumn(name="book_id")
	@JsonIgnore
	private Book book;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "commentBook")
	@JsonIgnore
	private List<CommentBook> commentBooks = new ArrayList<>();
	
	private Integer status = 1;
	
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
