function plotChart() {
        document.getElementById('hiddenText').style.display="block";

        var sport = document.getElementById('chosenSport').innerHTML = document.getElementById("sport").value;
        var league = document.getElementById('chosenLeague').innerHTML = document.getElementById("league").value;
        var team = document.getElementById('chosenTeam').innerHTML = document.getElementById("team").value;
        var season = document.getElementById('chosenSeason').innerHTML = document.getElementById("season").value;
        var match = document.getElementById('chosenGame').innerHTML = document.getElementById("game").value;



        var parameters = [["sports", sport], ["league", league], ["team", team], ["season", season], ["match", match]];
        console.log(parameters);

        var token = getRestResource("TokenResource", parameters);
        console.log("Token: " + token["token"]);


        var teams = getRestResource("HomeAndAwayTeamListResource", [["token", token["token"]],]);
        console.log("Teams: " + teams["homeAndAwayTeam"]);

        var ballPossession = getRestResource("BallPossessionStatResource", [["token", token["token"]],]);
        console.log("Ball Possession: " + ballPossession["possession"]);

        var yellowCards = getRestResource("YellowCardsStatResource", [["token", token["token"]],]);
        console.log("Yellow Cards: " + yellowCards["yellowCards"]);

        var cornerStats = getRestResource("CornerStatRestResource", [["token", token["token"]],]);
        console.log("Corner Stats: " + cornerStats["corners"]);

        var foulStats = getRestResource("FoulsStatResource", [["token", token["token"]],]);
        console.log("Foul Stats: " + foulStats["fouls"]);

        var homeTeamData = [ballPossession["possession"][0], yellowCards["yellowCards"][0], cornerStats["corners"][0], foulStats["fouls"][0]];
        var awayTeamData = [ballPossession["possession"][1], yellowCards["yellowCards"][1], cornerStats["corners"][1], foulStats["fouls"][1]];

        console.log(homeTeamData);
        console.log(awayTeamData);
        var homeTeamName = teams["homeAndAwayTeam"][0];
        var awayTeamName = teams["homeAndAwayTeam"][1];

        var chartType = document.getElementById("chartType").value;

        plotDefault(chartType, homeTeamName, awayTeamName, homeTeamData, awayTeamData);
}

function plotDefault(chartType, homeTeamName, awayTeamName, homeTeamData, awayTeamData){
        var canvas = document.getElementById("myChart");

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

        var gameData = {
                labels: ["Ball Possession", "Yellow Cards", "Corners", "Fouls"],
                datasets: [homeTeamData, awayTeamData]
        };

        var chartOptions = {
                scales: {
                        xAxes: [{
                                barPercentage: 1,
                                categoryPercentage: 0.6
                        }],
                        yAxes: [{
                                id: "y-axis"
                        }]
                }
        };

        var config = {
                type: chartType,
                data: gameData,
                options: chartOptions
        };

        window.bar = new Chart(canvas, config);	
}


function barChart(chartData, teamNames, chartLabels){
        // don't plot anything if a chart type isn't selected
        if (document.getElementById("chartType").value == "null") {
                console.log("No chart selected. Chart plotting skipped");
                return;
        }

        var homeTeam = teamNames[0];
        var homeTeamData = chartData[0];

        var awayTeam = teamNames[1];



        var config = {
                type: 'bar',
                data: {
                        label: teamNames,
                        labels: ["Rockets", "Mavericks", "Lakers", "Celtics", "Kings", "Thunder"],
                        datasets: [{
                                data: chartData,
                                borderWidth: 1
                        }]
                },
                options: {
                        scales: {
                        xAxes: [{ ticks: {beginAtZero:true} }],
                        yAxes: [{ ticks: {beginAtZero:true} }] }
                }
        }


        // Create chart
        var canvas = document.getElementById("myChart");

        // prevent chart from showing old data when mouse is scrolled over it
        if (window.bar != undefined) {
                window.bar.destroy();
        }

        window.bar = new Chart(canvas, config); 
}
