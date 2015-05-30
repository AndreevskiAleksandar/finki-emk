/*
 * Generic CRUD resource REST service
 */
FirstApp.factory('Wish', ['$resource', 'settings', function($resource, settings) {

  return $resource('/data/rest/wish_items/:id', {}, {
    getMyWishList: {
      method: 'GET',
      url: "/data/rest/wish_items/my",
      isArray: true
    }
  });

}]);
