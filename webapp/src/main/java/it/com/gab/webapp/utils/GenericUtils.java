package it.com.gab.webapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import it.present.daspoCore.utilities.HashBuilder;

public class GenericUtils {

	public static SimpleDateFormat sdf = new SimpleDateFormat();

	static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
	
	
	
	
	

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
				//Scrivi lo script per ogni record
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
		if (TipoProfessionista.AVV_ORD_CASS.getTipoAlbo().equals(tipoAlbo) && cassazionista.equalsIgnoreCase("Si")) {
			return TipoProfessionista.AVV_ORD_CASS.getIdAlbo();
		}
		if (TipoProfessionista.AVV_SPE_CASS.getTipoAlbo().equals(tipoAlbo) && cassazionista.equalsIgnoreCase("Si")) {
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


}
