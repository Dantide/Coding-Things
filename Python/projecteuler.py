import sys
import string
import timeit
#print (sys.version)

#REOCCURING FUCTIONS
letterdict = {
	'A' : 1, 'B' : 2, 'C' : 3, 'D' : 4, 'E' : 5, 'F' : 6, 'G' : 7, 'H' : 8, 'I' : 9,
	'J' : 10, 'K' : 11, 'L' : 12, 'M' : 13, 'N' : 14, 'O' : 15, 'P' : 16, 'Q' : 17,
	'R' : 18, 'S' : 19, 'T' : 20, 'U' : 21, 'V' : 22, 'W' : 23, 'X' : 24, 'Y' : 25, 'Z' : 26
}

def isPrime(n):
	"Test if number is prime. Returns True or False."
	if (n < 2):
		return False
	if (n == 2) or (n == 5):
		return True
	if (n % 2 == 0) or (n % 5 == 0):
		return False
	for x in range(2, int(n/2)):
		if (n % x == 0):
			return False
	return True

def fibnum(n):
	"Returns the nth fibbonacci number, assuming that we go: 1, 1, 2, 3, 5, etc."
	if n==1 or n==2: return 1

	num1, num2, temp = 1, 1, 0
	for x in range(n-2):
		temp = num2
		num2 += num1
		num1 = temp
	return num2

def primeFactor(n):
	"Find the prime factors of a number."
	if n < 2: return []

	array = []
	temp = n
	isP = isPrime
	finished = isP(temp)
	while not finished:
		for x in range(2,int(temp**0.5) + 1):
			if isP(x) and (temp % x == 0):
				array.append(x)
				temp /= x
				break
		finished = isPrime(temp)
	array.append(int(temp))
	array.sort()
	return array

def primeExpo(arr):
	"The 'key' shows up 'value' amount of times in the dictionary."
	temp = {}
	for x in arr:
		if x not in temp:
			temp[x] = 1
		else:
			temp[x] += 1
	return temp

def factorial(n):
	"Return the factorial of n."
	temp = 1
	for x in range(1, n+1):
		temp *= x
	return (temp)

def sumDigits(n):
	"Return the sum of the digits in a number. sumDigits(87) = 8 + 7 = 15"
	temp = 0
	for x in str(n):
		temp += int(x)
	return temp

def triNum(n):
	"Return the nth triangular number. Whereas tn = ½n(n+1)."
	temp = (n/2)*(n+1)
	return int(temp)








#Problem Solving Area

#print (timeit.repeat("for x in range(100): factor(x)", "from __main__ import factor", number=1000))
#print (timeit.repeat("for x in range(100): factor2(x)", "from __main__ import factor2", number=1000))


#problem 1 SOLVED
"""def problem1():
	"Find the sum of all multiples of 3 or 5 below 1000."
	count = 0
	for x in range(0, 1000):
		if (x % 3 == 0) or (x % 5 == 0):
			count += x
	print (count)"""

#problem 2 SOLVED
"""def fibiter2():
	"Find the sum of all even fibbonacci numbers below four million."
	i, count = 0, 0
	num = fibnum(i)
	while (num < 4000000):
		if (num % 2 == 0):
			count += num
		i += 1
		num = fibnum(i)
	print (count)"""

#problem 3 SOLVED
"""def problem3():
	"Find the largest prime factor of 600851475143."
	array = []
	temp = 600851475143
	while not isPrime(temp):
		for x in range(2,int(temp**0.5) + 1):
			if isPrime(x) and (temp % x == 0):
				array.append(x)
				temp /= x
				break
	array.append(int(temp))
	array.sort()
	return (array)"""

#problem 4 SOLVED
"""def isPalindrome(n):
	"Tests if a number is a palindrome."
	if str(n) == str(n)[::-1]:
		return True
	else:
		return False

def largestpalinproduct(digits):
	"A more general code for problem 4, but it is not very efficient. It works though."
	array = []
	largest = 10**digits
	smallest = 10**(digits - 1)
	for x in range(largest - 1, smallest, -1):
		for i in range(largest - 1, smallest, -1):
			temp = x*i
			if isPalindrome(temp):
				array.append(temp)
	else:
		return max(array)

def problem4():
	"Find the largest palindrome made from the product of two 2-digit numbers."
	for num in range(999, 700, -1):
		for x in range(999, 700, -1):
			temp = x*num
			if isPalindrome(temp):
				return temp"""

#problem 5 SOLVED
"""def problem5(n):
	"LCM of all numbers from 1 to n."
	most = {}
	pexpo = primeExpo
	pfactor = primeFactor
	for x in range(1, n):
		#Find the prime factors of all numbers from 1 to n.
		temp = pexpo(pfactor(x))
		testproblem5(temp, most)
	print (mult5(most))

def testproblem5(temp, most):
	"Just keeping most as the dictionary with the most occurrences of each prime factor."
	for x in temp:
		if not((x in most) and (temp[x] < most[x])):
			#The only time we don't run this code is if x is in both dictionaries
			#and temp[x] is less than most[x].
			most[x] = temp[x]

def mult5(most):
	"Multiply all the factors (to the power of the max times it appeared in one number) together."
	temp = 1
	for x in most:
		temp *= x**most[x]
	return temp"""

#problem 7
def problem7():
	count = 1
	x = 3
	while count<5000:
		if isPrime(x):
			count+=1
		x+=2
	print (x-2)

#problem 8


#problem 10
def sumOfPrimesBelow(n):
	count = 0
	prime = isPrime
	for x in range(3, n, 2):
		if prime(x):
			count += x
	return count + 2

#problem 12 SOLVED
"""def factor(n):
	"Return an array with all the factors of a number. Load increases greatly when using large numbers."
	if n <= 0:
		return None
	array = [1, n]
	for x in range(2, int(n**0.5)):
		if (n % x == 0):
			array.append(x)
			array.append(int(n/x))
	array.sort()
	return array

def factor2(n):
	if n <= 0:
		return None
	array = []
	for x in [x for x in range(1, int(n**0.5)) if (n % x == 0)]:
		array.append(x)
		array.append(int(n/x))
	array.sort()	
	return array

def problem12(n):
	"Find the first number in the range that has more than n factors."
	fact = factor
	tri = triNum
	for x in [tri(i) for i in range(11000, 13000)]:
		num2 = len(fact(x))
		if num2 >= n:
			print (num2)
			return x
	else:
		return None"""


#problem 13 SOLVED
"""def problem13():
	"Find the first 10 digits of the sum of the following one-hundred 50-digit numbers."
	thing = open("C:/Users/Stephen/Desktop/projecteuler/problem 13.txt")
	num = 0
	for line in thing:
		num += int(line)
	print (num)
	thing.close()"""

#problem 15 SOLVED
"""def findLattice(n):
	"Return # of lattice paths in box n by n. http://www.mozartreina.com/counting-lattice-paths.html"
	array = [[1 for x in range(n+1)] for x in range(n+1)]
	for x in range(1, n+1):
		first = True
		for i in range(x, n+1):
			if first:
				array[x][i] = array[x-1][i] * 2
				first = False
			else:
				array[x][i] = array[x-1][i] + array[x][i-1]
	else:
		return array[len(array)-1][len(array)-1]"""

#problem 18
def test18():
	"Test"
	f = open("C:/Users/Stephen/Desktop/projecteuler/test 18.txt")
	f.close()

def problem18():
	"Choose from the larger of the next two numbers. The idea worked, but it was wrong."
	f = open("C:/Users/Stephen/Desktop/projecteuler/problem 18.txt")
	print (findmaximumpath(f))
	f.close()

def findmaximumpath(f):
	for line in f:
		print(line)

problem18()

#problem 20 SOLVED
#print (sumDigits(factorial(10)))

#problem 22 SOLVED
"""def problem22():
	"What is the total of all the name scores in the file? (In alphabetical order)"
	temp, count = 0, 1
	f = open("C:/Users/Stephen/Desktop/projecteuler/p022_names.txt")
	thing = string.punctuation
	for i in f:
		array = i.split(',')
		array.sort()
	for word in array:
		for letter in word:
			if (letter not in thing) and (letter is not ""):
				temp += (letterdict[letter] * count)
		count += 1
	print (temp)"""

#problem 25 SOLVED
"""def problem25():
	"Return the n index of the first fibbonacci number longer than 1000 digits."
	i = 1
	while True:
		if len(str(fibnum(i))) == 1000:
			return i
		else:
			i += 1"""


#problem 28 SOLVED
"""def createSpiral(n):
	if (n%2 == 0):
		return None
	array = [[0 for x in range(n)] for x in range(n)]

	for i in array:
		print (i)

def addSpiralDiag(n):
	"Adds all the numbers on the diagonals of an n by n array."
	if (n%2 == 0):
		return None
	num = 1 					#the diagonal number to be added to count
	count = num 				#has the total to be returned later
	numofdiags = 2*n - 1 		#number of diagonal numbers in the array
	for i in range(1, numofdiags):

		inc = int((i+3)/4)*2 	#Every four numbers, the space between the diagonal numbers increases by two
		num += inc 				#The next diagonal number to add to count
		count += num
	return count"""