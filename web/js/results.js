/* *****************************************************************
Description:

Methods:
    displayStats:
    plot:
    generateChart:

Dependencies:

******************************************************************* */


/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function plot() {
  var sport = document.getElementById("sport").value;
  var league = document.getElementById("league").value;
  var team =  document.getElementById("team").value;
  var season =  document.getElementById("season").value;
  var match =  document.getElementById("game").value;
  var factatt = document.getElementById("factAttribute").value;
  var chartType = document.getElementById("chartType").value;
  /*
  var aggregfunc = document.getElementById("aggregationFunction").value;
  var aggregstyle = document.getElementById("aggregationStyle").value;
  var dimension  = document.getElementById("dimensions").value;
  */

  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["factatt", factatt]];
  console.log(parameters);

  var token = getRestResource("TokenResource", parameters);
  var data = null;
  
  // Get the data for the specific attribute selected
  if (sport == "Soccer"){
    data = getSoccerAttributeData(factatt, token);
    console.log("Got soccer attributes");

  } else if (sport == "Basketball"){
    data = getBasketballAttributeData(factatt, token);
    console.log("Got basketball attributes");

  } else {
    console.log("Invalid sport selection");
    return;
  }
  
  var label = document.getElementById("factAttribute").value;

  // Plot a specific chart based on the one selected in the 
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