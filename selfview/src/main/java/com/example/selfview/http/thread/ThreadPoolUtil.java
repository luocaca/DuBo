package com.example.selfview.http.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class ThreadPoolUtil {


    ThreadPoolExecutor poolExecutor;


    private Runnable mDownLoader;

    BlockingQueue<Runnable> mQueue;






    public static ExecutorService newFixThreadPool(int nThreads){
        return new ThreadPoolExecutor(nThreads,nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>());
    }





    public ThreadPoolUtil(Runnable runnable) {
        this.mDownLoader = runnable;
//        poolExecutor = new PausableThreadPoolExecutor();
        initThreadUtil();
    }








    private void initThreadUtil() {



//        poolExecutor = new ThreadPoolExecutor();
//        poolExecutor = new ThreadPoolExecutor(5, 10, 60000, TimeUnit.MILLISECONDS, )


    }


    public void startDownload() {

    }

//    ThreadFactory factory = new ThreadFactory() {
//        @Override
//        public Thread newThread(@NonNull Runnable r) {
//            return null;
//        }
//    };


}
