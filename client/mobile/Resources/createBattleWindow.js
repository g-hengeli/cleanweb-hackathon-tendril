Ti.include('createResultsWindow.js');

// CONSTRUCTOR FOR THE Battle WINDOW

battle.ui.createBattleWindow = function(){
	
	var win = Ti.UI.createWindow({
		title:'Battle',
		
	});
	
	var doneBtn = Ti.UI.createButton({
		title:'Done',
		style:Titanium.UI.iPhone.SystemButtonStyle.DONE
	});
	
	var flexSpace = Titanium.UI.createButton({
		systemButton:Titanium.UI.iPhone.SystemButton.FLEXIBLE_SPACE
	});
	
	var myAreaLabel = Ti.UI.createLabel({
		text:'My Area Code:',
		left:'auto',
		right:'auto',
		top:15,
		height:20,
		width:"40%"
	});
	win.add(myAreaLabel);
	var yourAreaLabel = Ti.UI.createLabel({
		text:'Opponent Area Code:',
		left:'auto',
		right:'auto',
		top:90,
		height:40,
		width:200
	});
	win.add(yourAreaLabel);
	
	
	Ti.App.Properties.setInt('user_zip', 80305);
	var areaCode = Ti.App.Properties.getInt('user_zip');
	var myArea = Titanium.UI.createTextField({
		value:areaCode,
		top:45,
		hintText:'Ex: 80303',
		left:'auto',
		right:'auto',
		width:'75%',
		height:40,
		paddingLeft:5,
		borderRadius:'4',
		keyboardToolbar:[flexSpace, doneBtn],
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
		hintText:'Ex: 90210',
		width:'75%',
		height:40,
		paddingLeft:5,
		borderRadius:'4',
		keyboardToolbar:[flexSpace, doneBtn],
		keyboardType:Titanium.UI.KEYBOARD_NUMBER_PAD,
		returnKeyType:Titanium.UI.RETURNKEY_DONE,
		backgroundColor:'white',
		borderColor:'black'
	});
	win.add(yourArea);
	

	var battleBtn = Ti.UI.createButton({
		title:'BATTLE!',
		height:50,
		color:'#000',
		width:'90%',
		backgroundImage:'images/BUTT_gry_off.png',
		backgroundSelectedImage:'images/BUTT_gry_on.png',
		top:190
	})
	//win.add(battleBtn);
	battleBtn.hide();
	
	var pricingBtn = Ti.UI.createButton({
		title:'Choose Pricing Plan',
		height:50,
		color:'#000',
		width:'90%',
		backgroundImage:'images/BUTT_gry_off.png',
		backgroundSelectedImage:'images/BUTT_gry_on.png',
		top:190
	})
	
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
	
	yourArea.addEventListener('change', function(){
		if(yourArea.value.length == 5){
			enterValueLabel.hide();
			win.add(pricingBtn);
			pricingBtn.show();
			yourArea.blur();
		}
	});
	
	
	doneBtn.addEventListener('click', function(){
		myArea.blur();
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
	
	var clearPicker = Ti.UI.createButton({
		title:'Done'
	});
	
	clearPicker.addEventListener('click', function(){
		picker.bottom = -1000;
		win.setRightNavButton();
	});
	pricingBtn.addEventListener('click', function(){
		if(!myArea.value || !yourArea.value){
			alert('Please enter the area code you would like to battle');
		} else {
			
			pricingBtn.hide();
			picker.bottom = 0;
			if(Ti.App.Properties.getString('platform') == 'android'){
				picker.bottom = 100;
			} else {
				win.setRightNavButton(clearPicker);
			}
			win.add(battleBtn);
			battleBtn.show();
		}
	});
	
	
	var names = ['Joanie', 'Mickey', 'Jean-Pierre', 'Gustav', 'Raul', 'Mimi', 'Emily', 'Sandra', 'Carrie', 'Chachi'];
	
	var rows1 = [];
	
	for (var i = 0; i < names.length; i++) {
		rows1.push(Ti.UI.createPickerRow({title: names[i]}));
	}
	
	var column1 = Ti.UI.createPickerColumn( {
		rows: rows1, font: {fontSize: "12"}
	});
	
	var picker = Ti.UI.createPicker({
		bottom:-1000,
		selectionIndicator:true,
		type : Ti.UI.PICKER_TYPE_PLAIN,
		columns: [column1],
		zIndex:5
	});
	
	picker.addEventListener('change', function(e) {
		//alert(e.row.title);
	});
	win.add(picker);


	
	return win;
	
}