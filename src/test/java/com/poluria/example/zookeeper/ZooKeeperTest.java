package com.poluria.example.zookeeper;

import com.poluria.zookeeper.ZooKeeperConnector;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ZooKeeperTest {

    private ZooKeeperConnector zooKeeperConnector;

    @Before
    public void before() throws Exception {
        zooKeeperConnector = new ZooKeeperConnector();
        zooKeeperConnector.setConnectString("127.0.0.1:2181");
        zooKeeperConnector.afterPropertiesSet();
    }

    @After
    public void after() throws Exception {
        zooKeeperConnector.destroy();
    }

    @Test(expected = KeeperException.class)
    public void create() throws KeeperException, InterruptedException {
        ZooKeeper zooKeeper = zooKeeperConnector.getZooKeeper();
        zooKeeper.create("/notexist/node", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    @Test
    public void exists() throws KeeperException, InterruptedException {
        ZooKeeper zooKeeper = zooKeeperConnector.getZooKeeper();
        Assert.assertThat(zooKeeper.exists("/notexist", false), Matchers.nullValue());
    }
}
