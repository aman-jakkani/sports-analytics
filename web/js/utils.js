/* *****************************************************************
Description:

Methods:

Dependencies:

******************************************************************* */


/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function getRandomColor() {
  var letters = '0123456789ABCDEF';
  var color = '#';
  for (var i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}


/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function getAxisNumbers(ctx) {
	var display;
	if (ctx.canvas.id == "thirdChart") {
		display = false;
	}
	else {
		display = true;
	}
	return display;
}



/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function getTitle(ctx, league, agFunc, agData){
	var title;
	if (ctx.canvas.id == "mainChart") {
		title = agFunc + ' ' + agData + ' By Each Team In ' + league + ' By Season';
	}
	else if (ctx.canvas.id == "secondChart"){
		title = agFunc + ' ' + agData + ' In ' + league + ' By Season';
	}
	else if (ctx.canvas.id == "thirdChart"){
		title = agFunc + ' ' + agData + ' By Each Team In ' + league + 'Over All Seasons';
	}
	else {
		title = null;
	}
	return title;
}

/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function backToStats(){
  document.getElementById("Charts").style.display = "block";
  document.getElementById('Player').style.display="none";
  document.getElementById("Dropdown").style.display = "none";
}


/*
  Description:
    Button to return back to previous dropdown page
  Args:
  Returns:
  Raises:
  Notes:
*/
function backToDropdown(){
  document.getElementById("Charts").style.display = "none";
  document.getElementById('Player').style.display="none";
  document.getElementById("Dropdown").style.display = "block";
  while(globalCharts.length > 0){
      globalCharts[globalCharts.length-1].destroy();
      globalCharts.pop();
  }
}



/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function loadResource()
{
    var xhttp = new XMLHttpRequest();
    var parameter = document.getElementById("parameter").value;
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("response").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("GET", "/rest/DemoRestResource?caller=".concat(parameter), true);
    xhttp.send();
}



/*
Description:
  This function generates a dynamic table 
Args:
  playerData: this is an array, where each element contains another array 
              that has all the player info for a single player.
Returns:
Raises:
Notes:
*/
function createPlayerTable(data){
   // Create an HTML table element
   var table = document.createElement("TABLE");
   table.border = "1";

   var columnCount = data[0].length;
   console.log('Column count: ' + columnCount);

   // Create a HTML Table element.
   var table = document.createElement("TABLE");
   table.border = "1";

   // Add the header row
   var row = table.insertRow(-1);
   for (var i = 0; i < columnCount; ++i) {
      var headerCell = document.createElement("TH");
      headerCell.innerHTML = data[0][i];
      row.appendChild(headerCell);
   }

   // Add the data rows
   for (var i = 1; i < data.length; ++i) {
      row = table.insertRow(-1);
      for (var j = 0; j < columnCount; ++j) {
         var cell = row.insertCell(-1);
         cell.innerHTML = data[i][j];
      }
   }

   var dvTable = document.getElementById("table");
   dvTable.innerHTML = "";
   dvTable.appendChild(table);
}



/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function w3_open() {
    document.getElementById("main").style.marginLeft = "20%";
    document.getElementById("mySidebar").style.width = "20%";
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("openNav").style.display = 'none';
}



/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function w3_close() {
   document.getElementById("main").style.marginLeft = "0%";
   document.getElementById("mySidebar").style.display = "none";
   document.getElementById("openNav").style.display = "inline-block";
}



/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
*/
function teamReset() {
  var defaultString = "<option value = \"null\" >--Make a choice--</option>";
  var sportString = defaultString + "<option value = \"Soccer\" >Soccer</option>" + "<option value = \"Basketball\" >Basketball</option>";
  
  $("#sport").html(sportString);
  $("#league").html(defaultString);
  $("#team").html(defaultString);
  $("#season").html(defaultString);
  $("#game").html(defaultString);
  $("#chartType").html(defaultString); 
  $("#factAttribute").html(defaultString);
  $("#dimensions").html(defaultString);
  $("#aggregationFunction").html(defaultString);
  $("#aggregationStyle").html(defaultString);
  $("#aggData").html(defaultString);
  $("#players").html(defaultString);

  
  //window.chart.destroy();
  document.getElementById("errorMessage").innerHTML = "";
  
  // Destroy all three charts
  try {
      window.chart1.destroy();
      window.chart2.destroy();
      window.chart3.destroy();

  } catch (TypeError){
      console.log('No charts to destroy or rollup called.');
  }
}


/*
  Description:
  Args:
  Returns:
  Raises:
  Notes:
      - [["token", token["token"]], ]) is an array of dict values
      - [ballPossession["possession"][0], yellowCards["yellowCards"][0], 
                        cornerStats["corners"][0], foulStats["fouls"][0]];
      - [ballPossession["possession"][1], yellowCards["yellowCards"][1], 
                        cornerStats["corners"][1], foulStats["fouls"][1]];
*/
function playerReset() {

  var defaultString = "<option value = \"null\" >--Make a choice--</option>";

  var sportString = defaultString + "<option value = \"Soccer\" >Soccer</option>" + "<option value = \"Basketball\" >Basketball</option>";

  // Set all dropdowns back to 
  $("#sport").html(sportString);
  $("#league").html(defaultString);
  $("#team").html(defaultString);
  $("#season").html(defaultString);
  $("#game").html(defaultString);
  $("#factAttribute").html(defaultString);
  $("#players").html(defaultString);

  // Reset error message
  document.getElementById("errorMessage").innerHTML = "";

  // Destroy chart
  window.chart.destroy();

  // Reset table
  document.getElementById("table").innerHTML = "";
  
}
        