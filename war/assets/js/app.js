  // create the module 
  var tweetQuizApp = angular.module('tweetQuiz', ['ngRoute', 'tweetQuizApp.Services']);

  // configure our routes
  tweetQuizApp.config(function($routeProvider) {
      $routeProvider

      // route for the home page
      .when('/', {
        templateUrl : '/assets/partials/home.html',
        controller  : 'mainController'
      })

      // route for the about page
      .when('/inGame', {
        templateUrl : '/assets/partials/inGame.html',
        controller  : 'inGameController'
      })

      // route for the contact page
      .when('/results', {
        templateUrl : '/assets/partials/results.html',
        controller  : 'resultsController'
      });
  });


    // create the controllers
  tweetQuizApp.controller('mainController', function($scope, WebService) {
    $scope.play = function(playerName) {
        //WebService.get("http://1-dot-tweetquiz-164516.appspot.com/tweetquizzzz?playerName="+playerName).$promise.then(function(data) {
    	WebService.get("/tweetquizzzz?playerName="+playerName).$promise.then(function(data) {
        alert('Player input : '+ data);
      });
   
    }
  });

  tweetQuizApp.controller('aboutController', function($scope) {
    
  });

  tweetQuizApp.controller('contactController', function($scope) {
    
  });