package com.postools.postools.ToolsFactory;

public interface Tool {

     String getToolType();
     void setToolType(String toolType);

     String getToolCode();
     void setToolCode(String toolCode);

     String getToolBrand();
     void setToolBrand(String toolBrand);

     Double getToolPrice();
     void setToolPrice(Double toolPrice);

     Boolean getHolidayExemption();
     void setHolidayExemption(Boolean flag);
     Boolean getWeekendExemption();
     void setWeekendExemption(Boolean flag);
}
