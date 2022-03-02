package com.yundenis.springboot.springboot_crud.exception_handling;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DataInfoHandler {
    private String info;

    public DataInfoHandler() {
    }

    public DataInfoHandler(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public DataInfoHandler getInstanceWithInfo(String info) {
        return new DataInfoHandler(info);
    }
}
