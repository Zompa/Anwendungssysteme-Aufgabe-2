 var mainControllers = angular.module('mainControllers', ['ngResource']);
 
 mainControllers.controller('RouteController', ['$scope', '$routeParams', function($scope, $routeParams) { 

$scope.auctionID = $routeParams.auctionID;
 }]); 
 
 
  mainControllers.controller('AuctionController', ['$scope', '$resource', '$routeParams', function($scope, $resource, $routeParams) { 

 var src = $resource('http://localhost:8080/myapp/auction/:id',
              {id: "@id", cmd: "@cmd"}, 
              {
                auctions: { method: "GET", params: {}, isArray:true },
                auction: { method: "GET", params: { id: 0 } },         
				
                //CreateTodo: { method: "POST", params: { content: "", order: 0, done: false } },
                //UpdateTodo: { method: "PATCH", params: { /*...*/ } },
                //DeleteTodo: { method: "DELETE", params: { id: 0 } },
                //ResetTodos: { method: "GET", params: { cmd: "reset" } },
				
              });


$scope.auctions = src.auctions({ id: 'auctions' });
$scope.auctionID = $routeParams.auctionID;
$scope.auction = src.auction({ id: $scope.auctionID });
var ID = $routeParams.auctionID;
console.log("ID: " + ID);
 }]); 

 
 