// NOT IN USE
var services = angular.module('services', ['ngResource']);

services.factory('Auction', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/myapp/auction/:id', {}, {
		query: {method:'GET', params:{id:'auctions'}, isArray:true}
	});
  }]);
	
	
services.factory('HighestBid', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/myapp/auction/:id/highestBid', {}, {
    });
  }]);
  


services.factory('Auctions', ['$resource',
  function($resource){
     return $resource('http://localhost:8080/myapp/auction/', {}, {
		query: {method:'GET', params:{id:'auctions'}, isArray:true}
	});
  }]);


  
  
