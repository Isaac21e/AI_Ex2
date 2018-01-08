/**
 * Created by Isaac on 12/28/2017.
 */
public class java_ex2 {


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
	public static void main(String[] args){

		Board board=CreateBoard("input2.txt");
		//board.Print();
		char winner=GetWinner(board);
		FileHandler.Write("output.txt",Character.toString(winner));
		System.out.println();


	}
}
