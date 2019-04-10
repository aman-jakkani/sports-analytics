

/*
Description:
  Returns an array of player data given a token. This data is used to populate the player table.
Args:
Returns:
Raises:
Notes:
  Format:
  [ [a, b, c, d, e], [f, g, h, i, k], [l, m, n, o, p]]
*/
function getPlayerData(token){
    // Get the list of players with 
    var playerList = getRestResource("PlayerListResource", [["token", token["token"]], ]);
  
    // Create list of IDs to get stats for each player
    var ids = playerList.homePlayersID;
    for (var i = 0; i < playerList.guestPlayersID.length; ++i){
      ids.push(playerList.guestPlayersID[i]);
    }
  
    // Initialize playerData array
    var playerData = [["Player ID", "Birthday", "Name", "Weight", "Height", "Rating", "Strength", "Shot Power", "Preferred Foot"], ];
  
    // Let the first element of the array be the player's id
    var statsList, playerstatistics, soccerplayerstatistics;
  
    // Populate playerData for all found players
    for (var i = 0; i < ids.length; ++i){
      statsList = [ids[i]];
  
      // Get general player stats and append
      playerstatistics = getRestResource("PlayerResource", [["token", token["token"]], ["playerID", ids[i]]]);
      playerstatistics = Object.values(playerstatistics);
  
      for (var j = 0; j < playerstatistics.length; ++j){
        statsList.push(playerstatistics[j]);
      }
  
      // Get soccer player stats and append
      soccerplayerstatistics = getRestResource("SoccerPlayerResource", [["token", token["token"]], ["playerID", ids[i]]]);
  
      soccerplayerstatistics = Object.values(soccerplayerstatistics);
  
  
      for (var j = 0; j < soccerplayerstatistics.length; ++j){
        statsList.push(soccerplayerstatistics[j]);
      }
  
      // Push the stats list for the specific player onto the playerData dictionary
      playerData.push(statsList);
    }
  
    return playerData;
  }



/*
  Description:
    This function should take all the selected attributes and return an array with all the data formatted
  Args:
  Returns:
  Raises:
  Notes:
*/
/*function getSoccerAttributeData(attribute, token){

	console.log(attribute);
    switch (attribute){
      case ("score"): 
        return getRestResource("ScoreRestResource", [["token", token["token"]], ]);
  
      case ("ballPossession"): 
        return getRestResource("BallPossessionStatResource", [["token", token["token"]], ]);
  
      case ("yellowCards"): 
        return getRestResource("YellowCardsStatResource", [["token", token["token"]], ]);
  
      case ("redCards"): 
        return getRestResource("RedCardsStatResource", [["token", token["token"]], ]);
  
      case ("cornerStats"): 
        return getRestResource("CornerStatRestResource", [["token", token["token"]],]);
  
      case ("fouls"): 
        return getRestResource("FoulsStatResource", [["token", token["token"]],]);
      
      case ("attendance"):
        return getRestResource("AttendanceRestResource", [["token", token["token"]],]);
  
      default: 
        break;
    }
  }*/
  
  /*
    Description:
      This function should take all the selected attributes and return an array with all the data formatted
    Args:
    Returns:
    Raises:
    Notes:
  */
  function getBasketballAttributeData(attributeList, token){
    for (i = 0; i < attributeList.length; ++i){
      switch (attribute){
        case ("points"): break;
        case (""): break;
        default: break;
      }
    }
  }