function testUser(userNameInput) {

    $.getJSON("http://172.17.0.3:8080/gpv-1.0-SNAPSHOT/passenger/check", userNameInput.value, function (data) {
        if(data.allowed){
            document.getElementsByClassName("error_message").item(0).innerHTML = ""
        }
        else{
            if(userNameInput.value !== "") {
                document.getElementsByClassName("error_message").item(0).innerHTML = "nome: " + userNameInput.value + " indisponivel"
                userNameInput.value = "";
            }
        }
    });
}