//
// CREATE THE DETAIL WINDOW 
//

battle.ui.createDetailWindow = function(tariff){
	var win = Ti.UI.createWindow({
		title:tariff.tariffName,
		barColor:Ti.App.Properties.getString('barColor'),
		
	});
	
	var detailsLabel = Ti.UI.createLabel({
		text:'Details: ',
		height:20,
		font:{fontWeight:'bold', fontSize:'18'},
		width:100,
		top:20,
		left:10
	});
	win.add(detailsLabel);
	
	var zipCodeLabel = Ti.UI.createLabel({
		text:'Zip Code: '+tariff.zipcode,
		height:20,
		width:220,
		top:45,
		left:20
	});
	win.add(zipCodeLabel);
	
	var scoreLabel = Ti.UI.createLabel({
		text:'Score: '+tariff.score,
		height:20,
		width:220,
		top:69,
		left:20
	});
	win.add(scoreLabel);
	
	
	var touLabel = Ti.UI.createLabel({
		text:'Time of Use: ',
		height:20,
		font:{fontWeight:'bold', fontSize:'16'},
		width:220,
		top:95,
		left:20
	});
	win.add(touLabel);
	
	var peakLabel = Ti.UI.createLabel({
		text:'Peak: 8am - 9pm',
		height:20,
		width:220,
		top:115,
		left:20
	});
	win.add(peakLabel);
	
	var offPeakLabel = Ti.UI.createLabel({
		text:'Off Peak: 10pm - 8am',
		height:20,
		width:220,
		top:135,
		left:20
	});
	win.add(offPeakLabel);
	
	return win;
}