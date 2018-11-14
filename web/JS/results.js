function showData() {
        document.getElementById('hiddenText').style.display="block";

        document.getElementById('chosenSport').innerHTML = document.getElementById("sport").value;
        document.getElementById('chosenLeague').innerHTML = document.getElementById("league").value;
        document.getElementById('chosenTeam').innerHTML = document.getElementById("team").value;
        document.getElementById('chosenSeason').innerHTML = document.getElementById("season").value;
        document.getElementById('chosenGame').innerHTML = document.getElementById("game").value;
        document.getElementById('chosenFirstStat').innerHTML = document.getElementById("firstStatistic").value;
        document.getElementById('chosenSecondStat').innerHTML = document.getElementById("secondStatistic").value;
        document.getElementById('chosenAnalysis').innerHTML = document.getElementById("analysis").value;   

let myChart = document.getElementById('myChart').getContext('2d');

let massPopChart = new Chart(myChart, {
	type: 'bar', //bar, horizontal bar, pie, line, doughnut, radar, polarArea
	data:{
	labels: ['Rockets','Raptors','Warriors','Celtics','76ers','Cavaliers'],
	datasets: [{
		label:'Wins',
		data: [
			65,
			59,
			58,
			55,
			52,
			50
		]
	}]
	},
	options:{}
});

}