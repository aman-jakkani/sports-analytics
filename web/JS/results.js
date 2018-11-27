// can't generate another token
function showData() {
        document.getElementById('hiddenText').style.display="block";

        document.getElementById('chosenSport').innerHTML = document.getElementById("sport").value;
        document.getElementById('chosenLeague').innerHTML = document.getElementById("league").value;
        document.getElementById('chosenTeam').innerHTML = document.getElementById("team").value;
        document.getElementById('chosenSeason').innerHTML = document.getElementById("season").value;
        document.getElementById('chosenGame').innerHTML = document.getElementById("game").value;
        document.getElementById('chosenChart').innerHTML = document.getElementById("chartType").value;

        // This block is for testing out the Token Resource. Remove later
        // ------------------------------------------------------------------

        var parameters = [["sports", "Soccer"],];

        league = getRestResource("LeagueListResource", parameters);
        parameters.push(["league", league.leagues[1]]);

        team = getRestResource("TeamListResource", parameters);
        parameters.push(["team", team.teams[0]]);

        season = getRestResource("SeasonListResource", parameters);
        parameters.push(["season", season.seasons[0]]);

        match = getRestResource("MatchListResource", parameters);
        parameters.push(["match", match.match[0]]);
 
        console.log(parameters);

        // ------------------------------------------------------------------

        var token = getRestResource("TokenResource", parameters);
        // var teams = getRestResource("HomeAndAwayTeamListResource", token);
        // var ballPossession = getRestResource("BallPossessionResource", token);

        console.log("Token: " + token);
        // console.log("Teams: " + teams);
        // console.log("Ball Possession: " + ballPossession);

/*
        config = {
                type: 'bar',
                data: {
                    labels: teams,
                    datasets: [{
                        label: 'Ball Possession',
                        data: ballPossession,
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero:true
                            }
                        }]
                    }
                }
            }

        var ctx = document.getElementById("myChart");
        var myChart = new Chart(ctx, config); 
        }
*/
}


// temporary file with all the plotting functions ... removing extra clutter
// pass in other parameters like axes type 
function showChart(chartType, parameters){
        switch(chartType){
                case 'bar': 
                        barChart(data);
                        console.log('Bar Chart Selected.');
                        break;

                case 'line':
                        // lineChart();
                        console.log('Line Chart Selected.');
                        break;

                case 'horizontalBar':
                        // horizontalBarChart();
                        console.log('Horizontal Bar Chart Selected.');
                        break;

                case 'pie':
                        // pieChart();
                        console.log('Pie Chart Selected.');
                        break;

                case 'doughnut':
                        // donutChart();
                        console.log('Donut Chart Selected.')
                        break;

                case 'radar':
                        // radarChart();
                        console.log('Radar Chart Selected.')
                        break;

                case 'polarArea':
                        // polarAreaChart();
                        console.log('Polar Area Chart Selected');
                        break;
                
                default:
                        console.log('Chart type not found.');
                        break;
        }
}
    
    
function barChart(chartLabels, chartData, xLabel, title){
let myChart = document.getElementById('myChart').getContext('2d');
let newBarChart = new Chart(myChart, {
        type: 'bar',
        data:{
        // labels: chartLabels,
        datasets: [{
        label: xLabel,
        data: chartData }]
        },
        options:{
        title:{
                display:true,
                text: title,
                fontsize:25
        },
        scales: {
                yAxes: [{ // add yaxis label
                ticks: {
                beginAtZero: true
                }
                }]
        }
        }
});
}
    

function lineChart(){
        /*
var token = getRestResource("TokenResource", parameters);
var teams = getRestResource("HomeAndAwayTeamListResource", token);
var ballPossession = getRestResource("BallPossessionResource", token);
var yellowCards = getRestResource("YellowCardStatResource", token);
var cornerStats = getRestResoruce("CornerStatsResource", token);
var redCards = getRestResource("RedCardsStatResource", token);
var foulStats = getRestResource("FoulsStatResource", token);
*/


var ctx = document.getElementById("myChart");
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});   
}

// bar, horizontal bar, pie, line, doughnut, radar, polarArea

function horizontalBarChart(){
        console.log("ALKSDJFL;AKSDJFL;ASKDJF;");
}

function pieChart(){

}

function lineChart(){

}

function donutChart(){

}

function radarChart(){

}

function polarAreaChart(){

}

// document.getElementById("SUBMIT").addEventListener("click", lineChart());