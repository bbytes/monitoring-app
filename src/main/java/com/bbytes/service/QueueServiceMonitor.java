package com.bbytes.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bbytes.dao.ServiceMonitorDao;
import com.bbytes.entity.ServiceMonitorEntity;
import com.bbytes.ping.PingQueue;



@Service
public class QueueServiceMonitor implements ServiceMonitor {

	@SuppressWarnings("restriction")
	@Resource
	ServiceMonitorDao queueServiceMonitorDao;

	@Autowired
	PingQueue pingQueue;

	@Override
	public List<ServiceMonitorEntity> getAllServices(long uid) {
		List<ServiceMonitorEntity> list = queueServiceMonitorDao
				.getAllServices(uid);
		return list;
	}

	@Override
	public boolean ping(int id, String services_name, String ip, String port,
			String url) {
		try {
			pingQueue.ping(ip, port);

			if (pingQueue.ping(ip, port) == true) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

}
