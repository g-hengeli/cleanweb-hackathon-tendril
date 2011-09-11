(function() {
	//Create sub-namespace
	battle.network = {};
	
	//Get The leader board
	battle.network.loginUser = function(username, password) {
		var xhr = Titanium.Network.createHTTPClient();
		xhr.onload = function() {
			var arrData = [];
			Ti.API.info(this.responseText);
			  arrData = eval('('+this.responseText+')');
			  var zipCode = arrData.zipCode;
			  Ti.App.Properties.setInt('user_zip', zipCode);
			  //alert('in here');
			  if(zipCode){
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
		
		xhr.onerror = function() {
		    alert('error' + this.status);
		}
		var url = Ti.App.Properties.getString('base_url')+'user/';
		Ti.API.info(url);
		xhr.open("GET",url);
		
		xhr.setRequestHeader('Authorization', 'Basic '+ Ti.Utils.base64encode(username+':'+password));
		
		Ti.API.info('username: '+ username + ' password:'+password);
		
		Ti.App.Properties.setString('username', username);
		Ti.App.Properties.setString('password', password);
		
		xhr.send();
	};
	
	//Get The leader board
	battle.network.getLeaderBoard = function(_cb) {
		var xhr = Titanium.Network.createHTTPClient();
		xhr.onload = function() {
			_cb(eval('('+this.responseText+')'));
		};
		var url = Ti.App.Properties.getString('base_url')+'leaderboard';//Ti.App.Properties.getString('base_url');
		Ti.API.info(url);
		xhr.open("GET",url);
		
		xhr.setRequestHeader('Authorization', 'Basic '+ Ti.Utils.base64encode(Ti.App.Properties.getString('username')+':'+Ti.App.Properties.getString('password')));
		
		xhr.send();
	};
	
	//Get The leader board
	battle.network.fight = function(_cb) {
		var xhr = Titanium.Network.createHTTPClient();
		xhr.onload = function() {
			_cb(this.responseXML.documentElement);
		};
		var url = Ti.App.Properties.getString('base_url')+'fight;myTariffId='+Ti.App.Properties.getInt('myTariffId')+';comparisonTariffId='+Ti.App.Properties.getInt('yourTariffId');//Ti.App.Properties.getString('base_url');
		Ti.API.info(url);
		xhr.open("GET",url);
		
		xhr.setRequestHeader('Authorization', 'Basic '+ Ti.Utils.base64encode(Ti.App.Properties.getString('username')+':'+Ti.App.Properties.getString('password')));
		
		xhr.send();
	};
	
	//Get The leader board
	battle.network.postfight = function(_cb) {
		var xhr = Titanium.Network.createHTTPClient();
		xhr.onload = function() {
			_cb(this.responseXML.documentElement);
		};
		var url = Ti.App.Properties.getString('base_url')+'leaderBoard?zipcode='+Ti.App.Properties.getInt('myZip')+'&tariffName='+Ti.App.Properties.getInt('myTariffName')+'&score='+myscore+'&userId='+Ti.App.Properties.getString('username');//Ti.App.Properties.getString('base_url');
		Ti.API.info(url);
		xhr.open("GET",url);
		
		xhr.setRequestHeader('Authorization', 'Basic '+ Ti.Utils.base64encode(Ti.App.Properties.getString('username')+':'+Ti.App.Properties.getString('password')));
		
		xhr.send();
	};

	

})();