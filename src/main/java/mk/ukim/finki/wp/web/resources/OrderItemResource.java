package mk.ukim.finki.wp.web.resources;

import mk.ukim.finki.wp.model.Category;
import mk.ukim.finki.wp.model.OrderItem;
import mk.ukim.finki.wp.service.CategoryService;
import mk.ukim.finki.wp.service.OrderItemService;
import mk.ukim.finki.wp.web.CrudResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/data/rest/order_items")
public class OrderItemResource extends
        CrudResource<OrderItem, OrderItemService> {

  @Autowired
  private OrderItemService service;

  @Override
  public OrderItemService getService() {
    return service;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public OrderItem create(@RequestBody OrderItem entity, HttpServletRequest request,
                          HttpServletResponse response) {
    entity.setUserToken(tempToken(request));
    getService().save(entity);
    return entity;
  }

  @RequestMapping(value = "/my", method = RequestMethod.GET, produces = "application/json")
  public List<OrderItem> myOrderItems(HttpServletRequest request) {
      return  service.findByUserToken(tempToken(request));
  }

  @RequestMapping(value = "/check_pay", method = RequestMethod.POST, produces = "application/json")
  public void pay(HttpServletRequest request) {
    System.out.println("pay invoked");
  }

  public static String tempToken(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    for (Cookie c : cookies) {
      if (c.getName().equals("temp_token")) {
        return c.getValue();
      }
    }
    return null;
  }


}
