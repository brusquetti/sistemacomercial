function buscarIdVenta() {
    var datosFormulario = $("#formPrograma").serialize();
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
            $("#id_venta").val(json.id_venta);
            $("#numero_factura_venta").val(json.numero_factura_venta);
            $("#ruc_venta").val(json.ruc_venta);
            $("#fecha_venta").val(json.fecha_venta);
            $("#estado_venta").val(json.estado_venta);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#id_tipopago").val(json.id_tipopago);
            $("#nombre_tipopago").val(json.nombre_tipopago);

            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#botonBuscarIdCliente").prop('disabled', false);

                siguienteCampo("#id_tipoventa", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                $("#botonBuscarIdCliente").prop('disabled', true);

                siguienteCampo("#id_tipoventa", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }
            if (json.estado_venta === "ANULADO"||json.estado_venta === "COBRADO") {
                $(".bloquearBoton").prop('disabled', true);
                $("#agregar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
            } else {
                $(".bloquearBoton").prop('disabled', false);
                 $("#agregar").prop('disabled', false);
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
function buscarNombreVenta() {
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
                $("#id_venta").val(id);
                $("#nombre_cliente").focus();
                buscarIdVenta();
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
function agregarVenta() {
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
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_venta").val(json.id_venta);
            buscarIdVenta();
            $("#id_venta").focus;
            $("#id_venta").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_venta").focus;
            $("#id_venta").select();
            buscarIdVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            //eliminarVentaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_venta").focus;
            $("#id_venta").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}


function buscarIdCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);

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
        url: 'jsp/buscarNombreCliente.jsp',
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



                siguienteCampo("#id_rol", "#botonAgregar", false);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //  $("#id_cliente").prop('disabled',true);

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
        url: 'jsp/buscarNombreUsuario.jsp',
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
function validarFormulario() {
    var valor = true;
    if ($("#nombre_venta").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Venta no puede estar vacio.");
        $("#nombre_venta").focus();
    }

    if ($("#nombre_cliente").val().length < 2) {
        valor = false;
        $("#mensajes").html("Cliente no puede estar vacio.");
        $("#id_cliente").focus();
    }

    if ($("#nombre_tipoventa").val().length < 2) {
        valor = false;
        $("#mensajes").html("Tipo Venta no puede estar vacio.");
        $("#id_tipoventa").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_venta").val("0");
    $("#nombre_venta").val("");
    $("#nombre_tipoventa").val("");
    $("#nombre_cliente").val("");
    $("#id_cliente").val("0");
    $("#id_tipocventa").val("0");
    $("#estado_venta").val("");
}
function agregarLinea() {
    $("#id_detalleventa").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#costo_venta").val("0");
    $("#iva_producto").val("");
    $("#cantidad_productoventa").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleventa", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalleventa").val(id);
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#costo_venta").val("0");
    $("#iva_producto").val("");
    $("#cantidad_productoventa").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdVentaDetalle();
    siguienteCampo("#cantidad_productoventa", "#botonModificarLinea", true);
}
// ventasproductos
function buscarIdVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            //$("#cantidad_existente").val(json.cantidad_existente);
            $("#costo_venta").val(json.costo_venta);
            $("#iva_producto").val(json.iva_producto);
            $("#cantidad_productoventa").val(json.cantidad_productoventa);
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
function agregarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        //url: 'jsp/anularVentaDetalle.jsp',
        url: 'jsp/eliminarVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//// productos
function buscarIdProducto() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProducto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            // $("#cantidad_existente").val(json.cantidad_existente);
            $("#costo_venta").val(json.costo_venta);
            $("#iva_producto").val(json.iva_producto);
            $("#stock_producto").val(json.stock_producto);


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
function buscarNombreProducto() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreProducto.jsp',
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
                $("#id_producto").val(id);
                $("#nombre_producto").focus();
                buscarIdProducto();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
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
function buscarCantidad() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCantidad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#cantidad_existente").val(json.cantidad_existente);
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

function validarFormulario() {
    var valor = true;
    if ($("#cantidad_productoventa").val().length < ("#cantidad_existente")) {
        valor = false;
        $("#mensajes").html("no puede estar vacio.");
        $("#cantidad_productoventa").focus();
    }
    return valor;
}
function validarFormularioVenta() {
    var valor = true;
    var cliente = $("#id_cliente").val();
    var pago = $("#id_tipopago").val();
    var prod = $("#id_producto").val();
    var cant = $("#cantidad_productoventa").val();
    var exi = $("#stock_producto").val();

    if (cliente === "0") {
        valor = false;
        $("#id_cliente").val("");
        $("#id_cliente").focus();
        $("#mensajes").html("Cliente no puede estar Vacio.");
    } else {
        if (pago === "0") {
            valor = false;
            $("#id_tipopago").val("");
            $("#id_tipopago").focus();
            $("#mensajes").html("Pago no puede estar vacio.");
        } else {
            if (prod === "0") {
                valor = false;
                $("#id_producto").val("");
                $("#id_producto").focus();
                $("#mensajes").html("Producto no puede estar vacio.");
            } else {
                if (cant === "0") {
                    valor = false;
                    $("#cantidad_productoventa").val("");
                    $("#cantidad_productoventa").focus();
                    $("#mensajes").html("Cantidad no puede estar vacia.");
                } else {
                    if (exi < cant) {
                        valor = false;
                        $("#cantidad_productoventa").val("");
                        $("#stock_producto").focus();
                        $("#mensajes").html("No puede ser mayor al stock");
                    }
                }
            }
        }

        return valor;
    }
}
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}
//function validarFormularioExistente() {
//    var valor = true;
//    var prod = $("#cantidad_existente").val();
//    var cant = $("#cantidad_productoventa").val();
//    if (cant <= prod) {
//        valor = false;
//        $("#cantidad_productoventa").val("");
//        $("#cantidad_productoventa").focus();
//        $("#mensajes").html("Cantidad no puede quedar nemos .");
//    }
//
//    return valor;
//}