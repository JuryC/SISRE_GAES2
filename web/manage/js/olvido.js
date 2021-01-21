function Olvido(){
  var correo=document.getElementById('correo').value;

  if (correo==='') {
    alert('Debe llenar todos los campos');
    return false;
  }
  else if (correo.length>20) {
    alert('El correo es muy extenso');
    return false;
  }

  if (correo==='admin@email.com') {
    alert('¡La contraseña fue enviada al correo satisfactoriamente!')
    window.location.href='H:/PROYECTO WEB/html/ingreso.html';
  }


}
