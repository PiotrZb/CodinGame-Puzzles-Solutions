n = int(input())
v = [int(i) for i in input().split()]

x, ls = v[0], [0]

for i in range(1,n):
    if v[i] < x: ls.append(v[i] - x)
    else: x = v[i]

print(min(ls))