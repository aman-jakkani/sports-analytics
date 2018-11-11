$(document).ready(function() {
  $("#sport, #league, #team, #season").change(function() {

    if ($(this).attr('id') == 'sport') {
      var leagueName = $(this).val();
      $("#league").html(leaguesList[leagueName]);

      var stat1 = $(this).val();
      $("#firstStatistic").html(statsList[stat1]);

      var stat2 = $(this).val();
      $("#secondStatistic").html(statsList[stat2]);
    } 

    else if ($(this).attr('id') == 'league') {
      var teamName = $("#league").val();
      $("#team").html(teamsList[teamName]);
    }

	  else if ($(this).attr('id') == 'team') {
      var seasonName = $("#team").val();
      $("#season").html(seasonsList[seasonName]);
    }

    else if ($(this).attr('id') == 'season') {
      var gameName = $("#season").val();
      $("#game").html(gamesList[gameName]);
    }

  }
);


 // once database is created, we won't need any of these lists. we'll just load the data dynamically

  var leaguesList = [
    "<option value='0'>--Make a choice--</option>",
    "<option value='0'>--Make a choice--</option><option value='1'>NBA</option>",
    "<option value='0'>--Make a choice--</option><option value='2'>La Liga</option><option value='3'>Premier League</option>"
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


