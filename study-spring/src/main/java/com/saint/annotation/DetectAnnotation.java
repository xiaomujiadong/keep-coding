package com.saint.annotation;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.saint.resource.ClasspathPackageScanner;

/**
 * Created by wdcao on 2017/11/21.
 */
public class DetectAnnotation {

    public List<Class> detectAnnotation(String packageName){
        List<Class> classAnnotationList = new ArrayList<Class>();
        ClasspathPackageScanner classpathPackageScanner = new ClasspathPackageScanner(packageName);

        try {
            List<Class> classList = classpathPackageScanner.getFullyQualifiedClass();

            if(classList!=null){
                for(Class clazz : classList){
                    Annotation[] annotations = clazz.getAnnotations();
                    if(annotations!=null && annotations.length > 0){
                        classAnnotationList.add(clazz);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classAnnotationList;
    }



    public List<Class> getAllClassesByPackageName(String packageName){
    //        URL url =
        return null;
    }

}
