import feedparser
import random
import smtplib
import time

from_email = 'ilapsyaupd@gmail.com'
from_passwd = ''
with open('email_pass.txt', 'r') as f:
    from_passwd = f.readline().strip()


feed = feedparser.parse('https://www.androidpolice.com/feed/')

items = {item.id : item.updated for item in feed.entries}

prefixes = ['This should be a good one!', 'Wow! A new post!',
            'Finally, Android Police', "I've been waiting for this one!",
            'The Police is here to arrest you', 'Police. Android Police.',
            'Updates are here!']

mailing_list = []
with open('mail_list.txt', 'r') as f:
    mailing_list.append(f.readline().strip())

while True:
    message = ''
    new_feed = feedparser.parse('https://www.androidpolice.com/feed/', etag=feed.etag)
    if new_feed.status != 304:
        for item in new_feed.entries:
            if item.id not in items.keys() or items[item.id] != item.updated:
                message += 'Check out {} by {} at {}\n'.format(item.title, item.author, item.link)

    if message != '':

        email_server = smtplib.SMTP('smtp.gmail.com', 587)
        email_server.ehlo()
        email_server.starttls()
        email_server.ehlo()
        email_server.login(from_email, from_passwd)

        feed = new_feed
        items = {item.id : item.updated for item in feed.entries}

        message = '\n{}\n{}'.format(random.choice(prefixes), message)
        for to_email in mailing_list:
            header = 'From: {}\r\nTo: {}\r\nSubject: Android Police update!\r\n\r\n'.format(from_email, to_email)
            email_server.sendmail(from_email, to_email, header + message)
        email_server.quit()
    time.sleep(60)
