// This will be called anytime a dropdown menu option is clicked. 
// Stores previous values of sport, league, team, season, and game

var dropdown = { 
  sport: "null",
  league: "null",
  team: "null",
  season: "null",
  game: "null",
  chartType: "null",
  token: "null",

  run: $(document).ready(function() {
    var defaultString = "<option value = \"null\" >--Make a choice--</option>";

    $("#sport, #league, #team, #season").change(function() {

      if ($(this).attr('id') == 'sport') {
        sport = $(this).val();
        $("#league").html(getLeagues(sport));

        $("#chartType").html(getCharts());

        // Reset options below league
        $("#team").html(defaultString);
        $("#season").html(defaultString);
        $("#game").html(defaultString);
        $("#stat1").html(defaultString);        
        // $("#chartType").html(defaultString); 
        $("axes").html(defaultString);
        }

      else if ($(this).attr('id') == 'league') {
        league = $("#league").val();
        $("#team").html(getTeams(sport, league));

        // Reset options below team
        $("#season").html(defaultString);
        $("#game").html(defaultString);
        $("#stat1").html(defaultString);        
        // $("#chartType").html(defaultString); 
        $("axes").html(defaultString);
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
        $("axes").html(defaultString);
      }

      else if ($(this).attr('id') == "game"){
        game = $("#game").val();
        $("#stat1").html(getStats(sport, league, team, season)); 

        // token = getToken(sport, league, team, season, game);

        // Reset chart type and axes
        $("#chartType").html(getCharts()); 
      }

      else if ($(this).attr('id') == "chartType"){
        chartType = $("chartType").val(); 

      }
    });
  })
}



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

  document.getElementById("team").innerHTML = htmlTeamString;

  return htmlTeamString;
}


// return string of all seasons for a given sport/league/team
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

  document.getElementById("game").innerHTML = htmlMatchString;

  return htmlMatchString;
}


function getCharts(){
  var htmlChartString = "<option value = \"null\" >--Make a choice--</option>";

  /*
  var charts = [['bar', 'Bar Chart'], ['line','Line Chart'], ['horizontalBar', 'Horizontal Bar Chart'], 
                ['pie', 'Pie Chart'], ['doughnut', 'Doughnut Chart'], ['radar','Radar Chart'], 
                ['polarArea', 'Polar Area Chart']];
  */

 var charts = [['bar', 'Bar Chart'], ['line','Line Chart'], ['radar','Radar Chart']];
  
  console.log("Number of charts: ".concat(charts.length));
 
  for (i = 0; i < charts.length; ++i){
    htmlChartString = htmlChartString.concat("<option value = \"" + charts[i][0] + "\" >" + charts[i][1] + "</option>");
  }

  document.getElementById("chartType").innerHTML = htmlChartString;

  return htmlChartString;
}


function getToken(sport, league, team, season, game){
  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", game]];

  var token = getRestResources("TokenResource", parameters);
  console.log("Token: " + token["token"]);

}


// return string of possible stats for a given sport
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


function getSoccerStats(htmlTokenString){
  htmlTokenString = htmlTokenString.concat("<option value = \"teamsInMatch\">Home and Away Teams</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"ballPossession\">Ball Possession</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"yellowCards\">Yellow Cards</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"cornerStats\">Corner Stats</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"redCards\">Red Cards</option>");
  htmlTokenString = htmlTokenString.concat("<option value = \"foulStats\">Foul Stats</option>");
  return htmlTokenString;
}


function getBasketballStats(htmlTokenString){
  
  return htmlTokenString;
}

