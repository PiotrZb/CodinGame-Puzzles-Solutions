import math

lonA = float(input().replace(',','.'))
latA = float(input().replace(',','.'))
n = int(input())
defib,distance,nearest = ([],[],'')
for i in range(n):
    defib.append(input().split(';'))

for i in defib:
    lonB = float(i[4].replace(',','.'))
    latB = float(i[5].replace(',','.'))
    x = (lonB - lonA) * math.cos((latA + latB)/2)
    y =(latB - latA)
    distance.append({'d' : math.sqrt((x**2) + (y**2)) * 6371,'name' : i[1]})

def fc(x):
    return x['d']

distance.sort(key = fc)
print(distance[0]['name'])