from requests import get
from bs4 import BeautifulSoup

url = 'https://fbref.com/en/players/d70ce98e/Lionel-Messi'
response = get(url)

soup = BeautifulSoup(response.content, 'html.parser')
stats1 = soup.find_all('div', attrs={'class': 'p1'})

for stat in stats1:
    pass

stats2 = soup.find_all('div', attrs={'class': 'p2'})
print(len(stats2))