/*
 *
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package com.dubbo;

import com.dubbo.api.OrderRESTService;
import com.dubbo.api.OrderService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RestConsumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/rest-consumer.xml"});
        context.start();

        OrderRESTService orderRESTService = context.getBean("orderRESTService",OrderRESTService.class);

        OrderService orderService = context.getBean("orderService", OrderService.class);

        System.out.println("consumer service start ......");
        while (true) {
            System.in.read();
            RpcContext rpcContext = RpcContext.getContext();
            rpcContext.setAttachment("clientName", "demoRest");
            rpcContext.setAttachment("clientImpl", "dubbo");
            System.out.println("SUCCESS: got order " + orderRESTService.getOrderInfo(1L));


            System.out.println("SUCCESS: got order list : " + orderService.getOrderInfo(1L));

        }

    }

}