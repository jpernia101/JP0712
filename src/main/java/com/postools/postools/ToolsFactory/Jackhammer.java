package com.postools.postools.ToolsFactory;
import lombok.Data;

@Data
public class Jackhammer implements Tool{
    String toolType;
    String toolCode;
    String toolBrand;
    Double toolPrice;
    Boolean holidayExemption;
    Boolean weekendExemption;

    Jackhammer(String code){
        this.toolPrice = ToolsConstants.JACKHAMMER_PRICE;
        this.holidayExemption = ToolsConstants.JACKHAMMER_HOLIDAY_EXEMPT;
        this.weekendExemption = ToolsConstants.JACKHAMMER_WEEKEND_EXEMPT; 
        this.toolType = ToolsConstants.JACKHAMMER_TOOLTYPE;
        if(code.equals("JAKR")){  
            this.toolCode = ToolsConstants.JACKHAMMER_CODE_RIGID;
            this.toolBrand = ToolsConstants.JACKHAMMER_BRAND_RIGID;
        }
        else if(code.equals("JAKD")){   
            this.toolCode = ToolsConstants.JACKHAMMER_CODE_DEWALT;
            this.toolBrand = ToolsConstants.JACKHAMMER_BRAND_DEWALT;
        } 
        else{
            throw new IllegalArgumentException("Invalid code when intializing object: " + code);
        } 
    }
}
