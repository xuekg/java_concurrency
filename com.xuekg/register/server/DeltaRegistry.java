package register.server;

import java.util.LinkedList;


/**
 * 增量注册表
 * @author xuekg
 *
 */
public class DeltaRegistry {

	private LinkedList<register.client.CachedServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue;
	private Long serviceInstanceTotalCount;
	
	public DeltaRegistry(LinkedList<register.client.CachedServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue,
			Long serviceInstanceTotalCount) {
		this.recentlyChangedQueue = recentlyChangedQueue;
		this.serviceInstanceTotalCount = serviceInstanceTotalCount;
	}
	
	public LinkedList<register.client.CachedServiceRegistry.RecentlyChangedServiceInstance> getRecentlyChangedQueue() {
		return recentlyChangedQueue;
	}
	public void setRecentlyChangedQueue(LinkedList<register.client.CachedServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue) {
		this.recentlyChangedQueue = recentlyChangedQueue;
	}
	public Long getServiceInstanceTotalCount() {
		return serviceInstanceTotalCount;
	}
	public void setServiceInstanceTotalCount(Long serviceInstanceTotalCount) {
		this.serviceInstanceTotalCount = serviceInstanceTotalCount;
	}
	
}
