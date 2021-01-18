package it.com.gab.webapp.utils;

public class UserCsv {

	public static final String[] COLUMNS = { "tessera", "datafine","sesso","cognome", "nome", "data",  "nazione", "provincia", "comune" };

	private String tessera;
	private String nome;
	private String cognome;
	private String data;
	private String comune;
	private String provincia;
	private String nazione;
	private String sesso;
	private String datafine;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getTessera() {
		return tessera;
	}

	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

	public String getDatafine() {
		return datafine;
	}

	public void setDatafine(String datafine) {
		this.datafine = datafine;
	}
	
	

}
