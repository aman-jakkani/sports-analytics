/*
    Description:
        Creates a bubble chart using json input
    Args:
        json: json object of all the data (with labels) to be plotted
            dim1: the first dimension
            dim2: the second dimension
            aggie: the dimension that was aggregrated using the aggregration function.
                   this is usually plotted on the third axis.
    Returns:
    Raises:
    Notes:
*/
function plotBubble(json, ctx){
	//Window.chart is global variable used to destroy previous chart to eliminate flicker
    /*if (window.chart != undefined){
        window.chart.destroy();
        console.log("window destroy bubble");
    
    }*/


    var league = document.getElementById("league").value;
    var agFunc = document.getElementById("aggregationFunction").value;
    var agData = document.getElementById("aggData").value;
    

    // Convert all 20XX/20YY to 20XX (assumes the first dimension is always seasons)
    var dim1 = new Array();
    for (var i = 0; i < json.dim1.length; i++){
        if(json.dim1[i] != null){
            dim1.push(parseInt(json.dim1[i].substr(0,4)));
        } else{
            dim1.push(null);
        }
        
    }
    
    var _globalDim1 = dim1;
    var _globalDim2 = json.dim2;
    
    // Pushes all of dim2 into xLabels and skips over duplicates
    var xLabels = new Array();
    for (var i = 0; i < json.dim2.length; i++) {
        if (xLabels.includes(json.dim2[i])) {
            break;
        }
        else {
            xLabels.push(json.dim2[i]);
        }
    }
    
    
    var normalizedR = [];
    //var min = Math.min(...json.aggie);
    var min = getMin(ctx, json);
    console.log("min");
    console.log(min);
    //var max = Math.max(...json.aggie);
    var max = getMax(ctx, json);
    console.log("max");
    console.log(max);
        
        for (i = 0; i < json.aggie.length; ++i){
        
        	
        	var result = ((json.aggie[i] - (min - 0.001))/(max - min)) * 15;
        	
        	console.log("result");
        	console.log(result);
        	
        	normalizedR.push(result);
        	
        
        }
        
       
        
        console.log("normalizedR");
        console.log(normalizedR);
        console.log("json aggie");
        console.log(json.aggie);
        console.log("json dim1");
        console.log(json.dim1);

	console.log(ctx);
    var datasets = new Array();
    var teamset = new Array();

    for (var i = 0; i < json.dim2.length; ++i) {
        if (teamset.includes(json.dim2[i]) /*|| dim2[i] == null*/) {
            break;
        }
        else {
            teamset.push(json.dim2[i]);
        }
        
    
		var label;
        var seasonData = new Array();
        for (var j = 0; j < dim1.length; ++j) {
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
            if (json.dim2[j] == json.dim2[i] /*&& dim1[j] != null*/) {
            	if (dim1[j] == null) {
            		dim1[j] = 1;
            	}
                seasonData.push({
                		x: i,
                        y: dim1[j],
                        r: normalizedR[j] //json.aggie[j].toFixed(2)
                    });
            }
        }
        
        // getRandomColor() is now defined in utils.js 
        // and needs to be included in the html file in order to work
		var col = getRandomColor();
        var teamData ={
                label: label, //json.dim2[i],
                data: seasonData,
                showLine: true,
                fill: false,
       			borderColor: col,
        		pointBorderColor: col,
        		backgroundColor: col,
        		hidden: false   
            };
            console.log("DIM2");
            console.log(json.dim2[i]);

        datasets.push(teamData);
    }
    
    var axisNumbers = getAxisNumbers(ctx);
    var title = getTitle(ctx, league, agFunc, agData);

    var config = {
        type: 'bubble',
        data: {datasets: datasets},
        options: {
        	title: {
        		display: true,
      			text: title
      		},
            scales: {
                xAxes: [{
                	scaleLabel: {
                	display: true,
        			labelString: 'Teams'
        			},
                    type: 'linear',
                    position: 'bottom',
                    ticks: {
                		display: true,
                		stepSize: 1,
                        callback: function(value, index, values) {
                            return xLabels[value];
                	    },
                	    min: 0,
                	    max: i,
                	    autoSkip: false
              		}
                }],
                yAxes: [{
                	scaleLabel: {
                	display: true,
        			labelString: 'Seasons'
        			},
        			ticks: {
                					display: axisNumbers,
                					callback: function (value) { if (Number.isInteger(value)) { return value; } }
            		},
            		min: 0
                }]
            },

            tooltips: {
                callbacks: {
                    label: function(t, d){
                        team = d.datasets[t.datasetIndex].label;
                        season = d.datasets[t.datasetIndex].data[t.index].y;
                        //r = d.datasets[t.datasetIndex].data[t.index].r[t.datasetIndex + 1];
                        value = 'N/A';
                        console.log("d");
                        console.log(d);
                        console.log("t");
                        console.log(t);
                        

                        //nextSeason = parseInt(season, 10) + 1;
                        
                        for (i = 0; i < json.aggie.length; ++i){
                            //s = (season + "/" + nextSeason);

                            if (_globalDim1[i] == season && _globalDim2[i] == team){
                            
                                value = json.aggie[i].toFixed(2);
                                
                            } else if (_globalDim1[i] == season && _globalDim2[i] == null) {
                            
                            	value = json.aggie[i].toFixed(2);
                            
                            }
                        }
                        return team + ": (" + season + ", " + value + ")";
                    }
                }
            }
        }
    }
	
	console.log(json);
    //window.chart = new Chart(ctx, config);
    return new Chart(ctx, config);
}