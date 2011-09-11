// CONSTRUCTOR FOR THE Nearby WINDOW

battle.ui.createNearbyWindow = function(){
	var win = Ti.UI.createWindow({
		title:'Nearby',
		barColor:Ti.App.Properties.getString('barColor'),
	});
	
	var mapview = Ti.Map.createView({
		
	})
	win.add(mapview);
	
	var zipArray = [];
	
	battle.network.getLeaderBoard(function(leaders) {
		//Ti.API.info(leaders);
		//alert(leaders.length);
		//Ti.App.info('LEADER LENGTH...'+ JSON.parse(leaders).length);
		for (var c=0;c<leaders.length;c++){
			Ti.API.info('mapview zip'+leaders[c].zipcode);
			Ti.API.info('mapview score'+leaders[c].score);
			zipArray[c] = leaders[c].zipcode;
			geoCodeAnnotation(leaders[c].zipcode, leaders[c].score, c);
			 /*var new_annotation = Titanium.Map.createAnnotation({
				latitude:evt.latitude,
				longitude:evt.longitude,
				title:leaders[c].zipcode,
				subtitle:leaders[c].score,
				animate:true,
				//rightButton: Titanium.UI.iPhone.SystemButton.DISCLOSURE,
				//url:'detail.js',
			});
			
			mapview.addAnnotation(new_annotation);*/
		}
	});
	
	function geoCodeAnnotation(addr, score, c){
		Ti.Geolocation.forwardGeocoder(addr, function(evt) {
		   Ti.API.info("lat:"+evt.latitude+", long:"+evt.longitude + ' c ' + c);
		   var new_annotation = Titanium.Map.createAnnotation({
				latitude:evt.latitude,
				longitude:evt.longitude,
				title:'Zip Code: '+addr,
				subtitle:'Score: '+score,
				animate:true,
				//rightButton: Titanium.UI.iPhone.SystemButton.DISCLOSURE,
				//url:'detail.js',
				
			});
			
			mapview.addAnnotation(new_annotation);
		});
	}
	
	for(var i = 0; i < zipArray.length; i++){
		Ti.API.info('mapview zip code array'+zipArray[i]);
		/*Ti.Geolocation.forwardGeocoder(addr, function(evt) {
			   Ti.API.info("lat:"+evt.latitude+", long:"+evt.longitude + ' c '+ c);
			   var new_annotation = Titanium.Map.createAnnotation({
					latitude:evt.latitude,
					longitude:evt.longitude,
					title:leaders[c].zipcode,
					subtitle:leaders[c].score,
					animate:true,
					//rightButton: Titanium.UI.iPhone.SystemButton.DISCLOSURE,
					//url:'detail.js',
					
				});
				
				mapview.addAnnotation(new_annotation);
			});*/
	}
	
	return win;
	
}