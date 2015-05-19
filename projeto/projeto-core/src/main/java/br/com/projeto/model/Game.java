package br.com.projeto.model;

import java.util.List;

import org.joda.time.DateTime;

public class Game implements Comparable<Game> {
	
	public class QuantidadeEvent {
        private Event event;
        private int quantidade;
		public Event getEvent() {
			return event;
		}
		public void setEvent(Event event) {
			this.event = event;
		}
		public int getQuantidade() {
			return quantidade;
		}
		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}
	}
	
	private Long cdGame;
	private DateTime dtInicio;
	private DateTime dtFim;
	private List<Event> events;
	
	public Long getCdGame() {
		return cdGame;
	}
	public void setCdGame(Long cdGame) {
		this.cdGame = cdGame;
	}
	public DateTime getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(DateTime dtInicio) {
		this.dtInicio = dtInicio;
	}
	public DateTime getDtFim() {
		return dtFim;
	}
	public void setDtFim(DateTime dtFim) {
		this.dtFim = dtFim;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	@Override
	public int compareTo(Game o) {
		return dtInicio.compareTo(o.getDtInicio());
	}
			
}