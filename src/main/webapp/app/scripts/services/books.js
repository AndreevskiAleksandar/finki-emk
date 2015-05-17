/*
 * Generic CRUD resource REST service
 */
FirstApp.factory('BookService', ['$resource', 'settings', function($resource, settings) {

  return $resource('/data/rest/books/:id', {}, {
    findPromoted: {
      method: 'GET',
      url: "/data/rest/books/promoted",
      isArray: true
    },
    findByCategory: {
      method: 'GET',
      url: "/data/rest/books/by_category/:id",
      isArray: true
    },
    search: {
      method: 'GET',
      url: '/data/rest/books/search',
      isArray: true
    },
    paged: {
      method: 'GET',
      url: "/data/rest/books/paged"
    }
  });

}]);
