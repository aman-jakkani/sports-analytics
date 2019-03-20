var globalCharts = new Array();


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

function getRandomColor() {
  var letters = '0123456789ABCDEF';
  var color = '#';
  for (var i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}


function showChart(json) {
    var dim1 = json.dim1;
    var dim2 = json.dim2;
    var aggie = json.aggie;
    /*alert(dim1.length);
    alert(dim2.length);
    alert(aggie.length);*/
    var ctx = document.getElementById("myChart").getContext('2d');

    var dim1Int = new Array();
   for(var i= 0;i<dim1.length;i++){
        if(dim1[i]!= null){
            dim1Int.push(parseInt(dim1[i].substr(0,4)));
        }else{
            dim1Int.push(null);
        }
        
    }

    var datasets = new Array();
    var teamset = new Array();
    for (var i=0; i<dim2.length; i++)
    {
        if(teamset.includes(dim2[i]) /*|| dim2[i] == null*/)
        {
            break;
        }
        else
        {
            teamset.push(dim2[i]);
        }

        var seasonData = new Array();
        for (var j=0; j<dim1Int.length; j++)
        {
            if(dim2[j] == dim2[i] && dim1Int[j] != null)
            {
                seasonData.push(
                    {
                        x: dim1Int[j],
                        y: aggie[j]
                    }
                );
                //console.log('x: ' + dim1Int[j] + ' (' +typeof dim1Int[j] + ') '+ ' y: ' + aggie[j] );
            }
        }
		var col = getRandomColor();
        var teamData =
            {
            
                label: dim2[i],
                data: seasonData,
                showLine: true,
                fill: false,
       			borderColor: col,
        		pointBorderColor: col,
        		backgroundColor: col,
        		hidden: true
                
            };

        datasets.push(teamData);
    }
    var scatterChart = new Chart(ctx, {
        type: 'scatter',
        data:
            {
                datasets: datasets
            },
        options: {
            scales: {
                xAxes: [{
                    type: 'linear',
                    position: 'bottom'
                }]
            }
        }
    });
}


function stats(){
	
	document.getElementById('hiddenText1').style.display="block";
	document.getElementById('Player').style.display="block";
	document.getElementById("Charts").style.display = "none";
	document.getElementById("Dropdown").style.display = "none";

	var sport = document.getElementById('chosenSport').innerHTML = document.getElementById("sport").value;
    var league = document.getElementById('chosenLeague').innerHTML = document.getElementById("league").value;
    var team = document.getElementById('chosenTeam').innerHTML = document.getElementById("team").value;
    var season = document.getElementById('chosenSeason').innerHTML = document.getElementById("season").value;
    var match = document.getElementById('chosenGame').innerHTML = document.getElementById("game").value;
    //var name = document.getElementById('name').innerHTML = document.getElementById("player").options[document.getElementById('player').selectedIndex].text;
        
    
    var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["name", name]];
    console.log(parameters);

	var token = getRestResource("TokenResource", parameters);
    console.log("Token: " + token["token"]);

	var stats = document.getElementById("player").value;
	console.log(stats);
	
	var playerstatistics = getRestResource("PlayerResource", [["token", token["token"]], ["playerID", stats]]);
	console.log(playerstatistics);
	var playerInfo = Object.values(playerstatistics);
	
	var birthday = playerInfo[0];
	document.getElementById("birthday").innerHTML = birthday;
	
	var name = playerInfo[1];
	document.getElementById("name").innerHTML = name;
	
	var weight = playerInfo[2];
	document.getElementById("weight").innerHTML = weight;
	
	var height = playerInfo[3];
	document.getElementById("height").innerHTML = height;

	
	var soccerplayerstatistics = getRestResource("SoccerPlayerResource", [["token", token["token"]], ["playerID", stats]]);
	console.log(soccerplayerstatistics);
	console.log(Object.values(soccerplayerstatistics));
	var individualStats = Object.values(soccerplayerstatistics);
	
	var overallRating = individualStats[0];
	//document.getElementById("overallRating").innerHTML = overallRating;
	
	var strength = individualStats[1];
	//document.getElementById("strength").innerHTML = strength;
	
	var shotPower = individualStats[2];
	//document.getElementById("shotPower").innerHTML = shotPower;
	
	var preferredFoot = individualStats[3];
	var preferredFootCap = preferredFoot.charAt(0).toUpperCase() + preferredFoot.slice(1)
	document.getElementById("preferredFoot").innerHTML = preferredFootCap;
	

	plotRadar(name, overallRating, strength, shotPower);
	
}

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


function plotChart() {
        document.getElementById('hiddenText').style.display="block";

        var sport = document.getElementById('chosenSport').innerHTML = document.getElementById("sport").value;
        var league = document.getElementById('chosenLeague').innerHTML = document.getElementById("league").value;
        var team = document.getElementById('chosenTeam').innerHTML = document.getElementById("team").value;
        var season = document.getElementById('chosenSeason').innerHTML = document.getElementById("season").value;
        var match = document.getElementById('chosenGame').innerHTML = document.getElementById("game").value;
        var factatt = document.getElementById("factAttribute").value;
        var aggregfunc = document.getElementById("aggregationFunction").value;
        var aggregstyle = document.getElementById("aggregationStyle").value;
        var dimension  = document.getElementById("dimensions").value;
        
    
        var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match], ["factatt", factatt], ["aggregfunc", aggregfunc], ["aggregstyle", aggregstyle], ["dimension", dimension]];
        console.log(parameters);

        var token = getRestResource("TokenResource", parameters);
        console.log("Token: " + token["token"]);

        //var token = getRestResource("RollupTokenResource", parameters);
        //console.log("Token: " + token["token"]);

        var teams = getRestResource("HomeAndAwayTeamListResource", [["token", token["token"]],]);
        console.log("Teams: " + teams["homeAndAwayTeam"]);


        var homeTeamData = [];
        var awayTeamData = [];
        var availableStats = [];


        // [["token", token["token"]], ]) is an array of dict values
        var score = getRestResource("ScoreRestResource", [["token", token["token"]], ]);
        if(score != null){
            console.log("Score: " + score["score"][0] + ":" + score["score"][1] );
            homeTeamData.push(score["score"][0]);
            awayTeamData.push(score["score"][1]);
            availableStats.push("Score");
        }

        var ballPossession = getRestResource("BallPossessionStatResource", [["token", token["token"]], ]);
        if(ballPossession != null){
            console.log("Ball Possession: " + ballPossession["possession"]);
            homeTeamData.push(ballPossession["possession"][0]);
            awayTeamData.push(ballPossession["possession"][1]);
            availableStats.push("Ball Possession");
        }

        var yellowCards = getRestResource("YellowCardsStatResource", [["token", token["token"]], ]);
        if(yellowCards != null){
            console.log("Yellow Cards: " + yellowCards["yellowCards"]);
            homeTeamData.push(yellowCards["yellowCards"][0]);
            awayTeamData.push(yellowCards["yellowCards"][1]);
            availableStats.push("Yellow Cards");
        }

        var redCards = getRestResource("RedCardsStatResource", [["token", token["token"]], ]);
        if(redCards != null){
            console.log("Red Cards: " + redCards["redCards"]);
            homeTeamData.push(redCards["redCards"][0]);
            awayTeamData.push(redCards["redCards"][1]);
            availableStats.push("Red Cards");
        }
        
  
   		var playerList = getRestResource("PlayerListResource", [["token", token["token"]], ]);
    
   		if (playerList != null) {
            console.log("Players found: " + playerList["homePlayers"]);
            //homeTeamData.push(playerList["homePlayers"][0]);
            //awayTeamData.push(playerList["guestPlayers"][1]);
            //availableStats.push("Players");

            var htmlPlayerString = "<option value = \"null\" >--Make a choice--</option>";
            console.log("Players found: ".concat(playerList.homePlayers.length + playerList.guestPlayers.length));
            
            for (i = 0; i < playerList.homePlayers.length; ++i) {
                htmlPlayerString = htmlPlayerString.concat("<option value = \"" + playerList.homePlayersID[i] + "\">" + playerList.homePlayers[i] + "</option>") //change teams to homePlayers and guestPlayers
            }
    
            for (i = 0; i < playerList.guestPlayers.length; ++i) {
                htmlPlayerString = htmlPlayerString.concat("<option value = \"" + playerList.guestPlayersID[i] + "\">" + playerList.guestPlayers[i] + "</option>") //change teams to homePlayers and guestPlayers
            }
            
            document.getElementById("player").innerHTML = htmlPlayerString;

		
		}

        var cornerStats = getRestResource("CornerStatRestResource", [["token", token["token"]],]);
        if(cornerStats != null){
            console.log("Corner Stats: " + cornerStats["corners"]);
            homeTeamData.push(cornerStats["corners"][0]);
            awayTeamData.push(cornerStats["corners"][1]);
            availableStats.push("Corners");
        }

        var foulStats = getRestResource("FoulsStatResource", [["token", token["token"]],]);
        if(foulStats != null){
            console.log("Foul Stats: " + foulStats["fouls"]);
            homeTeamData.push(foulStats["fouls"][0]);
            awayTeamData.push(foulStats["fouls"][1]);
            availableStats.push("Fouls");
        }

        var attendance = getRestResource("AttendanceRestResource", [["token", token["token"]],]);
        if(attendance != null) {
            console.log("Attendance: " + attendance["attendance"]);
            document.getElementById("attendance").innerHTML="Attendance: " + attendance["attendance"];
        }
        else {
            document.getElementById("attendance").innerHTML="";
        }
        

        // [ballPossession["possession"][0], yellowCards["yellowCards"][0], cornerStats["corners"][0], foulStats["fouls"][0]];
        // [ballPossession["possession"][1], yellowCards["yellowCards"][1], cornerStats["corners"][1], foulStats["fouls"][1]];

        console.log(homeTeamData[1]);
        console.log(homeTeamData);
        console.log(awayTeamData);
        var homeTeamName = teams["homeAndAwayTeam"][0];
        var awayTeamName = teams["homeAndAwayTeam"][1];

        console.log(homeTeamName);
        console.log(typeof(homeTeamName));

        var chartType = document.getElementById("chartType").value;
        
        		homeTeamData.forEach(function(element, index){
        
        if (chartType == "heatmap"){
        		
        		plotHeatMap();
        	
        }
        
        else {    	
            	plotDefault(chartType, homeTeamName, awayTeamName, homeTeamData[index], awayTeamData[index], index, availableStats[index]);

		}
        });
		

        document.getElementById("Charts").style.display = "block";
        document.getElementById("Dropdown").style.display = "none";
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

function plotHeatMap(){

	var canvasId = "myChart1";
	var canvas = document.getElementById(canvasId);
	var chartType;
	var offsetWidth = 0;
	var gameData = {
  labels: ['0h','1h','2h','3h','4h','5h','6h','7h','8h','9h','10h','11h'],
  datasets: [
    {
      label: 'Monday',
      data: [8, 6, 5, 7, 9, 8, 1, 6, 3, 3, 8, 7]
    },
    {
      label: 'Tuesday',
      data: [6, 8, 5, 6, 5, 5, 7, 0, 0, 3, 0, 7]
    },
    {
      label: 'Wednesday',
      data: [8, 5, 6, 4, 2, 2, 3, 0, 2, 0, 10, 8]
    },
    {
      label: 'Thursday',
      data: [4, 0, 7, 4, 6, 3, 2, 4, 2, 10, 8, 2]
    },
    {
      label: 'Friday',
      data: [1, 0, 0, 7, 0, 4, 1, 3, 4, 5, 1, 10]
    }
  ]
};
  


	var chartOptions = {
	
	// String - background color for graph
backgroundColor: '#fff',

// Boolean - whether each box in the dataset is outlined
stroke: false,

// Number - width of the outline stroke.
strokePerc: 0.05,

// String - the outline stroke color.
strokeColor: "rgb(128,128,128)",

// String - the outline stroke highlight color.
highlightStrokeColor: "rgb(192,192,192)",

// Boolean - whether to draw the heat map boxes with rounded corners
rounded: true,

// Number - the radius (as a percentage of size) of the rounded corners
roundedRadius: 0.1,

// Number - padding between heat map boxes (as a percentage of box size)
paddingScale: 0.05,

// String - "gradient", "palette"
colorInterpolation: "gradient",

// Array[String] - the colors used for the active color scheme.
// Any number of colors is allowed.
colors: [ "rgba(220,220,220,0.9)", "rgba(151,187,205,0.9)"],

// Boolean - whether boxes change color on hover.
colorHighlight: true, 

// Number - a floating point value which specifies how much lighter or
// darker a color becomes when hovered, where 1 is no change, 
// 0.9 is slightly darker, and 1.1 is slightly lighter.
colorHighlightMultiplier: 0.92,

// Boolean - Whether to draw labels on the boxes
showLabels: true, 

// Number - the font size of the label as percentage of box height
labelScale: 0.2,

// String - label font family
labelFontFamily: '"HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif',

// String - label font style
labelFontStyle: "normal",

// String - label font color
labelFontColor: "rgba(0,0,0,0.5)",

// String - tooltipTemplate
tooltipTemplate: "<%= xLabel %> | <%= yLabel %> : <%= value %>",

// String - template for legend generation
legendTemplate : '<div class="<%= name.toLowerCase() %>-legend">'+
        '<span class="<%= name.toLowerCase() %>-legend-text">'+
        '<%= min %>'+
        '</span>'+
        '<% for (var i = min; i <= max; i += (max-min)/6){ %>'+ // change 6 to number of divisions required
        '<span class="<%= name.toLowerCase() %>-legend-box" style="background-color: <%= colorManager.getColor(i).color %>;">  </span>'+
        '<% } %>'+
        '<span class="<%= name.toLowerCase() %>-legend-text">'+
        '<%= max %>'+
        '</span>'+
        '</div>'
	
	}
	
	var config = {
                type: chartType = "heatmap",
                data: gameData,
                options: chartOptions
        };
        
    globalCharts.push(new Chart(canvas, config));
	
	//var ctx = document.getElementById('myChart1').getContext('2d');
	//var newChart = new Chart(ctx).HeatMap(gameData);
	//var canvas = document.getElementById(canvasId);
	//var newChart = new Chart(ctx).HeatMap(gameData, chartOptions);

}

// Default plot will plot only a single plot
function plotDefault(chartType, homeTeamName, awayTeamName, homeTeamDataParam, awayTeamDataParam, num, label){
        
        if (chartType == 'bubble') {
        
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
        
        else {
        
        num+=1;
        var canvasId = "myChart" + num;
        var canvas = document.getElementById(canvasId);
        var showLabel = true;
        if(homeTeamName == undefined && awayTeamName == undefined)
        {
            showLabel = false;
        }

        if (window.bar != undefined){
                window.bar.destroy();
        }

        var homeTeamData = {
                label: homeTeamName,
                data: [homeTeamDataParam,"0"],
                backgroundColor: 'rgba(148, 28, 47, 0.6)',
                borderWidth: 0,
                yAxisID: "y-axis"
        };

        var awayTeamData = {
                label: awayTeamName,
                data: [awayTeamDataParam,"0"],
                backgroundColor: 'rgba(32, 164, 243, 0.6)',
                borderWidth: 0,
                yAxisID: "y-axis"
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
                type: chartType,
                data: gameData,
                options: chartOptions
        };
        
        console.log(chartType);

        globalCharts.push(new Chart(canvas, config));
        }
}
