#include <iostream>
#include <string>
#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */

void randomguess() {
	double num = rand() % 10;
	printf("%f\n", num);
}

int main(int argc, char const *argv[])
{
	randomguess();
	return 0;
}
