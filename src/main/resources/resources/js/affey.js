var affeyApp = angular.module('affeyApp', [ 'ui.router', 'ui.bootstrap', 'ngCookies' ]);

affeyApp.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise("/");
	$stateProvider.state("SignInNew", {
        url : "/signin",
        templateUrl : '/templates/SigninNew.html',
        controller : 'SigninFormCtrlNew'
    }).state("Logout", {
        url : "/signin",
        templateUrl : '/templates/SigninNew.html',
        controller : 'SigninFormCtrlNew',
        params : {
            logout : true
        }
	}).state("LoginLocal", {
		url : "/login",
		templateUrl : '/templates/loginlocal.html',
		controller : 'LoginLocalController'
	}).state("LogoutLocal", {
		url : "/login",
		templateUrl : '/templates/loginlocal.html',
		controller : 'LoginLocalController',
		params : {
			logout : true
		}
	});
});