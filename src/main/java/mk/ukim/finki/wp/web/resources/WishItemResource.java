package mk.ukim.finki.wp.web.resources;

import mk.ukim.finki.wp.model.OrderItem;
import mk.ukim.finki.wp.model.WishItem;
import mk.ukim.finki.wp.service.OrderItemService;
import mk.ukim.finki.wp.service.WishItemService;
import mk.ukim.finki.wp.web.CrudResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/data/rest/wish_items")
public class WishItemResource extends
        CrudResource<WishItem, WishItemService> {

    @Autowired
    private WishItemService service;

    @Override
    public WishItemService getService() {
        return service;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public WishItem create(@RequestBody WishItem entity, HttpServletRequest request,
                            HttpServletResponse response) {
        entity.setUserToken(tempToken(request));
        getService().save(entity);
        return entity;
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET, produces = "application/json")
    public List<WishItem> myWishItems(HttpServletRequest request) {
        return service.findByUserToken(tempToken(request));
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
