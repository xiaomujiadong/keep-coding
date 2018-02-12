package com.saint.resource;


import java.util.Map;

/**
 * Created by wdcao on 2017/11/21.
 */
public class ClassInfo {
    private String className;
    private Class clazz;
    private String beanId;
    private Map<String, String> fieldMap;
    private Object object;
    private Map<String,Object> annotationMap;

    public Map<String, Object> getAnnotationMap() {
        return annotationMap;
    }

    public void setAnnotationMap(Map<String, Object> annotationMap) {
        this.annotationMap = annotationMap;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getClassName() {
        return className;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
