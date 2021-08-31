package jvm;


import java.util.*;

/**
 * @author xuekg
 * @description
 * @date 2021/8/30 22:14
 **/
public class RedisLockDemo {

    public static void main(String[] args) {

        Long goodsSkuId = 1L;
        Long pucharseCount = 50L;

        Integer stockSegmentSeq = new Random().nextInt(10) + 1;

        InventoryDao dao = new InventoryDao();
        RLock lock = new RLock("stock_" + goodsSkuId + "_" + stockSegmentSeq);

        lock.lock();

        Long stock = dao.getStock(goodsSkuId, stockSegmentSeq);
        // TODO: 2021/8/30 感觉不是很靠谱
        if (stock == 0) {
            lock.unlock();
            boolean findOtherStockFlag = false;
            for (int i = 1; i <= 10; i++) {
                if (i == stockSegmentSeq) {
                    continue;
                }
                lock = new RLock("stock_" + goodsSkuId + "_" + i);
                lock.lock();
                stock = dao.getStock(goodsSkuId, i);
                if (stock != 0) {
                    stockSegmentSeq = i;
                    findOtherStockFlag = true;
                    break;
                } else {
                    lock.unlock();
                }
            }
            if (!findOtherStockFlag) {
                System.out.println("库存不够了 ");
                return;
            }
        }
        // TODO: 2021/8/30 走到这里的话，stock肯定不是0
        if (stock >= pucharseCount) {
            dao.updateStock(goodsSkuId, stockSegmentSeq, stock - pucharseCount);
            lock.unlock();
            return;
        }
        // TODO: 2021/8/30  当前分段的数量小于要购买的数量，此时需要合并分段加锁
        List<RLock> otherLocks = new ArrayList<>();
        Map<RLock, Long> lockMap = new HashMap<>();
        long totalStock = stock;

        for (int i = 1; i <= 10; i++) {
            if (i == stockSegmentSeq) {
                continue;
            }

            RLock otherlock = new RLock("stock_" + goodsSkuId + "_" + i);
            otherlock.lock();

            Long otherStock = dao.getStock(goodsSkuId, i);
            if (otherStock == 0) {
                otherlock.unlock();
                continue;
            }

            totalStock += otherStock;
            lockMap.put(otherlock, otherStock);

            if (totalStock >= pucharseCount) {
                break;
            }
        }
        // TODO: 2021/8/30 尝试所有的其他分段后，还是无法满足购买数量 将所有已经加过锁的段给释放了
        if (totalStock < pucharseCount) {
            System.out.println("库存不够了 ");
            for (Map.Entry<RLock, Long> otherLockEntry : lockMap.entrySet()) {
                RLock otherLock = otherLockEntry.getKey();
                otherLock.unlock();
            }
            return;
        }

        // TODO: 2021/8/30  先将最初加的那个分段库存扣减掉
        Long remainStock = pucharseCount - stock;
        dao.updateStock(goodsSkuId, stockSegmentSeq, 0L);
        lock.unlock();

        for (Map.Entry<RLock, Long> otherLockEntry : lockMap.entrySet()) {
            if(remainStock == 0){
                break;
            }
            RLock otherLock = otherLockEntry.getKey();
            Long otherStock = otherLockEntry.getValue();

            Integer integer = Integer.valueOf(otherLock.name.substring(otherLock.name.length() - 1));
            if(otherStock <= remainStock){
                remainStock -= otherStock;
                dao.updateStock(goodsSkuId, integer, 0L);
            }else{
                remainStock = 0L;
                dao.updateStock(goodsSkuId, integer, otherStock-remainStock);
            }
            otherLock.unlock();
        }
    }

    static class RLock {
        String name;

        public RLock(String name) {
            this.name = name;
        }

        public void lock() {
            System.out.println("加锁了");
        }

        public void unlock() {
            System.out.println("解锁了");
        }
    }

    static class InventoryDao {
        public Long getStock(Long goodsSkuId, Integer stockSegmentSeq) {
            return 999L;
        }

        public void updateStock(Long goodsSkuId, Integer stockSegmentSeq, Long stock) {
            System.out.println("更新商品库存");
        }
    }

}
