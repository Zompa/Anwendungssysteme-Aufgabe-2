var services = angular.module('services', ['ngResource']);

services.factory('Auction', ['$resource',
  function($resource){
    return $resource('http://localhost:8080/myapp/auction/666', {}, {
      query: {method:'GET', isArray:false}
    });
  }]);