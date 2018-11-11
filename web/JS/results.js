function execute() {
        document.getElementById('hiddenText').style.display="block";
                showSecondStatisticInput();
                showAnalysisInput();
                showFirstStatisticInput();
}

function showFirstStatisticInput() {
        document.getElementById('stat1').innerHTML = 
                    document.getElementById("firstStatistic").value;
}

function showSecondStatisticInput() {
        document.getElementById('stat2').innerHTML = 
                    document.getElementById("secondStatistic").value;
}

function showAnalysisInput() {
        document.getElementById('analyze').innerHTML = 
                    document.getElementById("analysis").value;
}