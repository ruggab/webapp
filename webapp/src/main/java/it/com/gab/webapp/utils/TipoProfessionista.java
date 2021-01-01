package it.com.gab.webapp.utils;

public enum TipoProfessionista {
	
	AVV_ORD_CASS("01","AVVOCATO CASSAZIONISTA", "CASS"),
	AVV_SPE_CASS("02","AVVOCATO SPECIALE CASSAZIONISTA", "CASS"),
	AVV_SPE("03","AVVOCATO SPECIALE", ""),
	AVV_ORD("04","AVVOCATO ORDINARIO", ""),
	AVV_STA("05","AVVOCATO STABILITO", ""),
	AVV_PRO("06","AVVOCATO PROFESSORE", ""),
	AVV_PAT("07","PRATICANTE ABILITATO", ""),
	AVV_SEN_PAT("08","PRATICANTE SEMPLICE",  "");
	
	
	private String descrizione; 
	private String tipoAlbo;
	private String idAlbo; 
	
	
	TipoProfessionista(String idAlbo, String tipoAlbo, String descrizione) {
		this.idAlbo = idAlbo;
		this.tipoAlbo = tipoAlbo;
		this.descrizione = descrizione;
    }


	public String getTipoAlbo() {
		return tipoAlbo;
	}


	public void setTipoAlbo(String tipoAlbo) {
		this.tipoAlbo = tipoAlbo;
	}


	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	public String getIdAlbo() {
		return idAlbo;
	}



	public void setIdAlbo(String idAlbo) {
		this.idAlbo = idAlbo;
	}
	

}
