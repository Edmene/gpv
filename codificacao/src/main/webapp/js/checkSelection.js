function checkSelectedOptions() {
    if(document.getElementById("confirm-checkbox").checked) {
        let days = ["Segunda", "Terca", "Quarta",
            "Quinta", "Sexta", "Sabado", "Domingo"];
        let shifts = ["Manha", "Tarde", "Noite"];
        let directions = ["Ida", "Volta"];
        let selections = [];
        for (let i = 0; i < days.length; i++) {
            for (let o = 0; o < shifts.length; o++) {
                for (let l = 0; l < directions.length; l++) {
                    let divWithCheckbox = document.getElementById((days[i] + shifts[o] + directions[l]).toString());
                    let options = divWithCheckbox.getElementsByTagName("input");
                    let selectedOption = null;
                    for (let option of options) {
                        if (option.checked) {
                            selectedOption = option;
                        }
                    }
                    if (selectedOption !== undefined && selectedOption !== null) {
                        let options = selectedOption.value.split(",");
                        let selection = {
                            day: i,
                            shift: o,
                            direction: l,
                            driverId: options[0],
                            vehicleId: options[1],
                            stopId: options[2]
                        };
                        selections.push(selection)
                    }
                }
            }
        }
        if(selections.length === 0){
            document.getElementById("confirm-checkbox").checked = false;
            alert("Para prosseguir pelo menos uma opcao deve ser selecionada.")
        }
        else {
            let json = document.getElementById("json");
            json.value = JSON.stringify(selections);
            document.getElementById("btn-confirmation").disabled = "";
        }
    }
    else {
        document.getElementById("btn-confirmation").disabled = "disabled";
    }
}