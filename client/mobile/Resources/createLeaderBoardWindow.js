// CONSTRUCTOR FOR THE LEADERBOARD WINDOW
Ti.include('createDetailWindow.js');
battle.ui.createLeaderBoardWindow = function(){
	var win = Ti.UI.createWindow({
		title:'Leader Board',
		barColor:Ti.App.Properties.getString('barColor'),
	});
	var data = [];
	var tableview = Titanium.UI.createTableView({minRowHeight:58});
	
	tableview.addEventListener('click', function(e){
		var tab = battle.leaderTab;
		tab.open(battle.ui.createDetailWindow(e.rowData));
	});
	
	function getLead(){
		battle.network.getLeaderBoard(function(leaders) {
			//Ti.API.info(leaders);
			//alert(leaders.length);
			//Ti.App.info('LEADER LENGTH...'+ JSON.parse(leaders).length);
			for (var c=0;c<leaders.length;c++){
				
				
				var zipcode = leaders[c].zipcode;
				var score = leaders[c].score;
				var tariffName = leaders[c].tariffName;
				
				var bgcolor = (c % 2) == 0 ? '#fff' : '#eee';
		
				var row = Ti.UI.createTableViewRow({hasChild:true,height:'auto',backgroundColor:bgcolor});
				
				// Create a vertical layout view to hold all the info labels and images for each tweet
				var post_view = Ti.UI.createView({
					height:'auto',
					layout:'vertical',
					left:5,
					top:5,
					bottom:5,
					right:5
				});
				var rank = (c+1);
				var av = Ti.UI.createLabel({
					text:rank+'.',
					left:10,
					font:{fontFamily:'Trebuchet MS',fontWeight:'bold', fontSize:'14'},
					top:-10,
					color:'#444444',
					height:48,
					width:48
				});
				// Add the avatar image to the view
				post_view.add(av);
		
				var user_label = Ti.UI.createLabel({
					text:'Zip Code: '+zipcode,
					left:30,
					width:120,
					top:-32,
					bottom:2,
					height:16,
					textAlign:'left',
					color:'#444444',
					font:{fontFamily:'Trebuchet MS',fontSize:14,fontWeight:'bold'}
				});
				// Add the username to the view
				post_view.add(user_label);
		
				var date_label = Ti.UI.createLabel({
					text:'Score: '+score,
					right:0,
					top:-25,
					bottom:2,
					height:14,
					textAlign:'right',
					width:110,
					color:'#444444',
					font:{fontFamily:'Trebuchet MS',fontSize:12}
				});
				// Add the date to the view
				post_view.add(date_label);
		
				var tweet_text = Ti.UI.createLabel({
					text:'Pricing Plan: '+tariffName,
					left:30,
					top:10,
					bottom:2,
					height:'auto',
					width:236,
					textAlign:'left',
					font:{fontSize:14}
				});
				Ti.API.info('ZIP: '+zipcode);
				Ti.API.info('SCORE: '+score);
				Ti.API.info('Tariff: '+tariffName);
				// Add the tweet to the view
				post_view.add(tweet_text);
				// Add the vertical layout view to the row
				
				row.add(post_view);
				Ti.API.info('MADE IT TO HERE');
				
				row.zipcode = zipcode;
				row.score = score;
				row.tariffName = tariffName;
				
				data[c] = row;
			}
			tableview.data = data;
			
			
		});
	}
	
	Ti.App.addEventListener('getLead', getLead);
	
	win.addEventListener('focus', getLead);
	
	win.add(tableview);
	return win;
	
}