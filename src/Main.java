/**
 * Created by Isaac on 12/28/2017.
 */
public class Main {


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
						board[i][j]=CellState.EMPTY;
						break;
					case 'W':
						board[i][j]=CellState.WHITE;
						break;
					case 'B':
						board[i][j]=CellState.BLACK;
						break;
				}
			}
		}
		return new Board(board,size);
	}
	public static void main(String[] args){

		Board board=CreateBoard("input.txt");
		board.Print();
		Board next=board.MakeStep(new Step(new Point(4,4),CellState.BLACK));
		System.out.println();
		next.Print();
	}
}
