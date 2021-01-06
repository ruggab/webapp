package it.com.gab.webapp.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

//import icontact.sms.ws.tutorial.ManageSmsWrapper;
//import icontact.sms.ws.tutorial.ManageUserWrapper;
//import icontact.sms.ws.tutorial.Utility;
//import icontact.sms.ws.tutorial.managesms.client.Result;
//import icontact.sms.ws.tutorial.managesms.client.SendResponse;
//import icontact.sms.ws.tutorial.managesms.client.SmsInfoResponse;
//import icontact.sms.ws.tutorial.manageuser.client.UserInfoResponse;

/**
 * Questa classe permette di effettuare l'invio di mail tramite PEC (Posta Elettronica Certificata)
 *
 * La classe si occupa di gestire l'intera comunicazione con il server SMTP tramite SSL usando la libreria standard
 * JavaMail.
 *
 * La classe &egrave; in grado di spedire mail con allegati.
 *
 * @author LeleFT
 */
public class MailerUtility {

	Logger log = Logger.getLogger(MailerUtility.class);

	/*
	 * UserAuthenticator: fornise un'implementazione concreta di Authenticator; permette, quindi, di fornire
	 * all'applicazione (in chiaro) l'utente e la password per l'accesso al server SMTP
	 */
	private class UserAuthenticator extends Authenticator {

		private String user;
		private String pass;

		public UserAuthenticator(String user, String pass) {
			this.user = user;
			this.pass = pass;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user, pass);
		}
	}

	/*
	 * Allegato: rappresenta un singolo file da allegare alla mail. Il singolo allegato &egrave; rappresentato da un
	 * file fisico e da un nome da visualizzare all'interno della mail.
	 */
	private class Allegato {
		private String nomeAllegato;
		private File fileAllegato;

		public Allegato(String nomeAllegato, File fileAllegato) {
			this.nomeAllegato = nomeAllegato;
			this.fileAllegato = fileAllegato;
		}

		public String getNomeAllegato() {
			return nomeAllegato;
		}

		public File getFileAllegato() {
			return fileAllegato;
		}
	}

	private String cfgFile; // Percorso al file properties di configurazione
	private String subject; // L'oggetto della mail
	private String txtMessage; // Il corpo della mail (plain text)
	private ArrayList<String> toAddress; // Elenco dei destinatari (TO)
	private ArrayList<String> ccAddress; // Elenco dei destinatari in copia (CC)
	private ArrayList<String> bccAddress; // Elenco dei destinatari (TO)
	private ArrayList<Allegato> attachments; // Elenco degli allegati
	private UserAuthenticator userAuthenticator; // Autenticatore per SMTP

	public MailerUtility(String cfgFile) {
		this.cfgFile = cfgFile;
		subject = "";
		txtMessage = "";
		toAddress = new ArrayList<String>();
		ccAddress = new ArrayList<String>();
		attachments = new ArrayList<Allegato>();
		bccAddress = new ArrayList<String>();
	}

	/**
	 * Imposta l'oggetto della mail
	 *
	 * @param subject
	 *            l'oggetto della mail
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Aggiunge un destinatario all'elenco dei destinatari di questa mail
	 *
	 * @param recipient
	 *            l'indirizzo email del destinatario da aggiungere
	 */
	public void addRecipient(String recipient) {
		toAddress.add(recipient);
	}

	/**
	 * Aggiunge un destinatario in copia all'elenco dei destinatari in copia
	 *
	 * @param ccRec
	 *            l'indirizzo email del destinatario in copia da aggiungere
	 */
	public void addCopyRecipient(String ccRec) {
		ccAddress.add(ccRec);
	}

	/**
	 * Aggiunge un destinatario in copia Noscosta all'elenco dei destinatari in copia
	 *
	 * @param ccRec
	 *            l'indirizzo email del destinatario in copia da aggiungere
	 */
	public void addBCCCopyRecipient(String ccRec) {
		bccAddress.add(ccRec);
	}

	/**
	 * Aggiunge un allegato alla mail
	 *
	 * @param f
	 *            un oggetto File che rappresenta il file da allegare
	 * @param newName
	 *            il nome dell'allegato da visualizzare nella mail
	 */
	public void addAttachment(File f, String newName) {
		attachments.add(new Allegato(newName, f));
	}

	/**
	 * Imposta il contenuto testuale della mail (corpo del messaggio)
	 *
	 * @param txtMsg
	 *            array di stringhe: ciascun elemento rappresenta una riga del corpo messaggio
	 */
	public void setTextMessage(String[] txtMsg) {
		StringBuilder messaggio = new StringBuilder();
		for (String str : txtMsg)
			messaggio.append(str + "\n");
		this.txtMessage = messaggio.toString();
	}

	/**
	 * Effettua l'invio della mail
	 */
	public void sendMail() throws Exception {
		try {
			// Ottengo le proprietà di sistema
			Properties props = System.getProperties();

			// Proprietà specifiche lette dal file
			Properties specProps = new Properties();

			// Carico il file con le proprietà specifiche

			specProps.load(getClass().getResourceAsStream(cfgFile));

			// Aggiungo alle proprietà specifiche, tutte le proprietà generali
			// di sistema
			Set<String> keys = props.stringPropertyNames();
			for (String key : keys)
				specProps.put(key, props.getProperty(key));

			// Ottengo le informazioni per l'autenticazione e l'invio della mail
			String user = specProps.getProperty("nomeutente");
			String pass = specProps.getProperty("password");
			String fromAddress = specProps.getProperty("from-address");
			String personalName = specProps.getProperty("from-name");

			// Rimuovo le proprietà appena lette dalle proprietà specifiche
			specProps.remove("nomeutente");
			specProps.remove("password");
			specProps.remove("from-address");
			specProps.remove("from-name");

			// La porta da usare per la connessione al server SMTP
			int port = Integer.parseInt(specProps.getProperty("mail.smtp.port"));

			// Costruisco l'oggetto authenticator per effettuare
			// l'autenticazione al server SMTP
			userAuthenticator = new UserAuthenticator(user, pass);

			// Ottengo una sessione SMTP
			Session session = Session.getDefaultInstance(specProps, userAuthenticator);
			session.setDebug(true);

			// Costruisco l'oggeto mail
			MimeMessage message = new MimeMessage(session);

			// Imposto il mittente
			InternetAddress iaFrom = new InternetAddress(fromAddress, personalName);
			message.setFrom(iaFrom);

			// Aggiungo i destinatari principali
			for (String rec : toAddress) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(rec));
			}

			// Aggiungo i destinatari in copia
			for (String rec : ccAddress) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(rec));
			}

			// Aggiungo i destinatari in copia
			for (String rec : bccAddress) {
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(rec));
			}

			// Setto l'oggetto della mail
			message.setSubject(subject);

			// Creo il corpo del messaggio (il testo + gli allegati)
			componiMessaggio(message);

			// Salvo lo stato dell'oggetto mail
			message.saveChanges();

			// Dalla sessione SMTP ottengo il Transport che si occuperà di
			// dialogare col server
			Transport t = session.getTransport("smtps");

			// Effettuo la connessione
			t.connect(specProps.getProperty("mail.smtp.host"), port, user, pass);

			// Invio il messaggio
			t.sendMessage(message, message.getAllRecipients());

			// Chiudo la connessione
			t.close();

		} catch (AddressException ae) {
			log.error("Indirizzo che ha generato l'eccezione: " + ae.getRef());
			throw new Exception(ae.getMessage());
		} catch (Exception e) {
			log.error("Errore generico durante l'invio email");
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * Effettua l'invio della mail
	 */
	public void sendMailArchitetto() throws Exception {
		try {
			// Ottengo le proprietà di sistema
			Properties props = System.getProperties();

			// Proprietà specifiche lette dal file
			Properties specProps = new Properties();

			// Carico il file con le proprietà specifiche

			specProps.load(getClass().getResourceAsStream(cfgFile));

			// Aggiungo alle proprietà specifiche, tutte le proprietà generali
			// di sistema
			Set<String> keys = props.stringPropertyNames();
			for (String key : keys)
				specProps.put(key, props.getProperty(key));

			// Ottengo le informazioni per l'autenticazione e l'invio della mail
			String user = specProps.getProperty("nomeutente");
			String pass = specProps.getProperty("password");
			String fromAddress = specProps.getProperty("from-address");
			String personalName = specProps.getProperty("from-name");

			// Rimuovo le proprietà appena lette dalle proprietà specifiche
			specProps.remove("nomeutente");
			specProps.remove("password");
			specProps.remove("from-address");
			specProps.remove("from-name");

			// La porta da usare per la connessione al server SMTP
			int port = Integer.parseInt(specProps.getProperty("mail.smtp.port"));

			// Costruisco l'oggetto authenticator per effettuare
			// l'autenticazione al server SMTP
			userAuthenticator = new UserAuthenticator(user, pass);

			// Ottengo una sessione SMTP
			Session session = Session.getDefaultInstance(specProps, userAuthenticator);
			session.setDebug(true);

			// Costruisco l'oggeto mail
			MimeMessage message = new MimeMessage(session);

			// Imposto il mittente
			InternetAddress iaFrom = new InternetAddress(fromAddress, personalName);
			message.setFrom(iaFrom);

			// Aggiungo i destinatari principali
			for (String rec : toAddress) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(rec));
			}

			// Aggiungo i destinatari in copia
			for (String rec : ccAddress) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(rec));
			}

			// Aggiungo i destinatari in copia
			for (String rec : bccAddress) {
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(rec));
			}

			// Setto l'oggetto della mail
			message.setSubject(subject);
			message.addHeader("Disposition-Notification-To", fromAddress);

			// Creo il corpo del messaggio (il testo + gli allegati)
			componiMessaggioArchitetto(message);

			// Salvo lo stato dell'oggetto mail
			message.saveChanges();

			// Dalla sessione SMTP ottengo il Transport che si occuperà di
			// dialogare col server
			Transport t = session.getTransport("smtps");

			// Effettuo la connessione
			t.connect(specProps.getProperty("mail.smtp.host"), port, user, pass);

			// Invio il messaggio
			t.sendMessage(message, message.getAllRecipients());

			// Chiudo la connessione
			t.close();

		} catch (AddressException ae) {
			log.error("Indirizzo che ha generato l'eccezione: " + ae.getRef());
			throw new Exception(ae.getMessage());
		} catch (Exception e) {
			log.error("Errore generico durante l'invio email");
			throw new Exception(e.getMessage());
		}
	}

	/*
	 * Si occupa di comporre il messaggio: costruisce il corpo del messaggio (testo) e aggiunge tutti gli eventuali
	 * allegati
	 */
	private void componiMessaggio(MimeMessage message) throws Exception {
		Multipart multiPart = new MimeMultipart();

		// //TXT
		// MimeBodyPart textPart = new MimeBodyPart();
		// textPart.setText(txtMessage, "utf-8");

		// HTML
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(txtMessage, "text/html; charset=utf-8");

		// logo1
		// second part (the image)
		MimeBodyPart logPart = new MimeBodyPart();
		DataSource fds = new FileDataSource("C:/Users/Gabriele/Desktop/logo/ordineavvRR.jpg");

		logPart.setDataHandler(new DataHandler(fds));
		logPart.setHeader("Content-ID", "<image1>");

		// logo2 second part (the image)
		MimeBodyPart logPart2 = new MimeBodyPart();
		DataSource fds2 = new FileDataSource("C:/Users/Gabriele/Desktop/logo/cbill.jpg");

		logPart2.setDataHandler(new DataHandler(fds2));
		logPart2.setHeader("Content-ID", "<image2>");

		// add image to the multipart
		multiPart.addBodyPart(logPart);
		multiPart.addBodyPart(logPart2);
		// multiPart.addBodyPart(textPart); // <-- first
		multiPart.addBodyPart(htmlPart); // <-- second
		message.setContent(multiPart);
		// Se ci sono allegati, li allego
		// for (Allegato al : attachments) {
		// textPart = new MimeBodyPart();
		// ((MimeBodyPart) textPart).attachFile(al.getFileAllegato());
		//
		// textPart.setFileName(al.getNomeAllegato());
		//
		// multiPart.addBodyPart(textPart);
		// }

		// Aggiungo il Multipart al messaggio
		message.setContent(multiPart);
	}

	/*
	 * Si occupa di comporre il messaggio: costruisce il corpo del messaggio (testo) e aggiunge tutti gli eventuali
	 * allegati
	 */
	private void componiMessaggioArchitetto(MimeMessage message) throws Exception {
		Multipart multiPart = new MimeMultipart();

		// HTML
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(txtMessage, "text/html; charset=utf-8");

		// Logo
		MimeBodyPart logPart = new MimeBodyPart();
		DataSource fds = new FileDataSource("C:/Users/Gabriele/Desktop/logo/architetti.png");
		logPart.setDataHandler(new DataHandler(fds));
		logPart.setHeader("Content-ID", "<image1>");

		multiPart.addBodyPart(logPart);
		multiPart.addBodyPart(htmlPart); // <-- second

		// Aggiungo il Multipart al messaggio
		message.setContent(multiPart);
	}

	// public void inviaSMS(String from, String to, String msx) throws Exception {
	// System.out.println("#################### Start application ####################");
	//
	// String username = "gabriele.ruggiero";
	// String password = "gabriel.01";
	//
	// System.out.println("\nusername " + username);
	// System.out.println("\npassword " + password);
	//
	// System.out.println("\n\n--> SEND SMS\n");
	// try {
	// ManageSmsWrapper smsWrapper = new ManageSmsWrapper(username, password);
	//
	// // Send SMS
	// SendResponse sendResult = smsWrapper.send(from, to, msx, null, null);
	//
	// Integer internalId = null;
	//
	// String opRes = Utility.checkOperarationResult(sendResult);
	// if (Utility.OPERATION_SUCCESS.equals(opRes)) {
	// System.out.println(Utility.toStringSendResponse(sendResult));
	// Result smsResult = sendResult.getResults().get(0);
	// internalId = new Integer(smsResult.getInternalId());
	//
	// } else {
	// System.out.println(opRes);
	// throw new Exception("ERRORE INVIO SMS");
	// }
	//
	// // Load SMS by Id
	// System.out.println("\n\n--> GET SMS\n");
	// SmsInfoResponse getSmsResult = smsWrapper.getSmsById(new Integer[] { internalId }, null);
	//
	// opRes = Utility.checkOperarationResult(getSmsResult);
	// if (Utility.OPERATION_SUCCESS.equals(opRes)) {
	// System.out.println(Utility.toStringSmsInfoResponse(getSmsResult));
	// } else {
	// System.out.println(opRes);
	// throw new Exception("ERRORE INVIO SMS");
	// }
	//
	// // Load SMS by Date
	// System.out.println("\n\n--> GET SMS BY DATE\n");
	// Calendar calendar = Calendar.getInstance();
	// calendar.set(Calendar.HOUR_OF_DAY, 0);
	// calendar.set(Calendar.MINUTE, 0);
	// calendar.set(Calendar.SECOND, 0);
	// calendar.set(Calendar.MILLISECOND, 0);
	//
	// SmsInfoResponse getSmsDateResult = smsWrapper.getSmsByDate(Utility.formatDate(calendar.getTime()), null);
	//
	// opRes = Utility.checkOperarationResult(getSmsDateResult);
	//// if (Utility.OPERATION_SUCCESS.equals(opRes)) {
	//// System.out.println(Utility.toStringSmsInfoResponse(getSmsDateResult));
	//// } else {
	//// System.out.println(opRes);
	//// }
	//
	// if (Utility.OPERATION_SUCCESS.equals(opRes)) {
	// String status = Utility.getStatusLastSMS(getSmsDateResult);
	// if (status.equalsIgnoreCase("NOTDELIVERED")) {
	// throw new Exception("SMS NON CONCEGNATO");
	// }
	// } else {
	// System.out.println(opRes);
	// throw new Exception("ERRORE INVIO SMS");
	// }
	//
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw e;
	// }
	//
	// System.out.println("\n\n--> USER INFO\n");
	// try {
	//
	// ManageUserWrapper userWrpapper = new ManageUserWrapper(username, password);
	// UserInfoResponse response = userWrpapper.getUserInfo();
	//
	// if (Utility.OPERATION_SUCCESS.equals(response.getOperationResult())) {
	// String name = response.getUserInfo().getName();
	// double credit = response.getUserInfo().getCredit();
	// System.out.println("User " + name + ": " + credit + " Eur");
	// } else {
	// System.out.println(response.getOperationResult());
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// }
	//
	// System.out.println("#################### End application ####################");
	//
	// }

}