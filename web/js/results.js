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
  Description:
    This function should take all the selected attributes and return an array with all the data formatted
  Args:
  Returns:
  Raises:
  Notes:
*/
function getSoccerAttributeData(attribute, token){

  switch (attribute){
    case ("score"): break;
    case ("ballPossession"): break;
    case ("yellowCards"): break;
    case ("redCards"): break;
    case ("cornerStats"): break;
    case ("fouls"): break;
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
function getBasketballAttributeData(attributeList, token){
  for (i = 0; i < attributeList.length; ++i){
    switch (attribute){
      case ("points"): break;
      case (""): break;
      default: break;
    }
  }
}


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
  var aggregfunc = document.getElementById("aggregationFunction").value;
  var aggregstyle = document.getElementById("aggregationStyle").value;
  var dimension  = document.getElementById("dimensions").value;

  var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["factatt", factatt], ["aggregfunc", aggregfunc], ["aggregstyle", aggregstyle], ["dimension", dimension]];
  console.log(parameters);

  var token = getRestResource("TokenResource", parameters);
  // console.log("Token: " + token["token"]);
  

  

}



/*
  Description
    This will generate a Chart object and render it on the html canvas

  Args:
    chartType (string): the specific chart type that is to be plotted
    homeTeamName (string): 
    awayTeamName (string):
    homeTeamData (int/float[]):
    awayTeamData (int/float[]):

  Returns:
  Raises:
  Notes:
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
