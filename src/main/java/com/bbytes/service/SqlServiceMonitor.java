package com.bbytes.service;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.bbytes.dao.ServiceMonitorDao;
import com.bbytes.entity.ServiceMonitorEntity;



@Service
public class SqlServiceMonitor implements ServiceMonitor {

	@Resource
	ServiceMonitorDao sqlServiceMonitorDao ;
	
	
	
	


	@Override
	public List<ServiceMonitorEntity> getAllServices(long uid) {
		
		List<ServiceMonitorEntity> list = sqlServiceMonitorDao.getAllServices(uid);
		return list;
	}


	@Override
	public boolean ping(int id, String services_name,String ip,String port,String url) {
		String ipAddress = ip;
		try{
			 InetAddress inet = InetAddress.getByName(ipAddress);
			 System.out.println("sending ping request to " + ipAddress);
			  boolean status = inet.isReachable(5000);
			  
			  if(status){
				  System.out.println("Status : Host is reachable");
				  return true;
			  }
			  else
	            {
	                System.out.println("Status : Host is not reachable");
	                return false;
	            }
		}
		catch(UnknownHostException ue){
			ue.printStackTrace();
			System.out.println("Host does not exists");
			 return false;
		}
		
         catch(IOException ie){
			ie.printStackTrace();
			System.out.println("Problem in reaching the host");
			 return false;
		}
		
		catch(Exception e){
			e.printStackTrace();
			 return false;
		}
		
	}


	
}


