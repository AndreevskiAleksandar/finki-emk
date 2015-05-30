/*
 * Directive for generic combo (select)
 *
 */

FirstApp.directive('bookDisplay', [
  'crudService',
  '$location',
  'ImageService',
  'settings',
  function (crudService, $location, ImageService,settings) {
    return {
      restrict: 'AE',
      scope: {
        entity: '=',
        shoppingCart: '=',
        wishlist: '=',
        bean: '@'
      },
      compile: function (tElem, attrs) {


        return function (scope, elem, attrs) {

        };
      },
      controller: function ($scope, $element, crudService, $cookies, settings) {
        var ordersService = crudService('order_items');
        var wishService = crudService('wish_items');

        if ($scope.entity) {
          $scope.img_url = settings.contextPath + "/books/photo/"+$scope.entity.id;

          $scope.attachments = ImageService.getImage({
            id: $scope.entity.id
          });
        }



        $scope.display = function () {
          $location.path('/book/details/' + $scope.entity.id);
        };


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
      },
      templateUrl: 'directives/book-display.html'
    };
  }]);

