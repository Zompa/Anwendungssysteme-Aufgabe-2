app.controller('MainController', ['$scope', function($scope) { 
  $scope.highestBid = 50;
  $scope.endDate = '18:05:09';
  

   $scope.plusOne = function(index) { 
  $scope.products[index].likes += 1; 
   };
 $scope.minusOne = function(index) { 
  $scope.products[index].dislikes += 1; 
};
}]);