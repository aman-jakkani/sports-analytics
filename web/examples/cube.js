// Return rollup or cube resource
function showChart(json, chartType) {

    if (window.bar != undefined){
        window.bar.destroy();
    }

    chartType = document.getElementById("chartType").value;
    console.log(chartType)
    switch (chartType){
        case "bubble": plotBubble(json);
            break;
        case "scatter": plotScatter(json);
            break;
        case "line": break; // create plotLine(json) function
        default:
            break;
    }
}
