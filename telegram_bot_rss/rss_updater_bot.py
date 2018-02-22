import random
from http import HTTPStatus

import feedparser
from telegram.ext import Updater, CommandHandler, MessageHandler, BaseFilter, Filters
from telegram import ReplyMarkup, ReplyKeyboardMarkup, KeyboardButton


feed = feedparser.parse('https://www.androidpolice.com/feed/')
feed_etag = feed.etag
items = {item.id: item.updated for item in feed.entries}

prefixes = ['This should be a good one!', 'Wow! A new post!',
            'Finally, Android Police', "I've been waiting for this one!",
            'The Police is here to arrest you', 'Police. Android Police.',
            'Updates are here!']


class UpdateFilter(BaseFilter):
    def filter(self, message):
        return 'update' in message.text.lower()

def start(bot, update):
    keyboard = [[KeyboardButton(text='Update me!')]]
    bot.send_message(chat_id=update.message.chat_id, text="Hi! I'm here to update you about new AP posts",
                        reply_markup=ReplyKeyboardMarkup(keyboard))

def feed_updates(bot, update):
    global feed, feed_etag, items
    messages = []
    new_feed = feedparser.parse('https://www.androidpolice.com/feed/', etag=feed_etag)
    if new_feed.status != HTTPStatus.NOT_MODIFIED:
        for item in new_feed.entries:
            if item.id not in items or items[item.id] != item.updated:
                messages.append('Check out "{}" by {} at {}'.format(item.title, item.author, item.link))

    if messages:
        items = {item.id: item.updated for item in new_feed.entries}
        feed_etag = new_feed.etag

        message = '{}\n\n{}'.format(random.choice(prefixes), ''.join(messages))
        bot.send_message(chat_id=update.message.chat_id, text=messages)
    else:
        bot.send_message(chat_id=update.message.chat_id, text="Nothing new here!")



with open('bot_token.token') as f:
    bot_token = f.readline().strip()

updater = Updater(token=bot_token)
dispatcher = updater.dispatcher

dispatcher.add_handler(CommandHandler('start', start))
dispatcher.add_handler(CommandHandler('feed_updates', feed_updates))
dispatcher.add_handler(MessageHandler(UpdateFilter(), feed_updates))
updater.start_polling()
