package com.wild.common;

import java.util.UUID;

public class GenerateUUID {

    public String getUuid(){
        String GUID = UUID.randomUUID().toString().replaceAll("-", "");
        return GUID;
    }
}
