package mk.ukim.finki.wp.repository;

import mk.ukim.finki.wp.model.WishItem;

import java.util.List;

public interface WishItemRepository extends JpaSpecificationRepository<WishItem> {

  List<WishItem> findByUserToken(String s);
}
