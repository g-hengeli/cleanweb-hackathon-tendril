// CONSTRUCTOR FOR THE LEADERBOARD WINDOW

battle.ui.createPricingPlanWindow = function(myZip, yourZip){
	var win = Ti.UI.createWindow({
		title:'Pricing Plans',
		backgroundColor:'white',
		modal:true
	});
	
	var scrollView = Titanium.UI.createScrollView({
		contentWidth:'auto',
		contentHeight:'auto',
		top:0,
		showVerticalScrollIndicator:true,
		showHorizontalScrollIndicator:true
	});
	
	
	var view = Ti.UI.createView({
		width:320,
		height:600,
		top:0
	});
	
	scrollView.add(view);
	
	var url = 'http://localhost:8080/service/tariff?zipcode='+myZip;
	var xhr = Titanium.Network.createHTTPClient();



	var names = [];

	xhr.onload = function()
	{
		Ti.API.info(' xml ' + this.responseXML + ' text ' + this.responseText);
		var doc = this.responseXML.documentElement;
		var tariffs = doc.getElementsByTagName("tariffName");
		Ti.API.info("tariffs = " + tariffs);
		
		
		
		for(var i = 0; i < tariffs.length; i++){
			names[i] = tariffs.item(i);
		}
		var tariffName = tariffs.item(0);
		
		var myPricingPlanLabel = Ti.UI.createLabel({
			text:'My Pricing Plan: \n' + names[0].text,
			width: 280,
			left:20,
			right:20,
			wordWrap:true,
			height:60,
			top:0
		});
		view.add(myPricingPlanLabel);
		//Ti.API.info("My Tariff Name = " + tariffName.text);
		//var names = ['Joanie', 'Mickey longer word', 'Jean-Pierre', 'Gustav', 'Raul', 'Mimi', 'Emily', 'Sandra', 'Carrie', 'Chachi'];
		var rows1 = [];
	
		for (var i = 0; i < names.length; i++) {
			rows1.push(Ti.UI.createPickerRow({title: names[i].text}));
			Ti.API.info("My Tariff Name"+i+"= " + names[i].text);
		}
		
		var column1 = Ti.UI.createPickerColumn( {
			rows: rows1, font: {fontSize: "8"}
		});

		var myPicker = Ti.UI.createPicker({
			top:60,
			height:50,
			//width:100,
			left:0,
			selectionIndicator:true,
			type : Ti.UI.PICKER_TYPE_PLAIN,
			columns: [column1],
			zIndex:5
		});
		
		myPicker.addEventListener('change', function(e) {
			myPricingPlanLabel.text = 'My Pricing Plan: \n' + e.row.title;
		});
		view.add(myPicker);
		
		

	};
	// open the client
	xhr.open('GET',url);
	xhr.setRequestHeader('Authorization', 'Basic ' + Ti.Utils.base64encode('greg:hackathon'));//Ti.Utils.base64encode(username+':'+password));
	// send the data
	xhr.send();
	
	
	var url1 = 'http://localhost:8080/service/tariff?zipcode='+yourZip;
	var xhr1 = Titanium.Network.createHTTPClient();

	xhr1.onload = function()
	{
		Ti.API.info(' xml ' + this.responseXML + ' text ' + this.responseText);
		var doc = this.responseXML.documentElement;
		var tariffs = doc.getElementsByTagName("tariffName");
		Ti.API.info("tariffs = " + tariffs);
		if(tariffs == null){
			alert('No Data Found For Your Opponents Zip Code');
		} else {
		
			for(var i = 0; i < tariffs.length; i++){
				names[i] = tariffs.item(i);
			}
			var tariffName = tariffs.item(0);
			
			
			var yourPricingPlanLabel = Ti.UI.createLabel({
				text:'Opponent Pricing Plan: \n' + names[0].text,
				width: 280,
				left:20,
				right:20,
				wordWrap:true,
				height:60,
				top:290
			});
			view.add(yourPricingPlanLabel);
			//Ti.API.info("My Tariff Name = " + tariffName.text);
			//var names = ['Joanie', 'Mickey longer word', 'Jean-Pierre', 'Gustav', 'Raul', 'Mimi', 'Emily', 'Sandra', 'Carrie', 'Chachi'];
			var rows1 = [];
		
			for (var i = 0; i < names.length; i++) {
				rows1.push(Ti.UI.createPickerRow({title: names[i].text}));
				Ti.API.info("Opponent Tariff Name"+i+"= " + names[i].text);
			}
			
			var column1 = Ti.UI.createPickerColumn( {
				rows: rows1, font: {fontSize: "8"}
			});
	
			var yourPicker = Ti.UI.createPicker({
				bottom:20,
				height:50,
				//width:100,
				left:0,
				selectionIndicator:true,
				type : Ti.UI.PICKER_TYPE_PLAIN,
				columns: [column1],
				zIndex:5
			});
			
			yourPicker.addEventListener('change', function(e) {
				yourPricingPlanLabel.text = 'Opponent Pricing Plan: \n' + e.row.title;
			});
			view.add(yourPicker);
		}
	};
	// open the client
	xhr1.open('GET',url1);
	
	// send the data
	xhr1.send();
	
	var done = Ti.UI.createButton({
		title:'Done'
	});
	
	if(Ti.App.Properties.getString('platform') != 'android'){
		win.setRightNavButton(done);
	}
	
	done.addEventListener('click', function(){
		win.close();
	});
	

		
	
	
	
	
	
	//win.add(yourPicker);
	
	/*battle.network.getPricingPlans(function(leaders) {
		
	});*/
	
	
	win.add(scrollView);
	return win;
	
}