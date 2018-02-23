import asyncio
import random
import requests

import aiohttp

async def make_request(session, url, cor_id):

    await asyncio.sleep(random.random() * 5)

    async with session.get(url) as response:
        headers = response.headers


    return '{}: URL "{}" was returned at "{}"'.format(cor_id, url, headers['date'])


async def pool(urls):
    async with aiohttp.ClientSession() as session:
        tasks = [make_request(session, url=enum[1], cor_id=enum[0]) for enum in enumerate(urls)]
        for task in asyncio.as_completed(tasks):
            result = await task
            print(result)

urls = ['https://vk.com', 'https://google.com', 'https://twitter.com',
        'https://androidpolice.com', 'https://9gag.com'] * 20

#urls = ['https://google.com'] * 100
event_loop = asyncio.get_event_loop()
event_loop.run_until_complete(pool(urls))
event_loop.close()
