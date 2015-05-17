FirstApp.controller('LanguageController', [
  '$scope',
  '$translate',
  '$location',
  function($scope, $translate, $location) {
    $scope.languages = [
      {name: 'mk'},
      {name: 'en'}
    ];

    $scope.isActive = function(lang) {
      return $translate.use() == lang;
    };

    $scope.changeLanguage = function(languageKey) {
      $translate.use(languageKey);
      moment.lang(languageKey);
    };
  }
]);
