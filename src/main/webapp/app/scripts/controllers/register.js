FirstApp.controller('RegisterController', [
  '$scope',
  '$rootScope',
  '$location',
  '$filter',
  'UserService',
  'toaster',
  function ($scope, $rootScope, $location, $filter, UserService, toaster) {

    $scope.register = function () {
      if ($scope.password !== $scope.confirmPassword) {
        toaster.pop('error', $filter('translate')('error'), $filter(
          'translate')('generic.confirm_password_missmatch'));
      } else {
        UserService.register($.param({
          username: $scope.username,
          password: $scope.password
        }), function (authenticationResult) {
          toaster.pop('success', $filter('translate')('success'), $filter(
            'translate')('generic.user_created'));
          $location.path("/login");
        });

      }
    };

  }]);
