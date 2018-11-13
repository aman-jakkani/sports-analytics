$(document).ready(function() {
    $("#sport, #league, #team, #season").change(function() {

      if ($(this).attr('id') == 'sport') {
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


function loadResource()
            {
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

  for (i = 0; i < json.matches.length; ++i){
    htmlMatchString = htmlMatchString.concat("<option value=" + (i + 1) + ">" + json.matches[i] + "</option>")
  }

  return htmlMatchString;
}



/*
 // once database is created, we won't need any of these lists. we'll just load the data dynamically

  var leaguesList = [
    "<option value='0'>--Make a choice--</option>",
    "<option value='0'>--Make a choice--</option> <option value='1'>NBA</option>",
    "<option value='0'>--Make a choice--</option> <option value='2'>La Liga</option> <option value='3'>Premier League</option>"
  ];


  var teamsList = [
    "<option value='0'>--Make a choice--</option>", 
    "<option value='0'>--Make a choice--</option> <option value='1'>Houston Rockets</option> <option value='2'>LA Lakers</option>", 
    "<option value='0'>--Make a choice--</option> <option value='3'>FC Barcelona</option> <option value='4'>Real Madrid</option>",
    "<option value='0'>--Make a choice--</option> <option value='5'>Manchester United</option><option value='6'>Arsenal</option>"
  ];
  
  var seasonsList = [
    "<option value='0'>--Make a choice--</option>", 
    "<option value='0'>--Make a choice--</option> <option value='1'>2017</option> <option value='2'>2018</option>", 
    "<option value='0'>--Make a choice--</option> <option value='3'>2017</option> <option value='4'>2018</option>",
    "<option value='0'>--Make a choice--</option> <option value='5'>2010</option> <option value='6'>2011</option>",
    "<option value='0'>--Make a choice--</option> <option value='7'>2013</option> <option value='8'>2014</option>",
    "<option value='0'>--Make a choice--</option> <option value='9'>2010</option> <option value='10'>2011</option>",
    "<option value='0'>--Make a choice--</option> <option value='11'>2013</option> <option value='12'>2014</option>"
  ];
  
  var gamesList = [
    "<option value='0'>--Make a choice--</option>", 
    "<option value='0'>--Make a choice--</option> <option value='1'>1</option> <option value='2'>2</option>", 
    "<option value='0'>--Make a choice--</option> <option value='3'>3</option> <option value='4'>4</option>",
    "<option value='0'>--Make a choice--</option> <option value='5'>5</option> <option value='6'>6</option>",
    "<option value='0'>--Make a choice--</option> <option value='7'>7</option> <option value='8'>8</option>",
    "<option value='0'>--Make a choice--</option> <option value='9'>9</option> <option value='10'>10</option>",
    "<option value='0'>--Make a choice--</option> <option value='11'>11</option> <option value='12'>12</option>",
    "<option value='0'>--Make a choice--</option> <option value='13'>13</option> <option value='14'>14</option>", 
    "<option value='0'>--Make a choice--</option> <option value='15'>15</option> <option value='16'>16</option>",
    "<option value='0'>--Make a choice--</option> <option value='17'>17</option> <option value='18'>18</option>",
    "<option value='0'>--Make a choice--</option> <option value='19'>19</option> <option value='20'>20</option>",
    "<option value='0'>--Make a choice--</option> <option value='21'>21</option> <option value='22'>22</option>",
    "<option value='0'>--Make a choice--</option> <option value='23'>23</option> <option value='24'>24</option>"
  ];
  
  var statsList = [
    "<option value='0'>--Make a choice--</option>",
    "<option value='0'>--Make a choice--</option> <option value='1'>Rebounds</option> <option value='2'>Shooting Percentage</option>",
    "<option value='0'>--Make a choice--</option> <option value='3'>Goals</option> <option value='4'>Assists</option>"
  ];
  
  // Not sure if this is necessary, we could probably just take values from one list
  var statistic2 = [
    "<option value='0'>--Make a choice--</option>",
    "<option value='0'>--Make a choice--</option><option value='1'>Rebounds</option><option value='2'>Shooting Percentage</option>",
    "<option value='0'>--Make a choice--</option><option value='3'>Goals</option><option value='4'>Assists</option>"
  ];

});
*/
