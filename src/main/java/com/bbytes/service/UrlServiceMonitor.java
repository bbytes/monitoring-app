package com.bbytes.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.Resource;










import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bbytes.dao.ServiceMonitorDao;
import com.bbytes.entity.ServiceMonitorEntity;

@Component
@Service
public class UrlServiceMonitor implements ServiceMonitor{

	@Resource
	ServiceMonitorDao urlServiceMonitorDao;
	

	
	@Override
	public List<ServiceMonitorEntity> getAllServices(long uid) {
		List<ServiceMonitorEntity> list = urlServiceMonitorDao.getAllServices(uid);
		return list;
	}



	/*@Override
	public List<ServiceMonitorEntity> ping(int id, String services_name, String ip) {
		String ipAddress = ip;
		try{
			 InetAddress inet = InetAddress.getByName(ipAddress);
			 System.out.println("sending ping request to " + ipAddress);
			  boolean status = inet.isReachable(5000);
			  
			  if(status){
				  System.out.println("Status : Host is reachable");
			  }
			  else
	            {
	                System.out.println("Status : Host is not reachable");
	            }
		}
		catch(UnknownHostException ue){
			ue.printStackTrace();
			System.out.println("Host does not exists");
		}
		
         catch(IOException ie){
			ie.printStackTrace();
			System.out.println("Problem in reaching the host");
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
*/


	@Override
	public  boolean ping(int id, String services_name,String ip,String port, String url) {
		    String  urls = url.replaceFirst("https", "http");

		    
		    try {
		    	HttpURLConnection   connection = (HttpURLConnection) new URL(urls).openConnection();
		        /*connection.setConnectTimeout(timeout);
		        connection.setReadTimeout(timeout);*/
		        connection.setRequestMethod("HEAD");
		        int responseCode = connection.getResponseCode();
		        if(200 <= responseCode && responseCode <= 399)
		        {
		        	connection.disconnect();
		        	return true;
		        	 
		        }
		        else{
		        	connection.disconnect();
		        	return false;
		        }
		        
		    } catch(MalformedURLException me){
		    	me.printStackTrace();
		    	 return false;
		    }
		    
		    catch (IOException exception) {
		    	exception.printStackTrace();
		        return false;
		    }
		   
	}

}
