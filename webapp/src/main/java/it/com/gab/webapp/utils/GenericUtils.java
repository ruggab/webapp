package it.com.gab.webapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.sun.el.parser.ParseException;

import it.com.gab.webapp.entity.Avvocato;
import it.com.gab.webapp.service.AvvocatoServiceImpl;
import it.present.daspoCore.utilities.HashBuilder;

public class GenericUtils {

	public static SimpleDateFormat sdf = new SimpleDateFormat();

	static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

	private static Logger logger = Logger.getLogger(AvvocatoServiceImpl.class);

	public static List<UserCsv> readUsers(InputStream stream) {
		try {

			InputStreamReader ireader = new InputStreamReader(stream, "UTF-8");
			CSVReader reader = new CSVReader(ireader, ';', '"', 1);
			ColumnPositionMappingStrategy<UserCsv> strat = new ColumnPositionMappingStrategy<UserCsv>();
			strat.setType(UserCsv.class);
			strat.setColumnMapping(UserCsv.COLUMNS);
			CsvToBean<UserCsv> csv = new CsvToBean<UserCsv>();
			List<UserCsv> list = csv.parse(strat, reader);

			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<AvvocatoCsv> readAvvocatos(InputStream stream) {
		try {

			InputStreamReader ireader = new InputStreamReader(stream, "UTF-8");
			CSVReader reader = new CSVReader(ireader, ';', '"', 1);
			ColumnPositionMappingStrategy<AvvocatoCsv> strat = new ColumnPositionMappingStrategy<AvvocatoCsv>();
			strat.setType(AvvocatoCsv.class);
			strat.setColumnMapping(AvvocatoCsv.AvvocatoCbill);
			CsvToBean<AvvocatoCsv> csv = new CsvToBean<AvvocatoCsv>();
			List<AvvocatoCsv> list = csv.parse(strat, reader);

			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Date fromStrigToDate(String s) throws Exception {
		Date date = null;
		try {
			if (StringUtils.isNotEmpty(s)) {
				sdf.applyPattern("dd/MM/yyyy");
				date = sdf.parse(s);
			}
		} catch (Exception e) {
			throw e;
		}
		return date;
	}

	public static void main(String[] args) {

		try {
			String fileNameIn = "C:\\Umberto\\fc_VRO_1530.csv";
			String fileNameOut = "C:\\Umberto\\anagraficaCodificata.csv";
			//
			FileInputStream fis = new FileInputStream(fileNameIn);
			List<UserCsv> listCsv = readUsers(fis);
			// HashBuilder.costruisciHash(cognome, nome, data, sesso,nazioneNascitaEstera, comune, provincia);
			File fileOut = new File(fileNameOut);
			FileWriter fr = new FileWriter(fileOut, true);
			fr.write("tessera;nome;cognome;data;sesso;nazione;provincia;comune;hash\n");
			for (Iterator iterator = listCsv.iterator(); iterator.hasNext();) {
				UserCsv schemaCsv = (UserCsv) iterator.next();
				//
				String riga = schemaCsv.getTessera() + ";";
				riga = riga + schemaCsv.getNome() + ";";
				riga = riga + schemaCsv.getCognome() + ";";
				riga = riga + schemaCsv.getData() + ";";
				riga = riga + schemaCsv.getSesso() + ";";
				riga = riga + schemaCsv.getNazione() + ";";
				riga = riga + schemaCsv.getProvincia() + ";";
				riga = riga + schemaCsv.getComune() + ";";
				if (StringUtils.isEmpty(schemaCsv.getComune()) && StringUtils.isEmpty(schemaCsv.getProvincia())) {
					riga = riga + HashBuilder.costruisciHash(schemaCsv.getCognome().toUpperCase(), schemaCsv.getNome().toUpperCase(), schemaCsv.getData().toUpperCase(), schemaCsv.getSesso().toUpperCase(), schemaCsv.getNazione().toUpperCase(), "", "");
				} else {
					riga = riga + HashBuilder.costruisciHash(schemaCsv.getCognome().toUpperCase(), schemaCsv.getNome().toUpperCase(), schemaCsv.getData().toUpperCase(), schemaCsv.getSesso().toUpperCase(), schemaCsv.getNazione().toUpperCase(), schemaCsv.getComune().toUpperCase(), schemaCsv.getProvincia().toUpperCase());
				}
				// Scrivi lo script per ogni record
				fr.write(riga + "\n");
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getIdAlbo(String tipoAlbo, String cassazionista) {
		String id = "";
		if (TipoProfessionista.AVV_ORD_CASS.getTipoAlbo().equals(tipoAlbo) || cassazionista.equalsIgnoreCase("Si")) {
			return TipoProfessionista.AVV_ORD_CASS.getIdAlbo();
		}
		if (TipoProfessionista.AVV_SPE_CASS.getTipoAlbo().equals(tipoAlbo) || cassazionista.equalsIgnoreCase("Si")) {
			return TipoProfessionista.AVV_SPE_CASS.getIdAlbo();
		}
		if (TipoProfessionista.AVV_ORD.getTipoAlbo().equals(tipoAlbo)) {
			return TipoProfessionista.AVV_ORD.getIdAlbo();
		}
		if (TipoProfessionista.AVV_PRO.getTipoAlbo().equals(tipoAlbo)) {
			return TipoProfessionista.AVV_PRO.getIdAlbo();
		}
		if (TipoProfessionista.AVV_SPE.getTipoAlbo().equals(tipoAlbo)) {
			return TipoProfessionista.AVV_SPE.getIdAlbo();
		}
		if (TipoProfessionista.AVV_STA.getTipoAlbo().equals(tipoAlbo)) {
			return TipoProfessionista.AVV_STA.getIdAlbo();
		}
		if (TipoProfessionista.AVV_PAT.getTipoAlbo().equals(tipoAlbo)) {
			return TipoProfessionista.AVV_PAT.getIdAlbo();
		}
		if (TipoProfessionista.AVV_SEN_PAT.getTipoAlbo().equals(tipoAlbo)) {
			return TipoProfessionista.AVV_SEN_PAT.getIdAlbo();
		}
		return id;
	}

	private final static String SIA_COD = "BEPJ3";

	public static BigInteger trasformaCodSia() {
		BigInteger ret = null;
		try {
			int lungString = SIA_COD.length();
			String ide = "";
			for (int i = 0; i < lungString; i++) {
				char charI = SIA_COD.charAt(i);
				if (Character.isLetter(charI)) {
					ide = ide + fromCaracterToNumber(charI);
				} else {
					ide = ide + charI;
				}
			}
			ret = new BigInteger(ide);
			logger.info("sInput: " + SIA_COD);
			logger.info("sInput: " + ide);
		} catch (RuntimeException e) {
			logger.error("sInput: " + SIA_COD);
			System.out.println("sInput: " + SIA_COD);
			throw e;
		}

		return ret;
	}

	private static int fromCaracterToNumber(Character letter) {
		int ret = -1;
		switch (letter) {
		case 'A': {
			ret = 0;
			break;
		}
		case 'B': {
			ret = 1;
			break;
		}
		case 'C': {
			ret = 2;
			break;
		}
		case 'D': {
			ret = 3;
			break;
		}
		case 'E': {
			ret = 4;
			break;
		}
		case 'F': {
			ret = 5;
			break;
		}
		case 'G': {
			ret = 6;
			break;
		}
		case 'H': {
			ret = 7;
			break;
		}
		case 'I': {
			ret = 8;
			break;
		}
		case 'J': {
			ret = 9;
			break;
		}
		case 'K': {
			ret = 0;
			break;
		}
		case 'L': {
			ret = 1;
			break;
		}
		case 'M': {
			ret = 2;
			break;
		}
		case 'N': {
			ret = 3;
			break;
		}
		case 'O': {
			ret = 4;
			break;
		}
		case 'P': {
			ret = 5;
			break;
		}
		case 'Q': {
			ret = 6;
			break;
		}
		case 'R': {
			ret = 7;
			break;
		}
		case 'S': {
			ret = 8;
			break;
		}
		case 'T': {
			ret = 9;
			break;
		}
		case 'U': {
			ret = 0;
			break;
		}
		case 'V': {
			ret = 1;
			break;
		}
		case 'W': {
			ret = 2;
			break;
		}
		case 'X': {
			ret = 3;
			break;
		}
		case 'Y': {
			ret = 4;
			break;
		}
		case 'Z': {
			ret = 5;
			break;
		}
		}
		return ret;
	}

	// 1 - Annuale 2 - Semestrale
	public final static String RATA_Annuale = "1";
	public final static String RATA_Prima = "2";
	public final static String RATA_Seconda = "3";

	public static AvvocatoCsv creaCBILLs(AvvocatoCsv schemaCsv, BigInteger codSia) {

		try {
			BigInteger identBollettaUnica = creaIdentificativoBolletta(schemaCsv, RATA_Annuale);
			BigInteger identBollettaPrima = creaIdentificativoBolletta(schemaCsv, RATA_Prima);
			BigInteger identBollettaSec = creaIdentificativoBolletta(schemaCsv, RATA_Seconda);

			String cbillUnica = creaCBILL(identBollettaUnica, codSia, schemaCsv.getQuotaUnica());
			String cbillPrimaRata = creaCBILL(identBollettaPrima, codSia, schemaCsv.getQuotaRata1());
			String cbillSecRata = creaCBILL(identBollettaSec, codSia, schemaCsv.getQuotaRata2());

			//
			schemaCsv.setCbillRataUnica(cbillUnica);
			schemaCsv.setCbillPrimaRata(cbillPrimaRata);
			schemaCsv.setCbillSecondaRata(cbillSecRata);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {

			throw e;
		}
		return schemaCsv;

	}
	
	
	public static String creaCBILL(BigInteger idBolletta, BigInteger codSia, String quota) {
		String cbill = null;
		if (quota.contains(",")) {
			quota = quota.replaceAll(",", "");
		}
		if (quota.length() == 2 || quota.length() == 3) {
			quota = quota + "00";
		}
		BigInteger importo = new BigInteger(quota);

		try {

			BigInteger summ = idBolletta.add(importo).add(codSia);
			// Divisione per 93
			BigDecimal intDiv = new BigDecimal(summ).divide(new BigDecimal(93), 0, BigDecimal.ROUND_DOWN);

			// Approssimazione per difetto
			BigDecimal bd = intDiv.multiply(new BigDecimal(93));
			//
			BigInteger cd = summ.subtract(bd.toBigInteger());
			String stringcd = cd.toString();
			if (cd.intValue() < 10) {
				stringcd = "0" + stringcd;
			}

			logger.info(idBolletta + " - " + bd + " = " + cd);

			cbill = idBolletta + stringcd;

		} catch (Exception e) {

			throw e;
		}

		return cbill;
	}
	
	public static BigInteger creaIdentificativoBolletta(AvvocatoCsv schemaCsv, String rata) throws ParseException {
		BigInteger ret = null;
		try {
			String idBolletta = "";
			// anno
			Calendar calendar = GregorianCalendar.getInstance();
			String anno = calendar.get(Calendar.YEAR) + "";
			// Albo
			String idAlbo = getIdAlbo(schemaCsv.getTipoAlbo(), schemaCsv.getCassazionista());
			// dd/MM/yyyy
			String annoNascitaLast2Char = schemaCsv.getAnno().substring(2, schemaCsv.getAnno().length());
			String meseNascita = String.format("%02d", new Integer(schemaCsv.getMese()));
			String iduniprof = String.format("%05d", new Integer(schemaCsv.getProgressivo()));
			//
			idBolletta = anno + rata + idAlbo + iduniprof + annoNascitaLast2Char + meseNascita;
			//
			ret = new BigInteger(idBolletta);
		} catch (RuntimeException e) {

			throw e;
		}

		return ret;
	}

	
	
	public static String[] buildMessage2020Pec(Avvocato avvocatoCbill) {

		
		StringBuffer message = new StringBuffer();
		message.append("<img src=\'cid:image1\'>");
		//message.append("</br>");
		message.append("<div align='left'>");
		//message.append("<p class='MsoNormal'>Chiar.mo/a Signor/a</p>");
		message.append("<p class='MsoNormal'>Caro/a Collega,</p>");
		message.append("<p class='MsoNormal'><em>" + avvocatoCbill.getNome() + " " + avvocatoCbill.getCognome() + "</em></p>");
		message.append("</div>");
		message.append("<br/>");
		//message.append("<p class='MsoNormal'>Caro/a Collega,</p>");
		
		message.append("<p class='MsoNormal'>anche per il 2020 la quota annuale sarà corrisposta utilizzando il circuito CBILL. ");
		message.append("Questo sistema semplice e moderno permette di pagare in molteplici modi: on line per chi utilizza l’home banking, ");
		message.append("presso gli sportelli del proprio istituto di credito, presso gli ATM (bancomat) ed infine, anche presso le tabaccherie ");
		message.append("dotate di terminale ITB. </p>");
		
		message.append("<p class='MsoNormal'>Consapevoli delle difficoltà economiche di quest’anno legate alla pandemia da ");
		message.append("covid 19, abbiamo previsto che il pagamento potrà essere effettuato o in una unica ");
		message.append("soluzione entro il 31.12.2020 oppure in due rate di pari importo di cui una con ");
		message.append("scadenza 31.12.2020 e una con scadenza 28.2.2021. Nella sottostante tabella, nel ");
		message.append("primo rigo della prima colonna è indicato il codice cbill relativo al pagamento da ");
		message.append("effettuarsi in una unica rata; nella terza colonna è indicato il relativo importo; nella ");
		message.append("quarta colonna è indicata la data di scadenza. Nel secondo e nel terzo rigo sono ");
		message.append("indicati i codici della prima e della seconda rata con i relativi importi e le relative scadenze. </p>");
		
	
		message.append("<p class='MsoNormal'>Aderiscono al circuito Cbill la maggior parte degli istituti di credito (Poste Italiane al momento non ancora) ");
		message.append("e per pagare basta indicare il codice azienda (BEPJ3) ovvero selezionare la dicitura “ordine avvocati di Santa Maria Capua Vetere”, ");
		message.append("digitare il proprio Codice CBILL composto di 18 cifre ed indicare l’importo dovuto (chi paga presso le tabaccherie dotate di terminale ");
		message.append("ITB dovrà fornire altresì il proprio codice fiscale). </p>");  
		
		message.append("<p class='MsoNormal'>Il tutto è presente nel riquadro sottostante: </p>");  
		
		message.append("    <table class='MsoNormalTable' border='0' cellspacing='0' cellpadding='0' width='600'>");
		message.append("       <tbody>");
		message.append("            <tr>");
		message.append("               <td width='600' nowrap='nowrap' colspan='4' valign='bottom' style='border:solid 1pt;'>");
		message.append("                    <p class='MsoNormal' align='center'><img src=\'cid:image2\'></p>");
		message.append("                    <p class='MsoNormal' align='center'>Codice Sia:<Strong>BEPJ3 - ‘ordine avvocati di Santa Maria Capua Vetere’ </Strong></p>");
		message.append("                    <p class='MsoNormal' align='center'>Codice Fiscale: " + avvocatoCbill.getCodiceFiscale() + " </p>");	
		message.append("                    <p class='MsoNormal' align='center'>   </p>");		
		message.append("               </td>");
		message.append("            </tr>");
		message.append("           <tr>");
		message.append("               <td width='150' nowrap='nowrap' style='border:solid 1pt;background:#D9E1F2'>");
		message.append("                    <p class='MsoNormal' align='center'><Strong>Bolletta Cbill</Strong></p>");
		message.append("               </td>");
		message.append("               <td width='150' nowrap='nowrap' style='border:solid 1pt;background:#D9E1F2'>");
		message.append("                    <p class='MsoNormal' align='center'><Strong>Tipologia</span></p>");
		message.append("               </td>");	
		message.append("               <td width='150' nowrap='nowrap' style='border:solid 1pt;background:#D9E1F2'>");
		message.append("                   <p class='MsoNormal' align='center'><Strong>Importo</Strong></p>");
		message.append("              </td>");
		message.append("               <td width='150' nowrap='nowrap' style='border:solid 1pt;background:#D9E1F2'>");
		message.append("                    <p class='MsoNormal' align='center'><Strong>Scadenza</Strong></p>");
		message.append("               </td>");
		message.append("           </tr>");
		
		
		message.append("           <tr>");
		message.append("                <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='center' style='text-align:right'><span style='color:#000000'>" + avvocatoCbill.getCbillUnica() + "</span></p>");
		message.append("               </td>");
		message.append("                <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='center' style='text-align:right'><span style='color:#000000'>Rata Unica</span></p>");
		message.append("               </td>");
		message.append("               <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='center' style='text-align:right'><span style='color:#000000'>" + avvocatoCbill.getImportoNumber(avvocatoCbill.getQuotaUnica()) + "</span></p>");
		message.append("               </td>");
		message.append("               <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='center' style='text-align:right'><span style='color:#000000'>31/12/2020</span></p>");
		message.append("               </td>");
		message.append("            </tr>");
		
		
		message.append("           <tr>");
		message.append("                <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='right' style='text-align:right'><span style='color:#000000'>" + avvocatoCbill.getCbillRata1() + "</span></p>");
		message.append("               </td>");
		message.append("                <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='right' style='text-align:right'><span style='color:#000000'>Prima Rata</span></p>");
		message.append("               </td>");
		message.append("               <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='right' style='text-align:right'><span style='color:#000000'>" + avvocatoCbill.getImportoNumber(avvocatoCbill.getQuotaRata1()) + "</span></p>");
		message.append("               </td>");
		message.append("               <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='right' style='text-align:right'><span style='color:#000000'>31/12/2020</span></p>");
		message.append("               </td>");
		message.append("            </tr>");
		
		message.append("           <tr>");
		message.append("                <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='right' style='text-align:right'><span style='color:#000000'>" + avvocatoCbill.getCbillRata2() + "</span></p>");
		message.append("               </td>");
		message.append("                <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='right' style='text-align:right'><span style='color:#000000'>Seconda Rata</span></p>");
		message.append("               </td>");
		message.append("               <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='right' style='text-align:right'><span style='color:#000000'>" + avvocatoCbill.getImportoNumber(avvocatoCbill.getQuotaRata2()) + "</span></p>");
		message.append("               </td>");
		message.append("               <td width='150' nowrap='nowrap' valign='bottom' style='border:solid 1pt'>");
		message.append("                   <p class='MsoNormal' align='right' style='text-align:right'><span style='color:#000000'>28/02/2021</span></p>");
		message.append("               </td>");
		message.append("            </tr>");
		message.append("       </tbody>");
		message.append("   </table>");
		message.append("<br/>");
		
		message.append("<p class='MsoNormal'>Ti rammentiamo che se non hai pagato le quote degli anni precedenti, per evitare le conseguenze  ");
		message.append("disciplinari previste e per evitare esecuzioni con aggravi di spesa, devi provvedere quanto prima. In caso di morosità, il pagamento va effettuato per gli anni ");
		message.append("fino al 2016 solo ed esclusivamente presso l’Agenzia delle Entrate Riscossione; per gli anni 2017, 2018 e 2019 va utilizzato, ");
		message.append("come per l’anno in corso, il circuito Cbill.  </p>");
		
		message.append("<p class='MsoNormal'>Cordiali Saluti </p> ");
		message.append("<p class='MsoNormal'>Il Presidente   </p>  ");   
		message.append("<p class='MsoNormal'>Avv. Adolfo Russo</p>  "); 
		message.append("<br/>");
		message.append("<p class='MsoNormal'>Il Tesoriere</p>");
		message.append("<p class='MsoNormal'>Avv. Annamaria Sadutto</p>");

		
		message.append("<p class='MsoNormal'><strong>N.B. La presente e-mail è stata generata automaticamente da un indirizzo di posta elettronica di ");
		message.append("solo invio; si chiede pertanto di non rispondere al messaggio. Per ogni informazione rivolgersi alla Segreteria dell'Ordine.</strong></p>");
		
		
		message.append("<p class='MsoNormal'>Per contatti e assistenza: www.ordineavvocatismcv.it - mail:info@ordineavvocatismcv.it – PEC: segreteria@avvocatismcv.it</p>");
		String[] message2 = new String[1];
		message2[0] = message.toString();
		return message2;
		
		
		
	}


}
