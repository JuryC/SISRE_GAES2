

//Control de las animaciones de la lista de convenciones
var btnCon = document.getElementById("btnCloseCon"),
        listElements = document.getElementsByClassName("list-group-item");

btnCon.addEventListener('click', function () {
    btnCon.classList.toggle("fa-angle-down");
    btnCon.classList.toggle("fa-angle-up");
    for (var i = 0; i < listElements.length; i++) {
        listElements[i].classList.toggle("activeItem");
    }
});
//Variables para los accionadores de los elementos 
var addL = document.getElementById("addList"),
        addP = document.getElementById("addParagraph"),
        addT = document.getElementById("addTitle");
//Variables para los contenedores de los elementos
var divL = document.getElementById("Lista"),
        divP = document.getElementById("Parrafo"),
        divT = document.getElementById("Titulo");

addL.addEventListener('click', function () {
    divL.classList.remove("Oculto");
    divP.classList.add("Oculto");
    divT.classList.add("Oculto");
});
addP.addEventListener('click', function () {
    divP.classList.remove("Oculto");
    divL.classList.add("Oculto");
    divT.classList.add("Oculto");
});
addT.addEventListener('click', function () {
    divT.classList.remove("Oculto");
    divP.classList.add("Oculto");
    divL.classList.add("Oculto");
});

//Variables para agregar items de la lista
var btnItems = document.getElementById("btnItem");
var nextItem = 0;
btnItems.addEventListener('click', function () {
    nextItem++;
    lista = '<li class="col-12 col-md-12" style="list-style: none; widht: 100%;">\n\
            <div class="row">\n\
                <div class="form-group col-12 col-md-1" style="display:flex; justify-content:center; align-items: center; font-size:2em;">\n\
                    <p>' + nextItem + '.</p></div><div class="form-group col-12 col-md-11">\n\
                    <input style="width:90%;" placeholder="Elemento" class="form-control itmList"/>\n\
                </div>\n\
            </div></li>';
    $("#Lista").append(lista);
});

//Variables para unificar la lista
var btnUnit = document.getElementById("btnItemUnit"),
        itemsList = document.getElementsByClassName("itmList"),
        inputFinal = document.getElementById("j_idt19:inputListFinal"),
        cantFinal = document.getElementById("j_idt19:cantItems");

btnUnit.addEventListener('click', function () {
    var itemListFinal = "";
    var cantItems = itemsList.length;
    for (var i = 0; i < itemsList.length; i++) {
        itemListFinal = itemListFinal + "<" + itemsList[i].value + ">";
    }
    inputFinal.value = itemListFinal;
    cantFinal.value = cantItems;
});