// can't generate another token
function showData() {
        document.getElementById('hiddenText').style.display="block";

        document.getElementById('chosenSport').innerHTML = document.getElementById("sport").value;
        document.getElementById('chosenLeague').innerHTML = document.getElementById("league").value;
        document.getElementById('chosenTeam').innerHTML = document.getElementById("team").value;
        document.getElementById('chosenSeason').innerHTML = document.getElementById("season").value;
        document.getElementById('chosenGame').innerHTML = document.getElementById("game").value;
        // document.getElementById('chosenChart').innerHTML = document.getElementById("chartType").value;

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

        console.log("Token: " + token);
}
