/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var btnMenu = document.getElementById("menu-btn"); 
var sideMenu = document.getElementById("sidebar-container");
var nav = document.getElementById("menu-nav");
var contenido = document.getElementById("contenido");

btnMenu.addEventListener('click',function(){
    sideMenu.classList.toggle('active');
    nav.classList.toggle('active');
    contenido.classList.toggle('active');
});
