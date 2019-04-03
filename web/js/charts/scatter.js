/*
    Description:
    Args:
    Returns:
    Raises:
    Dependencies:
        These following files need to be included above of this file as this file has dependencies linked to them. 
        - utils.js
        - 
    Notes:
*/
function plotScatter(json){
	
	//Window.chart is global variable used to destroy previous chart to eliminate flicker
    if (window.chart != undefined){
        window.chart.destroy();
    }   

    var ctx = document.getElementById("mainChart").getContext('2d');

    var dim1 = new Array();
    for(var i = 0;i < json.dim1.length; ++i){
        if (json.dim1[i] != null){
            dim1.push(parseInt(json.dim1[i].substr(0,4)));
        } else{
            dim1.push(null);
        }
        
    }

    var datasets = new Array();
    var teamset = new Array();
    for (var i = 0; i < json.dim2.length; i++) {
        if (teamset.includes(json.dim2[i]) /*|| json.dim2[i] == null*/) {
            break;
        }
        else {
            teamset.push(json.dim2[i]);
        }

        var seasonData = new Array();
        for (var j = 0; j < dim1.length; ++j) {
            if (json.dim2[j] == json.dim2[i] && dim1[j] != null) {
                seasonData.push({
                        x: dim1[j],
                        y: json.aggie[j]
                    });
                console.log('x: ' + dim1[j] + ' (' +typeof dim1[j] + ') '+ ' y: ' + json.aggie[j] );
            }
        }

		var col = getRandomColor();
        var teamData ={
                label: json.dim2[i],
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

    var config = {
        scales: {
            xAxes: [{
                type: 'linear',
                position: 'bottom'
            }]
        }
    }

    // window.bar just added without testing.
    // if Cube/rollup not working, remove "window.bar = "
    window.bar = new Chart(ctx, {
        type: 'bubble',
        data: { datasets: datasets },
        options: config
    });

    return window.chart;
}