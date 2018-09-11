package com.rollingstone.command.interfaces;

import java.io.Serializable;

import com.rollingstone.command.GenericCommandHeader;


public interface GenericCommand extends  Serializable {
    public GenericCommandHeader getHeader();
    public void setHeader(GenericCommandHeader header);
}


