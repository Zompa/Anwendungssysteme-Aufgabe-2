app.controller('MainController', ['$scope', 'Auction', 'HighestBid', function($scope, Auction, HighestBid) { 

  Auction.get({ id: 666 }, function(data) {
    $scope.auction = data;
  });
  
  HighestBid.get({ id: 666 }, function(data) {
    $scope.highestBid = data;
  });

  //$scope.auction = Auction.query();
  // $scope.auction = Auction.get({666}, function(auction) {
	

   $scope.plusOne = function(index) { 
  $scope.products[index].likes += 1; 
   };
 $scope.minusOne = function(index) { 
  $scope.products[index].dislikes += 1; 
};
}]);