package com.thoughtworks.interview.util;


import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReflectionBuilder implements InvocationHandler {

    private final Class cls;
    private Object instance;

    public <T> ReflectionBuilder (Class<T> userBuilderClass) {
        try {
            Method method = userBuilderClass.getMethod("build");
            this.instance = method.getReturnType().newInstance();
            this.cls = this.instance.getClass();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public static  <T> T builderFor (Class<T> userBuilderClass) {
        ReflectionBuilder builder = new ReflectionBuilder(userBuilderClass);
        return (T) Proxy.newProxyInstance(ReflectionBuilder.class.getClassLoader(), new Class[] {userBuilderClass}, builder);
    }


    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if(method.getName().equals("build")) {
            try {
                return instance;
            } finally {
                instance = cls.newInstance();
            }
        }
        Field field = null;
        try {
            field = cls.getDeclaredField(method.getName());
        } catch (NoSuchFieldException e) {
            if(method.getName().startsWith("with")) {
                field = cls.getDeclaredField(StringUtils.uncapitalize(method.getName().substring(3)));
            }
            Class superClass = cls.getSuperclass();
            while (superClass != null && field == null) {
                field = superClass.getDeclaredField(method.getName());
                superClass = superClass.getSuperclass();
            }
        }

        if(field == null) {
            throw new RuntimeException("Cannot determing field for builder method "+method.getName());
        }
        field.setAccessible(true);
        field.set(instance, objects[0]);

        return o;
    }
}
