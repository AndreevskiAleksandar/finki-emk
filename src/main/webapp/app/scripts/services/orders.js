/*
 * Generic CRUD resource REST service
 */
FirstApp.factory('Order', ['$resource', 'settings', function($resource, settings) {

  return $resource('/data/rest/order_items/:id', {}, {
    getMyOrders: {
      method: 'GET',
      url: "/data/rest/order_items/my",
      isArray: true
    },
    checkPay: {
      method: 'POST',
      url: "/data/rest/order_items/check_pay"
    }
  });

}]);
