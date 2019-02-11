# foreign key teamid gameid 
# data score ....

from nba_py import game
from time import sleep

infile = open("gameids.csv",'r')
outfile = open("nbascores.csv", 'a')
failfile = open("failgid.csv", 'a')

sent = True

for line in infile:
    gameid = line.rstrip()
    
    if gameid != "0021500841" and sent:
        print("not yet")
        continue
    else:
        sent = False
    try:
        bs = game.BoxscoreSummary(gameid)
        for i in range(0,2):
            teamscore = bs.line_score()[i]
            outfile.write(str(gameid) + ", ")
            outfile.write(str(teamscore["TEAM_ID"]) + ", ")
            outfile.write(str(teamscore["PTS"]) +"\n")    
        print(gameid + " successful")
        sleep(2)
    except Exception as exception:
        failfile.write(gameid + '\n')
        print(exception)
        sleep(2)

infile.close()
outfile.close()
failfile.close()