import Node

class Tree:
    def __init__(self):
        self.__layers__ = [[], [], [], [], [], [], [], [], [], [],
                            [], [], [], [], [], [], [], [], [], []] # phone number can be 20 digits long
        self.__number_of_nodes__ = 0
    
    def add_node(self, layer_number, node):
        self.__number_of_nodes__ += 1
        self.__layers__[layer_number].append(node)

    def get_number_of_nodes(self):
        return self.__number_of_nodes__
    
    def get_leyer(self, layer_number):
        return self.__layers__[layer_number]