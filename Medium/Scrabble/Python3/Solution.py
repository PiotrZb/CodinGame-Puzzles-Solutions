n = int(input())
dictionary = [input() for i in range(n)]
letters = input()

v = [('eaionrtlsu',1),('dg',2),('bcmp',3),('fhvwy',4),('k',5),('jx',8),('qz',10)]
values = {}
for i in v:
    for j in i[0]: values[j] = i[1]

def check_word(word):
    l = list(letters)
    for i in word:
        if i not in l: return False
        else: l.remove(i)
    return True

def check_value(word):
    val = 0
    for i in word: val += values[i]
    return val

possible_words = []
for i in dictionary: 
    if check_word(i): possible_words.append(i)

possible_words.sort(reverse = True, key = check_value)
print(possible_words[0])
