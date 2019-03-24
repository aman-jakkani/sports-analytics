//Quick info
//
//parameter:
//resourceName      -- Name of the resource requested e.g. LeagueListResource
//parameterPairs    -- 2DArray containing key value pairs for parameters should look like this:
//                     var array = [["sports", "soccer"],["league", "Bundesliga"], ...]
//return            -- JSON Object of requested resource
//demo usage        -- exampleRestClient.html starting at line 28


function getRestResource(resourceName, parameterPairs) {

    var xhttp = new XMLHttpRequest();
    var jsonAnswer;

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log("Answer received")
            var answer = xhttp.responseText;
            if(answer != "null"){
                jsonAnswer = JSON.parse(answer);
            }else {
                jsonAnswer = null;
            }

        }
    };

    var requestString = "/rest/".concat(resourceName);
    if(parameterPairs != undefined && parameterPairs.length > 0)
    {
        requestString = requestString.concat("?");

        for(var i = 0; i < parameterPairs.length; i++)
        {
            console.log("Parameter pairs: ".concat(parameterPairs[i]));

            if(parameterPairs[i] != undefined)
            {
                requestString = requestString.concat(parameterPairs[i][0]);
                requestString = requestString.concat("=");
                requestString = requestString.concat(parameterPairs[i][1]);
                requestString = requestString.concat("&");
            }
        }
        //remove last &
        requestString = requestString.substr(0, requestString.length-1);
    }
    console.log("Request string: ".concat(requestString));
    xhttp.open("GET", requestString, false);
    xhttp.send();

    return jsonAnswer;
}
