function buscarIdCaja() {
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
            $("#id_caja").val(json.id_caja);
            $("#fecha_apertura").val(json.fecha_apertura);
            $("#monto_inicial").val(json.monto_inicial);
            $("#estado_caja").val(json.estado_caja);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);

                siguienteCampo("#id_tipocaja", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#id_tipocaja", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
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
function buscarNombreCaja() {
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
                $("#id_caja").val(id);
                //    $("#nombre_proveedor").focus();
                buscarIdCaja();
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
function agregarCaja() {
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
            $("#id_caja").val(json.id_caja);
            buscarIdCaja();
            $("#id_caja").focus;
            $("#id_caja").select();

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
function modificarCaja() {
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
            $("#id_caja").focus;
            $("#id_caja").select();
            buscarIdCaja();
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
function eliminarCaja() {
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
            // eliminarCajaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_caja").focus;
            $("#id_caja").select();
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
function validarFormulario() {
    var valor = true;
    if ($("#monto_inicial").val().length < 3) {
        valor = false;
        $("#mensajes").html("Monto inicial no puede estar vacio.");
        $("#monto_inicial").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_caja").val("0");
    //$("#id_usuario").val("0");
    $("#estado_caja").val("");



}
function agregarLinea() {
    $("#id_detallecaja").val("0");
    $("#id_venta").val("0");
    $("#numero_factura_venta").val("");
    // $("#costo_producto").val("0");
    //  $("#iva_producto").val("");
    $("#importe").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_venta").focus();
    $("#id_venta").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detallecaja", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detallecaja").val(id);
    $("#id_venta").val("0");
    $("#numero_factura_venta").val("");
    $("#importe").val("0");
//    $("#iva_producto").val("");
//    $("#cantidad_productocaja").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_venta").focus();
    $("#id_venta").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdCajaDetalle();
    siguienteCampo("#cantidad_productocaja", "#botonModificarLinea", true);
}
// cajasproductos
function buscarIdCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_venta").val(json.id_venta);
            $("#numero_factura_venta").val(json.numero_factura_venta);
            $("#id_tipopago").val(json.id_tipopago);
            $("#nombre_tipopago").val(json.nombre_tipopago);
            //$("#total").val(json.total);
            $("#importe").val(json.importe);
            //$("#vuelto").val(json.vuelto);

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
function agregarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_caja = $("#id_caja").val();
    datosFormulario += "&id_caja=" + id_caja;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCaja();
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
function modificarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_caja = $("#id_caja").val();
    datosFormulario += "&id_caja=" + id_caja;

    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCaja();
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
function eliminarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_caja = $("#id_caja").val();
    datosFormulario += "&id_caja=" + id_caja;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarCajaDetalle.jsp',
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
            buscarIdCaja();

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
function buscarIdVenta() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_venta").val(json.id_venta);
            $("#numero_factura_venta").val(json.numero_factura_venta);
            $("#total").val(json.total);

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
        url: 'jsp/buscarNombreVenta.jsp',
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
                $("#numero_factura_venta").focus();
                $("#total").focus();
                buscarIdVenta();
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
function validarFormularioCaja() {

    var valor = true;

    var monto = $("#monto_inicial").val();
    // var cant = $("#cantidad_productocaja").val();

    if (monto === "0") {
        valor = false;

        $("#monto_inicial").val("");
        $("#monto_inicial").focus();
        $("#mensajes").html("Tipo pago no puede estar vacio.");

    }

    return valor;
}

function buscarIdTipoPago() {
    var datosFormulario = $("#formLinea").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPago.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tipopago").val(json.id_tipopago);
            $("#nombre_tipopago").val(json.nombre_tipopago);

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





function validarFormularioCaja() {

    var valor = true;
    var num = $("#id_venta").val();

    var prov = $("#id_tipopago").val();

    var total = $("#total").val();

    var prod = $("#importe").val();

    var im = $("#importe").val();

    // var cant = $("#cantidad_productocaja").val();

    if (prov === "0") {
        valor = false;

        $("#id_tipopago").val("");
        $("#id_tipopago").focus();
        $("#mensajes").html("Tipo pago no puede estar vacio.");

    } else {

        if (num === "0") {
            valor = false;

            $("#id_venta").val("");
            $("#id_venta").focus();
            $("#mensajes").html("Venta no puede estar vacio.");

        } else {
            if (prod === "0") {
                valor = false;

                $("#importe").val("");
                $("#importe").focus();
                $("#mensajes").html("Importe no puede estar vacia.");
            } else {
                if (im < total) {
                    valor = false;

                    $("#importe").val("");
                    $("#importe").focus();
                    $("#mensajes").html("Importe no puede ser menor al total a pagar");
                }

            }

        }


    }
    return valor;
}

function buscarIdTipoPago() {
    var datosFormulario = $("#formLinea").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPago.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tipopago").val(json.id_tipopago);
            $("#nombre_tipopago").val(json.nombre_tipopago);

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

function buscarNombreTipoPago() {

    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePago.jsp',
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
                $("#id_tipopago").val(id);
                $("#nombre_tipopago").focus();
                buscarIdTipoPago();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
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
