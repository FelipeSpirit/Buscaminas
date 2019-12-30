package view;
import com.setupteam.buscaminas.model.entity.Minesweeper;
import com.setupteam.buscaminas.utilities.MinesweeperConstants;
import com.setupteam.buscaminas.view.MainFrame;
import com.setupteam.buscaminas.view.game.CellMap;
import com.setupteam.buscaminas.view.game.GameAreaPanel;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class TestGameArea {

	public static void main(String[] args) {
		System.out.println("Minesweeper");
		Minesweeper mw = new Minesweeper(MinesweeperConstants.EASY);
		
		System.out.println("Generar campo");
		mw.generateField();
		
		System.out.println("Mostrar bombas");
		TestField.printBombs(mw);
		System.out.println();
		
		System.out.println("Mostrar perimetros");
		TestField.printField(mw);
		
		System.out.println("Vista campo");
		CellMap cellMap = new CellMap(mw);
		
		System.out.println("Imprimir");
		GameAreaPanel gap = new GameAreaPanel(cellMap);
		
		System.out.println("Ventana");
		new MainFrame(gap);
	}
}
