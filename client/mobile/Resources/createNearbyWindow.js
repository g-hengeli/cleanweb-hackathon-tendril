// CONSTRUCTOR FOR THE Nearby WINDOW

battle.ui.createNearbyWindow = function(){
	var win = Ti.UI.createWindow({
		title:'Nearby',
		barColor:Ti.App.Properties.getString('barColor'),
	});
	
	var mapview = Ti.Map.createView({
		
	})
	win.add(mapview);
	
	
	return win;
	
}