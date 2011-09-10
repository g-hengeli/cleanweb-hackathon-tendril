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
		left:'5%',
		top:10,
		height:20,
		width:"40%"
	});
	win.add(myAreaLabel);
	var yourAreaLabel = Ti.UI.createLabel({
		text:'Opponent Area Code:',
		right:'5%',
		top:10,
		height:40,
		wordWrap:true,
		width:"40%"
	});
	win.add(yourAreaLabel);
	var myArea = Titanium.UI.createTextField({
		top:55,
		hintText:'Ex: 80303',
		left:'5%',
		width:'40%',
		height:40,
		paddingLeft:5,
		borderRadius:'4',
		keyboardToolbar:[flexSpace, doneBtn],
		keyboardType:Titanium.UI.KEYBOARD_NUMBER_PAD,
		returnKeyType:Titanium.UI.RETURNKEY_DEFAULT,
		backgroundColor:'white',
		borderColor:'black',
		passwordMask: true,
	})
	
	win.add(myArea);
	
	var	yourArea = Titanium.UI.createTextField({
		top:55,
		right:'5%',
		hintText:'Ex: 90210',
		width:'40%',
		height:40,
		paddingLeft:5,
		borderRadius:'4',
		keyboardToolbar:[flexSpace, doneBtn],
		keyboardType:Titanium.UI.KEYBOARD_NUMBER_PAD,
		returnKeyType:Titanium.UI.RETURNKEY_DONE,
		backgroundColor:'white',
		borderColor:'black',
		passwordMask: true,
	})
	
	win.add(yourArea);
	
	
	var battleBtn = Ti.UI.createButton({
		title:'BATTLE!',
		height:50,
		width:'75%',
		top:'110'
	})
	win.add(battleBtn);
	
	doneBtn.addEventListener('click', function(){
		myArea.blur();
		yourArea.blur();
	});
	
	battleBtn.addEventListener('click', function(){
		alert('battle!');
	});
	
	return win;
	
}