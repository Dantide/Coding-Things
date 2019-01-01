import os

def checkFor(word, folder):
	for doc in folder:
		if (word in doc):
			return True
	else:
		return False