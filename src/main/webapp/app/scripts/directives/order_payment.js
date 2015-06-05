/*
 * Directive for generic combo (select)
 *
 */

FirstApp.directive('orderPayment', [
  'crudService',
  '$location',
  'ImageService',
  'settings',
  'Order',
  function (crudService, $location, ImageService, settings, Order) {
    return {
      restrict: 'AE',
      scope: {
        entities: '=',
        total: '='
      }, controller: function ($scope, $element, crudService, $cookies, settings, Order) {

        $scope.$watch('entities',
          function () {
            var form_html = '<input type="hidden" name="cmd" value="_cart">' +
              '<input type="hidden" name="upload" value="1">' +
              '<input type="hidden" name="business" value="test_button@gmail.com">';

            //console.log($scope.entities.length);
            angular.forEach($scope.entities, function (value, key) {
              // console.log(value);
            });
          }
        );


      }
      ,
      templateUrl: 'directives/order_payment.html'
    };
  }])
;

