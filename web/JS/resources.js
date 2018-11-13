
// This will be called anytime a dropdown menu option is clicked, regardless of if it changed or 
var analytics = { 
sport: "null",
league: "null",
team: "null",
season: "null",

run: $(document).ready(function() {
  var defaultString = "<option value = \"null\" >--Make a choice--</option>";

  $("#sport, #league, #team, #season").change(function() {

    if ($(this).attr('id') == 'sport') {
      sport = $(this).val();
      $("#league").html(getLeagues(sport));

      getStats(sport)
      $("#firstStatistic").html(getStats(sport));

      getStats(sport)
      $("#secondStatistic").html(getStats(sport));

      // Reset options below league
      $("#team").html(defaultString);
      $("#season").html(defaultString);
      $("#game").html(defaultString);
    } 

    else if ($(this).attr('id') == 'league') {
      league = $("#league").val();
      $("#team").html(getTeams(sport, league));

      // Reset options below team
      $("#season").html(defaultString);
      $("#game").html(defaultString);
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
    }
  });
})
}



// get string of leagues
function getLeagues(sport) {

  var parameters = [["sports", sport], ];
  var htmlLeagueString = "<option value = \"null\" >--Make a choice--</option>";

  if (sport == "null") return htmlLeagueString;

  console.log("Sport: ".concat(sport));

  var json = getRestResource("LeagueListResource", parameters);


  // return default option if there are no leagues in the sport, or if no sport is selected
  if (json.leagues.length == 0) return htmlLeagueString;

  console.log("Leagues found: ".concat(json.leagues.length));

  // create string for every league
  for (i = 0; i < json.leagues.length; ++i){
    htmlLeagueString = htmlLeagueString.concat("<option value = \"" + json.leagues[i] + "\">" + json.leagues[i] + "</option>");
  }

  // for debugging
  console.log(htmlLeagueString);
  return htmlLeagueString; 
};


// return string of all teams for a given sport/league
function getTeams(sport, league){
  var parameters = [["sports", sport], ["league", league]];
  var htmlTeamString = "<option value = \"null\" >--Make a choice--</option>";

  if (sport == "null" || league == "null") return htmlTeamString;

  var json = getRestResource("TeamListResource", parameters);

  console.log("Teams found: ".concat(json.teams.length));

  // return default string if there are no teams for a specific league, or if no league is selected
  if (json.teams.length == 0) return htmlTeamString;

  for (i = 0; i < json.teams.length; ++i){
    htmlTeamString = htmlTeamString.concat("<option value = \"" + json.teams[i] + "\">" + json.teams[i] + "</option>")
  }

  // for debugging
  console.log(htmlTeamString);
  return htmlTeamString;
}


// return string of all seasons for a given sport/league/team
function getSeasons(sport, league, team){
  var parameters = [["sports", sport], ["league", league], ["team", team]];
  var htmlSeasonString = "<option value = \"null\" >--Make a choice--</option>";

  if (sport == "null" || league == "null" || team == "null") return htmlSeasonString;

  var json = getRestResource("SeasonListResource", parameters);


  // return default string if there are no teams for a specific league, or if no league is selected
  if (json.seasons.length == 0) return htmlSeasonString;

  console.log("Seasons found: ".concat(json.seasons.length));

  for (i = 0; i < json.seasons.length; ++i){
    htmlSeasonString = htmlSeasonString.concat("<option value = \"" + json.seasons[i] + "\">" + json.seasons[i] + "</option>")
  }

  // for debugging
  console.log(htmlSeasonString);
  return htmlSeasonString;
}


// return string of all games for a given sport/league/team/season
function getGames(sport, league, team, season){
  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season]];
  var json = getRestResource("MatchListResource", parameters);

  var htmlMatchString = "<option value = \"null\" >--Make a choice--</option>";


  // return default string if there are no teams for a specific league, or if no league is selected
  if (json.matches.length == 0) return htmlMatchString;

  console.log("Matches found: ".concat(json.matches.length));

  for (i = 0; i < json.matches.length; ++i){
    htmlMatchString = htmlMatchString.concat("<option value = \"" + json.matches[i] + " \">" + json.matches[i] + "</option>")
  }

  // for debuggging
  console.log(htmlMatchString);
  return htmlMatchString;
}


// return string of possible stats for a given sport
function getStats(sport){
  var htmlStatString = "<option value = \"null\" >--Make a choice*--</option>";
  return htmlStatString;
}
