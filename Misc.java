public class Misc{
	public static void printCards(ElevensBoard board) {
		List<Integer> cIndexes = board.cardIndexes();
		for (int Index: cIndexes) {
			System.out.println(cardAt(Index));
		}
	}
}