import asyncio
import random
import requests

async def make_request(url):

    await asyncio.sleep(random.random())

    loop = asyncio.get_event_loop()
    url_task = loop.run_in_executor(None, requests.head, 'http://www.google.com')
    response = await url_task

    return 'URL "{}" was returned at "{}"'.format(url, response.headers['date'])


async def pool(urls):
    tasks = [make_request(url) for url in urls]
    for task in asyncio.as_completed(tasks):
        result = await task
        print(result)

urls = ['https://vk.com', 'https://google.com', 'https://twitter.com',
        'https://androidpolice.com', 'https://9gag.com']
event_loop = asyncio.get_event_loop()
event_loop.run_until_complete(pool(urls))
event_loop.close()
