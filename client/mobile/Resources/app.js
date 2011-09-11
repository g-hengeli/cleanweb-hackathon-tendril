Titanium.UI.setBackgroundColor('#ffffff');

Ti.App.Properties.setString('username', 'cleanweb.hackathon@tendrilinc.com');
Ti.App.Properties.setString('password', 'hackathon');


var platform = Titanium.Platform.osname;
//mainWindow.orientationModes = [Ti.UI.PORTRAIT];
//Ti.UI.orientation = Ti.UI.PORTRAIT;
Ti.App.Properties.setString('base_url', 'http://dorringtonclimbing.com:8080/energy-fight/');
Ti.App.Properties.setString('barColor', '339900');

if(Titanium.Platform.osname == 'android'){
	Ti.App.Properties.setString('platform', 'android');
} else if(Titanium.Platform.osname == 'iphone'){
	Ti.App.Properties.setString('platform', 'iphone');
} else if(Titanium.Platform.osname == 'ipad'){
	Ti.App.Properties.setString('platform', 'ipad');
}
var battle = {}; //`grizzly` is our app's namespace
Ti.include( //we'll be including all the files for our namespace in the root app context
	'ui.js',
	'network.js'
);

//Use our custom UI constructors to build the app's UI
var mainTabs = battle.ui.createMainApplicationTabGroup();


var loginTab = battle.ui.createLoginTabGroup();
loginTab.open();

//Log our current platform to the console
Ti.API.info('Welcome to Energy Battle for '+Ti.Platform.osname);
Ti.App.Properties.setInt('logged_in', 0);
		
Ti.App.addEventListener('login', function(){
	Ti.App.Properties.setInt('logged_in', 1);
	mainTabs = battle.ui.createMainApplicationTabGroup();
	loginTab.close();
	mainTabs.open();
});

Ti.App.addEventListener('logout', function(){
	Ti.App.Properties.setInt('logged_in', 0);
	mainTabs.close();
	loginTab.open();
});

if(Ti.App.Properties.getInt('logged_in') == 1){
	Ti.App.fireEvent('login');
}