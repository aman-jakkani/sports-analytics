// can't generate another token
function showData() {
        document.getElementById('hiddenText').style.display="block";

        document.getElementById('chosenSport').innerHTML = document.getElementById("sport").value;
        document.getElementById('chosenLeague').innerHTML = document.getElementById("league").value;
        document.getElementById('chosenTeam').innerHTML = document.getElementById("team").value;
        document.getElementById('chosenSeason').innerHTML = document.getElementById("season").value;
        document.getElementById('chosenGame').innerHTML = document.getElementById("game").value;
        // document.getElementById('chosenChart').innerHTML = document.getElementById("chartType").value;

        /* 
                This block is for testing out the Token Resource. 
                Gets the first combination of sport, league, team, season, and match then
                trys to create a token from it. 
                The token currently returns undefined
        */
        // ------------------------------------------------------------------
        var parameters = [["sports", "Soccer"],];

        league = getRestResource("LeagueListResource", parameters);
        parameters.push(["league", league.leagues[0]]);

        team = getRestResource("TeamListResource", parameters);
        parameters.push(["team", team.teams[0]]);

        season = getRestResource("SeasonListResource", parameters);
        parameters.push(["season", season.seasons[0]]);

        match = getRestResource("MatchListResource", parameters);
        parameters.push(["match", match.match[0]]);
 
        console.log(parameters);

        var token = getRestResource("TokenResource", parameters);
        console.log("Token: " + token);

        // ------------------------------------------------------------------

        // don't plot anything if a chart type isn't selected
        if (document.getElementById("chartType").value == "null") return;

        var ctx = document.getElementById("myChart");

        // prevent chart from showing old data when mouse is scrolled over it
        if(window.bar != undefined) window.bar.destroy();

        window.bar = new Chart(ctx, {
        type: document.getElementById("chartType").value, // bar, horizontal bar, pie, line, doughnut, radar, polarArea
        data: {
                labels: ["Rockets", "Mavericks", "Lakers", "Celtics", "Kings", "Thunder"],
                datasets: [{
                label: '# of Technical Fouls',
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
                        xAxes: [{
                        ticks: {
                        beginAtZero:true
                        }
                }],
                yAxes: [{
                        ticks: {
                        beginAtZero:true
                        }
                }]
                }
        }
    });   
}
