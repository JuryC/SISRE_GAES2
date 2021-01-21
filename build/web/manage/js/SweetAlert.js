/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function mensaje(titulo, texto, icono) {
    Swal.fire({title: titulo, text: texto, icon: icono, showConfirmButton: false, timer: 3000});
}
;
function AlertPopUp (titulo, texto, icono){
     Swal.fire({
        title: titulo,
        text: texto,
        icon: icono,
        confirmButtonText: 'Ok'
        
    });
};
function MensajeRedirect(link) {
    Swal.fire({
        title: 'Registro',
        text: 'El registro ha sido exitoso',
        icon: 'success',
        confirmButtonText: 'Ok'
        
    }).then(function (result) {
        console.log(result);
        if (result.value) {
            window.location.href = link;
        }
    });
};
function MensajeRedirect(link,titulo,texto,icono) {
    Swal.fire({
        title: titulo,
        text: texto,
        icon: icono,
        confirmButtonText: 'Ok'
        
    }).then(function (result) {
        console.log(result);
        if (result.value) {
            window.location.href = link;
        }
    });
};

function MensajeToast(title, icon) {
    Swal.fire({
        title: title,
        icon: icon,
        toast: true,
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        position: 'bottom-end',
        onOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    });
}
;
