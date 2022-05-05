package com.web.dacn.entity.book;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.web.dacn.entity.user.Author;
import com.web.dacn.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", 
			nullable = false, 
			columnDefinition = "NVARCHAR(100)")
	private String name;

	@Column(name = "thumbnail", columnDefinition = "TEXT")
	private String thumbnail;

	@Column(name = "view", 
			columnDefinition = "INTEGER DEFAULT 1",
			nullable = false)
	private Integer view;

	@Column(name = "price")
	private Double price;

	@Column(name = "vip")
	private Boolean vip;

	@Column(name = "description", 
			columnDefinition = "TEXT",
			nullable = false)
	private String description;

	@Column(name = "slug", 
			columnDefinition = "VARCHAR(2000)",
			nullable = false)
	private String slug;

	@Column(name = "meta_title",
			columnDefinition = "NVARCHAR(100)")
	private String metaTitle;
	
	@Column(name = "meta_description", columnDefinition = "TEXT")
	private String metaDescription;

	@Column(name = "status", 
			nullable = false, 
			columnDefinition = "INTEGER DEFAULT 1")
	private Integer status;

	@Column(name = "mod_time", nullable = false)
	private Date mod_time;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
	@JoinColumn(name="mod_user_id")
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", 
		joinColumns = @JoinColumn(name = "book_id", nullable = false), 
		inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false))
	private Set<Author> authors;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_bookcategory", 
		joinColumns = @JoinColumn(name = "book_id", nullable = false), 
		inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))

	private Set<BookCategory> categories;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<Audio> audios;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<Online> onlines;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<Pdf> pdfs;

	@Override
    public int hashCode() {
		 return Objects.hash(getId());
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book that = (Book) obj;
        return  Objects.equals(getId(),that.getId());
    }
}
