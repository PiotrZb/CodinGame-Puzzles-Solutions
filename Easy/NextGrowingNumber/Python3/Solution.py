x = int(input())
x += 1
n = list(str(x))
done = False
while not done:
    for i in range(len(n)-1):
        if int(n[i+1]) < int(n[i]):
            if n[i+1] != '9':
                for j in range(i+1,len(n)):
                    n[j] = n[i]
            else:
                n[i] = str(int(n[i]) + 1)
                for j in range(i+1,len(n)):
                    n[j] = n[i]
                break
    done = True

print(''.join(n))