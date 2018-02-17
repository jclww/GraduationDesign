package com.lww.design.graduation.common.threadpool;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPoolUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("threadPool");
    private static final int DEFAULT_MONITOR_PERIOD = 60;
    private int corePoolSize;
    private int maxPoolSize;
    private int monitorPeriod; // 监控时间间隔，单位：s

    private final static Integer DEFAULT_COREPOOLSIZE = 100;
    private final static Integer DEFAULT_MAXPOOLSIZE = 100;

    private AtomicLong rejectedTaskCount = new AtomicLong(0);
    private AtomicBoolean initFlag = new AtomicBoolean(false);
    private ThreadPoolExecutor threadPool;
    private ScheduledExecutorService monitor;
    private String bizName;
    private Runnable monitorTask = new Runnable() {
        @Override
        public void run() {
            try {
                int activeCount = threadPool.getActiveCount(); // 正在执行的任务数
                long completedTaskCount = threadPool.getCompletedTaskCount(); // 已完成任务数
                long totalTaskCount = threadPool.getTaskCount(); // 总任务数
                int largestPoolSize = threadPool.getLargestPoolSize(); // 最大线程数
                int poolSize = threadPool.getPoolSize();
                LOGGER.warn("active_thread:{}, waiting_thread:{}, completed_thread:{}, rejected_thread:{}, current_pool_size:{}, largest_pool_size:{}",
                        new Object[]{activeCount, totalTaskCount - activeCount - completedTaskCount, completedTaskCount, rejectedTaskCount.get(), poolSize, largestPoolSize});
            } catch (Exception e) {
                LOGGER.error("[SYSTEM-SafeGuard]Monitor thread run fail", e);
            }

        }
    };


    private static Map<String,ThreadPoolUtil> instances=new HashMap<>();

    /**
     * 通用的线程池提供方
     * @param bizName 业务名称
     * @return
     */
    public static ThreadPoolUtil getInstance(String bizName) {
        return getInstance(bizName, DEFAULT_COREPOOLSIZE, DEFAULT_MAXPOOLSIZE);
    }

    public static synchronized ThreadPoolUtil getInstance(String bizName, int corePoolSize, int maxPoolSize) {
        if (StringUtils.isBlank(bizName)) {
            return null;
        }
        if (instances.get(bizName) != null) {
            return instances.get(bizName);
        }

        ThreadPoolUtil threadPoolUtil=new ThreadPoolUtil(bizName);
        threadPoolUtil.setCorePoolSize(corePoolSize);
        threadPoolUtil.setMaxPoolSize(maxPoolSize);
        threadPoolUtil.init();
        instances.put(bizName,threadPoolUtil);
        return threadPoolUtil;
    }

    private ThreadPoolUtil(String bizName){
        LOGGER.info("new {} threadPool",bizName);
        this.bizName=bizName;
    }

    public void init() {
        if (!initFlag.compareAndSet(false, true)) {
            return; // 防止重复初始化
        }
        LOGGER.info("init thread pool, corePoolSize:" + corePoolSize + " maxPoolSize:" + maxPoolSize);

        threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                5L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1000), new NamedThreadFactory(bizName),
                new ThreadPoolExecutor.AbortPolicy()) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                LOGGER.debug("{} start.", t.getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                LOGGER.debug("{} finish.", Thread.currentThread().getName());
            }

            @Override
            protected void terminated() {
                super.terminated();
            }
        };

        monitorPeriod = monitorPeriod <= 0 ? DEFAULT_MONITOR_PERIOD : monitorPeriod;
        monitor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory(bizName + "-monitor", true));
        monitor.scheduleAtFixedRate(monitorTask, monitorPeriod, monitorPeriod, TimeUnit.SECONDS);
    }


    public <T> Future<T> submit(WorkTask<T> task) {
        if (task == null) {
            throw new NullPointerException();
        }
        LOGGER.warn("submit task:{}", task.getTaskName());
        return threadPool.submit(task);
    }

    public void submit(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        threadPool.submit(task);
    }

    public void destroy() {
        LOGGER.warn("start to stop thread pool");
        threadPool.shutdown();
        LOGGER.warn("finish to stop thread pool");
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}
