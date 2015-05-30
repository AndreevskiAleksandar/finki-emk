/**
 * Created by Aleksandar on 28/5/2015.
 */
FirstApp.factory('ImageService', ['$resource', function ($resource) {
  return $resource('/books/photo/{objectId}', {}, {
    'getImage': {
      method: 'GET',
      url: "/books/photo/:objectId"
    }

  });
}]);
