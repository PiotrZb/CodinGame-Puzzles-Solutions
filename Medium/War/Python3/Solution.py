n = int(input())                                                           # data reading
cardp_1,cardp_2 = ([],[])
for i in range(n): cardp_1.append(input())
m = int(input())  
for i in range(m): cardp_2.append(input())

values = {                                                                 # values of cards
    '2D': 2,
    '3D': 3,
    '4D': 4,
    '5D': 5,
    '6D': 6,
    '7D': 7,
    '8D': 8,
    '9D': 9,
    '10D': 10,
    'JD': 11,
    'QD': 12,
    'KD': 13,
    'AD': 14,
    '2H': 2,
    '3H': 3,
    '4H': 4,
    '5H': 5,
    '6H': 6,
    '7H': 7,
    '8H': 8,
    '9H': 9,
    '10H': 10,
    'JH': 11,
    'QH': 12,
    'KH': 13,
    'AH': 14,
    '2C': 2,
    '3C': 3,
    '4C': 4,
    '5C': 5,
    '6C': 6,
    '7C': 7,
    '8C': 8,
    '9C': 9,
    '10C': 10,
    'JC': 11,
    'QC': 12,
    'KC': 13,
    'AC': 14,
    '2S': 2,
    '3S': 3,
    '4S': 4,
    '5S': 5,
    '6S': 6,
    '7S': 7,
    '8S': 8,
    '9S': 9,
    '10S': 10,
    'JS': 11,
    'QS': 12,
    'KS': 13,
    'AS': 14
}

number_of_turns,pat = (0,False)

while len(cardp_1)*len(cardp_2) != 0 and pat == False:                     # game loop
    windex = 0
    if values[cardp_1[0]] <  values[cardp_2[0]]:                           # battle won by player 2
        cardp_2.append(cardp_1[0])
        cardp_2.append(cardp_2[0])
        cardp_2.remove(cardp_2[0])
        cardp_1.remove(cardp_1[0])
    elif values[cardp_1[0]] >  values[cardp_2[0]]:                         # batlle won by player 1
        cardp_1.append(cardp_1[0])
        cardp_1.append(cardp_2[0])
        cardp_2.remove(cardp_2[0])
        cardp_1.remove(cardp_1[0])
    else:                                                                  # war 
        while True:
            windex += 4
            if windex >= len(cardp_1) or windex >= len(cardp_2):
                pat = True
                break
            else:
                if values[cardp_1[windex]] > values[cardp_2[windex]]:
                    for i in range(windex+1):
                        cardp_1.append(cardp_1[0])
                        cardp_1.remove(cardp_1[0])
                    for i in range(windex+1):
                        cardp_1.append(cardp_2[0])
                        cardp_2.remove(cardp_2[0])
                    break
                elif values[cardp_1[windex]] < values[cardp_2[windex]]:
                    for i in range(windex+1):
                        cardp_2.append(cardp_1[0])
                        cardp_1.remove(cardp_1[0])
                    for i in range(windex+1):
                        cardp_2.append(cardp_2[0])
                        cardp_2.remove(cardp_2[0])
                    break
    number_of_turns += 1

if len(cardp_1) == 0: print('2',number_of_turns)                           # answer
elif len(cardp_2) == 0: print('1',number_of_turns)
else: print('PAT')