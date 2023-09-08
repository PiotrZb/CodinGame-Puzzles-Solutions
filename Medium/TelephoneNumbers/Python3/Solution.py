import Node
import Tree

phone_numbers_tree = Tree()

for i in range(int(input())):
    telephone = [int(x) for x in input()]
    current_node = None

    for index, digit in enumerate(telephone):

        node_exist = False

        # find first node
        if current_node is None: 
            for node in phone_numbers_tree.get_leyer(0):
                if node.get_value() == digit: # node exist
                    current_node = node
                    node_exist = True
                    break

            if not node_exist: # node doesn't exist (create new one)
                current_node = Node(digit)
                phone_numbers_tree.add_node(0, current_node)

        # find other nodes
        else:
            childs = current_node.get_childs()
            
            for child in childs:
                if child.get_value() == digit: # node exist
                    current_node = child
                    node_exist = True
                    break

            if not node_exist: # node doesn't exist (create new one)
                new_node = Node(digit)
                current_node.add_child(new_node)
                phone_numbers_tree.add_node(index, new_node)
                current_node = new_node

# The number of elements (referencing a number) stored in the structure.
print(phone_numbers_tree.get_number_of_nodes())
