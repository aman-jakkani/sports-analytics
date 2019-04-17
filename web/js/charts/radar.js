/*
Description:
Args:
Returns:
Raises:
Notes:
Dependencies:
  None
*/
function plotRadar(name, stat0, stat1, stat2) {
  
  if (window.bar != undefined){
    window.bar.destroy();
  }

  temp = ["Overall Rating", "Strength", "Shot Power"];
	var data = {
    labels: temp,
    datasets: [{
      label: name,
      backgroundColor: "rgba(200,0,0,0.2)",
      data: [stat0, stat1, stat2]
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
      type: 'radar',
      data: data,
      options: options
  };

  var ctx = document.getElementById("playerChart");
  return new Chart(ctx, config);
}
