'use strict';

/**
 * @ngdoc Simple controller definition that have the $scope and firstService
 *        injected by angular. The $scope is the glue between the controller
 *        and the view that displays the information. The controller is not
 *        aware about the view that displays the information.
 *
 * @name avAngularStartupApp.controller:MainCtrl
 * @description # MainCtrl Controller of the avAngularStartupApp
 */

FirstApp.controller('MainCtrl', ['$scope', 'BookService', '$rootScope',
  function ($scope, BookService, $rootScope) {
    $scope.promotedBooks = BookService.findPromoted({}, function(data){
        //$("#sidebar_helper").css("height" ,data.length * 250 + "px");
    });

    $scope.showBook = function (book) {
      $location.path("/book/details/" + book.id);
    };
  }]);
