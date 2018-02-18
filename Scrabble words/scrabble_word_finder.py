from itertools import permutations, combinations


def read_dict(filename):
    with open(filename, 'r') as f:
        for line in f:
            dictionary.append(line.strip().lower())

def inside_words(letters):
    words = []
    for l in range(1, len(letters) + 1):
        for perm in permutations(letters, l):
            perm_word = ''.join(perm).lower()
            print(perm_word)
            if perm_word in dictionary:
                words.append(perm_word)
                print([i for i in dictionary if i == perm_word])
    return words



dictionary = []
read_dict('dict.txt')
print('ca' in dictionary)

input_letters = 'CAT'
print(inside_words(input_letters))
