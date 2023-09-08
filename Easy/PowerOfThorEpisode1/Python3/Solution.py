import math

light_x, light_y, initial_tx, initial_ty = [int(i) for i in input().split()]

while True:
    remaining_turns = int(input())
    answer = ''
    if light_y > initial_ty:
        answer += 'S'
        initial_ty += 1
    elif light_y < initial_ty:
        answer += 'N'
        initial_ty -= 1
    if light_x > initial_tx:
        answer += 'E'
        initial_tx += 1
    elif light_x < initial_tx:
        answer += 'W'
        initial_tx -= 1

    print(answer)