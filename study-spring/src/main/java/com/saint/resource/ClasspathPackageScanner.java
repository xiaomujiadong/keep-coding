package com.saint.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**根据给的的包的路径，获取包下所有的.class文件
 * Created by wdcao on 2017/11/21.
 */
public class ClasspathPackageScanner {
    private String basePackage;
    private ClassLoader cl;

    public ClasspathPackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();

    }

    public ClasspathPackageScanner(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
    }

    public List<Class> getFullyQualifiedClass() throws IOException, ClassNotFoundException {
        List<String> classNameList = getFullyQualifiedClassNameList();

        List<Class> classList = new ArrayList<Class>();
        if(classNameList!=null){
            for(String className : classNameList){
                classList.add(Class.forName(className));
            }
        }
        return classList;
    }

    /**
     * 获取全部basePackage下所有的文件的路径名称，java形式的
     * @return
     * @throws IOException
     */
    public List<String> getFullyQualifiedClassNameList() throws IOException {
//        logger.info("开始扫描包{}下的所有类", basePackage);

        return doScan(basePackage, new ArrayList<String>());
    }

    /**
     * 真正执行扫描的地方
     * @param basePackage
     * @param nameList
     * @return
     * @throws IOException
     */
    private List<String> doScan(String basePackage, List<String> nameList) throws IOException {
        // replace dots with splashes
        String splashPath = StringUtils.dotToSplash(basePackage);

        // get file path
        URL url = cl.getResource(splashPath);
        String filePath = StringUtils.getRootPath(url);

        // Get classes in that package.
        // If the web server unzips the jar file, then the classes will exist in the form of
        // normal file in the directory.
        // If the web server does not unzip the jar file, then classes will exist in jar file.
        List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        if (isJarFile(filePath)) {
            // jar file
            System.out.println("是一个JAR包: "+ filePath);

            names = readFromJarFile(filePath, splashPath);
        } else {
            // directory
            System.out.println("是一个目录: "+filePath);

            names = readFromDirectory(filePath);
        }

        for (String name : names) {
            if (isClassFile(name)) {
                //nameList.add(basePackage + "." + StringUtil.trimExtension(name));
                nameList.add(toFullyQualifiedName(name, basePackage));
            } else {
                // this is a directory
                // check this directory for more classes
                // do recursive invocation
                //当路径是不是文件名的时候表是一个递归查找
                doScan(basePackage + "." + name, nameList);
            }
        }

//        for (String n : nameList) {
//            System.out.println("找到: "+ n);
//        }

        return nameList;
    }

    private String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(StringUtils.trimExtension(shortName));

        return sb.toString();
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        System.out.println("从JAR包中读取类: "+jarPath);

        List<String> nameList = new ArrayList<String>();
        JarInputStream jarIn = null;
        try{
            jarIn = new JarInputStream(new FileInputStream(jarPath));
            JarEntry entry = jarIn.getNextJarEntry();

            while (null != entry) {
                String name = entry.getName();
                if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                    nameList.add(name);
                }

                entry = jarIn.getNextJarEntry();
            }
        }catch (IOException e){
            throw e;
        }finally {
            if(jarIn != null){
                jarIn.close();
            }
        }

        return nameList;
    }

    /**
     * 根据目录读取所有的文件
     * @param path
     * @return
     */
    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    /**
     *判断是否是一个.class文件
     * @name 路径
     * @return
     */
    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    /**
     * 判断是否是一个.jar文件
     * @param name 路径
     * @return
     */
    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    public static void main(String[] args) throws Exception {
        ClasspathPackageScanner scan = new ClasspathPackageScanner("com.saint.ioc.bean");
        scan.getFullyQualifiedClassNameList();
    }
}
