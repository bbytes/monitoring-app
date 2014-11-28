package com.bbytes.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbytes.dao.ServiceMonitorDao;
import com.bbytes.entity.CacheServiceMonitorEntity;
import com.bbytes.entity.DbServiceMonitorEntity;
import com.bbytes.entity.QueueServiceMonitorEntity;
import com.bbytes.entity.ServiceMonitorEntity;
import com.bbytes.entity.UrlServiceMonitorEntity;
import com.bbytes.scheduler.ServiceMonitorScheduler;
import com.bbytes.service.ServiceMonitor;

@Controller
@Transactional
public class ServiceMonitorController {

	
	
	
	@Autowired
	private ServiceMonitorScheduler serviceMonitorScheduler;

	@Resource
	private ServiceMonitorDao queueServiceMonitorDao;

	@Resource
	private ServiceMonitorDao cacheServiceMonitorDao;

	@Resource
	private ServiceMonitorDao sqlServiceMonitorDao;

	@Resource
	private ServiceMonitorDao urlServiceMonitorDao;

	@Resource
	private ServiceMonitor sqlServiceMonitor;

	@Resource
	private ServiceMonitor cacheServiceMonitor;

	@Resource
	private ServiceMonitor queueServiceMonitor;

	@Resource
	private ServiceMonitor urlServiceMonitor;
	
	
	
	

	@RequestMapping(value = "/SaveQueue", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String SaveQueue(
			@RequestBody QueueServiceMonitorEntity queueServiceMonitorEntity) {

		
	queueServiceMonitorDao.save(queueServiceMonitorEntity);
       
		return "user/home";

	}

	@RequestMapping(value = "/SaveCache", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String SaveCache(
			@RequestBody CacheServiceMonitorEntity cacheServiceMonitorEntity) {

		cacheServiceMonitorDao.save(cacheServiceMonitorEntity);
		return "user/home";

	}

	@RequestMapping(value = "/SaveDatabase", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public String SaveDatabase(
			@RequestBody DbServiceMonitorEntity dbServiceMonitorEntity) {

		sqlServiceMonitorDao.save(dbServiceMonitorEntity);
		
		return "user/home";

	}

	@RequestMapping(value = "/SaveUrl", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public List<ServiceMonitorEntity> SaveUrl(
			@RequestBody UrlServiceMonitorEntity urlServiceMonitorEntity) {

		urlServiceMonitorDao.save(urlServiceMonitorEntity);
		
		return urlServiceMonitorDao.getAllServices();

	}

	@RequestMapping(value = "/getAllServicesByUserId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceMonitorEntity> getAllServices(@RequestParam("userid") int userid) {
		System.out.println(userid);
		List<ServiceMonitorEntity> list = cacheServiceMonitor
				.getAllServices(userid);
		list.addAll(sqlServiceMonitor.getAllServices(userid));
		list.addAll(urlServiceMonitor.getAllServices(userid));
		list.addAll(queueServiceMonitor.getAllServices(userid));
		return list;
		
		 /** cacheServiceMonitor.isActive(); queueServiceMonitor.isActive();
		 * urlServiceMonitor.isActive(); sqlServiceMonitor.isActive();*/ 
		/*return "user/home";*/
		 
	}

	@RequestMapping(value = "/getSavedUrl", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceMonitorEntity> getSavedUrl(
			@RequestParam("userid") int userid) {
		System.out.println(userid);
		List<ServiceMonitorEntity> list = cacheServiceMonitorDao
				.getAllServices(userid);
		list.addAll(sqlServiceMonitorDao.getAllServices(userid));
		list.addAll(urlServiceMonitorDao.getAllServices(userid));
		list.addAll(queueServiceMonitorDao.getAllServices(userid));
		return list;

	}

	@RequestMapping(value = "/getSavedDatabase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceMonitorEntity> getSavedDatabase(
			@RequestParam("userid") int userid) {
		System.out.println(userid);
		List<ServiceMonitorEntity> list = cacheServiceMonitorDao
				.getAllServices(userid);
		list.addAll(sqlServiceMonitorDao.getAllServices(userid));
		list.addAll(urlServiceMonitorDao.getAllServices(userid));
		list.addAll(queueServiceMonitorDao.getAllServices(userid));
		return list;

	}

	@RequestMapping(value = "/getSavedCache", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceMonitorEntity> getSavedCache(
			@RequestParam("userid") int userid) {
		System.out.println(userid);
		List<ServiceMonitorEntity> list = cacheServiceMonitorDao
				.getAllServices(userid);
		list.addAll(sqlServiceMonitorDao.getAllServices(userid));
		list.addAll(urlServiceMonitorDao.getAllServices(userid));
		list.addAll(queueServiceMonitorDao.getAllServices(userid));
		return list;

	}

	@RequestMapping(value = "/getSavedQueue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceMonitorEntity> getSavedQueue() {
		
		List<ServiceMonitorEntity> list = cacheServiceMonitorDao.getAllServices();
		list.addAll(getAllServices());	
        return list;
	}

	
	@RequestMapping(value = "/getServicesById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceMonitorEntity> getServicesById(
			@RequestParam("userid") int userid, @RequestParam("id") int id,
			@RequestParam("services_name") String services_name) {
		System.out.println(services_name);

		try {

			if (services_name.equals("database")) {
				List<ServiceMonitorEntity> list = sqlServiceMonitorDao
						.getServiceById(id, services_name);

				
				/*List<ServiceMonitorEntity> list1 = sqlServiceMonitorDao
						.getServiceById(id, services_name);*/
				/*list.addAll(getAllServices());*/
				return list;
				
			}
			if (services_name.equals("cache")) {
				List<ServiceMonitorEntity> list = cacheServiceMonitorDao
						.getServiceById(id, services_name);

				return list;
			}
			if (services_name.equals("url")) {
				List<ServiceMonitorEntity> list = urlServiceMonitorDao
						.getServiceById(id, services_name);

				return list;
			}
			if (services_name.equals("queue")) {
				List<ServiceMonitorEntity> list = queueServiceMonitorDao
						.getServiceById(id, services_name);

				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	@RequestMapping(value = "/getAllServices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	
	
	public @ResponseBody List<ServiceMonitorEntity> getAllServices() {
	    try {
	    	/*JobExecutionContext context = null;
	    	serviceMonitorScheduler.execute(context);*/
	    	
	    	List<ServiceMonitorEntity> list1  =	serviceMonitorScheduler.getCacheServiceMonitorDao().getAllServices();
	    	list1.addAll(serviceMonitorScheduler.getQueueServiceMonitorDao().getAllServices());
	    	list1.addAll(serviceMonitorScheduler.getSqlServiceMonitorDao().getAllServices());
	    	list1.addAll(serviceMonitorScheduler.getUrlServiceMonitorDao().getAllServices());
	    	return list1;
	    } catch (Exception e) {
	       
	        e.printStackTrace();
	    }

	    return null;
	}
	

	@RequestMapping(value = "/updateServicesById", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceMonitorEntity> startServicesById(
			@RequestParam("services_name") String services_name,
			@RequestParam("id") int id,
			@RequestParam("isactive") boolean isactive) {
		System.out.println(services_name);
		if (services_name.equals("database") && isactive == false) {
			List<ServiceMonitorEntity> list = sqlServiceMonitorDao.saveActive(
					id, isactive);
			return list;
		}
		if (services_name.equals("cache") && isactive == false) {
			List<ServiceMonitorEntity> list = cacheServiceMonitorDao
					.saveActive(id, isactive);
			return list;
		}
		if (services_name.equals("queue") && isactive == false) {
			List<ServiceMonitorEntity> list = queueServiceMonitorDao
					.saveActive(id, isactive);
			return list;
		}
		if (services_name.equals("url") && isactive == false) {
			List<ServiceMonitorEntity> list = urlServiceMonitorDao.saveActive(
					id, isactive);
			return list;
		}
		if (services_name.equals("database") && isactive == true) {
			List<ServiceMonitorEntity> list = sqlServiceMonitorDao.stopActive(
					id, isactive);
			return list;
		}
		if (services_name.equals("cache") && isactive == true) {
			List<ServiceMonitorEntity> list = cacheServiceMonitorDao
					.stopActive(id, isactive);
			return list;
		}
		if (services_name.equals("queue") && isactive == true) {
			List<ServiceMonitorEntity> list = queueServiceMonitorDao
					.stopActive(id, isactive);
			return list;
		}
		if (services_name.equals("url") && isactive == true) {
			List<ServiceMonitorEntity> list = urlServiceMonitorDao.stopActive(
					id, isactive);
			return list;
		}
		return null;
	}
	
	@RequestMapping(value = "/deleteServicesById", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceMonitorEntity> deleteServicesById(
			@RequestParam("services_name") String services_name,
			@RequestParam("id") int id) {
		System.out.println(services_name);
		
		if (services_name.equals("cache") ) {
			List<ServiceMonitorEntity> list = cacheServiceMonitorDao.deleteService(id, services_name);
				
			list.addAll(getAllServices());
			return list;
		}
		if (services_name.equals("queue")) {
			List<ServiceMonitorEntity> list = queueServiceMonitorDao.deleteService(id, services_name);
			list.addAll(getAllServices());	
			return list;
			
		}
		if (services_name.equals("url")) {
			List<ServiceMonitorEntity> list = urlServiceMonitorDao.deleteService(id, services_name);
			list.addAll(getAllServices());
			return list;
		}
		if (services_name.equals("database")) {
			List<ServiceMonitorEntity> list = sqlServiceMonitorDao.deleteService(id, services_name);
			list.addAll(getAllServices());
			return list;
		}
		
		return null;
	}
	
	@RequestMapping(value = "/pingService", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean pingService(@RequestParam("services_name") String services_name,
			@RequestParam("id") int id, @RequestParam("ip") String ip, @RequestParam("port") String port, @RequestParam("url") String url){
		
		System.out.println(services_name);
		
		if (services_name.equals("cache") ) {
			try{
				cacheServiceMonitor.ping(id, services_name,ip,port,url);
				if(cacheServiceMonitor.ping(id, services_name,ip,port,url)==true){
					return true;
				}
				else{
					return false;
				}
				
			}
			 catch(Exception e){
				 e.printStackTrace();
				 return false;
			 }
					
			
		}
		if (services_name.equals("queue")) {
			try{
				queueServiceMonitor.ping(id, services_name,ip,port,url);
				if(queueServiceMonitor.ping(id, services_name,ip,port,url)==true){
					return true;
				}
				else{
					return false;
				}
				
			}
			 
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
		}
		if (services_name.equals("url")) {
			try{
			urlServiceMonitor.ping(id, services_name,ip,port,url);
			if (urlServiceMonitor.ping(id, services_name,ip,port,url)==true)
			{
				return true;
			}
			else{
				return false;
			}
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		if (services_name.equals("database")) {
			try{
			sqlServiceMonitor.ping(id, services_name,ip,port,url);
			if(sqlServiceMonitor.ping(id, services_name,ip,port,url)==true){
				return true;
			}
			else{
				return false;
			}
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	
}
