package com.solstice.command;

import java.sql.Timestamp;

/*
 * Created by bdatta on 12/26/2016.
 */
public class GenericCommandHeader {

	private String commandType;
	private String schemaVersion;
	private Timestamp createdDate;

	public GenericCommandHeader() {

	}

	public GenericCommandHeader(String commandType, String schemaVersion, Timestamp createdDate) {
		this.commandType = commandType;
		this.schemaVersion = schemaVersion;
		this.createdDate = createdDate;
	}

	public String getCommandType() {
		return this.commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public String getSchemaVersion() {
		return this.schemaVersion;
	}

	public void setSchemaVersion(String schemaVersion) {
		this.schemaVersion = schemaVersion;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
}
