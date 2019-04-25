function plotPie(homeTeamName, awayTeamName, homeTeamData, awayTeamData, label, canvas){
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
	

var allData = {
    
                labels:["HOME","AWAY"],
                 datasets:[
                     {label:"STATS",
                      data:[homeTeamData.data[0], awayTeamData.data[0]],
                      backgroundColor:["rgb(255, 99, 132)","rgb(54, 162, 235)"]
                     }]
                };

	console.log(allData);
    var chartOptions = {
        
  };
  
    var config = {
          type: "pie",
          data: allData,
          options: chartOptions
  };
     return new Chart(canvas, config);
    
}