package services;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormatterInput {

	public static String ValidatorEmail(String email) {
		
		String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	    Boolean b = email.matches(EMAIL_REGEX);
	    
	    if(b) {
	    String validationEmail = "Cet email est valide";
	    System.out.println(validationEmail);

	    return validationEmail;
	    
	    }else {
		    String validationEmail = "Cet email n'est pas valide";
		    System.out.println(validationEmail);
		    return validationEmail;
	    }
	}
	
	public static String formatNumberPhone (JTextField Phone) {
		NumberFormat numberFormatter = new DecimalFormat("##,##,##,##,##");
		String formattedNumber = numberFormatter.format(Phone);
		return formattedNumber;
	}

}
