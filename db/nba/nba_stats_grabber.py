from nba_py import Scoreboard
from datetime import date
from datetime import timedelta
from time import sleep
import json

sd = date(2012,11,22)#(1980,11,17)
ed = date.today()#(2008,1,1)
league_id = '00'
day_offset = 0

gameids = set()

def daterange(start_date, end_date):
    for n in range(int ((end_date - start_date).days)):
        yield start_date + timedelta(n)

file = open("gameids.csv", 'a')
for date in daterange(sd, ed):
    try:
        sb = Scoreboard(date.month, date.day, date.year, league_id, day_offset)
        for game in sb.game_header():
            file.write(game['GAME_ID'] + '\n')
        print(date.strftime("%Y-%m-%d") + " successful")
        sleep(2)
    except Exception as exception:
        print(exception)

file.close()