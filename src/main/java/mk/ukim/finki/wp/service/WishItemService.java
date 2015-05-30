package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.OrderItem;
import mk.ukim.finki.wp.model.WishItem;

import java.util.List;

public interface WishItemService extends BaseEntityCrudService<WishItem> {

  List<WishItem> findByUserToken(String s);
}
