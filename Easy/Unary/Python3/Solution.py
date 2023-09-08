bin_message = ''.join(map(bin,bytearray(input(),encoding = 'utf8')))
bin_message_array = bin_message.split('0b')

for i in bin_message_array[1:]:
    index = bin_message_array.index(i)
    while len(i) < 7:
        i = '0' + i
    bin_message_array[index] = i
    

bin_message = ''.join(bin_message_array) + 'e'

sign,times,answer = (bin_message[0],0,'')

for i in bin_message:
    if i == sign:
        times += 1
    else:
        if sign == '0':
            answer += '00 '
        else:
            answer += '0 '
        
        for j in range(times):
            answer += '0'

        if i != bin_message[len(bin_message)-1]: answer += ' '
        times = 1
        sign = i

print(answer)

