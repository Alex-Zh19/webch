package com.epam.webch.controller.command;

public class Router {
    public enum RouterType{
        FORWARD,REDIRECT
    }
    private String pathToNextPage;
    private RouterType routerType;

    public Router (String pathToNextPage,RouterType routerType){
        this.pathToNextPage=pathToNextPage;
        this.routerType=routerType;
    }

    public String getPathToNextPage(){
        return pathToNextPage;
    }
    public RouterType getRouterType() {
        return routerType;
    }

}
