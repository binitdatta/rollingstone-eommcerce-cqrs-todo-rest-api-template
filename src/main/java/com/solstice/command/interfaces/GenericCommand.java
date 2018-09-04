package com.solstice.command.interfaces;

import java.io.Serializable;

import com.solstice.command.GenericCommandHeader;


public interface GenericCommand extends  Serializable {
    public GenericCommandHeader getHeader();
    public void setHeader(GenericCommandHeader header);
}


