var app = angular.module("searchApp", []);
app.controller('MainController',['$scope','$http',
	function($scope,$http){
		var url = "http://localhost:8080/codesearch/v1/rest/getMatch/";
		var newURl = "";
		var fileUrl = "http://localhost:8080/codesearch/v1/rest/getFile/";
		var newFileUrl = "";
		
		$scope.submit = function() {
			
			
          	newUrl = url+ $scope.text;
			
			$http.get(newUrl).success( function(response) {
				
      			$scope.products = response; 
   			});	
		};
		$scope.readfile = function(path){

			var data;
			$scope.lines = [];

			path= path.replace(/[/]/g,"-");
			newFileUrl = fileUrl+path;

			console.log(newFileUrl);

			$http.get(newFileUrl).success(function (response) {
				//data = response;
				//data = data.replace(' ','&nbsp;');
				//$scope.lines = data.split('\n');
				$scope.lines = response;
            });
		}
	}
]);

// var myApp = angular.module('myApp',[]);

// myApp.controller('GreetingController', ['$scope', function($scope) {
//   $scope.greeting = 'Hola!';
// }]);