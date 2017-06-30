affeyApp.service('AuthService', function ($http, $cookieStore) {
	
	var login = function(url, username, password, host, remember, 
		onSuccess, onFailure) {
		var authHashCode = 'Basic ' + btoa(username+':'+password);
		var authHeaders = {headers: {'Authorization': authHashCode}};
		$http.get(url, authHeaders)
		     .success(function(response){
				 $cookieStore.put('affey.authenticated', true);
				 $cookieStore.put('affey.auth', authHashCode);
				 $cookieStore.put('affey.user', username);
				 $cookieStore.put('affey.host', host);
				 $cookieStore.put('affey.remember', remember);
				 onSuccess && onSuccess();
			 }).error(function(){
				 $cookieStore.put('affey.authenticated', false);
				 onFailure && onFailure();
			 })
	}
	
	this.consoleUrl = function(host) {
        if (!host || host === '' || host === 'local') {
          return '';
        }
		var url = host;

		var httpPrefix = 'http://';
		var elbSuffix = '.elb.amazonaws.com';
		var portSuffix = ':8282';
		
		// check http
		if (url.slice(0, httpPrefix.length) !== httpPrefix) {
			// no http, add http
			url = httpPrefix + url;
		}
		
		// check elb
		if (url.indexOf(elbSuffix, url.length - elbSuffix.length) === -1) {

			// not elb, check port
			if (url.indexOf(portSuffix, url.length - portSuffix.length) === -1) {
				// no port, add port
				url = url + portSuffix;
			}
		}
		return url;
	}
	
	this.adminLogin = function(username, password, onSuccess, onFailure) {
		login('/api/login', username, password, 'admin', false, onSuccess, onFailure);
	}
	
	this.tenantLogin = function(host, username, password, remember, onSuccess, onFailure) {
		login(this.consoleUrl(host) + '/status', username, password, host, remember, onSuccess, onFailure);
	}
	
	this.logout = function() {
		$cookieStore.remove('affey.authenticated');
		$cookieStore.remove('affey.auth');
		if (!this.remember()) {
			$cookieStore.remove('affey.user');
			$cookieStore.remove('affey.host');
		}
	}

	this.headers = function() {
		return {'Authorization': $cookieStore.get('affey.auth')};
	}
	
	this.host = function() {
		return $cookieStore.get('affey.host') || '';
	}
	
	this.console = function() {
		return this.consoleUrl($cookieStore.get('affey.host'));
	}
	
	this.user = function() {
		return $cookieStore.get('affey.user') || '';
	}
	
	this.remember = function() {
		return $cookieStore.get('affey.remember') || false;
	}
	
});