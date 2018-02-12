package com.saint.aop.jdkproxy;

public class UserServiceImpl implements IUserService {
    public void add() {
        System.out.println("invoke userserviceimpl add method");
    }
}
