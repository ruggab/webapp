package it.com.gab.webapp.model;

public class InvioFormMail {


	private String numMailDaInviare;
	private String numMailSel;
	
	
	public InvioFormMail() {
		
	}
	
	public InvioFormMail(String numMailDaInviare, String numMailSel) {
		this.numMailDaInviare = numMailDaInviare;
		this.numMailSel = numMailSel;
	}

	public String getNumPecDaInviare() {
		return numMailDaInviare;
	}

	public void setNumPecDaInviare(String numMailDaInviare) {
		this.numMailDaInviare = numMailDaInviare;
	}

	public String getNumMailSel() {
		return numMailSel;
	}

	public void setNumMailSel(String numPecSel) {
		this.numMailSel = numPecSel;
	}
	

	
}
