Ti.include('createResultsWindow.js');
Ti.include('createPricingPlanWindow.js');

// CONSTRUCTOR FOR THE Battle WINDOW

battle.ui.createBattleWindow = function(){
	
	var win = Ti.UI.createWindow({
		title:'Battle',
		barColor:Ti.App.Properties.getString('barColor'),
		backgroundImage:'images/wood-bg.png'
	});
	
	var myDoneBtn = Ti.UI.createButton({
		title:'Done',
		style:Titanium.UI.iPhone.SystemButtonStyle.DONE
	});
	
	var yourDoneBtn = Ti.UI.createButton({
		title:'Done',
		style:Titanium.UI.iPhone.SystemButtonStyle.DONE
	});
	
	var flexSpace = Titanium.UI.createButton({
		systemButton:Titanium.UI.iPhone.SystemButton.FLEXIBLE_SPACE
	});
	
	var myAreaLabel = Ti.UI.createLabel({
		text:'My Zip Code:',
		left:'auto',
		right:'auto',
		top:15,
		height:20,
		color:"#CCCCCC",
		width:"auto"
	});
	win.add(myAreaLabel);
	var yourAreaLabel = Ti.UI.createLabel({
		text:'Opponent Zip Code:',
		left:'auto',
		right:'auto',
		color:"#CCCCCC",
		top:90,
		height:40,
		width:"auto"
	});
	win.add(yourAreaLabel);
	
	
	Ti.App.Properties.setInt('user_zip', 80026);
	var zipCode = Ti.App.Properties.getInt('user_zip');
	var myArea = Titanium.UI.createTextField({
		value:zipCode,
		//value:80304,
		top:45,
		editable:false,
		left:'auto',
		right:'auto',
		width:'40%',
		height:40,
		paddingLeft:'40',
		paddingRight:'40',
		borderRadius:'4',
		keyboardToolbar:[flexSpace, myDoneBtn],
		keyboardToolbarColor:Ti.App.Properties.getString('barColor'),
		keyboardType:Titanium.UI.KEYBOARD_NUMBER_PAD,
		returnKeyType:Titanium.UI.RETURNKEY_DEFAULT,
		backgroundColor:'white',
		borderColor:'black'
	})
	
	win.add(myArea);
	
	var	yourArea = Titanium.UI.createTextField({
		top:130,
		left:'auto',
		right:'auto',
		width:'40%',
		height:40,
		paddingLeft:'40',
		paddingRight:'40',
		borderRadius:'4',
		keyboardToolbar:[flexSpace, yourDoneBtn],
		keyboardToolbarColor:Ti.App.Properties.getString('barColor'),
		keyboardType:Titanium.UI.KEYBOARD_NUMBER_PAD,
		returnKeyType:Titanium.UI.RETURNKEY_DONE,
		backgroundColor:'white',
		borderColor:'black'
	});
	

	win.add(yourArea);
	

	var battleBtn = Ti.UI.createButton({
		title:'FIGHT!',
		height:50,
		color:'#fff',
		font: { fontWeight: 'bold', fontSize:'16'},
		width:'90%',
		hires:true,
		backgroundImage:'images/greenBtnOFF.png',
		backgroundSelectedImage:'images/greenBtnON.png',
		top:240
	});
	//win.add(battleBtn);
	battleBtn.hide();
	
	var pricingBtn = Ti.UI.createButton({
		title:'CHOOSE PRICING PLAN',
		height:50,
		color:'#fff',
		width:'90%',
		font: { fontWeight: 'bold', fontSize:'16'},
		backgroundImage:'images/greenBtnOFF.png',
		backgroundSelectedImage:'images/greenBtnON.png',
		top:190
	});
	
	pricingBtn.hide();
	
	
	
	var enterValueLabel = Ti.UI.createLabel({
		text:'Enter a Valid Zip Code',
		height:50,
		left:'auto',
		right:'auto',
		color:'gray',
		width:190,
		top:190
	})
	
	win.add(enterValueLabel);
	
	
	
	myArea.addEventListener('change', function(){
		//alert('change');
		battleBtn.hide();
		Ti.App.Properties.setString('myZip', null);
		if(myArea.value.length == 5){
			enterValueLabel.hide();
			win.add(pricingBtn);
			pricingBtn.show();
			myArea.blur();
		}
	});
	

	
	yourArea.addEventListener('change', function(){
		//alert('change');
		battleBtn.hide();
		Ti.App.Properties.setString('yourZip', null);
		if(yourArea.value.length == 5){
			enterValueLabel.hide();
			win.add(pricingBtn);
			pricingBtn.show();
			yourArea.blur();
		}
	});
	
	
	myDoneBtn.addEventListener('click', function(){
		myArea.blur();
	});
	yourDoneBtn.addEventListener('click', function(){
		yourArea.blur();
	});
	
	battleBtn.addEventListener('click', function(){
		
		if(myArea.value.length == 5 || yourArea.value.length == 5){
			var resultWin = battle.ui.createResultsWindow();
			resultWin.open();
		} else {
			alert('Please enter a valid zip code');
		}
		
	});
	
	
	pricingBtn.addEventListener('click', function(){
		Ti.API.info('myziplength: '+ myArea.value.length);
		Ti.API.info('yourziplength: '+ yourArea.value.length);
		if(yourArea.value.length != 5){
			alert('Please enter valid area codes to battle');
		} else {
			var pickerWin = battle.ui.createPricingPlanWindow(myArea.value, yourArea.value);
			pickerWin.open();
			win.add(battleBtn);
			battleBtn.show();
			
			pricingBtn.title = 'CHANGE PRICING PLAN'
			
			
			
		}
	});
	
	
	


	
	return win;
	
}