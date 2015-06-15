app.controller('MainController', ['$scope', 'Auction', function($scope, Auction) { 
  $scope.highestBid = 50;
  $scope.endDate = '18:05:09';
  $scope.auction =
  {
    "auctionID": 666,
    "auctionDetails": {
        "title": "Tollster titel",
        "description": "description",
        "imageURL": "example.com",
        "endDate": 1000
    }
  }
  $scope.auction = Auction.query();
  // $scope.auction = Auction.get({666}, function(auction) {
	

   $scope.plusOne = function(index) { 
  $scope.products[index].likes += 1; 
   };
 $scope.minusOne = function(index) { 
  $scope.products[index].dislikes += 1; 
};
}]);