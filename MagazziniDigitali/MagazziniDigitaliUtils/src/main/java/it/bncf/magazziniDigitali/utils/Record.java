/**
 * 
 */
package it.bncf.magazziniDigitali.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;


/**
 * @author massi
 *
 */
@SuppressWarnings("serial")
public class Record implements Serializable {

//	private HashMap<String, Vector<Object>> dati;

	private HashMap<String, Vector<Object>> datiString;

	/**
	 * 
	 */
	public Record() {
	}

	public void set(String key, Object dati){
		if (this.datiString == null){
			this.datiString = new HashMap<String, Vector<Object>>();
		}

		if (this.datiString.get(key)==null){
			this.datiString.put(key, new Vector<Object>());
		}
		this.datiString.get(key).add(dati);
	}

	public boolean isEmpty(String key){
		boolean result = false;
		Vector<Object> values = null;
		
		values = get(key);
		if (values == null || values.size()==0 || values.get(0)==null){
			result = true;
		}
		return result;
	}

	public boolean isString(String key){
		boolean result = false;
		Vector<Object> values = null;
		
		values = get(key);
		if (values != null && values.size()>0){
			if (values.get(0).getClass().getName().equals(String.class.getName())){
				result = true;
			}
		}
		return result;
	}

	public boolean isInteger(String key){
		boolean result = false;
		Vector<Object> values = null;
		
		values = get(key);
		if (values != null && values.size()>0){
			if (values.get(0).getClass().getName().equals(Integer.class.getName())){
				result = true;
			}
		}
		return result;
	}

	public boolean isBoolean(String key){
		boolean result = false;
		Vector<Object> values = null;
		
		values = get(key);
		if (values != null && values.size()>0){
			if (values.get(0) instanceof Boolean){
				result = true;
			}
		}
		return result;
	}

	public boolean isDate(String key){
		boolean result = false;
		Vector<Object> values = null;
		
		values = get(key);
		if (values != null && values.size()>0){
			if (values.get(0).getClass().getName().equals(Date.class.getName())){
				result = true;
			}
		}
		return result;
	}

	public Vector<Object> get(String key){
		if (this.datiString != null){
			if (this.datiString.get(key)!= null){
				return this.datiString.get(key);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public String[] getStrings(String key){
		String[] record = null;
		Vector<Object> dati = null;
		if (this.datiString!=null && this.datiString.get(key)!= null){
			dati = this.datiString.get(key);
			record = new String[dati.size()];
			for (int x=0; x<dati.size(); x++){
				record[x]=(String) dati.get(x);
			}
		}
		return record;
	}

	public Record[] getRecords(String key){
		Record[] record = null;
		Vector<Object> dati = null;
		if (this.datiString!=null && this.datiString.get(key)!= null){
			dati = this.datiString.get(key);
			record = new Record[dati.size()];
			for (int x=0; x<dati.size(); x++){
				record[x]=(Record) dati.get(x);
			}
		}
		return record;
	}

	public Boolean getBoolean(String key){
		Boolean[] datis = null;
		Boolean dati = null;
		
		datis = getBooleans(key);
		if (datis != null && datis.length>0){
			dati = datis[0];
		}
		return dati;
	}

	public Boolean[] getBooleans(String key){
		Boolean[] record = null;
		Vector<Object> dati = null;
		if (this.datiString!=null && this.datiString.get(key)!= null){
			dati = this.datiString.get(key);
			record = new Boolean[dati.size()];
			for (int x=0; x<dati.size(); x++){
				record[x]=(Boolean) dati.get(x);
			}
		}
		return record;
	}

	public Date getDate(String key){
		Date[] datis = null;
		Date dati = null;
		
		datis = getDates(key);
		if (datis != null && datis.length>0){
			dati = datis[0];
		}
		return dati;
	}

	public Date[] getDates(String key){
		Date[] record = null;
		Vector<Object> dati = null;
		if (this.datiString!=null && this.datiString.get(key)!= null){
			dati = this.datiString.get(key);
			record = new Date[dati.size()];
			for (int x=0; x<dati.size(); x++){
				record[x]=(Date) dati.get(x);
			}
		}
		return record;
	}

	public String getString(String key){
		String[] datis = null;
		String dati = null;
		
		datis = getStrings(key);
		if (datis != null && datis.length>0){
			dati = datis[0];
		}
		return dati;
	}

	public Integer[] getIntegers(String key){
		Integer[] record = null;
		Vector<Object> dati = null;
		if (this.datiString!=null && this.datiString.get(key)!= null){
			dati = this.datiString.get(key);
			record = new Integer[dati.size()];
			for (int x=0; x<dati.size(); x++){
				record[x]=(Integer) dati.get(x);
			}
		}
		return record;
	}

	public Integer getInteger(String key){
		Integer[] datis = null;
		Integer dati = null;
		
		datis = getIntegers(key);
		if (datis != null && datis.length>0){
			dati = datis[0];
		}
		return dati;
	}

	public Set<String> key(){
		if (datiString != null){
			return datiString.keySet();
		} else {
			return null;
		}
	}
}
