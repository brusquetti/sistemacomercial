function agregarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarCampos();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_timbrado").focus();
        }
    });
}
function buscarIdTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_timbrado").val(json.id_timbrado);
             $("#numero_timbrado").val(json.numero_timbrado);
            $("#fecha_inicio").val(json.fecha_inicio);
            $("#fecha_vencimiento").val(json.fecha_vencimiento);
            $("#fecha_actual").val(json.fecha_actual);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            $("#desde").val(json.desde);
            $("#hasta").val(json.hasta);
            
            if(json.nuevo==="true"){
                $("#botonAgregar").prop('disabled',false);
                $("#botonModificar").prop('disabled',true);
                $("#botonEliminar").prop('disabled',true);
              
            }
            else{
                $("#botonAgregar").prop('disabled',true);
                $("#botonModificar").prop('disabled',false);
                $("#botonEliminar").prop('disabled',false);
                
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNumero_timbrado() {

    var datosFormulario = $("#formBuscar").serialize();
//alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNumero_timbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
            $("#contenidoBusqueda").css("display","none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_timbrado").val(id);
                $("#numero_timbrado").focus();
                buscarIdTimbrado();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros Error:" + e.status);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}
function modificarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarCampos();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos error" + e.status);
        },
        complete: function (objeto, exito, error) {
            limpiarCampos();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos error" + e.status);
        },
        complete: function (objeto, exito, error) {
            limpiarCampos();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
            if (exito === "success") {

            }
        }
    });



}

function limpiarCampos() {
    $("#id_timbrado").val("0");
    $("#numero_timbrado").val("0");
    $("#fecha_inicio").val("");
     $("#fecha_vencimiento").val("");
      $("#fecha_actual").val("");
    $("#telefono_vencimiento").val("");
    
    $("#id_timbrado").focus();
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
function buscarIdPuesto() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPuesto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos Error:" + e.status);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function buscarNombrePuesto() {

    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePuesto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
            //$("#contenidoBusqueda").css("display","none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_puesto").val(id);
                $("#nombre_puesto").focus();
                buscarIdPuesto();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros Error:" + e.status);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}
function buscarIdEstablecimiento() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstablecimiento.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            $("#actividad_economica").val(json.actividad_economica);
            $("#ruc").val(json.ruc);
            $("#representante").val(json.representante);
            $("#direccion").val(json.direccion);
            $("#telefono").val(json.telefono);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreEstablecimiento() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEstablecimiento.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_establecimiento").val(id);
                $("#nombre_establecimiento").focus();
                buscarIdEstablecimiento();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });

}
