  // create the module 
  var tweetQuiz = angular.module('tweetQuiz', ['ngRoute', 'tweetQuiz.Services']);

  // configure our routes
  tweetQuiz.config(function($routeProvider) {
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
      })
      
      .when('/bestScores', {
    	templateUrl : '/assets/partials/bestScores.html',
    	controller  : 'bestScoresController'
      });
  });


    // create the controllers
  tweetQuiz.controller('mainController', function($scope, WebService, $window, $rootScope, $location) {
	  $scope.loading = false;
    $scope.play = function(playerName) {
    	if (playerName){
    		$scope.loading = true;
    		WebService.get("/tweetquizzzz?playerName="+playerName).$promise.then(function(data) {
    			if (data.error){
    				$window.alert(data.error);
    			} else {
    				$rootScope.tweets = data;
            		$rootScope.turn = 0;
            		$rootScope.results = 0;
            		$location.path('/inGame');
    			}
        		
          });
    	} else {
    		$window.alert("Vous devez saisir votre compte twitter!");
    	}
    }
  });

  tweetQuiz.controller('inGameController', function($scope, $location, $rootScope, $route) {
	  $scope.buttonContinue = false;
	  $scope.currentTweet = $rootScope.tweets[$rootScope.turn];
	  $scope.answers = [];
	  var array = [$scope.currentTweet.author, $scope.currentTweet.falseAuthor1, $scope.currentTweet.falseAuthor2, $scope.currentTweet.falseAuthor3];
	  var n = array.length;
	  var i;

	  // While there remain elements to shuffle…
	  while (n) {

	    // Pick a remaining element…
	    i = Math.floor(Math.random() * array.length);

	    // If not already shuffled, move it to the new array.
	    if (i in array) {
	    	$scope.answers.push(array[i]);
	    	delete array[i];
	    	n--;
	    }
	  }
	  
	  //Player input
	  $scope.inputResult = function(result) {
		  document.getElementById("button0").setAttribute('disabled', 'disabled');;
		  document.getElementById("button1").setAttribute('disabled', 'disabled');;
		  document.getElementById("button2").setAttribute('disabled', 'disabled');;
		  document.getElementById("button3").setAttribute('disabled', 'disabled');;

		  if ($scope.answers[result] === $rootScope.tweets[$rootScope.turn].author){
			  $rootScope.results += 1;
			  document.getElementById("button"+result).style.background='#329932';
		  } else {
			  var j = 0;
			  while ($scope.answers[j] != $rootScope.tweets[$rootScope.turn].author){
				  j += 1;
			  }
			  document.getElementById("button"+j).style.background='#329932';
			  document.getElementById("button"+result).style.background='#ff6666';
		  }
		  $scope.buttonContinue = true;
		  $rootScope.turn += 1;
	  }
	  
	  //Player wants to play next question
	  $scope.next = function() {
		  if ($rootScope.turn === 10){
			  $location.path('/results');
		  } else {
			  $route.reload();
		  }
		  
	  }
  });

  tweetQuiz.controller('resultsController', function($scope, $rootScope, $location) {
	  $scope.nbGoodAnswers = $rootScope.results;
	  //WebService.get("/tweetquizzzz?playerName="+playerName&score=$scope.nbGoodAnswers).$promise.then(function(data) {}
	  
	  $scope.home = function() {
		  $location.path('/');
	  }
  });
  
  tweetQuiz.controller('bestScoresController', function($scope, $rootScope, $location) {
	  /*
	   * WebService.get("/tweetquizzzz?bestScore="getBestScores").$promise.then(function(data) {
	   * 	$scope.bestScores = data;
	   * }
	  */
  });
  