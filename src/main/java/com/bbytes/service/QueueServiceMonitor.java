package com.bbytes.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.Resource;










import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;












import com.bbytes.dao.ServiceMonitorDao;
import com.bbytes.entity.ServiceMonitorEntity;
import com.bbytes.ping.ReceiveQueue;
import com.bbytes.ping.SendQueue;

@Component
@Service
public class QueueServiceMonitor implements ServiceMonitor{
	
	@Resource
	ServiceMonitorDao queueServiceMonitorDao;
	
	@Autowired
	private SendQueue sendqueue;
	
	@Autowired
	private ReceiveQueue receivequeue;
	
    @Override
	public List<ServiceMonitorEntity> getAllServices(long uid) {
		List<ServiceMonitorEntity> list = queueServiceMonitorDao.getAllServices(uid);
		return list;
	}

	/*@Override
	public List<ServiceMonitorEntity> ping(int id, String services_name,String ip) {
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
	}*/

	@Override
	public boolean ping(int id, String services_name,String ip,String port,String url) {
		try {
			sendqueue.ping(ip,port);
			receivequeue.ping(ip,port);
			if(receivequeue.ping(ip,port)==true){
				return true;
			}
			else{
				return false;
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

	
	
}
