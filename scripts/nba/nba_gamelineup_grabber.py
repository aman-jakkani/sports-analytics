from nba_py import game
from time import sleep

infile = open("gameids.csv",'r')
outfile = open("gamelineup.csv", 'a')
failfile = open("failgid.csv", 'a')

sent = True

for line in infile:
    gameid = line.rstrip()
    
#    if gameid != "0021500841" and sent:
#        print("not yet")
#        continue
#    else:
#        sent = False
    try:
        bs = game.Boxscore(gameid)
        players = bs.player_stats()
        playerTouples = list() 
        for player in players:
            playerTouples.append([player["TEAM_ID"], player["PLAYER_ID"]])
        playerTeamOne = list()
        playerTeamTwo = list()
        tid = "-1"
        for touple in playerTouples:
            if tid == "-1":
                tid = touple[0]
            if(touple[0]==tid):
                playerTeamOne.append(touple)
            else:
                playerTeamTwo.append(touple)
        for i in range(0,2):
            if i==0:
                playerTouples=playerTeamOne
            else:
                playerTouples=playerTeamTwo
            outfile.write(str(gameid) + ", ")
            outfile.write(str(playerTouples[0][0])) #teamid
            cnt=0
            for player in playerTouples:
                outfile.write(", ")
                outfile.write(str(player[1]))
                cnt+=1
            for j in range(cnt,13):
                outfile.write(", null")
            outfile.write("\n")
        print(gameid + " successful")
        sleep(1)
    except Exception as exception:
        failfile.write(gameid + '\n')
        print(exception)
        sleep(1)

infile.close()
outfile.close()
failfile.close()