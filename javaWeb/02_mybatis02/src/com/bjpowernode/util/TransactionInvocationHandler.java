package com.bjpowernode.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler implements InvocationHandler {
    private Object target;//target:����

    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    //�������ҵ�񷽷�
    //���ĵģ���ף�����
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        SqlSession session = null;
        Object obj = null;
        try {
            session = SqlSessionUtil.getSession();
            //����ҵ���߼��������ı�׷�����
            obj = method.invoke(target, args);
            //���������,�ύ����
            session.commit();
        }catch(Exception e){
            //�����쳣���ع�
            session.rollback();
            e.printStackTrace();
        }finally{
            SqlSessionUtil.myClose(session);
        }
        return obj;
    }
    //��ȡ������Ķ���(����)
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }
}
