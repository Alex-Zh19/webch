package com.epam.webch.controller.command;

public class Router {
    public enum RouterType{
        FORWARD,REDIRECT
    }
    String pathToNextPage;
    RouterType routerType;

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
