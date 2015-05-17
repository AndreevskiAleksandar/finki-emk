package mk.ukim.finki.wp.service.impl;

import mk.ukim.finki.wp.model.OrderItem;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.repository.OrderItemRepository;
import mk.ukim.finki.wp.repository.UserRepository;
import mk.ukim.finki.wp.service.OrderItemService;
import mk.ukim.finki.wp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends
  BaseEntityCrudServiceImpl<User, UserRepository> implements
  UserService {

  @Autowired
  private UserRepository repository;

  @Override
  protected UserRepository getRepository() {
    return repository;
  }

  @Override
  public User findByUsername(String username) {
    return repository.findByUsername(username);
  }
}
