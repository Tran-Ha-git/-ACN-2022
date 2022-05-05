package com.web.dacn.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.BookMark;
import com.web.dacn.entity.book.Book_BookCategory;
import com.web.dacn.entity.quote.Quote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uname", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	private String fullname;

	private String email;

	private Date birthday;

	private String address;

	private String phone;

	private Integer status;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "favoritequote",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id")
            )
	private Set<Quote> favoriteQuotes = new HashSet<>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "favoritebook",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
            )
	private Set<Book> favoriteBooks = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(	name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id", nullable = false), 
				inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	private Set<BookMark> bookmarks;
	

	@Override
    public int hashCode() {
		 return Objects.hash(getId());
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User that = (User) obj;
        return  Objects.equals(getId(),that.getId());
    }
}
