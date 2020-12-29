package it.com.gab.webapp.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "avvocato_morosita")

public class AvvocatoMorosita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "denominazione", length = 250)
	private String denominazione;

	@Column(name = "codice_fiscale", length = 16)
	private String codiceFiscale;

	@Column(name = "pec", length = 100)
	private String pec;

	@Column(name = "mail", length = 100)
	private String mail;

	@Column(name = "data_invio_pec")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataInvioPec;

	@Column(name = "data_invio_mail")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataInvioMail;

	@Column(name = "imp2001", length = 10)
	private String imp2001;

	@Column(name = "imp2002", length = 10)
	private String imp2002;

	@Column(name = "imp2003", length = 10)
	private String imp2003;

	@Column(name = "imp2004", length = 10)
	private String imp2004;

	@Column(name = "imp2005", length = 10)
	private String imp2005;

	@Column(name = "imp2006", length = 10)
	private String imp2006;

	@Column(name = "imp2007", length = 10)
	private String imp2007;

	@Column(name = "imp2008", length = 10)
	private String imp2008;

	@Column(name = "imp2009", length = 10)
	private String imp2009;

	@Column(name = "imp2010", length = 10)
	private String imp2010;

	@Column(name = "imp2011", length = 10)
	private String imp2011;

	@Column(name = "imp2012", length = 10)
	private String imp2012;

	@Column(name = "imp2013", length = 10)
	private String imp2013;

	@Column(name = "imp2014", length = 10)
	private String imp2014;

	@Column(name = "imp2015", length = 10)
	private String imp2015;

	@Column(name = "imp2016", length = 10)
	private String imp2016;

	@Column(name = "imp2017", length = 10)
	private String imp2017;

	@Column(name = "imp2018", length = 10)
	private String imp2018;

	@Column(name = "imp2019", length = 10)
	private String imp2019;

	@Column(name = "cbill2017", length = 45)
	private String cbill2017;

	@Column(name = "cbill2018", length = 45)
	private String cbill2018;

	@Column(name = "cbill2019", length = 45)
	private String cbill2019;
	
	@Column(name = "indirizzo", length = 100)
	private String indirizzo;
	
	@Column(name = "cap", length = 5)
	private String cap;
	
	@Column(name = "citta", length = 50)
	private String citta;
	
	@Column(name = "provincia", length = 2)
	private String provincia;

	public Date getDataInvioPec() {
		return dataInvioPec;
	}

	public void setDataInvioPec(Date dataInvioPec) {
		this.dataInvioPec = dataInvioPec;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDataInvioMail() {
		return dataInvioMail;
	}

	public void setDataInvioMail(Date dataInvioMail) {
		this.dataInvioMail = dataInvioMail;
	}

	public String getImp2001() {
		return imp2001;
	}

	public void setImp2001(String imp2001) {
		this.imp2001 = imp2001;
	}

	public String getImp2002() {
		return imp2002;
	}

	public void setImp2002(String imp2002) {
		this.imp2002 = imp2002;
	}

	public String getImp2003() {
		return imp2003;
	}

	public void setImp2003(String imp2003) {
		this.imp2003 = imp2003;
	}

	public String getImp2004() {
		return imp2004;
	}

	public void setImp2004(String imp2004) {
		this.imp2004 = imp2004;
	}

	public String getImp2005() {
		return imp2005;
	}

	public void setImp2005(String imp2005) {
		this.imp2005 = imp2005;
	}

	public String getImp2006() {
		return imp2006;
	}

	public void setImp2006(String imp2006) {
		this.imp2006 = imp2006;
	}

	public String getImp2007() {
		return imp2007;
	}

	public void setImp2007(String imp2007) {
		this.imp2007 = imp2007;
	}

	public String getImp2008() {
		return imp2008;
	}

	public void setImp2008(String imp2008) {
		this.imp2008 = imp2008;
	}

	public String getImp2009() {
		return imp2009;
	}

	public void setImp2009(String imp2009) {
		this.imp2009 = imp2009;
	}

	public String getImp2010() {
		return imp2010;
	}

	public void setImp2010(String imp2010) {
		this.imp2010 = imp2010;
	}

	public String getImp2011() {
		return imp2011;
	}

	public void setImp2011(String imp2011) {
		this.imp2011 = imp2011;
	}

	public String getImp2012() {
		return imp2012;
	}

	public void setImp2012(String imp2012) {
		this.imp2012 = imp2012;
	}

	public String getImp2013() {
		return imp2013;
	}

	public void setImp2013(String imp2013) {
		this.imp2013 = imp2013;
	}

	public String getImp2014() {
		return imp2014;
	}

	public void setImp2014(String imp2014) {
		this.imp2014 = imp2014;
	}

	public String getImp2015() {
		return imp2015;
	}

	public void setImp2015(String imp2015) {
		this.imp2015 = imp2015;
	}

	public String getImp2016() {
		return imp2016;
	}

	public void setImp2016(String imp2016) {
		this.imp2016 = imp2016;
	}

	public String getImp2017() {
		return imp2017;
	}

	public void setImp2017(String imp2017) {
		this.imp2017 = imp2017;
	}

	public String getImp2018() {
		return imp2018;
	}

	public void setImp2018(String imp2018) {
		this.imp2018 = imp2018;
	}

	public String getImp2019() {
		return imp2019;
	}

	public void setImp2019(String imp2019) {
		this.imp2019 = imp2019;
	}

	public String getCbill2017() {
		return cbill2017;
	}

	public void setCbill2017(String cbill2017) {
		this.cbill2017 = cbill2017;
	}

	public String getCbill2018() {
		return cbill2018;
	}

	public void setCbill2018(String cbill2018) {
		this.cbill2018 = cbill2018;
	}

	public String getCbill2019() {
		return cbill2019;
	}

	public void setCbill2019(String cbill2019) {
		this.cbill2019 = cbill2019;
	}
	
	
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getImportoNumber(String importo) {
		String ret =  "";
		String imp = importo.trim();
		if (StringUtils.isNotEmpty(imp)) {
			if (importo.contains(",")) {
				importo = importo.replace(",", ".");
				ret = new StringBuilder(importo).toString();
			} 
			if (!importo.contains(".")) { 
				ret = new StringBuilder(importo).append(".00").toString();
			} else {
				ret = importo;
			}
		} else {
			ret = "0.00";
		}
		return ret;
	}
	
	public String getImportoNumberFormat(String importo) {
		String ret =  "";
		if (StringUtils.isNotEmpty(importo)) {
			if (importo.contains(",")) {
				importo = importo.replace(",", ".");
				ret = new StringBuilder(importo).toString();
			} 
			if (!importo.contains(".")) { 
				ret = new StringBuilder(importo).append(".00").toString();
			} else {
				ret = importo;
			}
		} else {
			ret = "0.00";
		}
		return ret;
	}

	public String getTotale() {
		BigDecimal bd = new BigDecimal(getImportoNumber(imp2001)).add(new BigDecimal(getImportoNumber(imp2002))).add(new BigDecimal(getImportoNumber(imp2003))).add(new BigDecimal(getImportoNumber(imp2004))).add(new BigDecimal(getImportoNumber(imp2005))).add(new BigDecimal(getImportoNumber(imp2006))).add(new BigDecimal(getImportoNumber(imp2007))).add(new BigDecimal(getImportoNumber(imp2008)))
				.add(new BigDecimal(getImportoNumber(imp2009))).add(new BigDecimal(getImportoNumber(imp2010))).add(new BigDecimal(getImportoNumber(imp2011))).add(new BigDecimal(getImportoNumber(imp2012))).add(new BigDecimal(getImportoNumber(imp2013))).add(new BigDecimal(getImportoNumber(imp2014))).add(new BigDecimal(getImportoNumber(imp2015))).add(new BigDecimal(getImportoNumber(imp2016)))
				.add(new BigDecimal(getImportoNumber(imp2017))).add(new BigDecimal(getImportoNumber(imp2018))).add(new BigDecimal(getImportoNumber(imp2019)));
		String bdString = bd.toString().replace(".", ",");

		return bdString;
	}

}
