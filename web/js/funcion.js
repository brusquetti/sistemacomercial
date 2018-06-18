/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function validarAcceso(){
$("#mensajes").html("Mensajes del Sistema");
if($("#login_usuario").val()===""){
$("#mensajes").html("Usuario no debe estar vacio. ");
}
else if($("#password_usuario").val()===""){
$("#mensajes").html("Clave no debe estar vacio. ");
}
else{
validarAccesoAjax();
}
}
function validarAccesoAjax(){
var datosFormulario=$("#formAcceso").serialize();
$.ajax({
type:'POST',
url:"jsp/validarAcceso.jsp",
data:datosFormulario,
dataType:'json',
beforeSend:function(objeto){
$("#mensajes").html("Enviando datos al servidor ...");
},
success: function(json){
if(json.acceso==="true"){
location.href="menu.html";
}
else{
$("#mensajes").html("Credencial Invalida");
}
},
error:function(e){
$("#mensajes").html("No se pudo conectar con el servidor Error:"+e.status);
},
complete:function(objeto,exito,error){
if(exito==="success"){
}
}
});
}
function siguienteCampo(actual,siguiente,preventDefault){
$(actual).keydown(function(event){
if(event.which===13){
if(preventDefault){
event.preventDefault();
}
$(siguiente).focus();
$(siguiente).select();
}
});
}
function verificarSesion(programa) {
var url = 'jsp/verificarSesion.jsp';
if (programa) {
url = '../../../jsp/verificarSesion.jsp';
}
var datosFormulario = $("#formAcceso").serialize();
$.ajax({
type: 'POST',
url: url,
data: datosFormulario,

dataType: 'json',
beforeSend: function (objeto) {
$("#mensajes").html("Enviando datos al Servidor ...");
},
success: function (json) {
if (json.activo === "false") {
if (programa) {
location.href = "../../../index.html";
} else {
location.href = "index.html";
}
}
$("#snombre_empresa").html("JAVA WEB");
$("#susuario_usuario").html(json.login_usuario);
$("#mensajes").html(json.mensaje);
},
error: function (e) {
$("#mensajes").html("ERROR: No se pudo recuperar la sesión.");
},
complete: function (objeto, exito, error) {
if (exito === "success") {
}
}
});
}
function cerrarSesion() {
var datosFormulario = $("#formAcceso").serialize();
$.ajax({
type: 'POST',
url: 'jsp/cerrarSesion.jsp',
data: datosFormulario,
dataType: 'json',
beforeSend: function (objeto) {
$("#mensajes").html("Enviando datos al Servidor ...");
},
success: function (json) {
$("#mensajes").html("Sesión Cerrada.");
},
error: function (e) {
$("#mensajes").html("No se pudo cerrar la sesión.");
},
complete: function (objeto, exito, error) {
if (exito === "success") {
}
}
});
}
