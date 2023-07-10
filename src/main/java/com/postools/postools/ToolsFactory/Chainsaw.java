package com.postools.postools.ToolsFactory;

import lombok.Data;

@Data
public class Chainsaw implements Tool{
    String toolType;
    String toolCode;
    String toolBrand;
    Double toolPrice;
    Boolean holidayExemption;
    Boolean weekendExemption;

    Chainsaw(String code){
        this.toolBrand = ToolsConstants.CHAINSAW_BRAND;
        this.toolPrice = ToolsConstants.CHAINSAW_PRICE;
        this.holidayExemption = ToolsConstants.CHAINSAW_HOLIDAY_EXEMPT;
        this.weekendExemption = ToolsConstants.CHAINSAW_WEEKEND_EXEMPT;    
        this.toolCode = ToolsConstants.CHAINSAW_CODE;
        this.toolType = ToolsConstants.CHAINSAW_TOOLTYPE;
    }
}
