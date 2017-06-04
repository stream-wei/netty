package com.stream.io.taskVersion;

import java.util.concurrent.*;

/**
 * Created by stream on 2017/6/4.
 */
public class TimeServerHandlerExecutePool {
    
    private ExecutorService executor;
    
    public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize) {
        /**
         * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
         * 参数分别为:线程池中保持的线程数量,线程池允许的最大数量,线程池中线程的存活时间,存活时间的单位,存放线程的队列
         * RejectedExecutionHandler的rejectedExecution方法会在添加线程失败时调用
         */
        executor = new ThreadPoolExecutor(1, maxPoolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize), new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("添加线程失败");
            }
        });
    }
    
    public void execute(Runnable task){
        executor.execute(task);
    }
}
