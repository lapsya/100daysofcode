from bs4 import BeautifulSoup
from urllib.request import urlopen
import re

rss_re = r'[Rr][Ss]{2}'
http_re = r'[Hh][Tt]{2}[Pp]'

def find_rss_url(link):

    html_page = urlopen(link)
    soup = BeautifulSoup(html_page,  "html5lib")

    for a_element in soup.findAll('a'):
        if re.search(rss_re, a_element.get_text()) or (a_element.get('title') and re.search(rss_re, a_element.get('title'))):
            if not re.search(http_re, a_element.get('href')):
                rss_link = '{}{}'.format(link, a_element.get('href'))
            else:
                rss_link = a_element.get('href')
            rss_link = rss_link.replace('//', '/').replace(':/', '://')
            break
    return rss_link

print(find_rss_url('https://www.androidpolice.com/'))
