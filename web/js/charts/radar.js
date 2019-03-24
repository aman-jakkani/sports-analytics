function plotRadar(name, stat0, stat1, stat2) {


	var canvasId = "myRadar";
	var canvas = document.getElementById(canvasId);

	var radarData = {
  labels: ["Overall Rating", "Strength", "Shot Power"],
  datasets: [{
    label: name,
    backgroundColor: "rgba(200,0,0,0.2)",
    data: [stat0, stat1, stat2]
  }]
};
 
var chartOptions = {
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
                data: radarData,
                options: chartOptions
        };

globalCharts.push(new Chart(canvas, config));

}
