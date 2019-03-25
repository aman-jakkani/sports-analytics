/* *****************************************************************
Description:

Methods:
    displayStats:
    plot:
    generateChart:

******************************************************************* */


/*
  Description
    Displays all relevant statistics within the html page.
    Need to figure out which stats need to be displayed

  Args:
    None

  Returns:

  Raises:

*/
function displayStats(){
	document.getElementById('Player Stats').style.display="block";
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
  playerstatistics = Object.values(playerstatistics)
  console.log(playerstatistics);
	
	var soccerplayerstatistics = getRestResource("SoccerPlayerResource", [["token", token["token"]], ["playerID", stats]]);
  soccerplayerstatistics = Object.values(soccerplayerstatistics);
  console.log(soccerplayerstatistics);
}



/*
  Description  

  Args:
    None

  Returns:

  Raises:

  Notes:
      - [["token", token["token"]], ]) is an array of dict values
      - [ballPossession["possession"][0], yellowCards["yellowCards"][0], 
                        cornerStats["corners"][0], foulStats["fouls"][0]];
      - [ballPossession["possession"][1], yellowCards["yellowCards"][1], 
                        cornerStats["corners"][1], foulStats["fouls"][1]];
*/
function plot() {
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

    var score = getRestResource("ScoreRestResource", [["token", token["token"]], ]);
    if (score != null) {
        console.log("Score: " + score["score"][0] + ":" + score["score"][1] );
        homeTeamData.push(score["score"][0]);
        awayTeamData.push(score["score"][1]);
        availableStats.push("Score");
    }

    var ballPossession = getRestResource("BallPossessionStatResource", [["token", token["token"]], ]);
    if (ballPossession != null) {
        console.log("Ball Possession: " + ballPossession["possession"]);
        homeTeamData.push(ballPossession["possession"][0]);
        awayTeamData.push(ballPossession["possession"][1]);
        availableStats.push("Ball Possession");
    }

    var yellowCards = getRestResource("YellowCardsStatResource", [["token", token["token"]], ]);
    if (yellowCards != null) {
        console.log("Yellow Cards: " + yellowCards["yellowCards"]);
        homeTeamData.push(yellowCards["yellowCards"][0]);
        awayTeamData.push(yellowCards["yellowCards"][1]);
        availableStats.push("Yellow Cards");
    }

    var redCards = getRestResource("RedCardsStatResource", [["token", token["token"]], ]);
    if (redCards != null) {
        console.log("Red Cards: " + redCards["redCards"]);
        homeTeamData.push(redCards["redCards"][0]);
        awayTeamData.push(redCards["redCards"][1]);
        availableStats.push("Red Cards");
    }

/*
    var playerList = getRestResource("PlayerListResource", [["token", token["token"]], ]);
    if (playerList != null) {
      console.log("Players found: " + playerList["homePlayers"]);
      // homeTeamData.push(playerList["players"][0]);
      // awayTeamData.push(playerList["players"][1]);
      // availableStats.push("Players");
    }
  */

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
/*
    var attendance = getRestResource("AttendanceRestResource", [["token", token["token"]],]);
    if(attendance != null) {
        console.log("Attendance: " + attendance["attendance"]);
        document.getElementById("attendance").innerHTML="Attendance: " + attendance["attendance"];
    }
    
*/

    console.log(homeTeamData[1]);
    console.log(homeTeamData);
    console.log(awayTeamData);
    var homeTeamName = teams["homeAndAwayTeam"][0];
    var awayTeamName = teams["homeAndAwayTeam"][1];

    console.log(homeTeamName);
    console.log(typeof(homeTeamName));

    var chartType = document.getElementById("chartType").value;
    
    generateChart(chartType, homeTeamName, awayTeamName, homeTeamData, awayTeamData, availableStats);
}


/*
  Description
    This will generate a Chart object and return it to the 

  Args:
    chartType (string): the specific chart type that is to be plotted
    homeTeamName (string): 
    awayTeamName (string):
    homeTeamData (int/float[]):
    awayTeamData (int/float[]):

  Returns:

  Raises:

*/
function generateChart(chartType, homeTeamName, awayTeamName, homeTeamData, awayTeamData){
  var canvas = document.getElementById("mainChart");

  if (window.bar != undefined){
          window.bar.destroy();
  }

  var homeTeamData = {
          label: homeTeamName,
          data: homeTeamData,
          backgroundColor: 'rgba(148, 28, 47, 0.6)',
          borderWidth: 0,
          yAxisID: "y-axis"
  };

  var awayTeamData = {
          label: awayTeamName,
          data: awayTeamData,
          backgroundColor: 'rgba(32, 164, 243, 0.6)',
          borderWidth: 0,
          yAxisID: "y-axis"
  };

  var gameData = {
          labels: ["Ball Possession", "Yellow Cards", "Corners", "Fouls"],
          datasets: [homeTeamData, awayTeamData]
  };

  var chartOptions = {
          scales: {
                  xAxes: [{
                          barPercentage: 1,
                          categoryPercentage: 0.6
                  }],
                  yAxes: [{
                          id: "y-axis"
                  }]
          }
  };

  var config = {
          type: chartType,
          data: gameData,
          options: chartOptions
  };

  window.bar = new Chart(canvas, config);	
}
