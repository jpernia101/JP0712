package com.postools.postools.ToolsFactory;

public class ToolFactory{

    public static Tool createTool(String code){
        if(code.equalsIgnoreCase("LADW")){
            return new Ladder("LADW");
        }
        else if(code.equalsIgnoreCase("CHNS")){
            return new Chainsaw("CHNS");
        }
        else if(code.equalsIgnoreCase("JAKD")){
            return new Jackhammer("JAKD");
        }
        else if(code.equalsIgnoreCase("JAKR")){
            return new Jackhammer("JAKR");
        }
        else {
            throw new IllegalArgumentException("Unknown tool code:" + code);
        }
    }
}
