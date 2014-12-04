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
import com.bbytes.ping.PingCache;

@Service
public class CacheServiceMonitor implements ServiceMonitor {

	@Resource
	ServiceMonitorDao cacheServiceMonitorDao;

	@Autowired
	private PingCache pingCache;

	public List<ServiceMonitorEntity> getAllServices(long uid) {
		List<ServiceMonitorEntity> list = cacheServiceMonitorDao
				.getAllServices(uid);
		return list;
	}

	public boolean ping(int id, String services_name, String ip, String port,
			String url) {
		try {
			pingCache.ping(ip, port);

			if (pingCache.ping(ip, port) == "PONG") {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
