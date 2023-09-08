n, x = (int(input()), [])
for i in range(n): x.append(input())

for i in x:
    steps,var,k = ([],0,i)
    while True:
        for j in k: var += (int(j) ** 2)
        if var == 1:
            print(i, ':)')
            break
        elif var in steps:
            print(i, ':(')
            break
        steps.append(var)
        k = str(var)
        var = 0
