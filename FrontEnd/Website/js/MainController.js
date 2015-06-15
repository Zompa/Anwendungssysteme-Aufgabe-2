app.controller('MainController', ['$scope', 'Auction', function($scope, Auction) { 

  $scope.auction = Auction.query();
  // $scope.auction = Auction.get({666}, function(auction) {
	

   $scope.plusOne = function(index) { 
  $scope.products[index].likes += 1; 
   };
 $scope.minusOne = function(index) { 
  $scope.products[index].dislikes += 1; 
};
}]);