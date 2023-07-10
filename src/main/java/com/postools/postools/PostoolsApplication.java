package com.postools.postools;

import java.text.ParseException;

import javax.naming.directory.InvalidAttributeValueException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.postools.postools.ToolsFactory.ToolFactory;
import com.postools.postools.ToolsFactory.Tool;

@SpringBootApplication
public class PostoolsApplication {

	public static void main(String[] args) throws ParseException, InvalidAttributeValueException {
		System.out.println("TESTING");
		

		Tool jackHammer2 = ToolFactory.createTool("JAKR");
		Tool jackHammer3 = ToolFactory.createTool("JAKR");
		// CheckoutItem cart = new CheckoutItem(jackHammer2, "09/03/15",5, 101);
		CheckoutItem cart2 = new CheckoutItem("JAKR", "07/02/15",9, 0);
		CheckoutItem cart3 = new CheckoutItem("JAKR", "07/02/20",4, 50);
		
		// System.out.println(jackHammer2.toString()); 
		// System.out.println(cart.getTotal()); 
		// cart.printRentalAgreement();
		cart2.printRentalAgreement();
		cart3.printRentalAgreement();
	
		
		
		SpringApplication.run(PostoolsApplication.class, args);
	}

}
