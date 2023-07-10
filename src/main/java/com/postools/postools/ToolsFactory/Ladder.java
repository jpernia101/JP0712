package com.postools.postools.ToolsFactory;
import lombok.Data;

@Data
public class Ladder implements Tool {
    String toolType;
    String toolCode;
    String toolBrand;
    Double toolPrice;
    Boolean holidayExemption;
    Boolean weekendExemption;

    Ladder(String code){
        this.toolBrand = ToolsConstants.LADDER_BRAND;
        this.toolPrice = ToolsConstants.LADDER_PRICE;
        this.holidayExemption = ToolsConstants.LADDER_HOLIDAY_EXEMPT;
        this.weekendExemption = ToolsConstants.LADDER_WEEKEND_EXEMPT;    
        this.toolCode = ToolsConstants.LADDER_CODE;
        this.toolType = ToolsConstants.LADDER_TOOLTYPE;
    }
}
