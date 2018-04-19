package com.saint.demo.design.model.prototype;

/**
 * 深浅复制的区别在于是否对于：当你重写clone方法的时候，是否会对内嵌的对象一样的进行clone
 *
 * 会的话就是深复制，不会的话，就是浅复制
 */
public class Student implements Cloneable {
    public static void main(String[] args){
        Professor professor = new Professor("testProfessor", 40);
        Student student = new Student("zhangsan", 22, professor);

        Student student1 = (Student) student.clone();
        student.name = "lisi";
        professor.name = "testdsfasdfas";

        System.out.println("s1: "+student.toString());
        System.out.println("s2: "+student1.toString());
    }

    @Override
    public String toString(){
        return "name: "+name+". age: "+age+", p: "+professor.toString();
    }

    private String name;
    private int age;
    private Professor professor;

    public Student(String name, int age, Professor professor){
        this.name = name;
        this.age = age;
        this.professor = professor;
    }
    @Override
    public Object clone(){
        Student o = null;

        try{
            o = (Student)super.clone();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //去掉这个就是浅复制
        o.professor = (Professor) professor.clone();
        return o;

//        try {
//            return super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

}

class Professor implements Cloneable{
    String name;
    int age;
    public Professor(String name, int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String toString(){
        return "professoName: "+name+", professorAge: "+age;
    }
}
