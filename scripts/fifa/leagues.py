from requests import get
from requests.exceptions import RequestException
from contextlib import closing
from bs4 import BeautifulSoup


def simple_get(url):
    '''
    Attempts to get the url by making an HTTP get request
    '''
    try:
        # closing function ensures that any network resources are freed
        with closing(get(url, stream=True)) as resp:
            if is_good_response(resp):
                return resp.content
            else:
                return None
    except RequestException as e:
        print('Error during requests to {0} : {1}'.format(url, str(e)))


def is_good_response(resp):
    '''
    returns true if the response is HTML, else false
    '''
    content_type = resp.headers['Content-Type'].lower()
    return (resp.status_code == 200
            and content_type is not None
            and content_type.find('html') > -1)



url = 'https://fbref.com/en/squads/'
resp = simple_get(url)
soup = BeautifulSoup(resp, 'html.parser')
leagues = soup.find_all('td', attrs={'class': 'left', 'data-stat': 'competitions'}) # check this split

# links = leagues.find_all('a', href=True)
print(len(leagues))
for league in leagues:
    if league.a is None:
        continue
    print(league.a)
    print()
    

''' Example of England's leagues:
<tr>
    <th scope="row" class="left " data-stat="country" >
        <a href="/en/squads/country/ENG/England-Football-Clubs">England Football Clubs</a>
    </th>

    <td class="right iz" data-stat="flag" >
        <span class="f-i f-gb-eng">eng</span> 
    </td>

    <td class="right " data-stat="num_clubs" > 154 </td>

    <td class="left " data-stat="competitions" >
        <a href="/en/comps/9/Premier-League-Seasons">Premier League</a>, 
        <a href="/en/comps/10/EFL-Championship-Seasons">EFL Championship</a>, 
        <a href="/en/comps/15/EFL-League-One-Seasons">EFL League One</a>, 
        <a href="/en/comps/16/EFL-League-Two-Seasons">EFL League Two</a>, 
        <a href="/en/comps/34/National-League-Seasons">National League</a>
    </td>
</tr>
'''

