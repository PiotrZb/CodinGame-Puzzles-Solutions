while True:
    mountain_h = []
    for i in range(8):
        mountain_h.append(int(input()))
    print(mountain_h.index(max(mountain_h)))
