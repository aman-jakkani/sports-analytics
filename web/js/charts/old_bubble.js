function plotBubble(){

	var canvasId = "myChart1";
	var canvas = document.getElementById(canvasId);
	var chartType;
	var offsetWidth = 0;
	var gameData = {
  labels: ['0h','1h','2h','3h','4h','5h','6h','7h','8h','9h','10h','11h'],
  datasets: [
    {
      label: 'Hawks',
      backgroundColor: "rgba(255,221,50,0.2)",
      data: [{x: 20,
            y: 41,
            r: 45}]
    },
    {
      label: 'Heat',
      backgroundColor: "rgba(60,186,159,0.2)",
      data: [{x: 25,
            y: 38,
            r: 29}]
    },
    {
      label: 'Lakers',
      backgroundColor: "rgba(0,0,0,0.2)",
      data: [{x: 30,
            y: 34,
            r: 58}]
    },
    {
      label: 'Rockets',
      backgroundColor: "rgba(193,46,12,0.2)",
      data: [{x: 23,
            y: 45,
            r: 41}]
    },
    {
      label: 'Warriors',
      backgroundColor: "rgba(128,0,0,0.2)",
      data: [{x: 28,
            y: 47,
            r: 30}]
    }
  ]
};
  


	var chartOptions = {
	
	title: {
        display: true,
        text: 'Correlation of Field Goal Percentage and Assists Per Game To Number of Wins'
      }, scales: {
        yAxes: [{ 
          scaleLabel: {
            display: true,
            labelString: "Field Goal Percentage"
          }
        }],
        xAxes: [{ 
          scaleLabel: {
            display: true,
            labelString: "Assists Per Game"
        }
      }]
	
	}
	}
	var config = {
                type: chartType = 'bubble',
                data: gameData,
                options: chartOptions
        };
        
    globalCharts.push(new Chart(canvas, config));
	
	//var ctx = document.getElementById('myChart1').getContext('2d');
	//var newChart = new Chart(ctx).HeatMap(gameData);
	//var canvas = document.getElementById(canvasId);
	//var newChart = new Chart(ctx).HeatMap(gameData, chartOptions);

}
