var globalCharts = new Array();

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


        var homeTeamData = [];
        var awayTeamData = [];
        var availableStats = [];


        var score = getRestResource("ScoreRestResource", [["token", token["token"]],]);
        if(score != null){
            console.log("Score: " + score["score"][0] + ":" + score["score"][1] );
            homeTeamData.push(score["score"][0]);
            awayTeamData.push(score["score"][1]);
            availableStats.push("Score");
        }

        var ballPossession = getRestResource("BallPossessionStatResource", [["token", token["token"]],]);
        if(ballPossession != null){
            console.log("Ball Possession: " + ballPossession["possession"]);
            homeTeamData.push(ballPossession["possession"][0]);
            awayTeamData.push(ballPossession["possession"][1]);
            availableStats.push("Ball Possession");
        }

        var yellowCards = getRestResource("YellowCardsStatResource", [["token", token["token"]],]);
        if(yellowCards != null){
            console.log("Yellow Cards: " + yellowCards["yellowCards"]);
            homeTeamData.push(yellowCards["yellowCards"][0]);
            awayTeamData.push(yellowCards["yellowCards"][1]);
            availableStats.push("Yellow Cards");
        }

        var redCards = getRestResource("RedCardsStatResource", [["token", token["token"]],]);
        if(redCards != null){
            console.log("Red Cards: " + redCards["redCards"]);
            homeTeamData.push(redCards["redCards"][0]);
            awayTeamData.push(redCards["redCards"][1]);
            availableStats.push("Red Cards");
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
        if(attendance != null)
        {
            console.log("Attendance: " + attendance["attendance"]);
            document.getElementById("attendance").innerHTML="Attendance: " + attendance["attendance"];
        }
        else
        {
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
            plotDefault(chartType, homeTeamName, awayTeamName, homeTeamData[index], awayTeamData[index], index, availableStats[index]);
        });


        document.getElementById("Charts").style.display = "block";
        document.getElementById("Dropdown").style.display = "none";
}

function backToDropdown(){
    document.getElementById("Charts").style.display = "none";
    document.getElementById("Dropdown").style.display = "block";
    while(globalCharts.length > 0){
        globalCharts[globalCharts.length-1].destroy();
        globalCharts.pop();
    }
}

function plotDefault(chartType, homeTeamName, awayTeamName, homeTeamDataParam, awayTeamDataParam, num, label){
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
                                id: "y-axis"
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

        globalCharts.push(new Chart(canvas, config));
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
