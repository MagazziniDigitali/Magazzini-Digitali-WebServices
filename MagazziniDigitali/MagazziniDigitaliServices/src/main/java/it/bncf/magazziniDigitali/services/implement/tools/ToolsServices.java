package it.bncf.magazziniDigitali.services.implement.tools;

import org.apache.axis.MessageContext;

public class ToolsServices {

	public ToolsServices() {
	}

	public static boolean testIP(String ipAutorizzati){
		return testIP(ipAutorizzati, getRemoteIP());
	}

	public static String getRemoteIP(){
		MessageContext mc = null;
		String ipRemoto = null;

		mc = MessageContext.getCurrentContext();
		if (mc!=null){
			ipRemoto = (String) mc.getProperty("remoteaddr");
		}
		return ipRemoto;
	}

	public static boolean testIP(String ipAutorizzati, String ipClient){
		String[] ipRemoto = null;

		if (ipClient==null){
			ipRemoto = "127.0.0.1".split("\\.");
		} else {
			ipRemoto = ipClient.split("\\.");
		}
		return testIP(ipAutorizzati, ipRemoto);
	}
	
	private static boolean testIP(String ipAutorizzati, String[] ipRemoto){
		boolean result = false;
		String[] st = null;
		String[] ipSplit = null;

		if (ipAutorizzati!= null &&
				!ipAutorizzati.trim().equals("")){
			st = ipAutorizzati.split(",");

			for (int x=0; x<st.length; x++){
				ipSplit = st[x].trim().split("\\.");
				if ((ipSplit[0].trim().equals("*") || ipSplit[0].trim().equals(ipRemoto[0].trim())) &&
						(ipSplit[1].trim().equals("*") || ipSplit[1].trim().equals(ipRemoto[1].trim())) &&
						(ipSplit[2].trim().equals("*") || ipSplit[2].trim().equals(ipRemoto[2].trim())) &&
						(ipSplit[3].trim().equals("*") || ipSplit[3].trim().equals(ipRemoto[3].trim()))){
					result = true;
				}
			}
		}
		return result;
	}

}
