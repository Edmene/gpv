package app.json;

import app.models.State;

public class StateJson {
    public Integer key;
    public String name,acronym;

    public StateJson(){}

    public StateJson(String name, String acronym){
        this.name = name;
        this.acronym = acronym;
    }

    public StateJson(State state){
        this.key = (Integer) state.getId();
        this.name = state.getString("name");
        this.acronym = state.getString("acronym");
    }

    public void setAttributesOfState(State state){
        state.set("id", this.key,
                "name", this.name,
                "acronym", this.acronym);
    }
}
