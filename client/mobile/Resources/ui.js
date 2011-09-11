//Wrap all code in a self-calling function to protect the global namespace
(function() {
	//Create sub-namespace
	battle.ui = {};
	
	Ti.include('functions.js');
	Ti.include('createLoginWindow.js');
	Ti.include('createBattleWindow.js');
	Ti.include('createNearbyWindow.js');
	Ti.include('createLeaderBoardWindow.js');

	// create the login tab tabgroup
	battle.ui.createLoginTabGroup = function(_args) {
		//Create the tab group for logging in, wont have phyical tab, just window to get login info
		var loginTabGroup = Ti.UI.createTabGroup();
		
		//Create the login window
		var loginWin = battle.ui.createLoginWindow();
		
		//create the login tab
		battle.loginTab = Ti.UI.createTab({
			title:'Battle',
			icon:'KS_nav_ui.png',
			window:loginWin
		});
		loginTabGroup.addTab(battle.loginTab);
		
		return loginTabGroup;
	};

	//Create the main application tab group
	battle.ui.createMainApplicationTabGroup = function(_args) {
		
		
		//create the main tab group, available after logging in
		var tabGroup = Ti.UI.createTabGroup();
		
		//Create the main tab group windows
		var battleWin = battle.ui.createBattleWindow();
		var leaderWin = battle.ui.createLeaderBoardWindow();
		var nearbyWin = battle.ui.createNearbyWindow();


		//create the tabs to put in the tabgroups
		
		
		//create the battle tab
		battle.battleTab = Ti.UI.createTab({
			title:'Battle',
			icon:'KS_nav_ui.png',
			window:battleWin
		});
		
		//create the battle tab
		battle.leaderTab = Ti.UI.createTab({
			title:'Leader Board',
			icon:'KS_nav_ui.png',
			window:leaderWin
		});
		
		//create the nearby tab
		battle.nearbyTab = Ti.UI.createTab({
			title:'Nearby',
			icon:'KS_nav_views.png',
			window:nearbyWin
		});
		
		//Add the tabs to the tab group
		
		tabGroup.addTab(battle.battleTab);
		tabGroup.addTab(battle.leaderTab);
		tabGroup.addTab(battle.nearbyTab);
		
		
		return tabGroup;
	};

})();