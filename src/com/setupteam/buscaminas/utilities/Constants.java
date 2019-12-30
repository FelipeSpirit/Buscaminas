package com.setupteam.buscaminas.utilities;

import com.setupteam.buscaminas.connection.Client;
import com.setupteam.buscaminas.connection.Server;
import com.setupteam.buscaminas.model.entity.Minesweeper;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class Constants implements MinesweeperConstants {
	public static Minesweeper minesweeper = new Minesweeper(EASY);
	public static Server server;
	public static Client client;
	public static String nickname = "Minesweeper";

	public static boolean isStarted = false;
	public static int rowCount;
	public static byte[][] field;

	public static String host = "localhost";
	public static int port = 3333;
	public static String prompt = "server :> ";

}
