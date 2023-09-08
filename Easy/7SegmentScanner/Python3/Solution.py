line_1 = input()
line_2 = input()
line_3 = input()
answer = ''
for i in range(0,len(line_1),3):
    if line_1[i+1] != '_':
        if line_2[i] == '|': answer += '4'
        else: answer += '1' 
    elif line_2[i + 1] != '_':
        if line_2[i] == '|': answer += '0'
        else: answer += '7'
    elif line_2[i] != '|':
        if line_3[i] == '|': answer += '2'
        else: answer += '3'
    elif line_3[i] == '|':
        if line_2[i+2] == '|': answer += '8'
        else: answer += '6'
    else:
        if line_2[i+2] == '|': answer += '9'
        else: answer += '5'
print(answer)