package br.com.projeto.test;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.projeto.exception.GameException;
import br.com.projeto.game.GameHelper;
import br.com.projeto.model.Event;
import br.com.projeto.model.Game;

public class GameHelperTest {

	@Test
	public void montaRanking() throws GameException {
		
		List<Game> games = new ArrayList<Game>();
		Game game = new Game();
		List<Event> events = new ArrayList<Event>();
		Event event = new Event();
		
		event.setDtEvent(new DateTime());
		event.setNmKilled("Joao");
		event.setNmKiller("Maria");
		event.setNmWeapon("R15");
		events.add(event);
		
		game.setCdGame(1L);
		game.setDtInicio(new DateTime());
		game.setDtFim(new DateTime());
		game.setEvents(events);
		
		games.add(game);
		
		new GameHelper().montaRanking(games);
		
		
	}
}
