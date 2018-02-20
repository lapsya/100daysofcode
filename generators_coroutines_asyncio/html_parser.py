# the goal is to print out a link from an html file
# if its text is at most `max_cnt_words` long

from html.parser import HTMLParser


class MyHTMLParser(HTMLParser):
    def __init__(self, target_coroutine):
        HTMLParser.__init__(self)
        self.target_coroutine = target_coroutine

    def handle_starttag(self, tag, attrs):
        self.target_coroutine.send(('start', (tag, attrs)))

    def handle_endtag(self, tag):
        self.target_coroutine.send(('end', tag))

    def handle_data(self, data):
        self.target_coroutine.send(('data', data))



def coroutine(func):
    def start(*args,**kwargs):
        cr = func(*args,**kwargs)
        next(cr)
        return cr
    return start

@coroutine
def link_finder(target_coroutine):
    while True:
        event, value = yield
        if event == 'start' and value[0] == 'a':
            for attr in value[1]:
                if attr[0] == 'href':
                    link = attr[1]
                    break
            while True:
                event, value = yield
                if event == 'data':
                    #links[data] = link
                    link_name = value
                elif event == 'end':
                    if value == 'a':
                        target_coroutine.send((link_name, link))
                    break
                elif event == 'start':
                    raise ValueError('Unexpected start tag {}'.format(value[0]))


@coroutine
def link_printer(max_cnt_words):
    while True:
        link_name, link = yield
        if len(link_name.split()) <= max_cnt_words:
            print('"{}" leads here: {}'.format(link_name, link))


max_cnt_words = 5
my_parser = MyHTMLParser(link_finder(link_printer(max_cnt_words)))
my_parser.feed('<html><div><a href="http://yandex.ru">I am so not cool you will not believe it</a>'
                '<a href="http://twitter.com">Twitter here</a></div><a href="http://google.com">Google</a></html>')
