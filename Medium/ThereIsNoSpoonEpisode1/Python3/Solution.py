width,height,lines = (int(input()),int(input()),[])
for i in range(height): lines.append(input()) 

for i in range(height):
    for j in range(width):
        answer = []
        if lines[i][j] == '0':
            answer.append(str(j) + ' ')
            answer.append(str(i) + ' ')
            if j+1 < width:
                for k in range(j+1,width):
                    if lines[i][k] == '0':
                        answer.append(str(k) + ' ')
                        answer.append(str(i) + ' ')
                        break
            if len(answer) != 4:
                answer.append('-1 ')
                answer.append('-1 ')
            
            if i+1 < height:
                for k in  range(i+1,height):
                    if lines[k][j] == '0':
                        answer.append(str(j) + ' ')
                        answer.append(str(k))
                        break
            if len(answer) != 6:
                answer.append('-1 ')
                answer.append('-1')
        if len(answer) == 6: print(''.join(answer))
