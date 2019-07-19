package cn.db.separate.config;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Cheng
 * @create 2019-07-09 10:35
 **/
public class DBContextHolder {

    /**
     * 确保创建的变量只被同一个线程进行读和写操作
     */
    private static final ThreadLocal<DBTypeEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 原子操作类,确保线程安全
     */
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    /**
     * 设置当前线程的枚举值
     * @param dbType
     */
    public static void set(DBTypeEnum dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    /**
     * 获取当前线程的枚举值
     * @return
     */
    public static DBTypeEnum get() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 移除当前线程的枚举值
     */
    public static void remove(){
        CONTEXT_HOLDER.remove();
    }

    /**
     * 切换到主库
     */
    public static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("切换到" + DBTypeEnum.MASTER);
    }

    /**
     * 切换到从库
     */
    public static void slave() {
        //  轮询，枚举值数量减去一个主库，表示从库数量
        int index = COUNTER.getAndIncrement() % (DBTypeEnum.values().length - 1);
        if (COUNTER.get() > 9999) {
            COUNTER.set(0);
        }
        set(DBTypeEnum.values()[index + 1]);
        System.out.println("切换到" + DBTypeEnum.values()[index + 1]);
    }
}
