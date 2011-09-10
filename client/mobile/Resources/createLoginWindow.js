// CONSTRUCTOR FOR THE LOGIN WINDOW

battle.ui.createLoginWindow = function(){
	var win = Ti.UI.createWindow({
		title:'Login',
		tabBarHidden:true
	});
	
	var email = Titanium.UI.createTextField({
		top:45,
		hintText:'E-Mail',
		width:250,
		height:30,
		borderRadius:'4',
		borderColor:'black',
		paddingLeft:5,
		backgroundColor:'white'
	})
	
	win.add(email);
	
	var password = Titanium.UI.createTextField({
		top:85,
		hintText:'Password',
		width:250,
		height:30,
		paddingLeft:5,
		borderRadius:'4',
		backgroundColor:'white',
		borderColor:'black',
		passwordMask: true,
	})
	
	win.add(password);
	
	var loginBtn = Ti.UI.createButton({
		title:'Login',
		height:50,
		width:'75%',
		bottom:20
	})
	win.add(loginBtn);
	
	function getUserInfo(e){
		
	  var user = email.value;
	  var pass = password.value;
	  loginUser(user,pass);
	}
	
	loginBtn.addEventListener('click', getUserInfo);
	
	
	function loginUser(email,pass){
		
	 	if(email == 'Greg' && pass == 'password'){
	 		Ti.App.fireEvent('login');
	 	} else {
	 		alert('NOPE');
	 	}
	}
	
	return win;
	
}