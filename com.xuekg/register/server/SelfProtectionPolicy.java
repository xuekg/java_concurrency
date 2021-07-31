package register.server;

/**
 * @author xuekg
 * @description 自我保护机制
 * @date 2021/7/31 15:04
 **/
public class SelfProtectionPolicy {
    private static SelfProtectionPolicy instance = new SelfProtectionPolicy();

    /**
     * 期望的一个心跳的次数，如果你有10个服务实例，这个数值就是10 * 2 = 20
     */
    private long expectedHeartbeatRate = 0L;
    /**
     * 期望的心跳次数的阈值，10 * 2 * 0.85 = 17，每分钟至少得有17次心跳，才不用进入自我保护机制
     */
    private long expectedHeartbeatThreshold = 0L;

    /**
     * 返回实例
     *
     * @return
     */
    public static SelfProtectionPolicy getInstance() {
        return instance;
    }

    public long getExpectedHeartbeatRate() {
        return expectedHeartbeatRate;
    }

    public void setExpectedHeartbeatRate(long expectedHeartbeatRate) {
        this.expectedHeartbeatRate = expectedHeartbeatRate;
    }

    public long getExpectedHeartbeatThreshold() {
        return expectedHeartbeatThreshold;
    }

    public void setExpectedHeartbeatThreshold(long expectedHeartbeatThreshold) {
        this.expectedHeartbeatThreshold = expectedHeartbeatThreshold;
    }

}
