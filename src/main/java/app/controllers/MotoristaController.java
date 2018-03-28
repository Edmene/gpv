package app.controllers;

import app.models.Motorista;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.DELETE;
import org.javalite.activeweb.annotations.POST;

public class MotoristaController extends AppController {
    public void index(){
        view("motoristas", Motorista.findAll().toMaps());
    }

    @POST
    public void create(){
        Motorista motorista = new Motorista();
        motorista.fromMap(params1st());
        if(!motorista.save()){
            flash("message", "Something went wrong, please  fill out all fields");
            flash("errors", motorista.errors());
            flash("params", params1st());
            redirect(MotoristaController.class, "new_form");
        }else{
            flash("message", "New book was added: " + motorista.get("nome"));
            redirect(MotoristaController.class);
        }
    }

    public void show(){
        //this is to protect from URL hacking
        Motorista m = (Motorista) Motorista.findById(getId());
        if(m != null){
            view("motorista", m);
        }else{
            view("message", "are you trying to hack the URL?");
            render("/system/404");
        }
    }

    @DELETE
    public void delete(){

        //Motorista m = (Motorista) Motorista.findById(getId());
        Integer id = Integer.valueOf(getId());
        Motorista m = (Motorista) Motorista.findBySQL("SELECT * FROM MOTORISTAS WHERE id = ?",id).get(0);

        String nome = m.getString("nome");
        m.delete();
        flash("message", "Usuario: '" + nome + "' was deleted");
        redirect(UsuarioController.class);
    }

    public void newForm(){}
}
