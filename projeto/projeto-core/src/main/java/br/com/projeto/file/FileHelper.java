package br.com.projeto.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import br.com.projeto.exception.FileReadException;
import br.com.projeto.model.Event;
import br.com.projeto.model.Game;

public class FileHelper {
	
	protected  transient Logger log = Logger.getLogger(FileHelper.class);

	public static String FILE_PATH = "/META-INF/jogo.log";
	
	public static String INICIO = "New";
	public static String FIM = "ended";
	public static String EVENTO = "killed";
	public static String ARMA = "using";
	public static String WORLD = "<WORLD>";
	
	public static int DATA = 0;
	public static int HORA = 1;
	public static int SEPARADOR = 2;
	public static int KILLER = 3;
	public static int KILLED = 5;
	public static int WEAPON = 7;
	public static int COD_GAME = 5;
	
	public List<Game> readFile() throws FileReadException {
		
		log.debug("readFile - INICIO ");
		BufferedReader bufferedReader = null;
		ArrayList<Game> games = null;
		//GameHelper gameHelper = new GameHelper();
		Game game = null;
		
		try {
		
			bufferedReader = new BufferedReader(new FileReader(getClass().getResource(FILE_PATH).getPath()));
			String s = "";
			games = new ArrayList<Game>();
						
		    while ((s = bufferedReader.readLine()) != null) {
		    	if(s.contains(INICIO)) {
		    		//Vai ser um novo jogo
		    		String[] temp = s.split(" ");
		    		String data = temp[DATA] + " " + temp[HORA];
			    	game = new Game();
			    	game.setDtInicio(DateTime.parse(data,DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss")));
			    	game.setCdGame(new Long(temp[COD_GAME]));
			    	game.setEvents(new ArrayList<Event>());			    	
		    	} else if(s.contains(FIM)) {
		    		//Vai ser um fim de jogo
		    		String[] temp = s.split(" ");
		    		String data = temp[DATA] + " " + temp[HORA];
		    		game.setDtFim(DateTime.parse(data,DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss")));
		    		games.add(game);
		    	} else {
		    		//Vai ser um evento
		    		String[] temp = s.split(" ");
		    		String data = temp[DATA] + " " + temp[HORA];
		    		Event event = new Event();
		    		event.setDtEvent(DateTime.parse(data,DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss")));
		    		event.setNmKilled(temp[KILLED]);
		    		if(!temp[KILLER].equals(WORLD)) {
		    			event.setNmKiller(temp[KILLER]);
		    			event.setNmWeapon(temp[WEAPON]);
		    		}
		    		game.getEvents().add(event);
		    	}
		    	
		    }
		    
		    Collections.sort(games);
		    
		} catch (Exception e) {
			log.error(e);
			throw new FileReadException(e);
		} finally {
			IOUtils.closeQuietly(bufferedReader);
		}
		
		log.debug("readFile - FIM ");
		
		return games;
		
	}
}
