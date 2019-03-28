
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
  
  var jsonData = new Array();
  for (var i = 0; i<json.length; ++i){
  jsonData.push(parseFloat(json[i]));
  }  
  
  var data = {
    labels: ["Home Team", "Away Team"],
    datasets: jsonData
  };

console.log(data);

  var options = {
    scales: {
      xAxes: [{
      	ticks: {
                					beginAtZero: true
        },
        min: 0,
        max: 100
      }],
      pointLabels: {
        fontSize: 18
      }
    },
    legend: {
      position: 'left'
    }
    /*title: {
      display: true,
      text: 'World population per region (in millions)'
    }*/
  };

  var config = {
      type: 'line',
      data: data,
      options: options
  };


  var ctx = document.getElementById("mainChart");
  new Chart(ctx, config);	
}

