var services = angular.module('services', ['ngResource']);

services.factory('Auction', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/myapp/auction/:id', {}, {
      query: {method:'GET', isArray:false}
    });
  }]);
	
services.factory('HighestBid', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/myapp/auction/:id/highestBid', {}, {
      query: {method:'GET', isArray:false}
    });
  }]);