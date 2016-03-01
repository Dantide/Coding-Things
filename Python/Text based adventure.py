"""This will be my sandbox for creating a text adventure game.
Not exactly sure how to do it, but we will start with something anyway."""

class Room():
	def __init__(self, name):
		self.name = name

roomlist = {
	1 : Room("LivingRoom"),
	2 : Room("Kitchen"),
	3 : Room("Bedroom"),
	4 : Room("Bathroom")
}

def starting_statements():
	print ("This is a test of something new.")
	print ("Please enjoy.\n")

	print ("You are sitting on the couch in the LivingRoom and you decide that you are getting hungry.")

def main():
	starting_statements()
	playing = True
	location = 1;

def livingroom():
	answer = input("What would you like to do?")


main()