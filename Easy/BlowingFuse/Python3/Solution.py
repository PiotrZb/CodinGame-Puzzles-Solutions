n, m, c = [int(i) for i in input().split()]

data,sequence,current_consumption,highest_consumption = ([],[],0,0)

for i in input().split(): data.append({'c_consumption' : int(i),'status' : 'off'})
for i in input().split(): sequence.append(int(i))

for i in sequence:
    if data[i-1]['status'] == 'off':
        data[i-1]['status'] = 'on'
        current_consumption += data[i-1]['c_consumption']
    else:
        data[i-1]['status'] = 'off'
        current_consumption -= data[i-1]['c_consumption']
    if current_consumption > highest_consumption: highest_consumption = current_consumption

if highest_consumption >= c: 
    print("Fuse was blown.")
else:
    print("Fuse was not blown.")
    print("Maximal consumed current was",highest_consumption,'A.')
