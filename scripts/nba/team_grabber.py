from nba_py.team import TeamList, TeamSummary
from time import sleep

# Builds a csv with all current players in ../csv/team_info.csv

statslist = ['TEAM_NAME', 'TEAM_CODE', 'TEAM_ID','TEAM_CITY','TEAM_CONFERENCE',
            'TEAM_DIVISION','TEAM_ABBREVIATION','CONF_RANK','SEASON_YEAR','PCT','MIN_YEAR',
            'DIV_RANK','W','L','MIN_YEAR','MAX_YEAR']

def build_header(outfile):
        for stat in statslist:
                outfile.write(stat + ',')
        outfile.write('\n')

with open('../csv/team_info.csv', 'w') as csvfile:
    tl = TeamList()

    build_header(csvfile)

    for team in tl.info():
        ts = TeamSummary(team['TEAM_ID']).info() # returns a dict wrapped in a list for some reason
        sleep(2)
        
        for tm in ts:   
                try:
                        for stat in statslist: csvfile.write(str(tm[stat]) + ',')
                        csvfile.write('\n')
                except Exception as exception:
                        print(exception)
                sleep(2)

        print('Team data for ' + str(team['ABBREVIATION']) + ' transferred.')

print('All teams successfully transferred to csv file team_info.csv.') 