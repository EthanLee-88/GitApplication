package com.example.jiangnan.gitapplication.Util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Allen on 2016/5/4.
 */
public class ThreadPoolUtil {
    private static final int CORE_THREAD_COUNT = 12;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_THREAD_COUNT, 36, 30L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10));//Executors.newFixedThreadPool(CORE_THREAD_COUNT);

    private static ThreadPoolUtil threadPoolUtil = new ThreadPoolUtil();

    private ThreadPoolUtil() {}

    public static ThreadPoolUtil getInstance() {
        executor.allowCoreThreadTimeOut(true);
        return threadPoolUtil;
    }

    public void execute(Runnable runnable) {
        if (!executor.isShutdown()) {
            try {
                executor.execute(runnable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        executor.shutdown();
    }

    public void shutdownNow() {
        executor.shutdownNow();
        executor = new ThreadPoolExecutor(CORE_THREAD_COUNT, 36, 30L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10));//Executors.newFixedThreadPool(CORE_THREAD_COUNT);
    }

    public boolean isShutdown() {
        return executor.isShutdown();
    }

    public <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }
}
