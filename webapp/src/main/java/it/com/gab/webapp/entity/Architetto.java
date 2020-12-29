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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "architetto")

public class Architetto {

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

    @Column(name = "matricola", length = 45)
    private String matricola;
    
    @Column(name = "nome", length = 45)
    private String nome;

    @Column(name = "codice_fiscale", length = 16)
    private String codiceFiscale;

    @Column(name = "anno1", length = 4)
    private String anno1;
    
    @Column(name = "anno2", length = 4)
    private String anno2;
    
    @Column(name = "anno3", length = 4)
    private String anno3;
    
    @Column(name = "anno4", length = 4)
    private String anno4;
    
    @Column(name = "pec", length = 100)
    private String pec;
    
    @Column(name = "mail", length = 100)
    private String mail;
    
    @Column(name = "telefono1", length = 100)
    private String telefono1;
    
    @Column(name = "telefono2", length = 100)
    private String telefono2;
    
    @Column(name = "data_invio_pec")
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dataInvioPec; 
  
    @Column(name = "data_invio_mail")
   	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dataInvioMail; 
    
    
    @Column(name = "importo1", length = 10)
    private String importo1;
    
    @Column(name = "importo2", length = 10)
    private String importo2;
    
    @Column(name = "importo3", length = 10)
    private String importo3;
    
    @Column(name = "importo4", length = 10)
    private String importo4;
 

    @Column(name = "data_invio_tel1")
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dataInvioTel1; 
  
    @Column(name = "data_invio_tel2")
   	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dataInvioTel2; 
    
    


	public Date getDataInvioPec() {
		return dataInvioPec;
	}

	public void setDataInvioPec(Date dataInvioPec) {
		this.dataInvioPec = dataInvioPec;
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

   
    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

  

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getAnno1() {
		return anno1;
	}

	public void setAnno1(String anno1) {
		this.anno1 = anno1;
	}

	public String getAnno2() {
		return anno2;
	}

	public void setAnno2(String anno2) {
		this.anno2 = anno2;
	}

	public String getAnno3() {
		return anno3;
	}

	public void setAnno3(String anno3) {
		this.anno3 = anno3;
	}

	public String getAnno4() {
		return anno4;
	}

	public void setAnno4(String anno4) {
		this.anno4 = anno4;
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

	public String getImporto1() {
		return importo1;
	}

	public void setImporto1(String importo1) {
		this.importo1 = importo1;
	}

	public String getImporto2() {
		return importo2;
	}

	public void setImporto2(String importo2) {
		this.importo2 = importo2;
	}

	public String getImporto3() {
		return importo3;
	}

	public void setImporto3(String importo3) {
		this.importo3 = importo3;
	}

	public String getImporto4() {
		return importo4;
	}

	public void setImporto4(String importo4) {
		this.importo4 = importo4;
	}
	
	public String getImporto1Number() {
		String ret =  "";
		if (importo1.contains(",")) {
			importo1 = importo1.replace(",", ".");
			ret = new StringBuilder(importo1).toString();
			
		} else {
			ret = new StringBuilder(importo1).append(".00").toString();
		}
		return ret;
	}

	public String getImporto2Number() {
		String ret =  "";
		if (importo2.contains(",")) {
			importo2 = importo2.replace(",", ".");
			ret = new StringBuilder(importo2).toString();
		} else {
			ret = new StringBuilder(importo2).append(".00").toString();
		}
		return ret;
	}

	public String getImporto3Number() {
		String ret =  "";
		if (importo3.contains(",")) {
			importo3 = importo3.replace(",", ".");
			ret =  new StringBuilder(importo3).toString();
		} else {
			ret =  new StringBuilder(importo3).append(".00").toString();
		}
		return ret;
	}

	public String getImporto4Number() {
		String ret =  "";
		if (importo4.contains(",")) {
			importo4 = importo4.replace(",", ".");
			ret = new StringBuilder(importo4).toString();
		} else {
			ret = new StringBuilder(importo4).append(".00").toString();
		}
		return ret;
	}
	

	public String getImporto1Format() {
		if (importo1.contains(",")) {
			return new StringBuilder(importo1).toString();
		} else {
			return new StringBuilder(importo1).append(",00").toString();
		}
		
	}

	public String getImporto2Format() {
		if (importo2.contains(",")) {
			return new StringBuilder(importo2).toString();
		} else {
			return new StringBuilder(importo2).append(",00").toString();
		}
	}

	public String getImporto3Format() {
		if (importo3.contains(",")) {
			return new StringBuilder(importo3).toString();
		} else {
			return new StringBuilder(importo3).append(",00").toString();
		}
	}

	public String getImporto4Format() {
		if (importo4.contains(",")) {
			return new StringBuilder(importo4).toString();
		} else {
			return new StringBuilder(importo4).append(",00").toString();
		}
	}

	
	
	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public Date getDataInvioTel1() {
		return dataInvioTel1;
	}

	public void setDataInvioTel1(Date dataInvioTel1) {
		this.dataInvioTel1 = dataInvioTel1;
	}

	public Date getDataInvioTel2() {
		return dataInvioTel2;
	}

	public void setDataInvioTel2(Date dataInvioTel2) {
		this.dataInvioTel2 = dataInvioTel2;
	}

	public String getTotale() {
		BigDecimal bd =new BigDecimal(getImporto1Number()).add(new BigDecimal(getImporto2Number())).add(new BigDecimal(getImporto3Number())).add(new BigDecimal(getImporto4Number()));
		String bdString = bd.toString().replace(".", ",");
		
		return bdString;
	}

	
}
