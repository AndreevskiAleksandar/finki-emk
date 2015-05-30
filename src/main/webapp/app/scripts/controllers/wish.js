FirstApp.controller('WishController',
  [
    '$scope',
    'Wish',
    '$location',
    function ($scope, Wish, $location) {

      $scope.totalPayment = 0;

      $scope.entities = Wish.getMyWishList(function () {
        for (var i = 0; i < $scope.entities.length; i++) {
          $scope.totalPayment += $scope.entities[i].book.price;
        }
      });


    }]);
