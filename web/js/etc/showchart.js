// Return rollup or cube resource



function showChart(json) {

    if (window.bar != undefined){
        window.bar.destroy();
    }

    var dim1 = json.dim1;
    var dim2 = json.dim2;
    var aggie = json.aggie;

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
    for (var i = 0; i < dim2.length; i++) {
        if(teamset.includes(dim2[i]) /*|| dim2[i] == null*/) {
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
                //console.log('x: ' + dim1Int[j] + ' (' +typeof dim1Int[j] + ') '+ ' y: ' + aggie[j] );
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

    window.bar(ctx, scatterChart);
}


function getGroupBy(){
    var aggieFunc = [["aggregation", document.getElementById("aggregationFunction").value], ];
    var json;
  
    switch(document.getElementById("aggregationStyle").value){
        case "Rollup": json = getRestResource("RollupResource", aggieFunc);
            break;
  
        case "Cube": json = getRestResource("CubeResource", aggieFunc);
            break;
  
        default: console.log("No aggie function chosen")
            return;
    }
    showChart(json);
  }
