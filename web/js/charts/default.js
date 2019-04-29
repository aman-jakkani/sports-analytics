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
function plotDefault(chartType, homeTeamName, awayTeamName, homeTeamData, awayTeamData, label, canvas){

  if (window.bar != undefined){
          window.bar.destroy();
  }

  var homeTeamData = {
          label: homeTeamName,
          data: homeTeamData,
          backgroundColor: "rgb(255, 99, 132)",
          borderWidth: 0,
          yAxisID: "y-axis"
  };

  var awayTeamData = {
          label: awayTeamName,
          data: awayTeamData,
          backgroundColor: "rgb(54, 162, 235)",
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

  return new Chart(canvas, config);	
}

