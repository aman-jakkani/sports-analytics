/* *****************************************************************
Description:

Methods:
    displayStats:
    plot:
    generateChart:

Dependencies:
*/
/*	var canvasId = "myChart1";
	var canvas = document.getElementById(canvasId);
	var chartType;
	var offsetWidth = 0;
	var gameData = {
  labels: ['0h','1h','2h','3h','4h','5h','6h','7h','8h','9h','10h','11h'],
  datasets: [
    {
      label: 'Hawks',
      backgroundColor: "rgba(255,221,50,0.2)",
      data: [{x: 20,
            y: 41,
            r: 45}]
    },
    {
      label: 'Heat',
      backgroundColor: "rgba(60,186,159,0.2)",
      data: [{x: 25,
            y: 38,
            r: 29}]
    },
    {
      label: 'Lakers',
      backgroundColor: "rgba(0,0,0,0.2)",
      data: [{x: 30,
            y: 34,
            r: 58}]
    },
    {
      label: 'Rockets',
      backgroundColor: "rgba(193,46,12,0.2)",
      data: [{x: 23,
            y: 45,
            r: 41}]
    },
    {
      label: 'Warriors',
      backgroundColor: "rgba(128,0,0,0.2)",
      data: [{x: 28,
            y: 47,
            r: 30}]
    }
  ]
};
  


	var chartOptions = {
	
	title: {
        display: true,
        text: 'Correlation of Field Goal Percentage and Assists Per Game To Number of Wins'
      }, scales: {
        yAxes: [{
          scaleLabel: {
            display: true,
            labelString: "Field Goal Percentage"
          }
        }],
        xAxes: [{
          scaleLabel: {
            display: true,
            labelString: "Assists Per Game"
        }
      }]

	}
	}
	var config = {
                type: chartType = 'bubble',
                data: gameData,
                options: chartOptions
        };

    globalCharts.push(new Chart(canvas, config));

	//var ctx = document.getElementById('myChart1').getContext('2d');
	//var newChart = new Chart(ctx).HeatMap(gameData);
	//var canvas = document.getElementById(canvasId);
	//var newChart = new Chart(ctx).HeatMap(gameData, chartOptions);*/




function plotRollup(json, nullValues) {
    chartType = document.getElementById("chartType").value;
    console.log(chartType)
    var mainCanvas = document.getElementById("mainChart").getContext('2d');
    var secondCanvas = document.getElementById("secondChart").getContext('2d');
    
    switch (chartType){
        case "bubble": plotBubble(json, mainCanvas);
        			   plotBubble(nullValues, secondCanvas);
            break;
        case "scatter": plotScatter(json, mainCanvas);
        				plotScatter(nullValues, secondCanvas);
            break;
        case "line": plotLineChart(json, mainCanvas);
        			 plotLineChart(nullValues, secondCanvas);
        	break; // create plotLine(json) function
        default:
            break;
    }
}

function plotCube(json, nullValues, nullValues1) {
    chartType = document.getElementById("chartType").value;
    console.log(chartType)
    var mainCanvas = document.getElementById("mainChart").getContext('2d');
    var secondCanvas = document.getElementById("secondChart").getContext('2d');
    var thirdCanvas = document.getElementById("thirdChart").getContext('2d');
    switch (chartType){
        case "bubble": plotBubble(json, mainCanvas);
        			   plotBubble(nullValues, secondCanvas);
        			   plotBubble(nullValues1, thirdCanvas);
            break;
        case "scatter": plotScatter(json, mainCanvas);
        				plotScatter(nullValues, secondCanvas);
        				plotScatter(nullValues1, thirdCanvas);
            break;
        case "line": plotLineChart(json, mainCanvas);
        			 plotLineChart(nullValues, secondCanvas);
        			 plotLineChart(nullValues1, thirdCanvas);
        	break; // create plotLine(json) function
        default:
            break;
    }
}




function stats(){
	
	document.getElementById('hiddenText1').style.display="block";
	document.getElementById('Player').style.display="block";
	document.getElementById("Charts").style.display = "none";
	document.getElementById("Dropdown").style.display = "none";

	var sport = document.getElementById('chosenSport').innerHTML = document.getElementById("sport").value;
    var league = document.getElementById('chosenLeague').innerHTML = document.getElementById("league").value;
    var team = document.getElementById('chosenTeam').innerHTML = document.getElementById("team").value;
    var season = document.getElementById('chosenSeason').innerHTML = document.getElementById("season").value;
    var match = document.getElementById('chosenGame').innerHTML = document.getElementById("game").value;
    //var name = document.getElementById('name').innerHTML = document.getElementById("player").options[document.getElementById('player').selectedIndex].text;
        
    
    var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["name", name]];
    console.log(parameters);

	var token = getRestResource("TokenResource", parameters);
    console.log("Token: " + token["token"]);

	var stats = document.getElementById("player").value;
	console.log(stats);
	
	var playerstatistics = getRestResource("PlayerResource", [["token", token["token"]], ["playerID", stats]]);
	console.log(playerstatistics);
	var playerInfo = Object.values(playerstatistics);
	
	var birthday = playerInfo[0];
	document.getElementById("birthday").innerHTML = birthday;
	
	var name = playerInfo[1];
	document.getElementById("name").innerHTML = name;
	
	var weight = playerInfo[2];
	document.getElementById("weight").innerHTML = weight;
	
	var height = playerInfo[3];
	document.getElementById("height").innerHTML = height;

	
	var soccerplayerstatistics = getRestResource("SoccerPlayerResource", [["token", token["token"]], ["playerID", stats]]);
	console.log(soccerplayerstatistics);
	console.log(Object.values(soccerplayerstatistics));
	var individualStats = Object.values(soccerplayerstatistics);
	
	var overallRating = individualStats[0];
	//document.getElementById("overallRating").innerHTML = overallRating;
	
	var strength = individualStats[1];
	//document.getElementById("strength").innerHTML = strength;
	
	var shotPower = individualStats[2];
	//document.getElementById("shotPower").innerHTML = shotPower;
	
	var preferredFoot = individualStats[3];
	var preferredFootCap = preferredFoot.charAt(0).toUpperCase() + preferredFoot.slice(1)
	document.getElementById("preferredFoot").innerHTML = preferredFootCap;
	

	plotRadar(name, overallRating, strength, shotPower);
	
}


function plotChart() {
        document.getElementById('mainChart').style.display="block";

        var sport /*= document.getElementById('chosenSport').innerHTML */= document.getElementById("sport").value;
        var league /*= document.getElementById('chosenLeague').innerHTML */= document.getElementById("league").value;
        var team /*= document.getElementById('chosenTeam').innerHTML */= document.getElementById("team").value;
        var season /*= document.getElementById('chosenSeason').innerHTML */= document.getElementById("season").value;
        var match /*= document.getElementById('chosenGame').innerHTML*/ = document.getElementById("game").value;
        var factatt = document.getElementById("factAttribute").value;
        //var aggregfunc = document.getElementById("aggregationFunction").value;
        var aggregstyle = document.getElementById("aggregationStyle").value;
        //var dimension  = document.getElementById("dimensions").value;

        /****************************************+************
        *we are displaying cube/rollup-queries in a seperate *
        *page, no use for dimension, aggregate function etc. *
        *here                                                *
        *****************************************************/

        //var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["factatt", //factatt], ["aggregfunc", aggregfunc], ["aggregstyle", aggregstyle], ["dimension", dimension]];

        var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match],["aggregstyle", aggregstyle]];
            console.log(parameters);

  var token = getRestResource("TokenResource", parameters);
  var data = null;

  // Get the data for the specific attribute selected
  if (sport == "Soccer"){
    data = getSoccerAttributeData(factatt, token);
    console.log("Got soccer attributes");
    console.log(data);

  } else if (sport == "Basketball"){
    data = getBasketballAttributeData(factatt, token);
    console.log("Got basketball attributes");

  } else {
    console.log("Invalid sport selection");
    return;
  }

  var label = document.getElementById("factAttribute").value;

  // Plot a specific chart based on the one selected in the
    chartType = document.getElementById("chartType").value;
    switch (chartType) {
    case ("bubble"):
      // plotBubble(data);
      break;

    case ("line"):
      //plotLineChart(data);
      plotDefault('line', 'home', 'away', Array(data[0]), Array(data[1]), label);
      break;

    case ("scatter"):
      // plotScatter(data);
      break;

    case ("bar"):
      // plotBarChart(data);
      plotDefault('bar', 'home', 'away', Array(data[0]), Array(data[1]), label);
      break;

    case ("radar"):
      // plotRadar(data);
      plotDefault('radar', 'home', 'away', Array(data[0]), Array(data[1]), label);
      break;

    default: break;
  }
}

/*
  Description:
    This function should take all the selected attributes and return an array with all the data formatted
  Args:
  Returns:
  Raises:
  Notes:
*/
function getSoccerAttributeData(attribute, token){
  console.log("Attribute: " + attribute);

  switch (attribute){
    case ("goals"):
      var score = getRestResource("ScoreRestResource", [["token", token["token"]], ]);
      return score["score"]; // add index

	case ("corners"):
      var corners = getRestResource("CornerStatRestResource", [["token", token["token"]], ]);
      return corners["corners"]; // add index
	
    case ("ballPossession"):
      var ballPossession = getRestResource("BallPossessionStatResource", [["token", token["token"]], ]);
      return ballPossession["possession"];

    case ("yellowCards"):
      var yellowCards = getRestResource("YellowCardsStatResource", [["token", token["token"]], ]);
      return yellowCards["yellowCards"]; // add index

    case ("redCards"):
      var redCards = getRestResource("RedCardsStatResource", [["token", token["token"]], ]);
      return redCards["redCards"]; // add index

    case ("cornerStats"):
      var cornerStats = getRestResource("CornerStatRestResource", [["token", token["token"]], ]);
      return cornerStats["corners"]; // add index

    case ("fouls"):
      var fouls = getRestResource("FoulsStatResource", [["token", token["token"]], ]);
      return fouls["fouls"]; // add index

    case ("attendance"):
      var attendance = getRestResource("AttendanceRestResource", [["token", token["token"]], ]);
      return attendance["attendance"]; // add index

    default:
      return null;
      break;
  }
}

/*
  Description:
    This function should take all the selected attributes and return an array with all the data formatted
  Args:
  Returns:
  Raises:
  Notes:
*/
function getBasketballAttributeData(attributeList, token){
  for (i = 0; i < attributeList.length; ++i){
      switch (attribute){
      case ("points"): break;
      case ("assists"): break;
      case ("rebounds"): break;
      default: break;
    }
  }
}


/*
  Description
    Displays all relevant statistics within the html page.
    Need to figure out which stats need to be displayed

  Args:
    None

  Returns:

  Raises:

*/

function displayPlayerStats(){


  var sport = document.getElementById("sport").value;
  var league = document.getElementById("league").value;
  var team = document.getElementById("team").value;
  var season = document.getElementById("season").value;
  var match = document.getElementById("game").value;
  // var name = document.getElementById('name').innerHTML = document.getElementById("player").options[document.getElementById('player').selectedIndex].text;

  console.log(sport);
  console.log(league);
  if (sport == "null" || league == "null" || team == "null" || season == "null" || match == "null" ){
  
  	console.log("Not enough parameters");
  	return;
  
  }

  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["name", name]];
  console.log(parameters);

  var token = getRestResource("TokenResource", parameters);
  console.log("Token: " + token["token"]);

  playerData = getPlayerData(token);
  createPlayerTable(playerData);

  playerID = document.getElementById("players").value;
  if (playerID == "null"){
    console.log("No player selected.");
    return;
  }
  var player = getRestResource("PlayerResource", [["token", token["token"]], ["playerID", playerID]]);
  player = Object.values(player);
  var soccerplayerstatistics = getRestResource("SoccerPlayerResource", [["token", token["token"]], ["playerID", playerID]]);
  soccerplayerstatistics = Object.values(soccerplayerstatistics);
  plotRadar(player[1], soccerplayerstatistics[0], soccerplayerstatistics[1], soccerplayerstatistics[2]);
}


/*
Description:
  Returns an array of player data given a token. This data is used to populate the player table.
Args:
Returns:
Raises:
Notes:
  Format:
  [ [a, b, c, d, e], [f, g, h, i, k], [l, m, n, o, p]]
*/
function getPlayerData(token){
  // Get the list of players with
  var playerList = getRestResource("PlayerListResource", [["token", token["token"]], ]);

  // Create list of IDs to get stats for each player
  var ids = playerList.homePlayersID;
  for (var i = 0; i < playerList.guestPlayersID.length; ++i){
    ids.push(playerList.guestPlayersID[i]);
  }

  // Initialize playerData array
  var playerData = [["Player ID", "Birthday", "Name", "Weight", "Height", "Rating", "Strength", "Shot Power", "Preferred Foot"], ];

  // Let the first element of the array be the player's id
  var statsList, playerstatistics, soccerplayerstatistics;

  // Populate playerData for all found players
  for (var i = 0; i < ids.length; ++i){
    statsList = [ids[i]];

    // Get general player stats and append
    playerstatistics = getRestResource("PlayerResource", [["token", token["token"]], ["playerID", ids[i]]]);
    playerstatistics = Object.values(playerstatistics);

    for (var j = 0; j < playerstatistics.length; ++j){
      statsList.push(playerstatistics[j]);
    }

    // Get soccer player stats and append
    soccerplayerstatistics = getRestResource("SoccerPlayerResource", [["token", token["token"]], ["playerID", ids[i]]]);

    soccerplayerstatistics = Object.values(soccerplayerstatistics);


    for (var j = 0; j < soccerplayerstatistics.length; ++j){
      statsList.push(soccerplayerstatistics[j]);
    }

    // Push the stats list for the specific player onto the playerData dictionary
    playerData.push(statsList);
  }

  return playerData;
}