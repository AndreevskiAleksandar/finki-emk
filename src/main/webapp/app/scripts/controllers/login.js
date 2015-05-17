FirstApp.controller('LoginController', [
  '$scope',
  '$rootScope',
  '$location',
  '$filter',
  'UserService',
  'toaster',
  function($scope, $rootScope, $location, $filter, UserService, toaster) {
    $scope.rememberMe = false;

    $scope.login = function() {
      UserService.authenticate($.param({
          username: $scope.username,
          password: $scope.password,
          rememberMe: $scope.rememberMe
        }), function(authenticationResult) {
          $rootScope.authToken = authenticationResult.token;
          UserService.get(function(u) {
            $rootScope.user = u;
          });
          $rootScope.loginAction = true;
          if($rootScope.returnPath
            && $rootScope.returnPath != "/login") {
            $location.path($rootScope.returnPath);
            delete $rootScope.returnPath;
          } else {
            $location.path("/");
          }
        },
        function() {
          toaster.pop('error', $filter('translate')('generic.login_error'),
            $filter('translate')('generic.invalid_username_or_password'));
        });
    };
  }]);
