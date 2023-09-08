class Node:
    def __init__(self, value):
        self.__value__ = value
        self.__childs__ = set()

    def add_child(self, child):
        self.__childs__.add(child)

    def get_value(self):
        return self.__value__
    
    def get_childs(self):
        return self.__childs__