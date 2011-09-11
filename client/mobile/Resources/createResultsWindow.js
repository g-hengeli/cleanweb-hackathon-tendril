// CONSTRUCTOR FOR THE RESULTS WINDOW

battle.ui.createResultsWindow = function(){
	var win = Ti.UI.createWindow({
		title:'Results',
		backgroundColor:'white',
		modal:true
	});
	
	var close = Ti.UI.createButton({
		title:"Close"
	});
	
	
	if(Ti.App.Properties.getString('platform') != 'android'){
		win.setRightNavButton(close);
	}
	
	
	var myId = Ti.UI.createLabel({
		text:Ti.App.Properties.getInt('myTariffId'),
		top:20,
		height:'20',
		width:'100'
	});
	
	win.add(myId);
	
	var yourId = Ti.UI.createLabel({
		text:Ti.App.Properties.getInt('yourTariffId'),
		height:'20',
		top:40,
		width:'100'
	});
	win.add(yourId);
	
	close.addEventListener('click', function(){
		win.close();
	});
	
	return win;
	
}