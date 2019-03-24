from nba_py.player import PlayerList, PlayerSummary
from time import sleep

# Builds a csv with all current players ../csv/player_info.csv

statslist = ['FIRST_NAME','LAST_NAME','DISPLAY_FIRST_LAST','PERSON_ID','PLAYERCODE','BIRTHDATE', 'COUNTRY','SCHOOL',
        'POSITION','WEIGHT','HEIGHT','JERSEY','SEASON_EXP','FROM_YEAR','TO_YEAR',
        'TEAM_NAME','TEAM_CITY','TEAM_ABBREVIATION','TEAM_CODE','TEAM_ID',
        'NBA_FLAG','DLEAGUE_FLAG','GAMES_PLAYED_FLAG','ROSTERSTATUS',
        'DRAFT_ROUND','DRAFT_NUMBER','DRAFT_YEAR','LAST_AFFILIATION']


def build_header(outfile):
        for stat in statslist:
                outfile.write(stat + ',')
        outfile.write('\n')

# implement this later to remove all duplicates within the csv
def remove_duplicates(new_player_list, old_player_list):
        pass


with open('csv/player_info.csv','w') as csvfile:
        print('Opened CSV...')
        build_header(csvfile)
        print('Header built...')
        pl = PlayerList(season=2016, only_current=1)
        print('Player list generated...')
        sleep(2)
        for player in pl.info():
                ps = PlayerSummary(player['PERSON_ID'])

                for plyr in ps.info():
                        try:
                                for stat in statslist: csvfile.write(str(plyr[stat]) + ',')
                                csvfile.write('\n')
                        except Exception as exception:
                                print(exception)
                        sleep(2)

                print('Player data for ' + str(player['DISPLAY_FIRST_LAST']) + ' transferred successfully.')

print('All players successfully transferred to csv file player_info.csv.')   


# if plyr not in player_list: write to csv (for current season only)
# increment player years of experience by one for every player still currently in the nba?
# if plyr in player_list: delete old data and replace with new data