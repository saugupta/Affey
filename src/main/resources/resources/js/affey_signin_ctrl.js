affeyApp.controller('IndexCtrl' , function ($state, $scope) {
	
	$state.go("LoginLocal");
		
});

affeyApp.controller('LoginLocalController' , function ($state, $scope, AuthService) {
	
	if ($state.params.logout) {
		AuthService.logout();
	}
	
	$scope.remember = AuthService.remember();
	$scope.username = AuthService.user();
	$scope.password = '';
	$scope.id = "local";
	
	$scope.alert = {
			message:'',
			type:''
		};						
	
	$scope.loginGeneric = function(onSuccess) {
        var tenantName = $scope.username;
        var password = $scope.password;     
        var remember = $scope.remember;
        
        
        AuthService.tenantLogin('local', tenantName, password, remember,
        function() {
            onSuccess && onSuccess();
        }, function() {
            $scope.alert.message = 'Invalid credentials, failed to login';
            $scope.alert.type = 'danger';
        });
	}
	
    $scope.loginAsTenant = function() {
      $scope.loginGeneric(function() {
			  $state.go('ListJobs', {
			   clusterId : $scope.id,
			   tenantName : $scope.username
			  });
		  });
    }
	
    $scope.loginAsAdmin = function() {
        $scope.loginGeneric(function() {
			$state.go('ClusterAdminNew', {id: $scope.id});
		});
    }
	
	$scope.closeAlert = function() {
		$scope.alert.message = '';
	}		
		
});
