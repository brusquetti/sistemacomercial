function agregarRol(){
    var datosFormulario=$("#formPrograma").serialize();
    $.ajax({
        type:'POST',
        url:'jsp/agregar.jsp',
        data:datosFormulario,
        dataType:'json',
        beforeSend:function(objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success:function(json){
            limpiarCampos();
            $("#mensajes").html(json.mensaje);
        },
        error: function(e){
            $("#mensajes").html("No se puede gregar los datos, Error:"+e.status);
        },
        complete: function(objeto, exito,error){
            $("#id_rol").focus();
            $("#id_rol").select();
            if(exito==="success"){
                
            }
        }
    });
}

function buscarIdRol(){
    var datosFormulario= $("#formPrograma").serialize();
    
    $.ajax({
        type:'POST',
        url:'jsp/buscarId.jsp',
        data:datosFormulario,
        dataType:'json',
        beforeSend: function(objeto){
            $("#mensajes").html("Enviando daatos al servidor...");
        },
        success: function(json){
            $("#mensajes").html(json.mensaje);
            $("#id_rol").val(json.id_rol);
            $("#nombre_rol").val(json.nombre_rol);
            if(json.nuevo==="true"){
                $("#botonAgregar").prop('disabled',false);
                $("#botonModificar").prop('disabled',true);
                $("#botonEliminar").prop('disabled',true);
                siguienteCampo("#nombre_rol","#botonAgregar",false);
            }
            else{
                $("#botonAgregar").prop('disabled',true);
                $("#botonModificar").prop('disabled',false);
                $("#botonEliminar").prop('disabled',false);
                siguienteCampo("#nombre_rol","#botonAgregar",false);
            }
        },
        error: function(e){
            $("#mensajes").html("No se pudo recuperar los datos Error:"+e.status);
        },
        complete: function(objeto,exito,error){
            if(exito==="success"){
                
            }
        }
    });
}

function buscarNombreRol(){
   
    var datosFormulario= $("#formBuscar").serialize();
    
    $.ajax({
        type:'POST',
        url:'jsp/buscarNombre.jsp',
        data:datosFormulario,
        dataType:'json',
        beforeSend: function(objeto){
            $("#mensajes").html("Enviando daatos al servidor...");
            //$("#contenidoBusqueda").css("display","none");
        },
        success: function(json){
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function(){
                var id=$(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_rol").val(id);
                $("#nombre_rol").focus();
                buscarIdRol();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function(e){
            $("#mensajes").html("No se pudo buscar registros Error:"+e.status);
        },
        complete: function(objeto,exito,error){
            if(exito==="success"){
                
            }
        }
    });
}

function eliminarRol(){
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type:'POST',
        url:'jsp/eliminar.jsp',
        data:datosFormulario,
        dataType:'json',
        beforeSend: function(objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function(json){
            $("#mensajes").html(json.mensaje);
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos error"+e.status);
        },
        complete: function(objeto, exito,error){
           // limpiarCampos();
            $("#id_rol").focus();
            $("#id_rol").select();
            if(exito === "success"){
                
            }
        }
    });
}

function limpiarCampos(){
    $("#id_rol").val("0");
    $("#nombre_rol").val("");
    $("#id_rol").focus();
}

function modificarRol(){
     var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type:'POST',
        url:'jsp/modificar.jsp',
        data:datosFormulario,
        dataType:'json',
        beforeSend: function(objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function(json){
            $("#mensajes").html(json.mensaje);
            limpiarCampos();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos error"+e.status);
        },
        complete: function(objeto, exito,error){
           // limpiarCampos();
            $("#id_rol").focus();
            $("#id_rol").select();
            if(exito === "success"){
                
            }
        }
    });
}
function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        return false;
    }
}
function soloNumeros(e){
	var key = window.Event ? e.which : e.keyCode();
	return (key >= 48 && key <= 57);
}