// CONSTRUCTOR FOR THE RESULTS WINDOW

battle.ui.createResultsWindow = function(){
	var win = Ti.UI.createWindow({
		title:'Results',
		backgroundImage:'images/wood-bg.png',
		barColor:Ti.App.Properties.getString('barColor'),
		modal:true
	});
	
	var close = Ti.UI.createButton({
		title:"Close"
	});
	
	var winner = Ti.UI.createImageView({
		image:'images/youWon.png',
		top:0,
		width:320,
		height:150
	});
	
	
	var loser = Ti.UI.createImageView({
		image:'images/youLost.png',
		top:0,
		width:320,
		height:150
	});
	
	var actInd = Ti.UI.createActivityIndicator();
	win.add(actInd);
	actInd.show();
	battle.network.fight(function(results) {
		
		Ti.API.info('fight response:'+ results);
		var comparisonScore = results.getElementsByTagName("comparisonScore");
		var localScore = results.getElementsByTagName("localScore");
		Ti.API.info('localScore:'+ localScore.item(0).text);
		Ti.API.info('compareScore:'+ comparisonScore.item(0).text);
		localScore = localScore.item(0).text;
		comparisonScore = comparisonScore.item(0).text;
		if(localScore > comparisonScore){
			var highScore = localScore;
			win.add(winner);
		} else {
			var highScore = comparisonScore;
			win.add(loser);
		}
		
		
		
		
		//Ti.App.Properties.getInt('myTariffId');
		//Ti.App.Properties.getInt('yourTariffId')
	
		var myScore = Ti.UI.createLabel({
			text:'My Score: ' + localScore,
			width:250,
		    top:0,
		    color:'white'
		    
		});
		win.add(myScore);
		
		var mypb = Ti.UI.createSlider({
		    width:250,
		    min:0,
		    max:100,
		    top:220,
		    touchEnabled:false,
		    thumbImage:'images/bolt1.png',
		    value:localScore,
		    left:30,
		    color:'#fff',
		    //message:'My Score: '+50,
		    //font:{fontSize:14, fontWeight:'bold'},
		    //style:Ti.UI.iPhone.ProgressBarStyle.PLAIN,
		});
		mypb.max = highScore * 1.5;
		win.add(mypb);
		
		mypb.show();
		
		
		var yourScore = Ti.UI.createLabel({
			text:'Opponent Score: ' + comparisonScore,
			width:250,
		    top:100,
		    color:'white'
		    
		});
		win.add(yourScore);
		
		
		var yourpb = Ti.UI.createSlider({
			
		    width:250,
		    min:0,
		    max:100,
		    top:280,
		    value:comparisonScore,
		    thumbImage:'images/bolt.png',
		    touchEnabled:false,
		    left:30,
		    color:'#fff',
		    message:'Their Score: '+52,
		    //font:{fontSize:14, fontWeight:'bold'},
		    style:Ti.UI.iPhone.ProgressBarStyle.PLAIN,
		});
		win.add(yourpb);
		yourpb.max = highScore * 1.5;
		yourpb.show();
		
		
		var shareBtn = Ti.UI.createButton({
			title:'Share Your Score',
			height:50,
			color:'#fff',
			width:'90%',
			font: { fontWeight: 'bold', fontSize:'16'},
			backgroundImage:'images/greenBtnOFF.png',
			backgroundSelectedImage:'images/greenBtnON.png',
			bottom:20
		});
		
		shareBtn.addEventListener('click', function(){
			
			var xhr = Titanium.Network.createHTTPClient();
			xhr.onload = function() {
				alert('Score Submitted');
				win.close();
				//_cb(this.responseXML.documentElement);
			};
			var url = Ti.App.Properties.getString('base_url')+'leaderboard?zipcode='+Ti.App.Properties.getInt('user_zip')+'&tariffName='+Ti.App.Properties.getString('myTariffName')+'&score='+localScore+'&userId='+Ti.App.Properties.getString('username');//Ti.App.Properties.getString('base_url');
			Ti.API.info(url);
			xhr.open("POST",url);
			
			xhr.setRequestHeader('Authorization', 'Basic '+ Ti.Utils.base64encode(Ti.App.Properties.getString('username')+':'+Ti.App.Properties.getString('password')));
			
			xhr.send();
			Ti.App.fireEvent('getLead');
			
		});
		
		win.add(shareBtn);
		actInd.hide();
	});
	
	if(Ti.App.Properties.getString('platform') != 'android'){
		win.setRightNavButton(close);
	}
	close.addEventListener('click', function(){
		win.close();
	});
	
	return win;
	
}