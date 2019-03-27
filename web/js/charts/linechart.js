
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
    Move this to its own separate js file within charts
*/
function plotLineChart(json){
  console.log("Entered line chart");
  console.log(json);
  // destroy any old chart already on the canvas
  if (window.bar != undefined){
        window.bar.destroy();
  }

  var data = {
    labels: ["Home Team", "Away Team"],
    datasets: [{
      label: name,
      backgroundColor: "rgba(200,0,0,0.2)",
      data: json
    }]
  };


  var options = {
    scale: {
      ticks: {
        beginAtZero: true,
        min: 0,
        max: 100
      },
      pointLabels: {
        fontSize: 18
      }
    },
    legend: {
      position: 'left'
    }
  };

  var config = {
      type: 'line',
      data: data,
      options: options
  };


  var ctx = document.getElementById("mainChart");
  new Chart(ctx, config);	
}

