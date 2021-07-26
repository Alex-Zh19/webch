package com.epam.webch.controller.command;

import java.util.Optional;

public class Router {
    public enum RouterType {
        FORWARD, REDIRECT
    }

    private String pathToNextPage;
    private RouterType routerType;
    private Optional<CommandName> commandOptional = Optional.empty();

    public Router(String pathToNextPage, RouterType routerType) {
        this.pathToNextPage = pathToNextPage;
        this.routerType = routerType;
    }

    public Router(String pathToNextPage, RouterType routerType, CommandName command) {
        this.pathToNextPage = pathToNextPage;
        this.routerType = routerType;
        commandOptional = Optional.of(command);
    }

    public String getPathToNextPage() {
        return pathToNextPage;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public Optional<CommandName> getCommandOptional() {
        return commandOptional;
    }

}
