 var mainControllers = angular.module('mainControllers', ['ngResource']);
 
 mainControllers.controller('RouteController', ['$scope', '$routeParams', function($scope, $routeParams) { 
$scope.auctionID = $routeParams.auctionID;
 }]); 
 
 
  mainControllers.controller('AuctionController', ['$scope', '$resource', '$routeParams', function($scope, $resource, $routeParams) { 
 var auctionSrc = $resource('http://localhost:8080/myapp/auction/:id',
              {id: "@id", cmd: "@cmd"}, 
              {
                auctions: { method: "GET", params: {}, isArray:true },
                auction: { method: "GET", params: { id: 0 } },         				
				highestBid: { method: "GET", params: { id: 0 } }, 
				deleteAuction: { method: "DELETE", params: { id: 0 } },
                //CreateTodo: { method: "POST", params: { content: "", order: 0, done: false } },
                //UpdateTodo: { method: "PATCH", params: { /*...*/ } },
                
                //ResetTodos: { method: "GET", params: { cmd: "reset" } },				
              });			  
			  
var bidSrc = $resource('http://localhost:8080/myapp/auction/:id/highestBid',
              {id: "id", cmd: "@cmd"}, 
              {     				
				highestBid: { method: "GET", params: { id: 0 } }, 
                makeBid: { method: "POST", params: {id: 0} }			
              });	

$scope.auctions = auctionSrc.auctions({ id: 'auctions' });
$scope.auctionID = $routeParams.auctionID;
$scope.auction = auctionSrc.auction({ id: $scope.auctionID });
$scope.highestBid = bidSrc.highestBid({ id: $scope.auctionID });

$scope.makeBid = function(mybid){
$scope.mybid.userID = 'PaulBrowser';
//bidSrc.id = $scope.auctionID ;
bidSrc.makeBid({ id: $scope.auctionID, mybid });
 console.log( $scope.auctionID );
}

$scope.deleteAuction = function(){
auctionSrc.deleteAuction({ id: $scope.auctionID });
window.location.href = '/Website/#/auctionOverview';
}
/*
function($routeProvider) {
    $routeProvider.
      otherwise({
        redirectTo: '/Website/#/auctionOverview'
      });
  }]);
*/

//var ID = $routeParams.auctionID;
//console.log("ID: " + ID);
 }]); 
 
 


 
 