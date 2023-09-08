import sys
import math

def sort_function(e):
    return e[0]

# nb_floors: number of floors
# width: width of the area
# nb_rounds: maximum number of rounds
# exit_floor: floor on which the exit is found
# exit_pos: position of the exit on its floor
# nb_total_clones: number of generated clones
# nb_additional_elevators: ignore (always zero)
# nb_elevators: number of elevators

# variables read from standard input
nb_floors, width, nb_rounds, exit_floor, exit_pos, nb_total_clones,\
 nb_additional_elevators, nb_elevators = [int(i) for i in input().split()]

# auxiliary variables
clone_blocked_on_current_floor = False
current_floor = 0
elevators = []

for i in range(nb_elevators):

    # elevator_floor: floor on which this elevator is found
    # elevator_pos: position of the elevator on its floor

    elevator_floor, elevator_pos = [int(j) for j in input().split()]
    elevators.append((elevator_floor, elevator_pos))

# treating exit as the last elevator
elevators.append((exit_floor,exit_pos))
elevators.sort(key=sort_function)

# game loop
while True:

    # variables read from standard input
    inputs = input().split()
    clone_floor = int(inputs[0])  # floor of the leading clone
    clone_pos = int(inputs[1])  # position of the leading clone on its floor
    direction = inputs[2]  # direction of the leading clone: LEFT or RIGHT

    # auxiliary variables
    action = "WAIT"
    elevator = elevators[current_floor]

    # algorithm

    # variables update
    if clone_floor > current_floor:
        current_floor = clone_floor
        clone_blocked_on_current_floor = False

    # check if the current direction is correct
    elif ((clone_floor == current_floor) and 
          not clone_blocked_on_current_floor and 
          ((clone_pos < elevator[1] and direction == "LEFT") or 
           (clone_pos > elevator[1] and direction == "RIGHT"))):
        action = "BLOCK"
        clone_blocked_on_current_floor = True

    # print action : BLOCK or WAIT
    print(action)