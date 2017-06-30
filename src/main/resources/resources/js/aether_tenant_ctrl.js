aetherApp.controller('ClusterAdminController', function($state, $scope,$timeout,$modal,
         TenantService) {
	
    $scope.tenants = [];
    
	$scope.alert = {
        message : '',
        type : ''
    };
	
	$scope.sortType = 'tenantName';
    $scope.sortReverse = false;
    $scope.clusterId = $state.params.id;

    $scope.fn = function() {
        TenantService.listTenants($scope.console).success(function(data) {
            $scope.tenants = data;
        })
    }

    $scope.host = ClusterService.getConsole();
    $scope.console = ClusterService.getConsole();
    $scope.fn();

    $scope.terminate = function() {
        $scope.console = ClusterService.getConsole();
    };

    $scope.addTenant = function() {
        var data = $scope.tenantConfig;
        if(data.resourceQuota == null) {
            data.resourceQuota = {};
        }
        $.each(data.resourceQuota, function(key, value){
            if (value === "" || value === null){
                delete data.resourceQuota[key];
            }
        })

        TenantService.createTenant($scope.console, data).success(
                function(data) {
                	$scope.alert.message = 'Tenant:\"'+data.tenantName+'\" successfully added';
                    $scope.alert.type = 'success';
                    TenantService.listTenants($scope.console).success(
                            function(data) {
                                $scope.tenants = data;
                            });
                    $timeout(function() {
                        $scope.closeAlert();
                    }, 2000);
                }).error(function(response) {
            $scope.alert.message = response;
            $scope.alert.type = 'danger';
        });
        
        $scope.tenantConfig = {};
    };
    
    $scope.deleteTenant = function(name) {
        TenantService.deleteTenant($scope.console, name).success(
                function(data) {
                	$scope.alert.message = 'Tenant:\"'+name+'\" successfully deleted';
                    $scope.alert.type = 'success';
                    TenantService.listTenants($scope.console).success(
                            function(data) {
                                $scope.tenants = data;
                            });
                    $timeout(function() {
                        $scope.closeAlert();
                    }, 2000);
                }).error(function(response) {
            $scope.alert.message = response;
            $scope.alert.type = 'danger';
        });
    };
    
    $scope.updateEnabled = function(name,enabled) {
        TenantService.updateEnabled($scope.console, name,enabled).success(
                function(data) {
                	$scope.alert.message = enabled==false?'Tenant:\"'+name+'\" disabled successfully':'Tenant:\"'+name+'\" enabled successfully';
                    $scope.alert.type = 'success';
                    TenantService.listTenants($scope.console).success(
                            function(data) {
                                $scope.tenants = data;
                            });
                    $timeout(function() {
                        $scope.closeAlert();
                    }, 2000);
                }).error(function(response) {
            $scope.alert.message = response;
            $scope.alert.type = 'danger';
        });
    };
    
    $scope.editTenant = function(tenantName) {
    	
        var modalInstance = $modal.open({
            animation : true,
            size : 'md',
            templateUrl : 'editTenant.html',
            controller : 'EditTenantController',
            resolve : {
            	tenantName : function() {
                    return tenantName;
                }
            }

        });
        
        modalInstance.result.then(function(tenantName) {
        	$scope.alert.message = 'Tenant:\"'+tenantName+'\" successfully updated';
            $scope.alert.type = 'success';
        	TenantService.listTenants($scope.console).success(
                    function(data) {
                        $scope.tenants = data;
                    });
        	$timeout(function() {
                $scope.closeAlert();
            }, 2000);
        });

    }

    $scope.closeAlert = function() {
        $scope.alert.message = '';
        $scope.alert.type = '';
    }

});

aetherApp.controller('EditTenantController', function($state, $scope,
    $modalInstance, tenantName, ClusterService, TenantService) {
	$scope.tenantConfig={
		password:""
	};
    $scope.updateType='password';
    $scope.alert = {
            message : '',
            type : ''
        };
    $scope.dismissPopup = function() {
        $modalInstance.dismiss('');
    }
    
    $scope.host = ClusterService.getConsole();
    $scope.console = ClusterService.getConsole();
    
    $scope.updateTenant = function() {
        var data = $scope.tenantConfig;
        if(data.resourceQuota == null) {
            data.resourceQuota = {};
        }
        $.each(data.resourceQuota, function(key, value){
            if (value === "" || value === null){
                delete data.resourceQuota[key];
            }
        })
    	TenantService.updateTenant($scope.console,tenantName,data).success(
            function() {
            	$modalInstance.close(tenantName);
            }).error(function(response) {
        $scope.alert.message = response;
        $scope.alert.type = 'danger';
    });
    	
    }

});
