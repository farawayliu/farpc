package com.ofcoder.farpc.demo.provider;

import com.ofcoder.farpc.cluster.ILoadbalance;
import com.ofcoder.farpc.cluster.LoadbalanceFactory;
import com.ofcoder.farpc.demo.api.IWelcome;
import com.ofcoder.farpc.registry.IRegistrar;
import com.ofcoder.farpc.registry.RegistrarFactory;
import com.ofcoder.farpc.rpc.netty.NettyConsumerProxy;
import com.ofcoder.farpc.rpc.netty.NettyProviderServer;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: yuanyuan.liu
 * @date: 2019/6/25 15:07
 */
public class SpiTest {
    @Test
    public void providerTest() throws IOException {
        NettyProviderServer server = new NettyProviderServer();
        server.init("192.168.0.110:20880");
        server.bind(IWelcome.class.getName(), new WelcomeImpl());
        server.publisher();
        System.in.read();
    }

    @Test
    public void consumerTest(){
        IRegistrar registrar = RegistrarFactory.getRegistrar();
        IWelcome welcome = NettyConsumerProxy.create(registrar, IWelcome.class);
        String far = welcome.greet("far");
        System.out.println(far);
    }

    @Test
    public void test() {
        ILoadbalance loadbalance = LoadbalanceFactory.getLoadbalance();
        IRegistrar registrar = RegistrarFactory.getRegistrar();
        System.out.println();
    }

}