package br.com.projeto.model;

import org.joda.time.DateTime;

public class Event implements Comparable<Event> {
	
	private String nmKiller;
	private String nmKilled;
	private String nmWeapon;
	private DateTime dtEvent;
	
	public String getNmKiller() {
		return nmKiller;
	}
	public void setNmKiller(String nmKiller) {
		this.nmKiller = nmKiller;
	}
	public String getNmKilled() {
		return nmKilled;
	}
	public void setNmKilled(String nmKilled) {
		this.nmKilled = nmKilled;
	}
	public String getNmWeapon() {
		return nmWeapon;
	}
	public void setNmWeapon(String nmWeapon) {
		this.nmWeapon = nmWeapon;
	}
	public DateTime getDtEvent() {
		return dtEvent;
	}
	public void setDtEvent(DateTime dtEvent) {
		this.dtEvent = dtEvent;
	}
	
	@Override
	public int compareTo(Event o) {
		return dtEvent.compareTo(o.getDtEvent());
	}
	
}