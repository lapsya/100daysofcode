import time


def gen_range(start, end, step):
    num = start
    while num < end:
        yield num
        num += step

def list_range(start, end, step):
    num = start
    res_list = []
    while num < end:
        res_list.append(num)
        num += step
    return res_list


def range_comparison(min_num, max_num, step):

    timestamp1 = time.time()
    for i in range(min_num, max_num, step):
        tmp_var = i

    timestamp2 = time.time()
    for i in list_range(min_num, max_num, step):
        tmp_var = i

    timestamp3 = time.time()
    for i in gen_range(min_num, max_num, step):
        tmp_var = i

    timestamp4 = time.time()

    print('In classic range: {}', timestamp2 - timestamp1)
    print('In list range: {}', timestamp3 - timestamp2)
    print('In generator range: {}', timestamp4 - timestamp3)



max_num = 10000000
step = 1
range_comparison(0, max_num, step)
