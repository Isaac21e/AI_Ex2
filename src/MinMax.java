/**
 * Created by Isaac on 1/8/2018.
 */

import java.util.LinkedList;
import java.util.List;

/***
01 function minimax(node, depth, maximizingPlayer)
		02     if depth = 0 or node is a terminal node
		03         return the heuristic value of node

		04     if maximizingPlayer
		05         bestValue := −∞
		06         for each child of node
		07             v := minimax(child, depth − 1, FALSE)
		08             bestValue := max(bestValue, v)
		09         return bestValue

		10     else    (* minimizing player *)
		11         bestValue := +∞
		12         for each child of node
		13             v := minimax(child, depth − 1, TRUE)
		14             bestValue := min(bestValue, v)
		15         return bestValue
 *///
public class MinMax {

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
