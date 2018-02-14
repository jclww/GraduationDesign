package com.lww.design.graduation.common.threadpool;

import java.util.concurrent.Callable;

public interface CrmTask<V> extends Callable<V> {
    /**
     * 返回本任务名字
     *
     * @return
     */
    public String getTaskName();
}
