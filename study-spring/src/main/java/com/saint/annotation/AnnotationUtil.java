package com.saint.annotation;

import com.saint.resource.ClasspathPackageScanner;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdcao on 2017/11/21.
 */
public class AnnotationUtil {

    private static Map<String, Class> classMap = new HashMap<String, Class>();

    /**
     * 存储作用与类上的自定义注解
     */
    private static Map<String, Class> classTypeAnnotationMap = new HashMap<String, Class>();
    /**
     * 存储作用与属性上的自定义注解
     */
    private static Map<String, Class> fieldAnnotationMap = new HashMap<String, Class>();

    /**
     * 获取所有有注解的类
     * @param clazzList
     * @return
     */
    public static List<Class> getHasAnnotationClass(List<Class> clazzList){
        List<Class> list = new ArrayList<Class>();

        for(Class clazz : clazzList){
            Annotation[] annotations = clazz.getAnnotations();

            if(annotations == null || annotations.length <=0){
                continue;
            }

            list.add(clazz);
        }

        return list;
    }

    public static List<Class> getAllAnnotation(String annotationPackageName) throws IOException, ClassNotFoundException {
        ClasspathPackageScanner classpathPackageScanner = new ClasspathPackageScanner(annotationPackageName);

        List<String> classNameList = classpathPackageScanner.getFullyQualifiedClassNameList();
        if(classNameList != null){
            for(String className : classNameList){
                Class clazz = Class.forName(className);
                classMap.put(className, Class.forName(className));

                Target target = (Target) clazz.getAnnotation(Target.class);

                if(target!=null){
                    ElementType[] elementTypes = target.value();
                    for(ElementType elementType : elementTypes){
                        if(elementType == ElementType.TYPE){
                            classTypeAnnotationMap.put(className, clazz);
                        }else if(elementType == ElementType.FIELD){
                            fieldAnnotationMap.put(className, clazz);
                        }
                    }
                }
            }
        }

        return classpathPackageScanner.getFullyQualifiedClass();
    }
}
