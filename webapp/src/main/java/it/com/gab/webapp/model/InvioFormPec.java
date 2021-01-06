package it.com.gab.webapp.model;

public class InvioFormPec {


	private String numPecDaInviare;
	private String numPecSel;
	
	
	public InvioFormPec() {
		
	}
	
	public InvioFormPec(String numPecDaInviare, String numPecSel) {
		this.numPecDaInviare = numPecDaInviare;
		this.numPecSel = numPecSel;
	}

	public String getNumPecDaInviare() {
		return numPecDaInviare;
	}

	public void setNumPecDaInviare(String numPecDaInviare) {
		this.numPecDaInviare = numPecDaInviare;
	}

	public String getNumPecSel() {
		return numPecSel;
	}

	public void setNumPecSel(String numPecSel) {
		this.numPecSel = numPecSel;
	}
	

	
}
