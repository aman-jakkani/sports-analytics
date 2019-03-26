// Return rollup or cube resource
function showChart(json, chartType) {

    if (window.bar != undefined){
        window.bar.destroy();
    }
    
    chart = document.getElementById("chartType").value;
    
	console.log(chart);
   if (chart == "bubble"){
   
   var dim1 = json.dim1;
    var dim2 = json.dim2;
    var dimm2 = json.dim2;
    var aggie = json.aggie;

    var ctx = document.getElementById("mainChart").getContext('2d');

    var dim1Int = new Array();
    for(var i= 0;i<dim1.length;i++){
        if(dim1[i]!= null){
            dim1Int.push(parseInt(dim1[i].substr(0,4)));
        }else{
            dim1Int.push(null);
        }
        
    }
    
    var xLabels = new Array();
        	for (var i = 0; i < dimm2.length; i++) {
        		if (xLabels.includes(dimm2[i])) {
            		break;
        		}
        		else {
            		xLabels.push(dimm2[i]);
            		//console.log(dimm2[i]);
        		}
        	}

    var datasets = new Array();
    var teamset = new Array();
    for (var i = 0; i < dim2.length; i++) {
        if (teamset.includes(dim2[i]) /*|| dim2[i] == null*/) {
            break;
        }
        else {
            teamset.push(dim2[i]);
        }

        var seasonData = new Array();
        for (var j=0; j<dim1Int.length; j++) {
            if(dim2[j] == dim2[i] && dim1Int[j] != null) {
                seasonData.push({
                		x: i,
                        y: dim1Int[j],
                        r: aggie[j]
                    });
                //console.log('x: ' + i);
                //console.log('y: ' + dim1Int[j] + ' r: ' + aggie[j] );
            }
        }
        
        /*var xLabels = new Array();
        	for (var i = 0; i < dimm2.length; i++) {
        		if (xLabels.includes(dimm2[i])) {
            		break;
        		}
        		else {
            		xLabels.push(dimm2[i]);
            		//console.log(dimm2[i]);
        		}
        	}*/

		var col = getRandomColor();
        var teamData ={
                label: dim2[i],
                data: seasonData,
                showLine: true,
                fill: false,
       			borderColor: col,
        		pointBorderColor: col,
        		backgroundColor: col,
        		hidden: true   
            };
            console.log(dim2[i]);

        datasets.push(teamData);
    }
    
    //console.log('30: ' + dim2[30]);
    console.log(dim2[i]);
    //console.log("xLabels" + xLabels);

    var scatterChart = new Chart(ctx, {
        type: 'bubble',
        data:
            {
                datasets: datasets
            },
        options: {
            scales: {
                xAxes: [{
                    type: 'linear',
                    position: 'bottom',
                    ticks: {
                		display: true,
                		stepSize: 1,
                        callback: function(value, index, values) {
                            return xLabels[value];
                	    }
              		}
                }]
            }
        }
    });
   
   }
   
   else if (chart == "scatter") {
    var dim1 = json.dim1;
    var dim2 = json.dim2;
    var aggie = json.aggie;

    var ctx = document.getElementById("mainChart").getContext('2d');

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
    for (var i = 0; i < dim2.length; i++) {
        if (teamset.includes(dim2[i]) /*|| dim2[i] == null*/) {
            break;
        }
        else {
            teamset.push(dim2[i]);
        }

        var seasonData = new Array();
        for (var j=0; j<dim1Int.length; j++) {
            if(dim2[j] == dim2[i] && dim1Int[j] != null) {
                seasonData.push({
                        x: dim1Int[j],
                        y: aggie[j]
                    });
                console.log('x: ' + dim1Int[j] + ' (' +typeof dim1Int[j] + ') '+ ' y: ' + aggie[j] );
            }
        }

		var col = getRandomColor();
        var teamData ={
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
        type: 'bubble',
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
}
