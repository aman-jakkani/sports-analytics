/* *****************************************************************
Description:
  This will be called anytime a dropdown menu option is clicked and will
  populate each dropdown with the resources retreived from the backend.
  Needed to store previous values of sport, league, team, season, and game

  Generates all the possible options based on what the user selects in the dropdowns.
  These parameters are passed to plot() in order to generate a token
  and get all the relevant statistics for those specific parameters.

Classes:
  dropdown: This will store values through page refreshes so that all values are present for
            subsequent calls to the api.
            All methods are used inside this class in order to shorten code.

Methods:
  getLeagues:
  getFactAttributes:
  getDimensions:
  getTeams:
  getSeasons:
  getGames:
  getCharts: this needs to be adjusted to limit chart options based on Group By (CUBE, ROLLUP, or NONE)
  getToken:
  getStats:
  getPlayerList: not currently implemented
  getGroupBy: replaced "getCubeOrRollup"

  getSoccerStats: can't have this hardcoded
  getBasketballStats: pointless but left it there

Dependencies:

******************************************************************* */

/*
Description:
  This object is intended to store all the values that the user selected while

Args:
Returns:
Raises:
Notes:
*/
var dropdown = {
  sport: "null",
  league: "null",
  team: "null",
  season: "null",
  game: "null",
  aggregationStyle: "null",
  chartType: "null",
  token: "null",
  player: "null",
  factatt: "null",

  run: $(document).ready(function() {
    var defaultString = "<option value = \"null\" >--Make a choice--</option>";

    $("#sport, #league, #team, #season, #game, #aggregationFunction, #aggregationStyle, #aggData").change(function() {

      if ($(this).attr('id') == 'sport') {
        sport = $(this).val();
        $("#league").html(getLeagues(sport));

        //$("#chartType").html(getCharts());

        // Reset options below league
        $("#team").html(defaultString);
        $("#season").html(defaultString);
        $("#game").html(defaultString);
        $("#stat1").html(defaultString);    // stat1 doesn't exist anymore, need to update later
        $("#chartType").html(getCharts(aggregationStyle)); 
        $("#axes").html(defaultString);
        $("#factAttribute").html(getFactAttribute(sport));
        $("#dimensions").html(getDimensions(sport));

      }

      else if ($(this).attr('id') == 'league') {
        league = $("#league").val();
        $("#team").html(getTeams(sport, league));
        $("#aggregationFunction").html(getAggregationFunction(league));

        // Reset options below team
        $("#season").html(defaultString);
        $("#game").html(defaultString);
        $("#stat1").html(defaultString);
        // $("#chartType").html(defaultString);
        $("#axes").html(defaultString);
      }

      else if ($(this).attr('id') == 'team') {
        team = $("#team").val();
        $("#season").html(getSeasons(sport, league, team));

        // Reset games
        $("#game").html(defaultString);
      }

      else if ($(this).attr('id') == 'season') {
        season = $("#season").val();
        $("#game").html(getGames(sport, league, team, season));

        // reset options below game
        $("#stat1").html(defaultString);
        // $("#chartType").html(defaultString);
        $("#axes").html(defaultString);
      }

      else if ($(this).attr('id') == "game"){
        game = $("#game").val();
        //$("#stat1").html(getStats(sport, league, team, season));
        $("#players").html(getPlayerList(sport, league, team, game));

        // token = getToken(sport, league, team, season, game);

        // Reset chart type and axes
        // $("#chartType").html(getCharts());
        // Populate players once games are selected
      }

	  else if ($(this).attr('id') == 'aggregationFunction') {
        aggregationFunction = $("#aggregationFunction").val();
        $("#aggregationStyle").html(getAggregationStyle(aggregationFunction));

        // Reset games
      }
      
      else if ($(this).attr('id') == 'aggregationStyle') {
        aggregationStyle = $("#aggregationStyle").val();
        $("#aggData").html(getAggData(sport));

        // Reset games
      }
      
      else if ($(this).attr('id') == 'aggData') {
        aggData = $("#aggData").val();
        $("#chartType").html(getCharts(aggregationStyle));

        // Reset games
      }

      else if ($(this).attr('id') == "chartType"){
        chartType = $("chartType").val();

      }
    });
  })
}


/*
  Description:
    return string of all seasons for a given sport/league/team
  Args:
  Returns:
  Raises:
  Notes:
*/
function getToken(sport, league, team, season, game){
  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", game]];

  var token = getRestResource("TokenResource", parameters);
  console.log("Token: " + token["token"]);

  return token;
}


/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
// get array of leagues based on sport
function getLeagues(sport) {

  var parameters = [["sports", sport], ];
  var htmlLeagueString = "<option value = \"null\" >--Make a choice--</option>";

  if (sport == "null") return htmlLeagueString;

  var json = getRestResource("LeagueListResource", parameters);

  console.log("Leagues found: ".concat(json.leagues.length));

  // create string for every league
  for (i = 0; i < json.leagues.length; ++i){
    htmlLeagueString = htmlLeagueString.concat("<option value = \"" + json.leagues[i] + "\">" + json.leagues[i] + "</option>");
  }

  document.getElementById("league").innerHTML = htmlLeagueString;

  return htmlLeagueString;
};

function getFactAttribute(sport) {

	var parameters = [["sports", sport], ];
	var htmlFactAttribute = "<option value = \"null\" >--Make a choice--</option>";

    if (sport == "null") return htmlFactAttribute;

	if (sport == "Basketball") {
		var htmlFactAttribute = "<option value='0'>--Make a choice--</option><option value='1'>Points</option><option value='2'>Assists</option><option value='3'>Rebounds</option><option value='4'>Steals</option><option value='5'>Blocks</option>";

	}

	else if (sport == "Soccer"){
		var htmlFactAttribute =  "<option value='0'>--Make a choice--</option><option value='1'>Goals</option><option value='2'>Assists</option><option value='3'>Possession Time</option><option value='4'>Fouls</option><option value='5'>Yellow Cards</option><option value='6'>Red Cards</option>";

	}
	return htmlFactAttribute;
}

function getDimensions(sport) {

	var parameters = [["sports", sport], ];
	var htmlDimensions = "<option value = \"null\" >--Make a choice--</option>";

    if (sport == "null") return htmlDimensions;

	if (sport == "Basketball") {
		var htmlDimensions = "<option value='0'>--Make a choice--</option><option value='1'>Point Guard</option><option value='2'>Shooting Guard</option><option value='3'>Small Forward</option><option value='4'>Power Forward</option><option value='5'>Center</option>";

	}

	else if (sport == "Soccer"){
		var htmlDimensions =  "<option value='0'>--Make a choice--</option><option value='1'>Goalkeeper</option><option value='2'>Fullback</option><option value='3'>Center Back</option><option value='4'>Midfielder</option><option value='5'>Striker</option>";

	}
	return htmlDimensions;
}

function getCubeOrRollup(){
	
	var sport = document.getElementById("sport").value;
	var league = document.getElementById("league").value;
	var agFunc = document.getElementById("aggregationFunction").value;
	var agStyle = document.getElementById("aggregationStyle").value;
	var agData = document.getElementById("aggData").value;
	var chart = document.getElementById("chartType").value;
	
	console.log(sport);
	console.log(league);
	console.log(agFunc);
	console.log(agStyle);
	console.log(chart);

	if (sport == null || league == null || agFunc == null || agStyle == null || agData == null || chart == null) {
	console.log("entered break");
	return;
	}

    var aggieFunc = [["aggregation", agFunc],["aggregationData", agData], ["sports", sport],["league", league]];
    console.log("QueryPairs: "+aggieFunc)   
    var json;
    var nullValues = {
    	"aggie": [], 
    	"dim1": [],
    	"dim2": []
    };
    var nullValues1 =  {
    	"aggie": [], 
    	"dim1": [],
    	"dim2": []
    };
    switch(agStyle){
        //case "Rollup": json = getRestResource("RollupResource",aggieFunc);
        case "Rollup": json = getRestResource("CubeResource",aggieFunc);
        	console.log(json);
    		for (i = 0; i < json.dim2.length; ++i){
    		
    			if (json.dim2[i] == null) {
    			
    				//add null values to new array
    				nullValues.dim2.push(json.dim2[i]);
    				nullValues.dim1.push(json.dim1[i]);
    				nullValues.aggie.push(json.aggie[i]);
    				
    			
    			}
    		
    		}
    		/*for (i = json.dim2.length - 1; i >= 0; --i){
    		
    			if (json.dim2[i] == null) {
    			
    				//remove null values from old array
    				json.dim1.splice(i,1);
    				json.dim2.splice(i,1);
    				json.aggie.splice(i,1);
    				
    			}
    		
    		}*/
    		
    		plotRollup(json, nullValues);
            break;
        case "Cube": json = getRestResource("CubeResource",aggieFunc);
        	console.log(json);
        	for (i = 0; i < json.dim2.length; ++i){
    		
    			if (json.dim2[i] == null) {
    			
    				//add null values to new array
    				nullValues.dim2.push(json.dim2[i]);
    				nullValues.dim1.push(json.dim1[i]);
    				nullValues.aggie.push(json.aggie[i]);
    				
    			
    			}
    		
    		}
    		for (i = 0; i < json.dim1.length; ++i){
    		
    			if (json.dim1[i] == null) {
    			
    				//add null values to new array
    				nullValues1.dim2.push(json.dim2[i]);
    				nullValues1.dim1.push(json.dim1[i]);
    				nullValues1.aggie.push(json.aggie[i]);
    				
    			
    			}
    		
    		}
    		/*console.log("before remove");
    		console.log(json.dim1.length);
    		for (i = json.dim2.length - 1; i >= 0; --i){
    		
    			if (json.dim2[i] == null) {
    			
    				//remove null values from old array
    				json.dim1.splice(i,1);
    				json.dim2.splice(i,1);
    				json.aggie.splice(i,1);
    				
    			}
    		
    		}*/
    		/*for (i = json.dim1.length - 1; i >= 0; --i){
    		
    			if (json.dim1[i] == null) {
    			
    				//remove null values from old array
    				json.dim1.splice(i,1);
    				json.dim2.splice(i,1);
    				json.aggie.splice(i,1);
    				
    			}
    		
    		}*/
    		console.log("after remove");
    		console.log(json.dim1.length);
         	plotCube(json, nullValues, nullValues1);
            break;
        default: console.log("No aggie function chosen")
    }
	
}

// return string of all teams based on sport/league
function getTeams(sport, league){

  var parameters = [["sports", sport], ["league", league]];
  var htmlTeamString = "<option value = \"null\" >--Make a choice--</option>";

  // place conditionals to get allow passing null values when other values are present (pass in all teams)
  if (sport == "null" || league == "null") return htmlTeamString;

  var json = getRestResource("TeamListResource", parameters);

  console.log("Teams found: ".concat(json.teams.length));

  for (i = 0; i < json.teams.length; ++i){
    htmlTeamString = htmlTeamString.concat("<option value = \"" + json.teams[i] + "\">" + json.teams[i] + "</option>")
  }

  //document.getElementById("team").innerHTML = htmlTeamString;

  return htmlTeamString;
}


/*
  Description:
    return string of all seasons for a given sport/league/team
  Args:
  Returns:
  Raises:
  Notes:
*/
function getSeasons(sport, league, team){
  var parameters = [["sports", sport], ["league", league], ["team", team]];
  var htmlSeasonString = "<option value = \"null\" >--Make a choice--</option>";

  // place conditionals to get allow passing null values when other values are present (pass in all teams)
  if (sport == "null" || league == "null" || team == "null") return htmlSeasonString;

  var json = getRestResource("SeasonListResource", parameters);

  console.log("Seasons found: ".concat(json.seasons.length));

  for (i = 0; i < json.seasons.length; ++i){
    htmlSeasonString = htmlSeasonString.concat("<option value = \"" + json.seasons[i] + "\">" + json.seasons[i] + "</option>")
  }

  document.getElementById("season").innerHTML = htmlSeasonString;

  return htmlSeasonString;
}


/*
  Description:
    return string of all seasons for a given sport/league/team
  Args:
  Returns:
  Raises:
  Notes:
*/
// return string of all games for a given sport/league/team/season
function getGames(sport, league, team, season){
  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season]];
  var htmlMatchString = "<option value = \"null\" >--Make a choice--</option>";

  // place conditionals to get allow passing null values when other values are present (pass in all teams)
  if (sport == "null" || league == "null" || team == "null" || season == "null") return htmlMatchString;

  var json = getRestResource("MatchListResource", parameters);

  console.log("Matches found: ".concat(json.match.length));

  for (i = 0; i < json.match.length; ++i){
    htmlMatchString = htmlMatchString.concat("<option value = \"" + json.match[i] + " \">" + json.match[i] + "</option>")
  }

  // Sets the dropdown to the list of values
  document.getElementById("game").innerHTML = htmlMatchString;

  return htmlMatchString;
}


/*
  Description:
    return string of all seasons for a given sport/league/team
  Args:
  Returns:
  Raises:
  Notes:
*/
// Attributes are currently hard coded in, should be fine for now
function getFactAttribute(sport) {
	var htmlFactAttribute = "<option value = \"null\" >--Make a choice--</option>";
  if (sport == "null") return htmlFactAttribute;

	if (sport == "Basketball") {
    htmlFactAttribute += "<option value='points'>Points</option>"
                        + "<option value='assists'>Assists</option>"
                        + "<option value='rebounds'>Rebounds</option>"
                        + "<option value='steals'>Steals</option>"
                        + "<option value='blocks'>Blocks</option>";
	}

	else if (sport == "Soccer"){
    htmlFactAttribute += "<option value='goals'>Goals</option>"
                        + "<option value='assists'>Assists</option>"
                        + "<option value='ballPossession'>Ball Possession</option>"
                        + "<option value='fouls'>Fouls</option>"
                        + "<option value='yellowCards'>Yellow Cards</option>"
                        + "<option value='redCards'>Red Cards</option>";
  }

	return htmlFactAttribute;
}


/*
  Description:
    return string of all seasons for a given sport/league/team
  Args:
  Returns:
  Raises:
  Notes:
*/
function getDimensions(sport) {
	var htmlDimensions = "<option value = \"null\" >--Make a choice--</option>";
  if (sport == "null") return htmlDimensions;

	if (sport == "Basketball") {
    htmlDimensions += "<option value='PG'>Point Guard</option>"
                    + "<option value='SG'>Shooting Guard</option>"
                    + "<option value='SF'>Small Forward</option>"
                    + "<option value='PF'>Power Forward</option>"
                    + "<option value='C'>Center</option>";
	}

	else if (sport == "Soccer"){
    htmlDimensions += "<option value='GK'>Goalkeeper</option>"
                    + "<option value='FB'>Fullback</option>"
                    + "<option value='CB'>Center Back</option>"
                    + "<option value='MF'>Midfielder</option>"
                    + "<option value='ST'>Striker</option>";
  }

	return htmlDimensions;
}


function getAggregationFunction(league){

  // Default value for the string
  var htmlChartString = "<option value = \"null\" >--Make a choice--</option>";

  /*
  // Dictionary of potential charts
  var charts = [['bar', 'Bar Chart'], ['line','Line Chart'], ['horizontalBar', 'Horizontal Bar Chart'],
                ['pie', 'Pie Chart'], ['doughnut', 'Doughnut Chart'], ['radar','Radar Chart'],
                ['polarArea', 'Polar Area Chart']];
  */
	console.log("made it to agFunc");
	console.log(league);

  if (league != null){
    var agFunction = [['SUM','Sum'], ['AVG','Average'], ['MAX','Max'], ['MIN','Min']];
  	console.log("Number of charts: ".concat(agFunction.length));
 
  	for (i = 0; i < agFunction.length; ++i){
    	htmlChartString = htmlChartString.concat("<option value = \"" + agFunction[i][0] + "\" >" + agFunction[i][1] + "</option>");
  	}
  
  }  else {
	 
  }

  document.getElementById("chartType").innerHTML = htmlChartString;

  return htmlChartString;
}


function getAggregationStyle(aggregationFunction){

  // Default value for the string
  var htmlChartString = "<option value = \"null\" >--Make a choice--</option>";

  /*
  // Dictionary of potential charts
  var charts = [['bar', 'Bar Chart'], ['line','Line Chart'], ['horizontalBar', 'Horizontal Bar Chart'],
                ['pie', 'Pie Chart'], ['doughnut', 'Doughnut Chart'], ['radar','Radar Chart'],
                ['polarArea', 'Polar Area Chart']];
  */

  if (aggregationFunction != null){
    var agStyle = [['Simple','Simple'], ['Rollup','Rollup'], ['Cube','Cube']];
  	console.log("Number of charts: ".concat(agStyle.length));
 
  	for (i = 0; i < agStyle.length; ++i){
    	htmlChartString = htmlChartString.concat("<option value = \"" + agStyle[i][0] + "\" >" + agStyle[i][1] + "</option>");
  	}
  
  }  else {
	 
  }

  document.getElementById("chartType").innerHTML = htmlChartString;

  return htmlChartString;
}


function getAggData(sport){

  // Default value for the string
  var htmlChartString = "<option value = \"null\" >--Make a choice--</option>";

  /*
  // Dictionary of potential charts
  var charts = [['bar', 'Bar Chart'], ['line','Line Chart'], ['horizontalBar', 'Horizontal Bar Chart'],
                ['pie', 'Pie Chart'], ['doughnut', 'Doughnut Chart'], ['radar','Radar Chart'],
                ['polarArea', 'Polar Area Chart']];
  */

  if (sport == "Soccer"){
    var agData = [['GOALS','Goals'], ['RED_CARDS','Red Cards'], ['YELLOW_CARDS','Yellow Cards'], ['FOULS','Fouls'], ['BALL_POSSESSION','Ball Possession'], ['CORNERS','Corners']];
  	console.log("Number of aggData: ".concat(agData.length));
 
  	for (i = 0; i < agData.length; ++i){
    	htmlChartString = htmlChartString.concat("<option value = \"" + agData[i][0] + "\" >" + agData[i][1] + "</option>");
  	}
  
  } else if (sport == "Basketball"){
  
  	var agData = [['0','No Options Yet']];
  	console.log("Number of aggData: ".concat(agData.length));
 
  	for (i = 0; i < agData.length; ++i){
    	htmlChartString = htmlChartString.concat("<option value = \"" + agData[i][0] + "\" >" + agData[i][1] + "</option>");
  	}
  
  } else {
	 
  }

  document.getElementById("chartType").innerHTML = htmlChartString;

  return htmlChartString;
}

/*
  Description:
    Returns a string in html to populate the chart dropdown based on the aggregration function selected
  Args:
  Returns:
  Raises:
  Notes:
*/
function getCharts(aggregationStyle){

  // Default value for the string
  var htmlChartString = "<option value = \"null\" >--Make a choice--</option>";

  /*
  // Dictionary of potential charts
  var charts = [['bar', 'Bar Chart'], ['line','Line Chart'], ['horizontalBar', 'Horizontal Bar Chart'],
                ['pie', 'Pie Chart'], ['doughnut', 'Doughnut Chart'], ['radar','Radar Chart'],
                ['polarArea', 'Polar Area Chart']];
  */


  if (aggregationStyle == "Cube"){
    var charts = [['bubble','Bubble Chart'], ['line','Line'], ['scatter','Scatter Plot']];
  	console.log("Number of charts: ".concat(charts.length));
 
  	for (i = 0; i < charts.length; ++i){
    	htmlChartString = htmlChartString.concat("<option value = \"" + charts[i][0] + "\" >" + charts[i][1] + "</option>");
  	}
  
  } else if (aggregationStyle=="Rollup"){
    var charts = [['bubble','Bubble Chart'], ['line','Line'], ['scatter','Scatter Plot']];
  	console.log("Number of charts: ".concat(charts.length));
 
  	for (i = 0; i < charts.length; ++i){
    	htmlChartString = htmlChartString.concat("<option value = \"" + charts[i][0] + "\" >" + charts[i][1] + "</option>");
  	}

  } else if (aggregationStyle=="Simple"){
    var charts = [['bar', 'Bar Chart'], ['line','Line Chart'], ['radar','Radar Chart']];
  	console.log("Number of charts: ".concat(charts.length));
 
  	for (i = 0; i < charts.length; ++i){
    	htmlChartString = htmlChartString.concat("<option value = \"" + charts[i][0] + "\" >" + charts[i][1] + "</option>");
  	}

  } else {
	 
  }

  document.getElementById("chartType").innerHTML = htmlChartString;

  return htmlChartString;
}



/*
  Description:
    return string of possible players
  Args:
  Returns:
  Raises:
  Notes:
*/
function getPlayerList(sport, league, team, game){
  var htmlPlayerString = "<option value = \"null\" >--Make a choice--</option>";

  // place conditionals to get allow passing null values when other values are present (pass in all teams)
  if (sport == "null" || league == "null" || team == "null" || season == "null" || game == "null") return htmlPlayerString;

  // var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", game]];
  // var token = getRestResource("TokenResource", parameters);
  var token = getToken(sport, league, team, season, game);
  var json = getRestResource("PlayerListResource", [["token", token["token"]], ]);

  console.log("Players found: ".concat(json.homePlayers.length + json.guestPlayers.length));

  for (i = 0; i < json.homePlayers.length; ++i){
    htmlPlayerString = htmlPlayerString.concat("<option value = \"" + json.homePlayersID[i] + "\">" + json.homePlayers[i] + "</option>");
  }

  // json.guestPlayersID[i + json.homePlayers.length]
  for (i = 0; i < json.guestPlayers.length; ++i){
    htmlPlayerString = htmlPlayerString.concat("<option value = \"" + json.guestPlayersID[i] + "\">" + json.guestPlayers[i] + "</option>");
  }

  console.log(json);

  document.getElementById("players").innerHTML = htmlPlayerString;

  return htmlPlayerString;
}


/*
  Description:
    Return rollup or cube resource
  Args:
  Returns:
  Raises:
  Notes:
*/
function getGroupBy(chartType){
  var aggieFunc = [["aggregation", document.getElementById("aggregationFunction").value], ];
  var json;

  switch(document.getElementById("aggregationStyle").value){
      case "Rollup": json = getRestResource("RollupResource", aggieFunc);
          break;

      case "Cube": json = getRestResource("CubeResource", aggieFunc);
          break;

      default: console.log("No aggie function chosen")
          return;
  }
  showChart(json, chartType);
}


/*
  Description:
  return string of possible stats for a given sport
  Args:
  Returns:
  Raises:
  Notes:
*/
function getStats(sport, league, team, game){
  var htmlStatString = "<option value = \"null\" >--Make a choice--</option>";

  // place conditionals to get allow passing null values when other values are present (pass in all teams)
  if (sport == "null" || league == "null" || team == "null" || season == "null") return htmlStatString;

  console.log("Sport: " + sport + ", League: " + league + ", Team: " + team + "Game: " + game);

  if (sport == "Soccer"){
    htmlTokenString = getSoccerStats(htmlTokenString);

  } else if (sport == "Basketball"){
    htmlTokenString = getBasketballStats(htmlTokenString);
  }

  return htmlStatString;
}

/*function getPlayerList(sport, league, team, game){
  var htmlPlayerString = "<option value = \"null\" >--Make a choice--</option>";

/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function getSoccerStats(htmlTokenString){
  htmlTokenString = htmlTokenString.concat("<option value = \"teamsInMatch\">Home and Away Teams</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"ballPossession\">Ball Possession</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"yellowCards\">Yellow Cards</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"cornerStats\">Corner Stats</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"redCards\">Red Cards</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"foulStats\">Foul Stats</option>");
  return htmlTokenString;
}


/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function getBasketballStats(htmlTokenString){
  
  return htmlTokenString;
}

