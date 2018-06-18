function agregarProveedor() {
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
            $("#id_proveedor").focus();
            $("#id_proveedor").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_proveedor").focus();
        }
    });
}
function buscarIdProveedor() {
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
            $("#id_proveedor").val(json.id_proveedor);
            $("#ruc_proveedor").val(json.ruc_proveedor);
            $("#nombre_proveedor").val(json.nombre_proveedor);
            $("#correo_proveedor").val(json.correo_proveedor);
            $("#direccion_proveedor").val(json.direccion_proveedor);
            $("#telefono_proveedor").val(json.telefono_proveedor);
            $("#telefono2_proveedor").val(json.telefono2_proveedor);
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
function buscarNombreProveedor() {
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
                $("#id_proveedor").val(id);
                $("#nombre_proveedor").focus();

                buscarIdProveedor();
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
function modificarProveedor() {
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
            $("#id_proveedor").focus();
            $("#id_proveedor").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarProveedor() {
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
            $("#id_proveedor").focus();
            $("#id_proveedor").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_proveedor").val("0");
    $("#nombre_proveedor").val("");
    $("#correo_proveedor").val("");
    $("#direccion_proveedor").val("");
    $("#telefono_proveedor").val("");
    $("#telefono2_proveedor").val("");
    $("#correo_proveedor").val("");
    $("#ruc_proveedor").val("");
    $("#id_ciudad").val("");
    $("#nombre_ciudad").val("");
    $("#id_proveedor").focus();
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
function validar(formulario) {
    if (formulario.dato.value.length !== 10)
        ;
    {
        alert("Debe introducir una contraseña de 10 dígitos");
    }
}
function validarFormulario() {
    var valor = true;
    if ($("#ruc_proveedor").val().length < 1) {
        valor = false;
        $("#mensajes").html("proveedor no puede estar vacio.");
        $("#ruc_proveedor").focus();
    }
    if ($("#nombre_proveedor").val().length < 1) {
        valor = false;
        $("#mensajes").html("Nombre proveedor no puede estar vacio.");
        $("#nombre_proveedor").focus();
    }
    if ($("#correo_proveedor").val().length < 1) {
        valor = false;
        $("#mensajes").html("correo  no puede estar vacio.");
        $("#correo_proveedor").focus();
    }
    if ($("#direccion_proveedor").val().length < 1) {
        valor = false;
        $("#mensajes").html("Direccion  no puede estar vacio.");
        $("#direccion_proveedor").focus();
    }
    if ($("#telefono_proveedor").val().length < 1) {
        valor = false;
        $("#mensajes").html("Telefono no puede estar vacio.");
        $("#telefono_proveedor").focus();
    }
    if ($("#telefono2_proveedor").val().length < 1) {
        valor = false;
        $("#mensajes").html("telefono no puede estar vacio.");
        $("#telefono2_proveedor").focus();
    }
   

    return valor;
}




