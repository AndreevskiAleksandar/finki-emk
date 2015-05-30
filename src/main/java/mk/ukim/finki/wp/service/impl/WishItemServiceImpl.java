package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.WishItem;
import mk.ukim.finki.wp.repository.WishItemRepository;
import mk.ukim.finki.wp.service.WishItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishItemServiceImpl extends
        BaseEntityCrudServiceImpl<WishItem, WishItemRepository> implements
        WishItemService {

  @Autowired
  private WishItemRepository repository;

  @Override
  protected WishItemRepository getRepository() {
    return repository;
  }

  @Override
  public List<WishItem> findByUserToken(String s) {
    return getRepository().findByUserToken(s);
  }
}
