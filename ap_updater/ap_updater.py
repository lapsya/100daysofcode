import sys
import random
import time
from http import HTTPStatus

import feedparser
import smtplib

def send_mail(mailing_list, message, subject=''):
    email_server = smtplib.SMTP('smtp.gmail.com', 587)
    email_server.ehlo()
    email_server.starttls()
    email_server.ehlo()
    email_server.login(from_email, from_passwd)

    if isinstance(mailing_list, str):
        email_server.sendmail(from_email, mailing_list, message)
    elif all(isinstance(item, str) for item in mailing_list):
        for to_email in mailing_list:
            header = 'From: {}\r\nTo: {}\r\nSubject: {}\r\n\r\n'.format(from_email, to_email, subject)
            email_server.sendmail(from_email, to_email, header + message)
    else:
        raise TypeError('Expecting container with strings')

    email_server.quit()


from_email = 'ilapsyaupd@gmail.com'
from_passwd = ''
with open('email_pass.txt', 'r') as f:
    from_passwd = f.readline().strip()

feed = feedparser.parse('https://www.androidpolice.com/feed/')
feed_etag = feed.etag
items = {item.id: item.updated for item in feed.entries}

prefixes = ['This should be a good one!', 'Wow! A new post!',
            'Finally, Android Police', "I've been waiting for this one!",
            'The Police is here to arrest you', 'Police. Android Police.',
            'Updates are here!']

mailing_list = []
with open('mail_list.txt', 'r') as f:
    for line in f:
        mailing_list.append(line.strip())


try:
    while True:
        messages = []
        new_feed = feedparser.parse('https://www.androidpolice.com/feed/', etag=feed_etag)
        if new_feed.status != HTTPStatus.NOT_MODIFIED:
            for item in new_feed.entries:
                if item.id not in items or items[item.id] != item.updated:
                    messages.append('Check out "{}" by {} at {}'.format(item.title, item.author, item.link))

        if messages:
            items = {item.id: item.updated for item in new_feed.entries}
            feed_etag = new_feed.etag

            message = '\n{}\n\n{}'.format(random.choice(prefixes), ''.join(messages))
            send_mail(mailing_list, message, subject='Android Poice update!')
        time.sleep(60)

except:
        err = sys.exc_info()[0]

        send_mail(from_email, 'Experienced troubles\n{}\n'.format(err), subject='AP updater exception')

        apology = "We're experiencing some troubles, but the AP updater should be back any moment now\n\nSorry for the inconvenience\n"
        send_mail(mailing_list, apology, subject='Sorry, no updates for now :(')
