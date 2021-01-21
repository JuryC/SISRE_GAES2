var Correcto = false;


function Enviar(){



    if(Registro.Name.value == ""){
        alert('No se pueden dejar campos vacios');
        Registro.Name.focus();
        return false;
    }

    if(Registro.Identification.value == ""){
        alert('No se pueden dejar campos vacios');
        Registro.Identification.focus();
        return false;
    }

    if(isNaN(Registro.Identification.value) || Registro.Identification.value.length != 10){
        alert('El número de documento no es válido');
        Registro.Identification.focus();
        return false;
    }

    if(Registro.Mail.value == ""){
        alert('No se pueden dejar campos vacios');
        Registro.Mail.focus();
        return false;
    }


    if(Registro.Password.value.length < 8){
        alert('La contraseña no cumple con los requisitos');
        Registro.Password.focus();
        return false;
    }

   Registro.submit();
   alert('Registro Exitoso')
   window.location.href='H:/PROYECTO WEB/html/ingreso.html';
}
