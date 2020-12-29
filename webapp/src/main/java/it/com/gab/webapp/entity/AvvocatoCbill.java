package it.com.gab.webapp.entity;

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
@Table(name = "avvocato_cbill")

public class AvvocatoCbill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "id_uni_prof", length = 10)
	private String idUniProf;

	@Column(name = "cod_tipo_avvocato", length = 10)
	private String codTipoAvvocato;

	@Column(name = "tipo_avvocato", length = 10)
	private String tipoAvvocato;

	@Column(name = "cassazionista", length = 10)
	private String cassazionista;

	@Column(name = "cognome", length = 50)
	private String cognome;

	@Column(name = "nome", length = 50)
	private String nome;

	@Column(name = "codice_fiscale", length = 16)
	private String codiceFiscale;

	@Column(name = "comune", length = 50)
	private String comune;

	@Column(name = "provincia", length = 2)
	private String provincia;

	@Column(name = "indirizzo", length = 100)
	private String indirizzo;

	@Column(name = "cap", length = 5)
	private String cap;

	@Column(name = "mail", length = 100)
	private String mail;

	@Column(name = "pec", length = 100)
	private String pec;

	@Column(name = "giorno", length = 100)
	private String giorno;

	@Column(name = "mese", length = 100)
	private String mese;

	@Column(name = "anno", length = 100)
	private String anno;

	@Column(name = "cbill_unica", length = 30)
	private String cbillUnica;

	@Column(name = "quota_unica", length = 30)
	private String quotaUnica;

	@Column(name = "cbill_rata_1", length = 30)
	private String cbillRata1;

	@Column(name = "quota_rata_1", length = 30)
	private String quotaRata1;

	@Column(name = "cbill_rata_2", length = 30)
	private String cbillRata2;

	@Column(name = "quota_rata_2", length = 30)
	private String quotaRata2;

	@Column(name = "data_pag_unica")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataPagUnica;

	@Column(name = "data_pag_1")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataPag1;

	@Column(name = "data_pag_2")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataPag2;

	@Column(name = "data_invio_mail")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataInvioMail;

	@Column(name = "data_invio_pec")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date dataInvioPec;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdUniProf() {
		return idUniProf;
	}

	public void setIdUniProf(String idUniProf) {
		this.idUniProf = idUniProf;
	}

	public String getCodTipoAvvocato() {
		return codTipoAvvocato;
	}

	public void setCodTipoAvvocato(String codTipoAvvocato) {
		this.codTipoAvvocato = codTipoAvvocato;
	}

	public String getTipoAvvocato() {
		return tipoAvvocato;
	}

	public void setTipoAvvocato(String tipoAvvocato) {
		this.tipoAvvocato = tipoAvvocato;
	}

	public String getCassazionista() {
		return cassazionista;
	}

	public void setCassazionista(String cassazionista) {
		this.cassazionista = cassazionista;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public String getMese() {
		return mese;
	}

	public void setMese(String mese) {
		this.mese = mese;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getCbillUnica() {
		return cbillUnica;
	}

	public void setCbillUnica(String cbillUnica) {
		this.cbillUnica = cbillUnica;
	}

	public String getQuotaUnica() {
		return quotaUnica;
	}

	public void setQuotaUnica(String quotaUnica) {
		this.quotaUnica = quotaUnica;
	}

	public String getCbillRata1() {
		return cbillRata1;
	}

	public void setCbillRata1(String cbillRata1) {
		this.cbillRata1 = cbillRata1;
	}

	public String getQuotaRata1() {
		return quotaRata1;
	}

	public void setQuotaRata1(String quotaRata1) {
		this.quotaRata1 = quotaRata1;
	}

	public String getCbillRata2() {
		return cbillRata2;
	}

	public void setCbillRata2(String cbillRata2) {
		this.cbillRata2 = cbillRata2;
	}

	public String getQuotaRata2() {
		return quotaRata2;
	}

	public void setQuotaRata2(String quotaRata2) {
		this.quotaRata2 = quotaRata2;
	}

	public Date getDataPagUnica() {
		return dataPagUnica;
	}

	public void setDataPagUnica(Date dataPagUnica) {
		this.dataPagUnica = dataPagUnica;
	}

	public Date getDataPag1() {
		return dataPag1;
	}

	public void setDataPag1(Date dataPag1) {
		this.dataPag1 = dataPag1;
	}

	public Date getDataPag2() {
		return dataPag2;
	}

	public void setDataPag2(Date dataPag2) {
		this.dataPag2 = dataPag2;
	}

	public Date getDataInvioMail() {
		return dataInvioMail;
	}

	public void setDataInvioMail(Date dataInvioMail) {
		this.dataInvioMail = dataInvioMail;
	}

	public Date getDataInvioPec() {
		return dataInvioPec;
	}

	public void setDataInvioPec(Date dataInvioPec) {
		this.dataInvioPec = dataInvioPec;
	}

	public String getImportoNumber(String importo) {
		String ret = "";
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
		String ret = "";
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

	// public String getTotale() {
	// BigDecimal bd = new BigDecimal(getImportoNumber(imp2001)).add(new BigDecimal(getImportoNumber(imp2002))).add(new
	// BigDecimal(getImportoNumber(imp2003))).add(new BigDecimal(getImportoNumber(imp2004))).add(new
	// BigDecimal(getImportoNumber(imp2005))).add(new BigDecimal(getImportoNumber(imp2006))).add(new
	// BigDecimal(getImportoNumber(imp2007))).add(new BigDecimal(getImportoNumber(imp2008)))
	// .add(new BigDecimal(getImportoNumber(imp2009))).add(new BigDecimal(getImportoNumber(imp2010))).add(new
	// BigDecimal(getImportoNumber(imp2011))).add(new BigDecimal(getImportoNumber(imp2012))).add(new
	// BigDecimal(getImportoNumber(imp2013))).add(new BigDecimal(getImportoNumber(imp2014))).add(new
	// BigDecimal(getImportoNumber(imp2015))).add(new BigDecimal(getImportoNumber(imp2016)))
	// .add(new BigDecimal(getImportoNumber(imp2017))).add(new BigDecimal(getImportoNumber(imp2018))).add(new
	// BigDecimal(getImportoNumber(imp2019)));
	// String bdString = bd.toString().replace(".", ",");
	//
	// return bdString;
	// }

}
