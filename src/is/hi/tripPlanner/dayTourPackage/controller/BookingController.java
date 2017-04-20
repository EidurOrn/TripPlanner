package is.hi.tripPlanner.dayTourPackage.controller;

import is.hi.tripPlanner.dayTourPackage.model.BookingModel;
import is.hi.tripPlanner.dayTourPackage.model.Trip;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class BookingController {

	private static DatabaseRetrival dbRetrival = new DatabaseRetrival();
	private static DatabaseUpdater dbUpdater = new DatabaseUpdater();


	public static String bookTrip(String[] tripInfo) { // tripInfo er listi af tripId, bookerEmail, numPeople, bookerSSN

		int bookingComplete = -1;
		String bookingResult = "";

		BookingModel booking = new BookingModel(-1, Integer.parseInt(tripInfo[0]), tripInfo[1], Integer.parseInt(tripInfo[2]), Long.parseLong(tripInfo[3]));

		bookingComplete = dbUpdater.insertBooking(booking);

		if(bookingComplete == 0) {
			sendVerification(Integer.parseInt(tripInfo[0]), tripInfo[1]);
			bookingResult = "Booking successful";
		} else if(bookingComplete == 1) {
			System.out.println("Því miður eru of fá sæti laus");
			bookingResult = "Unfortunately not enough seats are available";
		} else if(bookingComplete == 2) {
			System.out.println("Villa kom upp við bókun");
			bookingResult = "An error has occured, please try again";
		}

		return bookingResult;
	}

	public static void sendVerification(int tripId, String email) {

		final String username = "utrippin7d@gmail.com";
		final String password = "kulpassword";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("utrippin7d@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));

			Trip[] tripInfo = dbRetrival.queryTripInfo(tripId);

			message.setSubject("Booking confirmation");
			message.setText("Dear sir/madam," + "\n" + "\n"
					+ "We have received your booking." + "\n" + "\n"
					+ "Here are the information concerning your trip:" + "\n"
					+ "\tTrip: " + tripInfo[0].getTripName() + "\n"
					+ "\tFrom: " + tripInfo[0].getDateBegin() + "\n"
					+ "\tTo: " + tripInfo[0].getDateEnd() + "\n"
					+ "\tDescription: " + tripInfo[0].getDescription() + "\n"
					+ "\tLocation: " + tripInfo[0].getLocation() + "\n" + "\n"
					+ "If you have any questions, please feel free to contact us by email utrippin7d@gmail.com." + "\n\n"
					+ "Yours sincerely," + "\n"
					+ "uTrippin"
			);

			Transport.send(message);
		} catch (MessagingException e) {
			// do nothing
		}
	}
}