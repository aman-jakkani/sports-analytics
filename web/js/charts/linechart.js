
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
  // remove previous chart from the canvas and start fresh
  if (window.bar != undefined){
          window.bar.destroy();
  }

  temp = ["Overall Rating", "Strength", "Shot Power"];
  
  var data = {
    labels: temp,
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
        max: 100,
        stepSize: 20
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
  window.bar = new Chart(ctx, config);	
}

