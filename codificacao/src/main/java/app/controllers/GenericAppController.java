package app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.javalite.activejdbc.Model;
import org.jetbrains.annotations.NotNull;


public abstract class GenericAppController implements CrudHandler {

    ObjectMapper mapper = new ObjectMapper();

    public void getAll(@NotNull Context ctx){}

    public void delete(@NotNull Context ctx, @NotNull String resourceId) {}

    public void update(@NotNull Context ctx, @NotNull String resourceId) {}

    public void create(@NotNull Context ctx) {}

    public void getOne(@NotNull Context ctx, @NotNull String resourceId){}

}
