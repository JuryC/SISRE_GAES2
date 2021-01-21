function Enviar(){
  var correo=document.getElementById('correo').value;
  var contraseña=document.getElementById('contraseña').value;


  if (correo==='' || contraseña==='') {
    alert('Debe llenar todos los campos');
    return false;
  }
  else if (correo.length>20) {
    alert('El correo es muy extenso');
    return false;
  }

  if (correo==='admin@email.com' && contraseña==='12345678') {
    alert('Bienvenido')
    window.location.href='H:/PROYECTO WEB/SI/Administrador/home.html';
  }
  if (correo==='operario@email.com' && contraseña==='12345678') {
    alert('Bienvenido')
    window.location.href='H:/PROYECTO WEB/SI/Operario/home_oper.html';
  }
  if (correo==='supervisor@email.com' && contraseña==='12345678') {
    alert('Bienvenido')
    window.location.href='H:/PROYECTO WEB/SI/Supervisor/home_super.html';
  }
  if (correo==='cliente@email.com' && contraseña==='12345678') {
    alert('Bienvenido')
    window.location.href='H:/PROYECTO WEB/SI/Cliente/home_clien.html';
  }


  else if (contraseña!=='12345678') {
    alert('La contraseña es incorrecta');
    return false;
  }
}
