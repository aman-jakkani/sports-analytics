/*
    Description:
    Args:
    Returns:
    Raises:
    Notes:
*/
function plotScatter(json){
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
                        y: aggie[j]
                    });
                console.log('x: ' + dim1[j] + ' (' +typeof dim1[j] + ') '+ ' y: ' + aggie[j] );
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