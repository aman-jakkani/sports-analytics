$(document).ready(function() {
    $("#sport, #league, #team, #season").change(function() {
  
      if ($(this).attr('id') == 'sport') {
        var leagueName = $(this).val();
        var leaguesList;

        // not sure how you're supposed to get the actual sport value, but we just need to replace 'sport' with that variable

        if ('sport' == 'Basketball') {
            leaguesList = BasketballController.getLeagues();
        }
        else if ('sport' == 'Soccer'){
            leaguesList = SoccerController.getLeagues();
        }
        $("#league").html(leaguesList[leagueName]);
  
  
        var stat1 = $(this).val();
        $("#firstStatistic").html(statsList[stat1]);
  
        var stat2 = $(this).val();
        $("#secondStatistic").html(statsList[stat2]);
      } 
  
      else if ($(this).attr('id') == 'league') {
        var teamNames = $("#league").val();
        var teamsList;

        if ('sport' == 'Basketball'){
            teamsList = BasketballController.getTeams();

        } else if ('sport' == 'Soccer'){
            teamsList = SoccerController.getTeams();
        }

        $("#team").html(teamsList[teamNames]);
      }
  
        else if ($(this).attr('id') == 'team') {

        var seasonName = $("#team").val();
        var seasonsList;

        if ('sport' == 'Basketball'){
            seasonsList = BasketballController.getSeasons();
        } else if ('sport' == 'Soccer'){
            seasonsList = SoccerController.getSeasons();
        }
        $("#season").html(seasonsList[seasonName]);
      }
  
      else if ($(this).attr('id') == 'season') {
        var gameName = $("#season").val();
        var gamesList;

        if ('sport' == 'Basketball'){
            gamesList = BasketballController.getGames();
        } else if ('sport' == 'Soccer'){
            gamesList = SoccerController.getGames();
        }
        
        $("#game").html(gamesList[gameName]);
      }
  
    }
  );