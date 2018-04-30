package app.controllers;

import app.controllers.authorization.Protected;
import app.models.Destination;

@Protected
public class ReservationController extends GenericAppController {
    @Override
    public void index() {
        view("destinations", Destination.findAll());
    }
}
