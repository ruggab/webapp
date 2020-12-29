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

	public static List<SchemaCsv> read(InputStream stream) {
		try {

			InputStreamReader ireader = new InputStreamReader(stream, "UTF-8");
			CSVReader reader = new CSVReader(ireader, ';', '"', 1);
			ColumnPositionMappingStrategy<SchemaCsv> strat = new ColumnPositionMappingStrategy<SchemaCsv>();
			strat.setType(SchemaCsv.class);
			strat.setColumnMapping(SchemaCsv.COLUMNS);
			CsvToBean<SchemaCsv> csv = new CsvToBean<SchemaCsv>();
			List<SchemaCsv> list = csv.parse(strat, reader);

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
			List<SchemaCsv> listCsv = read(fis);
			// HashBuilder.costruisciHash(cognome, nome, data, sesso,nazioneNascitaEstera, comune, provincia);
			File fileOut = new File(fileNameOut);
			FileWriter fr = new FileWriter(fileOut, true);
			fr.write("tessera;nome;cognome;data;sesso;nazione;provincia;comune;hash\n");
			for (Iterator iterator = listCsv.iterator(); iterator.hasNext();) {
				SchemaCsv schemaCsv = (SchemaCsv) iterator.next();
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

}
