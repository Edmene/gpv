package app.controllers;

import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.javalite.activejdbc.Base;
import org.jetbrains.annotations.NotNull;


public abstract class GenericAppController implements CrudHandler {

    GenericAppController(){
        Base.open();
    }

    public void getAll(@NotNull Context ctx){}

    public void delete(@NotNull Context ctx, @NotNull String resourceId) {}

    public void update(@NotNull Context ctx, @NotNull String resourceId) {}

    public void create(@NotNull Context ctx) {}

    public void getOne(@NotNull Context ctx, @NotNull String resourceId){}

}
