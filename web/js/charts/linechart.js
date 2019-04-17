
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
function plotLineChart(json, ctx){
  console.log("Entered line chart");
  console.log(json);
  // destroy any old chart already on the canvas
  //Window.chart is global variable used to destroy previous chart to eliminate flicker
  /*if (window.chart != undefined){
        window.chart.destroy();
        console.log("window destroy");
  }*/
  
  var league = document.getElementById("league").value;
  var agFunc = document.getElementById("aggregationFunction").value;
  var agData = document.getElementById("aggData").value;
  
  var dim1 = json.dim1;
  var dim2 = json.dim2;
  var aggie = json.aggie;
    /*alert(dim1.length);
    alert(dim2.length);
    alert(aggie.length);*/
  //var ctx = document.getElementById("mainChart").getContext('2d');

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
		
		var label;
        var seasonData = new Array();
        for (var j=0; j<dim1Int.length; j++){
        if (ctx.canvas.id == "mainChart") {
        		if (json.dim1[j] == null || json.dim2[j] == null){
        			continue;
        		}
        	}
        	if (json.dim1[j] == null && json.dim2[j] == null) {
        		continue;
        	}
        	if (i == 0) {
        		i = 1; 
        	}
        	if (json.dim2[i] == null && json.dim1[j] != null) {
        
        		label = "All Teams";
        
       		} else {
        
        		label = json.dim2[i];
        
			}
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
            
                label: label, //dim2[i],
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
    
    var axisNumbers = getAxisNumbers(ctx);
    var title = getTitle(ctx, league, agFunc, agData);
    
    return new Chart(ctx, {
        type: 'line',
        data:
            {
                datasets: datasets
            },
        options: {
        	title: {
        		display: true,
      			text: title
      		},
            scales: {
                xAxes: [{
                	scaleLabel: {
                	display: true,
        			labelString: 'Seasons'
        			},
        			ticks: {
        				callback: function (value) { if (Number.isInteger(value)) { return value; } }
        			},
                    type: 'linear',
                    position: 'bottom'
                }],
            	 yAxes: [{
                	scaleLabel: {
                	display: true,
        			labelString: agData
        			},
        			ticks: {
                					display: axisNumbers,
                					callback: function (value) { if (Number.isInteger(value)) { return value; } }
            		},
            		min: 0
                }]
            }
        }
    });	
}

