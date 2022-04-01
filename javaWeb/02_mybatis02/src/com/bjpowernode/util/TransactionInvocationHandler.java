package com.bjpowernode.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler implements InvocationHandler {
    private Object target;//target:张三

    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    //代理类的业务方法
    //李四的（表白）方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        SqlSession session = null;
        Object obj = null;
        try {
            session = SqlSessionUtil.getSession();
            //处理业务逻辑（张三的表白方法）
            obj = method.invoke(target, args);
            //事务处理完毕,提交事务
            session.commit();
        }catch(Exception e){
            //处理异常，回滚
            session.rollback();
            e.printStackTrace();
        }finally{
            SqlSessionUtil.myClose(session);
        }
        return obj;
    }
    //获取代理类的对象(李四)
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }
}
