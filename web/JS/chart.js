// add different color options to charts later on

// pass in other parameters like axes type 
function showChart(chartType, data, xLabel, yLabel){
    switch(chartType){
        case 'barChart': 
            barChart(data, xLabel, yLabel);
            console.log('Bar Chart Selected.');
            break;
        case 'lineChart':
            // lineChart();
            console.log('Line Chart Selected.');
            break;
        case 'horizontalBarChart':
            // horizontalBarChart();
            console.log('Horizontal Bar Chart Selected.');
            break;
        case 'pieChart':
            // pieChart();
            console.log('Pie Chart Selected.');
            break;
        case 'donutChart':
            // donutChart();
            console.log('Donut Chart Selected.')
            break;
        case 'radarChart':
            // radarChart();
            console.log('Radar Chart Selected.')
            break;
        case 'polarAreaChart':
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
        labels: chartLabels,
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

}

// bar, horizontal bar, pie, line, doughnut, radar, polarArea

function horizontalBarChart(){

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