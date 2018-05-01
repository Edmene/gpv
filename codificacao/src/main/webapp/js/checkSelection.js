function checkSelectedOptions() {
    if(document.getElementById("confirm-checkbox").checked){
        //document.getElementById("btn-confirmation").enable;
    }
    let days = ["Domingo", "Segunda", "Terca", "Quarta",
        "Quinta", "Sexta", "Sabado"];
    let shifts = ["Manha", "Tarde", "Noite"];
    let directions = ["Ida", "Volta"];
    let selections = [];
    for(let i=0; i<days.length; i++){
        for(let o=0; o<shifts.length; o++){
            for(let l=0; l<directions.length; l++){
                let divWithCheckbox = document.getElementById((days[i]+shifts[o]+directions[l]).toString());
                let options = divWithCheckbox.getElementsByTagName("input");
                let selectedOption = null;
                for(let option of options){
                    if(option.checked){
                        selectedOption = option;
                    }
                }
                if(selectedOption !== undefined && selectedOption !== null){
                    let options = selectedOption.value.split(",");
                    let selection = new Selection;
                    selection.day = i;
                    selection.shift = o;
                    selection.direction = l;
                    selection.driver = options[0];
                    selection.vehicle = options[1];
                    selection.stop = options[2];
                    selections.append(selection)
                }
            }
        }
    }
}