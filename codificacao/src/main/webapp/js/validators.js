$(document).ready(function(){
    $('.cpf').mask('000.000.000-00');

    var maskBehavior = function (val) {
            return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
        },
        options = {onKeyPress: function(val, e, field, options) {
                field.mask(maskBehavior.apply({}, arguments), options);
            }
        };

    $('.phone').mask(maskBehavior, options);
    $('.hour').mask('00:00');
    $('.cnh').mask('00000000000');
    $('.license').mask('SSS-0000')

});

function testUser(userNameInput) {

    $.getJSON("http://172.17.0.3:8080/gpv-1.0-SNAPSHOT/passenger/check", userNameInput.value, function (data) {
        if(data.allowed){
            document.getElementsByClassName("error_message").item(0).innerHTML = ""
        }
        else{
            if(userNameInput.value !== "") {
                document.getElementsByClassName("error_message").item(0).innerHTML = "Nome de usuario: " + userNameInput.value + " indisponivel"
                userNameInput.value = "";
            }
        }
    });
}