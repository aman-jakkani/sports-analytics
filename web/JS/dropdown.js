
$(document).ready(function() {
    $("#sport, #league, #team, #season").change(function() {

      if ($(this).attr('id') == 'sport') {

        // load new resources every time the sport is changed
        loadResource();

        var sport = $(this).val();
        $("#league").html(getLeagues(sport));

        statsRequested(sport)
        $("#firstStatistic").html(getStats(sport));

        statsRequested(sport)
        $("#secondStatistic").html(getStats(sport));
      } 

      else if ($(this).attr('id') == 'league') {
        var league = $("#league").val();
        $("#team").html(getTeams(sport, league));
      }

      else if ($(this).attr('id') == 'team') {
        var team = $("#team").val();
        $("#season").html(getSeasons(sport, league, team));
      }

      else if ($(this).attr('id') == 'season') {
        var season = $("#season").val();
        $("#game").html(getGames(sport, league, team, season));
      }
    }
  );
});

function loadResource() {
    var xhttp = new XMLHttpRequest();
    var parameter = document.getElementById("parameter").value;
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("response").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("GET", "/rest/DemoRestResource?caller=".concat(parameter), true);
    xhttp.send();
}

// Note: need to include loadResources() ?

// get string of leagues
function getLeagues(sport) {

  var parameters = [["sports", sport], ];
  var json = getRestResource("LeagueListResource", parameters);

  var htmlLeagueString = "<option value=0 >--Make a choice--</option>";

  // return default option if there are no leagues in the sport, or if no sport is selected
  if (json.leagues.length == 0) return htmlLeagueString;


  console.log("Leagues found: ".concat(json.leagues.length));
  // create string for every league
  for (i = 0; i < json.leagues.length; ++i){
    htmlLeagueString = htmlLeagueString.concat("<option value=" + (i + 1) + ">" + json.leagues[i] + "</option>");
  }

  return htmlLeagueString; 
};


// return string of possible stats for a given sport
function getStats(sport){
  // don't have stats resource available yet
  return "";
}

// return string of all teams for a given sport/league
function getTeams(sport, league){
  var parameters = [["sports", sport], ["league", league]];
  var json = getRestResource("TeamListResource", parameters);

  var htmlTeamString = "<option value=0 >--Make a choice--</option>";


  console.log("Teams found: ".concat(json.teams.length));
  // return default string if there are no teams for a specific league, or if no league is selected
  if (json.teams.length == 0) return htmlTeamString;

  for (i = 0; i < json.teams.length; ++i){
    htmlTeamString = htmlTeamString.concat("<option value=" + (i + 1) + ">" + json.teams[i] + "</option>")
  }

  return htmlTeamString;
}


// return string of all seasons for a given sport/league/team
function getSeasons(sport, league, team){
  var parameters = [["sports", sport], ["league", league], ["team", team]];
  var json = getRestResource("SeasonListResource", parameters);

  var htmlSeasonString = "<option value=0 >--Make a choice--</option>";

  // return default string if there are no teams for a specific league, or if no league is selected
  if (json.seasons.length == 0) return htmlSeasonString;


  console.log("Seasons found: ".concat(json.seasons.length));
  for (i = 0; i < json.seasons.length; ++i){
    htmlSeasonString = htmlSeasonString.concat("<option value=" + (i + 1) + ">" + json.seasons[i] + "</option>")
  }

  return htmlSeasonString;
}


/* This REST resource isn't used in the example HTML file, need to confirm it is correct */
// return string of all games for a given sport/league/team/season
function getGames(sport, league, team, season){
  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season]];
  var json = getRestResource("MatchListResource", parameters);

  var htmlMatchString = "<option value=0 >--Make a choice--</option>";

  // return default string if there are no teams for a specific league, or if no league is selected
  if (json.matches.length == 0) return htmlMatchString;


  console.log("Matches found: ".concat(json.matches.length));
  for (i = 0; i < json.matches.length; ++i){
    htmlMatchString = htmlMatchString.concat("<option value=" + (i + 1) + ">" + json.matches[i] + "</option>")
  }

  return htmlMatchString;
}
