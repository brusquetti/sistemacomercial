function agregarCliente() {
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
            $("#id_cliente").focus();
            $("#id_cliente").select();

        },

        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_cliente").focus();
        }
    });
}
function buscarIdCliente() {
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
            $("#id_cliente").val(json.id_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#apellido_cliente").val(json.apellido_cliente);
            $("#cedula_cliente").val(json.cedula_cliente);
            $("#fecha_nacimiento_cliente").val(json.fecha_nacimiento_cliente);
            $("#direccion_cliente").val(json.direccion_cliente);
            $("#telefono_cliente").val(json.telefono_cliente);
            $("#correo_cliente").val(json.correo_cliente);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);

            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);

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
function buscarCedula() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCedula.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#cedula_cliente").val(json.cedula_cliente);

            if (json.nuevo === "false") {
                $("#cedula_cliente").val("");
                $("#cedula_cliente").focus();
            } else {
                $("#direccion_cliente").focus();
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


function buscarNombreCliente() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
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
                $("#id_cliente").val(id);
                $("#nombre_cliente").focus();

                buscarIdCliente();
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
function modificarCliente() {
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
            $("#id_cliente").focus();
            $("#id_cliente").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarCliente() {
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
            $("#id_cliente").focus();
            $("#id_cliente").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_cliente").val("0");
    $("#nombre_cliente").val("");
    $("#apellido_cliente").val("");
    $("#cedula_cliente").val("");
    $("#fecha_nacimiento_cliente").val("");
    $("#direccion_cliente").val("");
    $("#telefono_cliente").val("");
    $("#correo_cliente").val("");
    $("#ruc_cliente").val("");
    $("#id_ciudad").val("");
    $("#nombre_ciudad").val("");
    $("#id_cliente").focus();
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
function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', false);
                $("#botonEliminar").prop('disable', false);
                siguienteCampo("#nombre_ciudad", "#botonmodificar", true);

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
function buscarNombreCiudad() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCiudad.jsp',
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
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
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
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}
//function validar(formulario){
//      if (formulario.dato.value.length !== 10);
//           { alert("Debe introducir una contraseña de 10 dígitos");
//           }
//}
function validarFormulario() {
    var valor = true;
    if ($("#ruc_cliente").val().length < 1) {
        valor = false;
        $("#mensajes").html("Ruc no puede estar vacio.");
        $("#ruc_cliente").focus();
    }
    if ($("#nombre_cliente").val().length < 1) {
        valor = false;
        $("#mensajes").html("Nombre cliente no puede estar vacio.");
        $("#nombre_cliente").focus();
    }
    if ($("#apellido_cliente").val().length < 1) {
        valor = false;
        $("#mensajes").html("Apellido cliente no puede estar vacio.");
        $("#apellido_cliente").focus();
    }
    if ($("#cedula_cliente").val().length < 1) {
        valor = false;
        $("#mensajes").html("Cedula  no puede estar vacio.");
        $("#cedula_cliente").focus();
    }
    if ($("#fecha_nacimiento_cliente").val().length < 1) {
        valor = false;
        $("#mensajes").html("Fecha De Nacimiento no puede estar vacio.");
        $("#fecha_nacimiento_cliente").focus();
    }
    if ($("#direccion_cliente").val().length < 1) {
        valor = false;
        $("#mensajes").html("Direccion no puede estar vacio.");
        $("#direccion_cliente").focus();
    }
    if ($("#telefono_cliente").val().length < 1) {
        valor = false;
        $("#mensajes").html("Telefono no puede estar vacio.");
        $("#telefono_cliente").focus();
    }
    if ($("#correo_cliente").val().length < 1) {
        valor = false;
        $("#mensajes").html("Correo no puede estar vacio.");
        $("#correo_cliente").focus();
    }

    if ($("#nombre_ciudad").val().length < 1) {
        valor = false;
        $("#mensajes").html("Nombre ciudad no puede estar vacio.");
        $("#nombre_ciudad").focus();
    }
    return valor;
}

