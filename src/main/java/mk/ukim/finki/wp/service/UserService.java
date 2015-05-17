package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.OrderItem;
import mk.ukim.finki.wp.model.User;

import java.util.List;

public interface UserService extends BaseEntityCrudService<User> {

  User findByUsername(String username);
}
