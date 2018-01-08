import java.util.LinkedList;
import java.util.List;

/**
 * The minmax class.
 * This class runs the MINIMAX algorithm, to predict the winner of the reversi game.
 */
public class MinMax {
	/**
	 * gets the board and the current player, and return the possible boards (aka, screen shots of the game)
	 * that can be created by possible moves.
	 * @param board the board.
	 * @param player the current player.
	 * @return
	 */
	public static List<Board> GetPossibleBoards(Board board, char player){
		List<Step> possibleSteps= board.GetValidSteps(player);
		List<Board> possibleBoards= new LinkedList<>();
		for(Step step: possibleSteps){
			Board boardAfterStep=board.MakeStep(step);
			if(boardAfterStep!=null){
				possibleBoards.add(boardAfterStep);
			}
		}
		return possibleBoards;
	}

	/**
	 * runs the minmax algorithm
	 * @param board the board to run the algorithm
	 * @param depth depth of algorithm
	 * @param player the current player
	 * @return
	 */
	public static Answer RunMiniMax(Board board, int depth, char player){
		if(depth == 0 || board.GameEnded() != Enum.NOT_ENDED){
			return new Answer(board,board.Heuristic());
		}

		if(player == Enum.BLACK){
			List<Board> possibleBoards= MinMax.GetPossibleBoards(board,player);
			Answer ans1 = new Answer(possibleBoards.get(0),Integer.MIN_VALUE);
			for(Board b:possibleBoards){
				Answer ans2=MinMax.RunMiniMax(b,depth-1,Enum.WHITE);

				if(ans2.board.Heuristic() > ans1.heuristics){
					ans1.board = b;
					ans1.heuristics = ans2.heuristics;
				}
			}
			return ans1;

		}
		else{
			//WHITE
			List<Board> possibleBoards= MinMax.GetPossibleBoards(board,player);
			Answer ans1 = new Answer(possibleBoards.get(0),Integer.MAX_VALUE);
			for(Board b:possibleBoards){
				Answer ans2=MinMax.RunMiniMax(b,depth-1,Enum.BLACK);
				if(ans2.board.Heuristic() < ans1.heuristics){
					ans1.board = b;
					ans1.heuristics = ans2.heuristics;
				}
			}
			return ans1;

		}
	}


}
