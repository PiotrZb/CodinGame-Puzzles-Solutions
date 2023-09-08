from statistics import median

buildingsX = []
buildingsY = []

# reading data from standard input
for i in range(int(input())):
    x, y = [int(j) for j in input().split()]
    buildingsX.append(x)
    buildingsY.append(y)

# calculating main cable level
main_cable_lvl = median(buildingsY)

# calculating main cable lenght
first_buildingX = min(buildingsX)
last_buildingX = max(buildingsX)
main_cable_length = last_buildingX - first_buildingX

# calculating minor cables length
minor_cables_length = 0
for y in buildingsY:
    minor_cables_length += abs(y - main_cable_lvl)

# answer
total_length = main_cable_length + minor_cables_length
print(int(total_length))