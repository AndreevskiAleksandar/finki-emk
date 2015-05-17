/*
 * Generic CRUD resource REST service
 */
FirstApp.factory('crudService', ['$resource', 'settings', function($resource, settings) {
  return function(type) {
    return $resource('/data/rest/' + type + '/:id', {}, {
      import: {
        method: 'POST',
        url: "/data/rest/" + type + "/import"
      },
      paged: {
        method: 'GET',
        url: "/data/rest/" + type + "/paged"
      }
    });
  };
}]);
