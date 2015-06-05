FirstApp.controller('BookController',
  [
    '$scope',
    'crudService',
    '$routeParams',
    'toaster',
    'ngTableParams',
    function ($scope, crudService, $routeParams, toaster, ngTableParams) {
      var service = crudService('books');

      $scope.entities = service.query({}, function(data){
        $scope.tableParams = new ngTableParams({
          page: 1,            // show first page
          count: 10           // count per page
        }, {
          total: data.length, // length of data
          getData: function ($defer, params) {
            $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));
          }
        })
      });


      if ($routeParams.id) {
        $scope.entity = service.get({
          id: $routeParams.id
        });
      } else {
        $scope.entity = {};
      }

      $scope.edit = function (id) {
        $scope.entity = service.get({
          id: id
        });
      };

      $scope.save = function () {
        service.save($scope.entity, function (data) {
          $scope.entity = {};
          $scope.entities = service.query();
        });
      };

      $scope.remove = function (id) {
        service
          .remove(
          {
            id: id
          },
          function () {
            $scope.entities = service
              .query();
          },
          function (res) {
            if (res.status == 500) {
              // alert('ne moze da se
              // izbrise!');
              toaster
                .pop(
                'error',
                'Deleting error',
                "Ne moze da se izbrise objektot. Postoi drug objekt so nadvoresen kluc koj pokazuva kon nego!");
            }
          });

      };

    }]);
