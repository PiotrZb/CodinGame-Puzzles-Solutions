import queue

nodes_number, links_number, gateways_number = [int(i) for i in input().split()]

graph = {}
for i in range(nodes_number): graph[i] = []

for i in range(links_number):
    n1, n2 = (int(j) for j in input().split())
    graph[n1].append(n2)
    graph[n2].append(n1)

gateways = [int(input()) for i in range(gateways_number)]

def find_link(si):
    q = queue.SimpleQueue()
    for i in graph[si]: q.put(i)

    while not q.empty():
        current = q.get()
        if current in gateways:
            if si in graph[current]: n2 = si
            else: n2 = graph[current][0]
            graph[current].remove(n2)
            graph[n2].remove(current)
            return str(n2) + ' ' + str(current)
        else:
            for i in graph[current]: q.put(i)

while True: print(find_link(int(input())))