/*
  Description
    This is the original charting function (plotDefault) which will plot data for two 
    teams and compare them.

  Args:
    chartType (string): the specific chart type that is to be plotted
    homeTeamName (string): 
    awayTeamName (string):
    homeTeamData (int/float[]):
    awayTeamData (int/float[]):

  Returns:
  Raises:
  Notes:
    Move this to its own separate js file within charts
*/
function plotDefault(chartType, homeTeamName, awayTeamName, homeTeamData, awayTeamData, label){
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
	
	console.log(label);
	
  var gameData = {
          labels: [label],
          datasets: [homeTeamData, awayTeamData]
  };

  var chartOptions = {
          scales: {
                  xAxes: [{
                  		ticks: {
                					beginAtZero: true
        				},
                          barPercentage: 1,
                          categoryPercentage: 0.6
                  }],
                  yAxes: [{
                          id: "y-axis",
                          ticks: {
                					beginAtZero: true
        					}
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

