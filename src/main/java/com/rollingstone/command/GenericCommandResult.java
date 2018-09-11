package com.rollingstone.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class GenericCommandResult<T> {
	
	 private ResultCode code;
	    private Map<String, String> messages;
	    private T result;


	    public GenericCommandResult() {
	        code = ResultCode.OPERATION_UNKNOWN;
	        messages = new HashMap<>();
	    }

	    public GenericCommandResult(ResultCode code) {
	        this();
	        this.code = code;
	    }

	    public GenericCommandResult(String message) {
	        this();
	        addMessage("", message);
	    }

	    public GenericCommandResult(String message, ResultCode code) {
	        this(message);
	        this.code = code;
	    }

	    public void setCode(ResultCode code) {
	        this.code = code;
	    }

	    public ResultCode getCode() {
	        return code;
	    }

	    public Map<String, String> getMessages() {
	        return messages;
	    }

	    public void setMessages(Map<String, String> messages) {
	        this.messages = messages;
	    }

	    /**
	     * Get a consolidated String of all operation messages
	     * @return String - a concatenated String of all messages separated by a comma
	     */
	    public String getConsolidatedMessage() {
	        return String.join(", ", messages.values());
	    }

	    public void setAsFailed(String key, String message) {
	        setFailure();
	        addMessage(key, message);
	    }

	    public void setAsFailed(Iterable<Map.Entry<String, String>> messages) {
	        setFailure();
	        for (Map.Entry<String,String> message: messages) {
	            addMessage(message.getKey(), message.getValue());
	        }
	    }



	    public boolean isOK() {
	        return code == ResultCode.OPERATION_SUCCESS;
	    }

	    public boolean isFailure() {
	        return code == ResultCode.OPERATION_FAILURE;
	    }

	    protected void setFailure() {
	        code = ResultCode.OPERATION_FAILURE;
	    }

	    protected void addMessage(String key, String message) {
	        if (StringUtils.isEmpty(message)) {
	            return;
	        }
	        if (!messages.containsKey(key)) {
	            messages.put(key, message);
	        }
	    }    
	    
	    public void setResult(T result) {
	        this.result = result;
	    }

	    public T getResult() {
	        return result;
	    }

	    public boolean isOKWithItem() {
	        return (isOK() && result != null);
	    }

	    public boolean isOKWithNoItem() {
	        return (isOK() && result == null);
	    }

	    public void setAsSuccessful(String message, T result) {

	        this.result = result;

	        //If we have no item and no message, then set a basic item no found message
	        if (result == null && StringUtils.isEmpty(message)) {
	            addMessage("", "Item not found");
	        }
	        if (!StringUtils.isEmpty(message)) {
	            addMessage("", message);
	        }
	    }

	 
	    public void setAsSuccessful() {
	        setAsSuccessful("", null);
	    }

	    public void setAsFailed(String message, T item) {
	        setFailure(item);
	        addMessage(item == null ? "" : item.toString(), message);
	    }

	    /**
	     * Returns the Item if successfull, otherwise null
	     * @return result object
	     */
	    public T getResultIfSuccessful() {
	        return isOKWithItem() ? result : null;
	    }

	    private void setFailure(T result) {
	        setFailure();
	        this.result = result;
	    }


}
