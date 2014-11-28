package com.bbytes.scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.bbytes.dao.ServiceMonitorDao;
import com.bbytes.entity.ServiceMonitorEntity;


public class ServiceMonitorScheduler extends QuartzJobBean {
	@Resource
	private ServiceMonitorDao sqlServiceMonitorDao;

	@Resource
	private ServiceMonitorDao cacheServiceMonitorDao;

	@Resource
	private ServiceMonitorDao queueServiceMonitorDao;

	@Resource
	private ServiceMonitorDao urlServiceMonitorDao;

	public void isActive(JobExecutionContext jobContext)
			throws JobExecutionException {
       List<ServiceMonitorEntity> serviceMonitorEntities = sqlServiceMonitorDao.getAllServices();
       for(ServiceMonitorEntity serviceMonitorEntity : serviceMonitorEntities){
    	  boolean isactive = serviceMonitorEntity.isIsactive();
    	  long id = serviceMonitorEntity.getId();
    	  if(isactive==true){
    		  sqlServiceMonitorDao.saveActive(id, isactive);
    	  }
    	  if(isactive==false){
    		  sqlServiceMonitorDao.stopActive(id, isactive);
    	  }
       }
		
	}

	

	public ServiceMonitorDao getSqlServiceMonitorDao() {
		return sqlServiceMonitorDao;
	}



	public void setSqlServiceMonitorDao(ServiceMonitorDao sqlServiceMonitorDao) {
		this.sqlServiceMonitorDao = sqlServiceMonitorDao;
	}



	public ServiceMonitorDao getCacheServiceMonitorDao() {
		return cacheServiceMonitorDao;
	}



	public void setCacheServiceMonitorDao(ServiceMonitorDao cacheServiceMonitorDao) {
		this.cacheServiceMonitorDao = cacheServiceMonitorDao;
	}



	public ServiceMonitorDao getQueueServiceMonitorDao() {
		return queueServiceMonitorDao;
	}



	public void setQueueServiceMonitorDao(ServiceMonitorDao queueServiceMonitorDao) {
		this.queueServiceMonitorDao = queueServiceMonitorDao;
	}



	public ServiceMonitorDao getUrlServiceMonitorDao() {
		return urlServiceMonitorDao;
	}



	public void setUrlServiceMonitorDao(ServiceMonitorDao urlServiceMonitorDao) {
		this.urlServiceMonitorDao = urlServiceMonitorDao;
	}



	@Override
	protected void executeInternal(JobExecutionContext jobContext)
			throws JobExecutionException {
		System.out.println("hi");
		@SuppressWarnings("unused")
		ServiceMonitorEntity serviceMonitorEntity;

		sqlServiceMonitorDao = (ServiceMonitorDao) jobContext.getJobDetail()
				.getJobDataMap().get("sqlServiceMonitorDao");
		jobContext.getJobDetail().setName("SqlServiceMonitor");
		sqlServiceMonitorDao.getAllServices();

		cacheServiceMonitorDao = (ServiceMonitorDao) jobContext.getJobDetail()
				.getJobDataMap().get("cacheServiceMonitorDao");
		jobContext.getJobDetail().setName("CacheServiceMonitor");
		cacheServiceMonitorDao.getAllServices();

		queueServiceMonitorDao = (ServiceMonitorDao) jobContext.getJobDetail()
				.getJobDataMap().get("queueServiceMonitorDao");
		jobContext.getJobDetail().setName("QueueServiceMonitor");
		queueServiceMonitorDao.getAllServices();

		urlServiceMonitorDao = (ServiceMonitorDao) jobContext.getJobDetail()
				.getJobDataMap().get("urlServiceMonitorDao");
		jobContext.getJobDetail().setName("UrlServiceMonitor");
		urlServiceMonitorDao.getAllServices();
		
		System.out.println(jobContext.getJobDetail().getFullName());
		
		
		  List<ServiceMonitorEntity> serviceMonitorEntities = sqlServiceMonitorDao.getAllServices();
	       for(ServiceMonitorEntity serviceMonitorEntitylist : serviceMonitorEntities){
	    	  boolean isactive = serviceMonitorEntitylist.isIsactive();
	    	  long id = serviceMonitorEntitylist.getId();
	    	  if(isactive==true){
	    		  sqlServiceMonitorDao.saveActive(id, isactive);
	    	  }
	    	  if(isactive==false){
	    		  sqlServiceMonitorDao.stopActive(id, isactive);
	    	  }
	       }
	}
		

	
}
