  var app = angular.module('tweetQuiz', []);

app.controller('MainCtrl', function($scope) {
  $scope.name = 'Main';
});

  var init = function() {
    var rootApi = 'http://1-dot-tweetquiz-164516.appspot.com/_ah/api/';
    gapi.client.load('tweetentityendpoint', 'v1', function() {
      console.log("API tweetentityendpoint loaded");
      
      gapi.client.tweetentityendpoint.listTweetEntity().execute(
          function(resp) {
            console.log(resp);
          });
    }, rootApi);
  }
  