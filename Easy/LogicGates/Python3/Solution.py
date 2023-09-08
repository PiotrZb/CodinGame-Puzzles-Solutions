n,m,inputs,outputs = (int(input()),int(input()),[],[])
for i in range(n): inputs.append(input().split())
for i in range(m): outputs.append(input().split())

def and_(A, B):
    answer = ''
    for i in range(len(A)):
        if A[i] == '-' and B[i] == '-': answer += '-'
        else: answer += '_'
    return answer

def or_(A, B):
    answer = ''
    for i in range(len(A)):
        if A[i] == '-' or B[i] == '-': answer += '-'
        else: answer += '_'
    return answer

def nand_(A,B):
    answer = ''
    for i in range(len(A)):
        if A[i] == '-' and B[i] == '-': answer += '_'
        else: answer += '-'
    return answer

def nor_(A, B):
    answer = ''
    for i in range(len(A)):
        if A[i] == '-' or B[i] == '-': answer += '_'
        else: answer += '-'
    return answer

def xor_(A, B):
    answer = ''
    for i in range(len(A)):
        if A[i] == B[i]: answer += '_'
        else: answer += '-'
    return answer
    
def nxor_(A, B):
    answer = ''
    for i in range(len(A)):
        if A[i] == B[i]: answer += '-'
        else: answer += '_'
    return answer
    
switch = {
    'AND': and_,
    'OR': or_,
    'NAND': nand_,
    'XOR': xor_,
    'NOR': nor_,
    'NXOR': nxor_
}

def find_in(A):
    for i in inputs: 
        if i[0] == A: return i[1]

for i in outputs:
    print(i[0], switch[i[1]](find_in(i[2]),find_in(i[3])))