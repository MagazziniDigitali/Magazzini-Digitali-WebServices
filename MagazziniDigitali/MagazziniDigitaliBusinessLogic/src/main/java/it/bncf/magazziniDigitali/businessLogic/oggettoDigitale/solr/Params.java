/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.math.BigInteger;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

/**
 * @author massi
 *
 */
class Params {
	private Hashtable<String, List<Object>> params;
	
	public Params(){
		params = new Hashtable<String, List<Object>>();
	}

	public void add(String key, String value){
		List<Object> values = null;
		
		if (params.get(key)== null){
			values = new Vector<Object>();
		} else {
			values= params.get(key);
		}
		values.add(value);
		params.put(key, values);
	}

	public void add(String key, Date value){
		List<Object> values = null;
		
		if (params.get(key)== null){
			values = new Vector<Object>();
		} else {
			values= params.get(key);
		}
		values.add(value);
		params.put(key, values);
	}

	public void add(String key, BigInteger value){
		List<Object> values = null;
		
		if (params.get(key)== null){
			values = new Vector<Object>();
		} else {
			values= params.get(key);
		}
		values.add(value);
		params.put(key, values);
	}

	public void add(String key, Long value){
		List<Object> values = null;
		
		if (params.get(key)== null){
			values = new Vector<Object>();
		} else {
			values= params.get(key);
		}
		values.add(value);
		params.put(key, values);
	}

	public Hashtable<String, List<Object>> getParams() {
		return params;
	}

	
}