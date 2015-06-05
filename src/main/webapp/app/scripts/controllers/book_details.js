FirstApp.controller('BookDetailsController',
  [
    '$scope',
    'crudService',
    '$routeParams',
    'toaster',
    'settings',
    function ($scope, crudService, $routeParams, settings) {
      var service = crudService('books');
      var ordersService = crudService('order_items');
      var wishService = crudService('wish_items');

      if ($routeParams.id) {
        $scope.entity = service.get({
          id: $routeParams.id
        }, function () {
          //TODO
          //CONTEXT PATH = undefined
          //Settings = Constructor ??
          $scope.img_url = "/e-comerce" + "/books/photo/" + $scope.entity.id;
        });
      } else {

        $scope.entity = {};
      }


      $scope.addToWishlist = function () {
        wishService.save({
          book: {
            id: $scope.entity.id
          }
        });
      }

      $scope.addToCart = function () {
        ordersService.save({
          book: {
            id: $scope.entity.id
          }
        });
      }


    }]);
