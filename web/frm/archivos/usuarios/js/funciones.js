function agregarUsuario() {
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
            $("#id_usuario").focus();
            $("#id_usuario").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_usuario").focus();
        }
    });
}
function buscarIdUsuario() {
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
            $("#id_usuario").val(json.id_usuario);
            $("#nombre_usuario").val(json.nombre_usuario);
            $("#password_usuario").val(json.password_usuario);
            $("#login_usuario").val(json.login_usuario);
            $("#id_rol").val(json.id_rol);
            $("#nombre_rol").val(json.nombre_rol);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_formulario", "#codigo_formulario", false);
                siguienteCampo("#codigo_formulario", "#id_rol", false);
                siguienteCampo("#id_rol", "#botonAgregar", false);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#nombre_formulario", "#codigo_formulario", false);
                siguienteCampo("#codigo_formulario", "#id_rol", false);
                siguienteCampo("#id_rol", "#botonAgregar", false);
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
function buscarNombreUsuario() {
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
                $("#id_usuario").val(id);
                $("#nombre_usuario").focus();
                buscarIdUsuario();
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
function modificarUsuario() {
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
            $("#id_usuario").focus();
            $("#id_usuario").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarUsuario() {
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
            $("#id_usuario").focus();
            $("#id_usuario").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_usuario").val("0");
    $("#nombre_usuario").val("");
    $("#password_usuario").val("");
    $("#login_usuario").val("");
    $("#id_usuario").focus();
    $("#id_rol").val("");
    $("#nombre_rol").val("");
}
function buscarIdRol() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdRol.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_rol").val(json.id_rol);
            $("#nombre_rol").val(json.nombre_rol);

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

function buscarNombreRol() {

    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreRol.jsp',
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
                $("#id_rol").val(id);
                $("#nombre_rol").focus();
                buscarIdRol();
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
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}
function validarFormulario() {

    var valor = true;
    var pswd = $("#password_usuario").val();

    if (pswd === "") {
        valor = false;
        $("#password_usuario").val("");
        $("#password_usuario").focus();
        $("#mensajes").html("Clave Actual no puede estar vacio.");

    } else {
        if (pswd === "") {
            valor = false;

            $("#password_usuario").val("");
            $("#password_usuario").focus();
            $("#mensajes").html("Clave no puede estar vacia.");
        } else {
            if (pswd.length < 8) {
                valor = false;

                $("#password_usuario").val("");
                $("#password_usuario").focus();
                $("#mensajes").html("Clave Nueva ser superior a 8 caracteres.");
            } else {

                if (pswd.search(/[A-Z]/) === -1) {
                    valor = false;

                    $("#password_usuario").val("");
                    $("#password_usuario").focus();
                    $("#mensajes").html("Clave debe tener al menos una letra MAYUSCULA");
                } else {
                    if (pswd.search(/[a-z]/) === -1) {
                        valor = false;

                        $("#password_usuario").val("");
                        $("#password_usuario").focus();
                        $("#mensajes").html("Clave debe tener al menos una letra minuscula");
                    } else {
                        if (pswd.search(/[0-9]/) === -1) {
                            valor = false;

                            $("#password_usuario").val("");
                            $("#password_usuario").focus();
                            $("#mensajes").html("Clave debe tener al menos un número");
                        } else {
                            return valor;
                        }
                    }
                }
            }
        }
    }
}
function validarFormulario() {
    var valor = true;
    if ($("#nombre_usuario").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre usuario no puede estar vacio.");
        $("#nombre_usuario").focus();
    }

    if ($("#login_usuario").val().length < 2) {
        valor = false;
        $("#mensajes").html("Login no puede estar vacio.");
        $("#login_usuario").focus();
    }

    if ($("#nombre_rol").val().length < 2) {
        valor = false;
        $("#mensajes").html("Rol no puede estar vacio.");
        $("#nombre_rol").focus();
    }
    return valor;
}




