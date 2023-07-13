package com.postools.postools.Model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.naming.directory.InvalidAttributeValueException;
import com.postools.postools.ToolsFactory.Tool;
import com.postools.postools.ToolsFactory.ToolFactory;
import com.postools.postools.Util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class CheckoutItem {
    private Tool tool;
    private String total;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private String checkoutDate;
    private Integer numOfDaysTotal;
    private Integer numOfDaysCharged;
    private Integer discount; 

    
    public CheckoutItem(String code, String checkoutDate, Integer numOfDays, Integer discount) throws InvalidAttributeValueException{
        this.tool = ToolFactory.createTool(code);
        this.checkoutDate = checkoutDate;
        this.numOfDaysTotal = numOfDays;
        this.discount = discount;

        if(discount < 0 || discount > 100){
            throw new InvalidAttributeValueException("Invalid discount amount");
        }
        if(numOfDays <= 0 ){
            throw new InvalidAttributeValueException("Invalid amount of days");
        }
        this.numOfDaysCharged = numberOfDaysCharged(this.checkoutDate, this.numOfDaysTotal);
        if(discount > 0){
            this.total = getTotal(this.numOfDaysCharged, discount);
        }
        else{
            this.total = getTotal(this.numOfDaysCharged);
        }
    }

    private String getTotal(Integer numberOfDays){
        this.total = decimalFormat.format(tool.getToolPrice() * numberOfDays);
        return(total);
    }

    private String getTotal(Integer numberOfDays , Integer discount){
        Double amountOff = (discount * tool.getToolPrice()) / 100.00 ;
        Double newPrice = (tool.getToolPrice() - amountOff) * numberOfDays;
        this.total = decimalFormat.format(newPrice);

        return(total);
    }

    private String amountSaved(){
        if(discount.equals(0) ){
            return("0");
        }
        Double fullPrice = tool.getToolPrice() * this.numOfDaysCharged;
        Double discountPrice = tool.getToolPrice() * this.numOfDaysCharged * ((100.00 - discount) / 100.00);
        Double amountSaved = fullPrice - discountPrice;
        return decimalFormat.format(amountSaved);
    }

    public Integer numberOfDaysCharged(String startDate, Integer numOfDays) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate date = LocalDate.parse(startDate,formatter);
        //list of dates where holidays are observed
        ArrayList<LocalDate> observedHolidayDates = DateUtil.getObservedDatesOfHoliday(date.getYear());

        //from day after checkout is when we start counting
        date = date.plusDays(1);
        Integer skippedDays = 0 ;

        for(int i = 1 ; i <= numOfDays ; i++){
            if(DateUtil.isDateWeekend(date) && this.tool.getWeekendExemption()){
                skippedDays++;
                
            }
            if( observedHolidayDates.contains(date) && this.tool.getHolidayExemption()){
                //we remove it cause we already counted for it
                observedHolidayDates.remove(date);
                skippedDays++; 
            }
            date = date.plusDays(1);
                 
        }
        return this.numOfDaysTotal - skippedDays;
    }

    private String dueDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate date = LocalDate.parse(this.checkoutDate,formatter);
        LocalDate returnDate = date.plusDays(numOfDaysTotal);

        return formatter.format(returnDate).toString();
    }

    public void printRentalAgreement(){
        log.info("Rental Agreement: "+ "\n" + "Tool Code: " + tool.getToolCode() + "\n" + "Tool Type : " + tool.getToolType() + "\n" 
        + "Tool Brand: " + tool.getToolBrand() + "\n" + "Rental Days : " + this.numOfDaysTotal + "\n" + "Checkout Date: " + this.checkoutDate + "\n"
        + "Due Date: " + dueDate() + "\n" + "Daily Rental Charge: $" + tool.getToolPrice() + "\n" + "Charged Days: " + this.numOfDaysCharged + "\n"
        + "Discount Percent: " + this.discount + "% \n" + "Discount Amount: $" + amountSaved() + "\n" + "Final Charge: $" + this.getTotal() + "\n"
        + "Thanks For Renting With Us :)"
        );

    }

}
