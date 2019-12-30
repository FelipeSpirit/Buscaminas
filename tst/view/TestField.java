package view;
import com.setupteam.buscaminas.model.entity.Minesweeper;
import com.setupteam.buscaminas.utilities.MinesweeperConstants;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class TestField {
	public static void main(String[] args) {
		Minesweeper mw= new Minesweeper(10, 10, 6);
		mw.generateField();
		printBombs(mw);
		System.out.println();
		printField(mw);
	}
	
	public static void printBombs(Minesweeper mw) {
		for (int i = 0; i < mw.getField().length; i++) {
			for (int j = 0; j < mw.getField()[i].length; j++) {
				System.out.print((mw.getField()[i][j] == MinesweeperConstants.BOMB ? "B" : (mw.getField()[i][j] == MinesweeperConstants.LIMIT ? "A" : " ")) + " ");
			}
			System.out.println();
		}
	}

	public static void printField(Minesweeper mw) {
		for (int i = 0; i < mw.getField().length; i++) {
			System.out.print("A ");
		}
		System.out.println();

		for (int i = 0; i < mw.getField().length - 2; i++) {
			System.out.print("A ");
			for (int j = 0; j < mw.getField()[i].length - 2; j++) {
				System.out.print((mw.checkBombs(i, j) == MinesweeperConstants.EMPTY ? " " : mw.checkBombs(i, j)) + " ");
			}
			System.out.println("A");
		}

		for (int i = 0; i < mw.getField().length; i++) {
			System.out.print("A ");
		}
		System.out.println();

	}
}
