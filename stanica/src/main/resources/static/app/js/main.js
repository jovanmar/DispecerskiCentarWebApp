var stanicaApp = angular.module("stanicaApp", ['ngRoute']);

stanicaApp.controller("homeCtrl", function($scope){
	$scope.message = "Welcome to stanicaApp!";
});


stanicaApp.controller("staniceCtrl", function($scope, $http, $location){
	
	
	var voznjaUrl = "/api/voznje";
	var staniceUrl = "/api/stanice";
	
	$scope.voznje = [];
	$scope.stanice = [];

	$scope.newStanica = {};
	$scope.newStanica.vreme = "";
	$scope.newStanica.adresa = "";
	$scope.newStanica.redniBroj = "";

	$scope.newStanica.voznjaId = "";
	
	$scope.searchParams = {};
	$scope.searchParams.voznjaId = "";
	$scope.searchParams.adresa = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1
	
	
	
	var getStanice = function(){
		
		var config = { params: {} };		
		
		if($scope.searchParams.voznjaId != ""){
			config.params.voznjaId = $scope.searchParams.voznjaId;
		}
		
		if($scope.searchParams.adresa != ""){
			config.params.adresa = $scope.searchParams.adresa;
		}
		
//		if($scope.searchParams.cenaKarte != ""){
//			config.params.maksCena = $scope.searchParams.cenaKarte;
//		}
//		
		config.params.pageNum = $scope.pageNum;
		
		$http.get(staniceUrl, config).then(
			function success(res){
				$scope.stanice = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Neupešno dobavljanje stanica.");
			}
		);
	}
	
	getStanice();
	
	
	var getVoznje = function(){
		$http.get(voznjaUrl).then(
			function success(res){
				$scope.voznje = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje voznji.");
			}
		);
	}
	
	getVoznje();
	
	
	$scope.doAdd = function(){
		
		$http.post(staniceUrl, $scope.newStanica).then(
			function success(){
				getStanice();
				
				$scope.newStanica = {};

			},
			function error(){
				alert("Neuspešno čuvanje stanice!");
			}
		);
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(staniceUrl + "/" + id);
		promise.then(
			function success(){
				getStanice();
			},
			function error(){
				alert("Neuspešno brisanje stanice.");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/stanice/edit/" + id);
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getStanice();
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getStanice();
	}
	
	$scope.doClearSearch = function(){
		$scope.searchParams = {};
		$scope.searchParams.voznjaId = "";
		$scope.searchParams.adresa = "";
		getStanice();
	}
	
	
	$scope.goToPotvrdi = function(id){
		$location.path("/stanice/izvestaj/" + id);
	}
	
//	$scope.disableBtn = function (boolean) {
//		if(boolean == true){
//			document.getElementById("potvrdiBtn").disabled = false;
//		}else{
//			document.getElementById("potvrdiBtn").disabled = true;
//		}
//		
//	}
		
	
});


stanicaApp.controller("editStanicaCtrl", function($scope, $http, $routeParams, $location){
	
	var stanicaUrl = "/api/stanice/" + $routeParams.id;
	var voznjeUrl = "/api/voznje";

	$scope.voznje = [];
	
	$scope.stanica = {};
	$scope.stanica.vreme = "";
	$scope.stanica.adresa = "";
	$scope.stanica.redniBroj = "";
//	$scope.linija.destinacija = "";

	$scope.voznjaId = "";
	
	var getVoznje = function(){
		$http.get(voznjeUrl).then(
			function success(res){
				$scope.voznje = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje voznji.");
			}
		);
	}
	
	getVoznje();
	
	
	var getStanica = function(){
		$http.get(stanicaUrl).then(
			function success(res){
				$scope.stanica = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje stanice.");
			}
		);
	}
	
	//Ako bismo želeli da radimo kaskadiranje radi omogućavanja ng-selected odabira voznji,
	//onda bismo ovo morali da prebacimo u success callback pod getVoznje. Tu je izostavljen
	//taj mehanizam radi jednostavnosti.
	
	getStanica();
	
	
	$scope.doEdit = function(){
		$http.put(stanicaUrl, $scope.stanica).then(
			function success(){
				$location.path("/stanice");
			},
			function error(){
				alert("Neuspešna izmena stanice.");
			}
		);
	}
});

stanicaApp.controller("izvestajCtrl", function($scope, $http, $routeParams, $location){
	
	var stanicaUrl = "/api/stanice/" + $routeParams.id;
	var voznjeUrl = "/api/voznje";
	
	var getStanica = function(){
		$http.get(stanicaUrl).then(
			function success(res){
				$scope.stanica = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje stanice.");
			}
		);
	}
	
	getStanica();
	$scope.newIzvestaj = {};
	$scope.newIzvestaj.poruka = '';
	
	$scope.doPotvrdi = function(id){
		$scope.newIzvestaj.stanicaId = $routeParams.id;
		$scope.newIzvestaj.stanicaVreme = $scope.stanica.vreme;
		$scope.newIzvestaj.stanicaAdresa = $scope.stanica.adresa;
		$scope.newIzvestaj.brojVoznje = $scope.stanica.brojVoznje;
		
		var promise = $http.post(stanicaUrl, $scope.newIzvestaj);
		promise.then(
			function success(){
				alert("Uspešno sacuvan izvestaj.")
				$location.path("/stanice");
				getStanice();
				
			},
			function error(){
				alert("Neuspešna operacija.");
			}
		);
	}
	
	
	
});


stanicaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: 'homeCtrl'
		})
		.when('/stanice', {
			templateUrl : '/app/html/stanice.html'
		})
		.when('/stanice/edit/:id', {
			templateUrl : '/app/html/edit-stanica.html'
		})
		.when('/stanice/izvestaj/:id', {
			templateUrl : '/app/html/stanica-izvestaj.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);