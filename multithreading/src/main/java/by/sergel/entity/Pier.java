package by.sergel.entity;

import by.sergel.exception.ProcessingShipException;
import by.sergel.util.PierIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Pier {
    private static final Logger logger = LogManager.getLogger();
    public static final int DEFAULT_SECONDS_SLEEPING_TIME = 2;
    public static final int DEFAULT_WORKING_TIME = 1;
    public static final int DEFAULT_MAX_LOADING_SIZE = 10;
    public static final int DEFAULT_TIMER_EXECUTION_PERIOD = 30000;

    private long id;
    private final Port port;
    private Timer timer;

    public Pier(Port port) {
        this.id = PierIdGenerator.generateId();
        this.port = port;
        this.timer = new Timer(Boolean.TRUE);
    }

    public long getId() {
        return id;
    }

    public void processShip(Ship ship) throws ProcessingShipException {
        int goods = 0;
        boolean flag = false;
        timer.schedule(new TimeOut(Thread.currentThread()), DEFAULT_TIMER_EXECUTION_PERIOD);
        try{
            while(!flag) {
                switch (ship.getTask()) {
                    case LOAD -> {
                        goods = ship.getMaxSize() - ship.getCurrentSize();
                        logger.info("-" + goods);
                        flag = port.get(goods);
                    }
                    case UNLOAD -> {
                        goods = ship.getCurrentSize();
                        logger.info("+" + goods);
                        flag = port.put(goods);
                    }
                }
                if (!flag) {
                    TimeUnit.SECONDS.sleep(DEFAULT_SECONDS_SLEEPING_TIME);
                }
            }
            int sleepingTime = DEFAULT_WORKING_TIME * goods / DEFAULT_MAX_LOADING_SIZE;
            TimeUnit.SECONDS.sleep(sleepingTime);
        } catch(InterruptedException e){
            logger.error("Interrupting thread when processing ship.", e);
            throw new ProcessingShipException(e);
        }
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder()
                .append("Pier{ id=").append(id)
                .append(", port=").append(port)
                .append(", timer=").append(timer).append(" }");
        return builder.toString();
    }

    private static class TimeOut extends TimerTask {
        private final Thread thread;

        public TimeOut(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            Timer timer = new Timer();
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
                timer.cancel();
            }
        }
    }
}
