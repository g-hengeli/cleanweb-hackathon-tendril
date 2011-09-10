// CONSTRUCTOR FOR THE Nearby WINDOW

battle.ui.createNearbyWindow = function(){
	var win = Ti.UI.createWindow({
		title:'Nearby',
	});
	
	var mapview = Ti.Map.createView({
		
	})
	win.add(mapview);
	
	
	return win;
	
}