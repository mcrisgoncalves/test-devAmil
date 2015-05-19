package br.com.projeto.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.projeto.exception.GameException;
import br.com.projeto.model.Event;
import br.com.projeto.model.Game;


public class GameHelper {
	
	protected transient Logger log = Logger.getLogger(GameHelper.class);
	
	private static String KILLED = "nmKilled";
	private static String KILLER = "nmKiller";
	private static String WEAPON = "nmWeapon";
	
	public StringBuilder montaRanking(List<Game> games) throws GameException {
		
		log.debug("montaRanking - INICIO ");
		StringBuilder sb = new StringBuilder();
		Set<String> mortes = null;
		Set<String> assassinatos = null;
		
		try {
		
			//Montar o ranking de cada partida, com a quantidade assassinatos e a quantidade de mortes de cada jogador
			int i = 1;
			
			for (Game jogo : games) {
				sb.append("== Jogo ").append(i).append(" ==").append("\n");
				
				int totAssassinatos = 0;
				for (Event evento : jogo.getEvents()) {
					if(evento.getNmKiller() != null) totAssassinatos++;
				}
				
				sb.append("Quantidade total de assassinatos: ").append(totAssassinatos).append("\n");
				sb.append("Quantidade de mortes por jogador ").append("\n");
				
				mortes = listarQuantidadePorEvento(jogo, KILLED);
				
				for (String nmKilled : mortes) {
					int quantidade = quatidade(jogo.getEvents(), nmKilled, KILLED);
					sb.append("Jogador ").append(nmKilled + ": " + quantidade).append(" morte(s)").append("\n");
				}
				
				sb.append("Quantidade de assassinatos por jogador ").append("\n");
				
				assassinatos = listarQuantidadePorEvento(jogo, KILLER);
				
				int qtdAssassinatos = 0;
				String vencedor = "";
				for (String nmKiller : assassinatos) {
					int quantidade = quatidade(jogo.getEvents(), nmKiller, KILLER);
					if(quantidade > qtdAssassinatos) {
						vencedor = nmKiller;
						qtdAssassinatos = quantidade;
					}
					sb.append("Jogador ").append(nmKiller + " matou " + quantidade).append(" vez(es)").append("\n");
				}
				
				sb.append("Vencedor: ").append(vencedor).append(" com ").append(qtdAssassinatos).append(" assassinatos.").append("\n");
				
				//Bonus: Descobrir a arma preferida (a que mais matou) do vencedor
				String arma = getArmaVencedor(jogo.getEvents(), vencedor);		
				sb.append("Arma preferida por ").append(vencedor).append(": ").append(arma).append("\n");
				
				//Bonus: Identificar a maior sequência de assassinatos efetuadas por um jogador sem morrer
				
				//Bonus: Jogadores que vencerem uma partida sem morrerem devem ganhar um "award"
				if(getVencedorAward(jogo.getEvents(), vencedor) == 0) {
					sb.append("Vencedor ").append(vencedor).append(" ganhou um AWARD por não morrer na partida.").append("\n");
				}
				
				//Bonus: Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award"
								
				sb.append("== Fim do Jogo ==").append("\n").append("\n");
				i++;
			}
	        
		} catch (Exception e) {
			log.error(e);
			throw new GameException(e);
		} 
		
		log.debug("montaRanking - FIM ");
		
		return sb;
	}
	
	public static Set<String> listarQuantidadePorEvento(Game game, String tipo) {
		List<Event> events = game.getEvents();
		Set<String> set = new HashSet<String>(); 
		for (Event event : events) {
			if(tipo.equals(KILLED)) {
				if(event.getNmKilled() != null) {
					set.add(event.getNmKilled());
				}
			}
			if(tipo.equals(KILLER)) {
				if(event.getNmKiller() != null) {
					set.add(event.getNmKiller());
				}
			}
		}
		return set;
	}
		
	private static int quatidade(List<Event> events, String nome, String tipo) {
		int quantidade = 0;
		for (Event event : events) {
			if(tipo.equals(KILLED)) {
				if(nome.equals(event.getNmKilled())) {
					quantidade++;
				}
			} else if(tipo.equals(KILLER)) {
				if(nome.equals(event.getNmKiller())) {
					quantidade++;
				}
			} else if(tipo.equals(WEAPON)) {
				if(nome.equals(event.getNmWeapon())) {
					quantidade++;
				}
			}
		}
		return quantidade;
	}

	private static String getArmaVencedor(List<Event> events, String vencedor) {
		
		Set<String> armas = new HashSet<String>();
		String armaUsada = ""; 
				
		for (Event event : events) {
			if(event.getNmKiller() != null && event.getNmKiller().equals(vencedor)) {
				armas.add(event.getNmWeapon());
			}
		}
		
		int qtdUso = 0;
		for (String arma : armas) {
			int quantidade = quatidade(events, arma, WEAPON);
			if(quantidade > qtdUso) {
				armaUsada = arma;
				qtdUso = quantidade;
			}
		}
			
		return armaUsada;
	}
	
	private static int getVencedorAward(List<Event> events, String vencedor) {
		
		int i = 0;
		
		for (Event event : events) {
			if(event.getNmKilled().equals(vencedor)) {
				i++;
			}
		}
			
		return i;
	}
	
}
