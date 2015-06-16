 app.controller('MainController', ['$scope', 'HighestBid', '$resource', function($scope, HighestBid, $resource) { 
  HighestBid.get({ id: 666 }, function(data) {
    $scope.highestBid = data;
 
 
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

$scope.auction = src.auction({ id: 666 });
$scope.auctions = src.auctions({ id: 'auctions' });
 });
}]); 
