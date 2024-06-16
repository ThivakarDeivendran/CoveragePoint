package stepdefinitions;

import java.util.HashMap;
import java.util.Map;

public class Shared_State {
	
	private String sharedVariable;
	
	public void setSharedVariable(String sharedVariable) {
		this.sharedVariable = sharedVariable;
	}
	
	public String getSharedVariable() {
		return sharedVariable;
	}
	
	private Map<String, String> sharedVariables = new HashMap<String,String>();
	
	public void setSharedVariable(String key,String value) {
		this.sharedVariables.put(key, value);
	}
	
	public Map<String,String> getSharedVariables(){
		return sharedVariables;
	}
	
	public String getSharedVariable(String key) {
		return sharedVariables.get(key);
	}
}
