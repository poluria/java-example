package com.poluria.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZooKeeperConnector implements Watcher, InitializingBean, DisposableBean {

    private ZooKeeper zooKeeper;
    private CountDownLatch latch = new CountDownLatch(1);
    private String connectString;
    private int sessionTimeout = 60000;
    private int connectTimeout = 10;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (connect() == false) {
            throw new BeanCreationException("fail to connect zookeeper..");
        }
    }

    private synchronized boolean connect() throws IOException, InterruptedException {
        if (isConnected()) {
            return true;
        }

        zooKeeper = new ZooKeeper(connectString, sessionTimeout, this);
        latch.await(connectTimeout, TimeUnit.SECONDS);
        return isConnected();
    }

    public boolean isConnected() {
        return zooKeeper != null && zooKeeper.getState().isAlive();
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }

    private synchronized void disconnect() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
            zooKeeper = null;
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.None && event.getState() == Event.KeeperState.SyncConnected) {
            latch.countDown();
        }
    }

    public ZooKeeper getZooKeeper() {
        if (!isConnected()) {
            throw new IllegalStateException("ZooKeeper is not connected.");
        }

        return this.zooKeeper;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
