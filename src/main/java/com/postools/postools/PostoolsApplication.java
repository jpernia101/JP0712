package com.postools.postools;

import java.text.ParseException;
import javax.naming.directory.InvalidAttributeValueException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostoolsApplication {

	public static void main(String[] args) throws ParseException, InvalidAttributeValueException {
		System.out.println("TESTING");
		String[] toolCodes = {"JAKR","LADW", "CHNS", "JAKD" , "JAKR", "JAKR"};
		String[] checkoutDates = {"09/03/15","07/02/20","07/02/15","09/03/15","07/02/15","07/02/20"};
		Integer[] numofDays = {5,3,5,6,9,4 };
		Integer[] discount = {101,10,25,0,0,50 };

		for (int i = 0; i < discount.length; i++) {
			try {
				CheckoutItem item = new CheckoutItem(toolCodes[i],checkoutDates[i],numofDays[i],discount[i]);
				System.out.println(" \n Agreement for tool # " + (i +1) + "\n");
				item.printRentalAgreement();
				
			} catch (InvalidAttributeValueException e) {
				System.out.println( "\n Agreement for tool " + (i + 1) + " failed due to exception: " + e + "\n");
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
		}
				
		SpringApplication.run(PostoolsApplication.class, args);
	}

}
