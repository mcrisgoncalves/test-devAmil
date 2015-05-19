package br.com.projeto.main;

import java.util.List;

import br.com.projeto.exception.FileReadException;
import br.com.projeto.exception.GameException;
import br.com.projeto.file.FileHelper;
import br.com.projeto.game.GameHelper;
import br.com.projeto.model.Game;

public class Principal {

	public static void main(String[] args) {

		FileHelper fileHelper = new FileHelper();
		GameHelper gameHelper = new GameHelper();
		List<Game> games = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			games = fileHelper.readFile();
			
			//Montar o ranking de cada partida, com a quantidade assassinatos e a quantidade de mortes de cada jogador
			sb = gameHelper.montaRanking(games);
			System.out.println(sb.toString());
			
		} catch (FileReadException e) {
			e.printStackTrace();
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
}
