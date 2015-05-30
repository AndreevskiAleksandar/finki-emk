FirstApp.controller('BookDetailsController',
  [
    '$scope',
    'crudService',
    '$routeParams',
    'toaster',
    function ($scope, crudService, $routeParams, toaster) {
      var service = crudService('books');


      if ($routeParams.id) {
        $scope.entity = service.get({
          id: $routeParams.id
        });
      } else {
        $scope.entity = {};
      }



    }]);
