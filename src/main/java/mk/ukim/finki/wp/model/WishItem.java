package mk.ukim.finki.wp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by riste_000 on 4/15/2015.
 */
@Entity
@Table(name = "emk_wish_items")
public class WishItem extends BaseEntity {

  @ManyToOne
  private Book book;

  @ManyToOne
  private User user;

  private String userToken;

  private boolean inCart;

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getUserToken() {
    return userToken;
  }

  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }

  public boolean isInCart() {
    return inCart;
  }

  public void setInCart(boolean inCart) {
    this.inCart = inCart;
  }
}
