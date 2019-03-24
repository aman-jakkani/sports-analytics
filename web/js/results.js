/* *****************************************************************
Description:

Methods:
    stats:
    plotChart:
    plotDefault:

******************************************************************* */


/*
  Description
    Not entirely what the purpose of this function is

  Args:
    None

  Returns:

  Raises:

*/
function stats(){
  
  // hiddenText1??? what are these four lines doing??
	document.getElementById('hiddenText1').style.display="block";
	document.getElementById('Player').style.display="block";
	document.getElementById("Charts").style.display = "none";
	document.getElementById("Dropdown").style.display = "none";

	var sport = document.getElementById("sport").value;
  var league = document.getElementById("league").value;
  var team = document.getElementById("team").value;
  var season = document.getElementById("season").value;
  var match = document.getElementById("game").value;
  //var name = document.getElementById('name').innerHTML = document.getElementById("player").options[document.getElementById('player').selectedIndex].text;
      
  
  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["name", name]];
  console.log(parameters);

  var token = getRestResource("TokenResource", parameters);
  console.log("Token: " + token["token"]);
  
  var playerstatistics = getRestResource("PlayerResource", [["token", token["token"]], ["playerID", stats]]);
  console.log(Object.values(playerstatistics));
	
	var soccerplayerstatistics = getRestResource("SoccerPlayerResource", [["token", token["token"]], ["playerID", stats]]);
	console.log(Object.values(soccerplayerstatistics));
}



/*
  Description  

  Args:
    None

  Returns:

  Raises:

*/
function plotChart() {
    document.getElementById('hiddenText').style.display="block";

    var sport = document.getElementById("sport").value;
    var league = document.getElementById("league").value;
    var team =  document.getElementById("team").value;
    var season =  document.getElementById("season").value;
    var match =  document.getElementById("game").value;
    var factatt = document.getElementById("factAttribute").value;
    var aggregfunc = document.getElementById("aggregationFunction").value;
    var aggregstyle = document.getElementById("aggregationStyle").value;
    var dimension  = document.getElementById("dimensions").value;

    var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["factatt", factatt], ["aggregfunc", aggregfunc], ["aggregstyle", aggregstyle], ["dimension", dimension]];
    console.log(parameters);

    var token = getRestResource("TokenResource", parameters);
    console.log("Token: " + token["token"]);


    var teams = getRestResource("HomeAndAwayTeamListResource", [["token", token["token"]],]);
    console.log("Teams: " + teams["homeAndAwayTeam"]);


    var homeTeamData = [];
    var awayTeamData = [];
    var availableStats = [];


    // [["token", token["token"]], ]) is an array of dict values
    var score = getRestResource("ScoreRestResource", [["token", token["token"]], ]);
    if(score != null){
        console.log("Score: " + score["score"][0] + ":" + score["score"][1] );
        homeTeamData.push(score["score"][0]);
        awayTeamData.push(score["score"][1]);
        availableStats.push("Score");
    }

    var ballPossession = getRestResource("BallPossessionStatResource", [["token", token["token"]], ]);
    if(ballPossession != null){
        console.log("Ball Possession: " + ballPossession["possession"]);
        homeTeamData.push(ballPossession["possession"][0]);
        awayTeamData.push(ballPossession["possession"][1]);
        availableStats.push("Ball Possession");
    }

    var yellowCards = getRestResource("YellowCardsStatResource", [["token", token["token"]], ]);
    if(yellowCards != null){
        console.log("Yellow Cards: " + yellowCards["yellowCards"]);
        homeTeamData.push(yellowCards["yellowCards"][0]);
        awayTeamData.push(yellowCards["yellowCards"][1]);
        availableStats.push("Yellow Cards");
    }

    var redCards = getRestResource("RedCardsStatResource", [["token", token["token"]], ]);
    if(redCards != null){
        console.log("Red Cards: " + redCards["redCards"]);
        homeTeamData.push(redCards["redCards"][0]);
        awayTeamData.push(redCards["redCards"][1]);
        availableStats.push("Red Cards");
    }
    

    var playerList = getRestResource("PlayerListResource", [["token", token["token"]], ]);

    if (playerList != null) {
      console.log("Players found: " + playerList["homePlayers"]);
      //homeTeamData.push(playerList["homePlayers"][0]);
      //awayTeamData.push(playerList["guestPlayers"][1]);
      //availableStats.push("Players");

      var htmlPlayerString = "<option value = \"null\" >--Make a choice--</option>";
      console.log("Players found: ".concat(playerList.homePlayers.length + playerList.guestPlayers.length));
      
      for (i = 0; i < playerList.homePlayers.length; ++i) {
          htmlPlayerString = htmlPlayerString.concat("<option value = \"" + playerList.homePlayersID[i] + "\">" + playerList.homePlayers[i] + "</option>") //change teams to homePlayers and guestPlayers
      }

      for (i = 0; i < playerList.guestPlayers.length; ++i) {
          htmlPlayerString = htmlPlayerString.concat("<option value = \"" + playerList.guestPlayersID[i] + "\">" + playerList.guestPlayers[i] + "</option>") //change teams to homePlayers and guestPlayers
      }
      
      document.getElementById("player").innerHTML = htmlPlayerString;
    }

    var cornerStats = getRestResource("CornerStatRestResource", [["token", token["token"]],]);
    if(cornerStats != null){
        console.log("Corner Stats: " + cornerStats["corners"]);
        homeTeamData.push(cornerStats["corners"][0]);
        awayTeamData.push(cornerStats["corners"][1]);
        availableStats.push("Corners");
    }

    var foulStats = getRestResource("FoulsStatResource", [["token", token["token"]],]);
    if(foulStats != null){
        console.log("Foul Stats: " + foulStats["fouls"]);
        homeTeamData.push(foulStats["fouls"][0]);
        awayTeamData.push(foulStats["fouls"][1]);
        availableStats.push("Fouls");
    }

    var attendance = getRestResource("AttendanceRestResource", [["token", token["token"]],]);
    if(attendance != null) {
        console.log("Attendance: " + attendance["attendance"]);
        document.getElementById("attendance").innerHTML="Attendance: " + attendance["attendance"];
    }
    else {
        document.getElementById("attendance").innerHTML="";
    }
    

    // [ballPossession["possession"][0], yellowCards["yellowCards"][0], cornerStats["corners"][0], foulStats["fouls"][0]];
    // [ballPossession["possession"][1], yellowCards["yellowCards"][1], cornerStats["corners"][1], foulStats["fouls"][1]];

    console.log(homeTeamData[1]);
    console.log(homeTeamData);
    console.log(awayTeamData);
    var homeTeamName = teams["homeAndAwayTeam"][0];
    var awayTeamName = teams["homeAndAwayTeam"][1];

    console.log(homeTeamName);
    console.log(typeof(homeTeamName));

    var chartType = document.getElementById("chartType").value;
    
        homeTeamData.forEach(function(element, index){
        
        plotDefault(chartType, homeTeamName, awayTeamName, homeTeamData[index], awayTeamData[index], index, availableStats[index]);
    });

    document.getElementById("Charts").style.display = "block";
    document.getElementById("Dropdown").style.display = "none";
}


/*
  Description
    Default plot will plot only a single plot

  Args:
    chartType (string): the specific chart type that is to be plotted
    homeTeamName (string): 
    awayTeamName (string):
    homeTeamDataParam (string):
    awayTeamDataParam (string):
    num (int): ???
    label (string): ???

  Returns:

  Raises:

*/
function plotDefault(chartType, homeTeamName, awayTeamName, homeTeamDataParam, awayTeamDataParam, num, label){
 
  var chartOptions = {
    title: {
      display: true,
      text: 'Chart Title'
    },
    scales: {
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: "Y-Axis"
        }
      }],
      xAxes: [{
        scaleLabel: {
          display: true,
          labelString: "X-Axis"
        }
      }]
    }
  }
  
	var config = {
    type: chartType = chartType,
    data: gameData,
    options: chartOptions
  };
        
	
	//var ctx = document.getElementById('myChart1').getContext('2d');
	//var newChart = new Chart(ctx).HeatMap(gameData);
	//var canvas = document.getElementById(canvasId);
	//var newChart = new Chart(ctx).HeatMap(gameData, chartOptions);
 
    
    num += 1;
    var canvasId = "myChart" + num;
    var canvas = document.getElementById(canvasId);
    var showLabel = true;
    if(homeTeamName == undefined && awayTeamName == undefined)
    {
        showLabel = false;
    }

    if (window.bar != undefined){
            window.bar.destroy();
    }

    var homeTeamData = {
            label: homeTeamName,
            data: [homeTeamDataParam, "0"],
            backgroundColor: 'rgba(148, 28, 47, 0.6)',
            borderWidth: 0,
            yAxisID: "y-axis"
    };

    var awayTeamData = {
            label: awayTeamName,
            data: [awayTeamDataParam, "0"],
            backgroundColor: 'rgba(32, 164, 243, 0.6)',
            borderWidth: 0,
            yAxisID: "y-axis"
    };

    var gameData = {
            labels: [label],
            datasets: [homeTeamData, awayTeamData]
    };

    var chartOptions = {
            scales: {
                    xAxes: [{
                            barPercentage: 1,
                            categoryPercentage: 0.6
                    }],
                    yAxes: [{
                            id: "y-axis",
                            ticks: {
                      beginAtZero: true,
                      callback: function (value) { if (Number.isInteger(value)) { return value; } }
                  }
                    }]
            },
            legend: {
                display: showLabel
            }
    };

    var config = {
            type: chartType,
            data: gameData,
            options: chartOptions
    };
    
    console.log(chartType);

    globalCharts.push(new Chart(canvas, config));
}


/*
function backToStats(){
    document.getElementById("Charts").style.display = "block";
    document.getElementById('Player').style.display="none";
    document.getElementById("Dropdown").style.display = "none";
}

// Button to return back to previous dropdown page
function backToDropdown(){
    document.getElementById("Charts").style.display = "none";
    document.getElementById('Player').style.display="none";
    document.getElementById("Dropdown").style.display = "block";
    while(globalCharts.length > 0){
        globalCharts[globalCharts.length-1].destroy();
        globalCharts.pop();
    }
}
*/
