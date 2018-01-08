/**
 * The main class.
 * Creates board from input.txt, calculates the winner ('W' or 'B') and writes it to output.txt.
 */
public class java_ex2 {

	/**
	 * reades board from file, creates a board object and return it
	 * @param path path of file with board content
	 * @return the board as an object
	 */
	public static Board CreateBoard(String path){
		FileHandler reader= new FileHandler();
		String[] content =reader.Read(path);
		int size= content[0].length();
		char[][] board= new char[size][size];
		for(int i=0;i<size;i++){
			String str=content[i];
			for(int j=0; j<size;j++){
				switch (str.charAt(j)){
					case 'E':
						board[i][j]= Enum.EMPTY;
						break;
					case 'W':
						board[i][j]= Enum.WHITE;
						break;
					case 'B':
						board[i][j]= Enum.BLACK;
						break;
				}
			}
		}
		return new Board(board,size);
	}

	/**
	 * Gets winner of the game
	 * @param board the board
	 * @return the winner; 'W' if the white player wins, 'B' if the Black wins.
	 */
	public static char GetWinner(Board board){
		Answer next=null;
		char player=Enum.BLACK;
		while (Enum.NOT_ENDED==board.GameEnded()){

			next=MinMax.RunMiniMax(board,3,player);
			if(player == Enum.BLACK){
				player = Enum.WHITE;
			}else
				player = Enum.BLACK;
			board=next.board;
		}
		return board.GameEnded();
	}

	/**
	 * This main functions.
	 * @param args args to main
	 */
	public static void main(String[] args){

		Board board=CreateBoard("input.txt");
		char winner=GetWinner(board);
		FileHandler.Write("output.txt",Character.toString(winner));
		System.out.println();


	}
}
