import asyncio
import time
import requests

import aiohttp
import aiofiles


async def download_image(session, url, cor_id):

    async with session.get(url) as response:
        headers = response.headers
        bytes_response = await response.read()

    extension = url.split('.')[-1]
    async with aiofiles.open('images/img_{}.{}'.format(cor_id, extension), 'wb') as f:
        await f.write(bytes_response)


async def pool(urls):
    async with aiohttp.ClientSession() as session:
        tasks = [download_image(session, url=url, cor_id=cor_id) for cor_id, url in enumerate(urls)]
        for task in asyncio.as_completed(tasks):
            await task


urls = []
with open('urls.txt') as f:
    for line in f:
        urls.append(line.strip())

start_time = time.time()
event_loop = asyncio.get_event_loop()
event_loop.run_until_complete(pool(urls))
event_loop.close()
end_time = time.time()
print('Asynchronous time: {}'.format(end_time - start_time))


start_time = time.time()
for num, url in enumerate(urls):
    extension = url.split('.')[-1]
    response = requests.get(url)
    with open('images/img_{}.{}'.format(num, extension), 'wb') as f:
        f.write(response.content)
end_time = time.time()
print('Synchronous time: {}'.format(end_time - start_time))
