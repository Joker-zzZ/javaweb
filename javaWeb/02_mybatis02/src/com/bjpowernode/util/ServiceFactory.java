package com.bjpowernode.util;

public class ServiceFactory {

    //������������õ����Ķ���Ĺ���
    public static Object getService(Object service){
        return new TransactionInvocationHandler(service).getProxy();
    }
}
