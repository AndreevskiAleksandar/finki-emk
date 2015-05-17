'use strict';

/**
 * @ngdoc Definition of the application module. The first argument is the name
 *        of the module. It is used in the ng-app directive to expose the
 *        angular components that can be used. The second argument is an array
 *        that defines the dependencies (modules) that are used by the
 *        application. In this case we are only use the ngRoute module as a
 *        dependency in order to provide partial content inclusion through the
 *        routes
 * @see router.js for more information
 * @name avAngularStartupApp - the name of the module used in the ng-app
 *       directive
 * @description # avAngularStartupApp Main module of the application.
 */
var FirstApp = angular.module('avAngularStartupApp', ['ngResource', 'ngRoute',
  'ngAnimate', 'ngTable', 'ngTableExport', 'ngCookies',
  'chieffancypants.loadingBar', 'ui.bootstrap', 'ui.select2',
  'mgcrea.ngStrap', 'toaster', 'angularFileUpload',
  'pascalprecht.translate']);

FirstApp.config(['$translateProvider', '$httpProvider', 'settings',
  function($translateProvider, $httpProvider, settings) {

    $httpProvider.interceptors.push('HttpInterceptors');

    // Initialize angular-translate
    $translateProvider.useStaticFilesLoader({
      prefix: 'i18n/',
      suffix: '.json'
    });

    $translateProvider.preferredLanguage(settings.language);

    $translateProvider.useCookieStorage();

  }]);

FirstApp.run([
  '$rootScope',
  '$http',
  '$cookies',
  '$location',
  'crudService',
  '$cookieStore',
  'UserService',
  function($rootScope,
           $http,
           $cookies,
           $location,
           crudService,
           $cookieStore,
           UserService) {

    var categoryService = crudService('categories');

    $rootScope.categories = categoryService.query();

    $rootScope.authToken = $cookieStore.get('token');
    if($rootScope.authToken) {
      UserService.get(function(u) {
        $rootScope.user = u;
      });
    }

    $rootScope.logout = function() {
      delete $rootScope.user;
      delete $rootScope.authToken;
      $cookies['token'] = null;
      delete $cookies['token'];
      var delete_cookie = function(name) {
        document.cookie = name + '=; Path=/e-auction; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
      };
      delete_cookie('token');
      $location.path("/login");
    };

    if(!$rootScope.authToken) {
      var tempTokenService = $http.get('/data/rest/token').
        success(function(data, status, headers, config) {
          console.log('token obtained')
        });
    }


  }]);
