w, h = [int(i) for i in input().split()]
mp = [input().split() for i in range(h)]
ex = int(input())

shape0 = {}
shape1 = {'top':'down','left':'down','right':'down'}
shape2 = {'left':'right','right':'left'}
shape3 = {'top':'down'}
shape4 = {'top':'left','right':'down','left':'collision'}
shape5 = {'top':'right','left':'down','right':'collision'}
shape6 = {'top':'collision','left':'right','right':'left'}
shape7 = {'top':'down','right':'down'}
shape8 = {'left':'down','right':'down'}
shape9 = {'left':'down','top':'down'}
shape10 = {'top':'left','left':'collision'}
shape11 = {'top':'right','right':'collision'}
shape12 = {'right':'down'}
shape13 = {'left':'down'}

shapes = {0:shape0,1:shape1,2:shape2,3:shape3,4:shape4,5:shape5,6:shape6,7:shape7,8:shape8,9:shape9,10:shape10,11:shape11,12:shape12,13:shape13}

def answer(x,y,p):
    direction = shapes[int(mp[y][x])][p]
    if direction == 'down': return str(x) + ' ' + str(y+1)
    elif direction == 'left': return str(x-1) + ' ' + str(y)
    else: return str(x+1) + ' ' + str(y)

while True:
    xi, yi, pos = input().split()
    xi, yi = int(xi), int(yi)
    print(answer(xi,yi,pos.lower()))
