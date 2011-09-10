(function() {
	//Create sub-namespace
	battle.network = {};
	
	//Get The leader board
	battle.network.loginUser = function(username, password) {
		var xhr = Titanium.Network.createHTTPClient();
		xhr.onload = function() {
			var arrData = [];
			  arrData = eval('('+this.responseText+')');
			  var result = arrData[0].result;
			  Ti.API.info("result:"+result); 
			  if(result === "true"){
			    var alrt_Success = Titanium.UI.createAlertDialog({
			      title: 'Success!',
			      message: 'You are now logged in and can battle away.',
			      buttonNames: ['OK']
			    });
			    alrt_Success.show();
				Ti.App.fireEvent('login');
			  } else {
			    var alrt_Sorry = Titanium.UI.createAlertDialog({
			      title: 'Unsuccessful...',
			      message: 'You have provided the incorrect username and/or password.',
			      buttonNames: ['OK']
			    });
			    alrt_Sorry.show();
			  }
		};
		var url = Ti.App.Properties.getString('base_url')+'username='+username+'&password='+password;
		Ti.API.info(url);
		xhr.open("GET",url);
		xhr.send();
	};
	
	//Get The leader board
	battle.network.getLeaderBoard = function(_cb) {
		var xhr = Titanium.Network.createHTTPClient();
		xhr.onload = function() {
			_cb(JSON.parse(this.responseText));
		};
		var url = 'http://api.twitter.com/1/statuses/user_timeline.json?screen_name=cleanwebhack';//Ti.App.Properties.getString('base_url');
		Ti.API.info(url);
		xhr.open("GET",url);
		xhr.send();
	};

	

})();