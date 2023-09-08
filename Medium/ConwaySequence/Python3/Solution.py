# reading data from standard input
starting_number = int(input())
line_number = int(input())
lines = [[starting_number]]

for index in range(1,line_number):
    previous_line = lines[index-1]
    current_line = []
    last_number = None
    number_of_repetitions = 0

    for number in previous_line:
        # first number in line
        if last_number is None:
            last_number = number
            number_of_repetitions = 1

        # counting repetitions
        elif last_number == number:
            number_of_repetitions += 1

        else:
            current_line.append(number_of_repetitions)
            current_line.append(last_number)
            last_number = number
            number_of_repetitions = 1
    
    current_line.append(number_of_repetitions)
    current_line.append(last_number)
    lines.append(current_line)


# answer
print(' '.join(str(x) for x in lines[-1]))