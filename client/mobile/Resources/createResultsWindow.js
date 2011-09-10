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
	
	win.setRightNavButton(close);
	
	close.addEventListener('click', function(){
		win.close();
	});
	
	return win;
	
}