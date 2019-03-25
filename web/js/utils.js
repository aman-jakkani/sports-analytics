
function getRandomColor() {
  var letters = '0123456789ABCDEF';
  var color = '#';
  for (var i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}

function backToStats(){
  document.getElementById("Charts").style.display = "block";
  document.getElementById('Player').style.display="none";
  document.getElementById("Dropdown").style.display = "none";
}


// Button to return back to previous dropdown page
function backToDropdown(){
  document.getElementById("Charts").style.display = "none";
  document.getElementById('Player').style.display="none";
  document.getElementById("Dropdown").style.display = "block";
  while(globalCharts.length > 0){
      globalCharts[globalCharts.length-1].destroy();
      globalCharts.pop();
  }
}

/*
function loadResource()
{
    var xhttp = new XMLHttpRequest();
    var parameter = document.getElementById("parameter").value;
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("response").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("GET", "/rest/DemoRestResource?caller=".concat(parameter), true);
    xhttp.send();
}

function generateChart(chartType, homeTeamName, awayTeamName, homeTeamDataParam, awayTeamDataParam, label){
 
  var chartOptions = {
    title: {
      display: true,
      text: 'Chart Title'
    },
    scales: {
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: "Y-Axis"
        }
      }],
      xAxes: [{
        scaleLabel: {
          display: true,
          labelString: "X-Axis"
        }
      }]
    }
  }
 

  var canvas = document.getElementById("myChart");

  var showLabel = true;
  if (homeTeamName == undefined && awayTeamName == undefined) {
      showLabel = false;
  }

  // Needed for something ... 
  if (window.bar != undefined) {
          window.bar.destroy();
  }

  // Home color is currently hardcoded in
  var homeTeamData = {
          label: homeTeamName,
          data: [homeTeamDataParam, "0"],
          backgroundColor: 'rgba(148, 28, 47, 0.6)',
          borderWidth: 0,
          yAxisID: homeTeamName // "y-axis"
  };

  // Away color is currently hardcoded in
  var awayTeamData = {
          label: awayTeamName,
          data: [awayTeamDataParam, "0"],
          backgroundColor: 'rgba(32, 164, 243, 0.6)',
          borderWidth: 0,
          yAxisID: awayTeamName // "x-axis"
  };

  var gameData = {
          labels: [label],
          datasets: [homeTeamData, awayTeamData]
  };

  var chartOptions = {
          scales: {
                  xAxes: [{
                          barPercentage: 1,
                          categoryPercentage: 0.6
                  }],
                  yAxes: [{
                          id: "y-axis",
                          ticks: {
                    beginAtZero: true,
                    callback: function (value) { if (Number.isInteger(value)) { return value; } }
                }
                  }]
          },
          legend: {
              display: showLabel
          }
  };
  
  var config = {
    type: chartType = chartType,
    data: gameData,
    options: chartOptions
  };

  console.log(chartType);
  window.bar = new Chart(canvas, config);
}

*/