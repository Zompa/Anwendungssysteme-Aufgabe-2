var app = angular.module('app', ['ngRoute', 'mainControllers']);

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/auctionOverview', {
        templateUrl: 'auction-overview.html',
        controller: 'AuctionController'
      }).
      when('/auctions/:auctionID', {
        templateUrl: 'details.html',
        controller: 'AuctionController'
      }).
      otherwise({
        redirectTo: '/auctionOverview'
      });
  }]);