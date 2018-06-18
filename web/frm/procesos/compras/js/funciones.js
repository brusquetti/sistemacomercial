function buscarIdCompra() {
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
            $("#id_compra").val(json.id_compra);
            $("#numero_factura_compra").val(json.numero_factura_compra);
            $("#fecha_compra").val(json.fecha_compra);
            $("#fecha_emitida").val(json.fecha_emitida);
            //$("#timbrado_compra").val(json.timbrado_compra);
            $("#estado_compra").val(json.estado_compra);
            $("#id_proveedor").val(json.id_proveedor);
            $("#nombre_proveedor").val(json.nombre_proveedor);
            $("#ruc_proveedor").val(json.ruc_proveedor);
            $("#id_modopago").val(json.id_modopago);
            $("#nombre_modopago").val(json.nombre_modopago);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#botonBuscarIdProveedor").prop('disabled', false);
                siguienteCampo("#id_tipocompra", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                siguienteCampo("#id_tipocompra", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }
            if (json.estado_compra === "ANULADO") {
                $(".bloquearBoton").prop('disabled', true);
                $("#agregar").prop('disabled', true);
                $("#botonBuscarIdModoPago").prop('disabled', true);
                $("#botonBuscarIdProveedor").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
            } else {
                $(".bloquearBoton").prop('disabled', false);
                $("#agregar").prop('disabled', false);
                $("#botonBuscarIdModoPago").prop('disabled', false);
                $("#botonBuscarIdProveedor").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);


            }
            if (json.estado_compra === "ANULADO"||json.estado_compra === "PAGADO") {
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
function buscarNombreCompra() {
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
                $("#id_compra").val(id);
                $("#nombre_proveedor").focus();
                buscarIdCompra();
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
function agregarCompra() {
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
            $("#id_compra").val(json.id_compra);
            buscarIdCompra();
            $("#id_compra").focus;
            $("#id_compra").select();

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
function modificarCompra() {
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
            $("#id_compra").focus;
            $("#id_compra").select();
            buscarIdCompra();
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
function eliminarCompra() {
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
            //eliminarCompraDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_compra").focus;
            $("#id_compra").select();
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


function buscarIdProveedor() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProveedor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_proveedor").val(json.id_proveedor);
            $("#nombre_proveedor").val(json.nombre_proveedor);
            $("#ruc_proveedor").val(json.ruc_proveedor);
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
        url: 'jsp/buscarNombreProveedor.jsp',
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
function buscarIdModoPago() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdModo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_modopago").val(json.id_modopago);
            $("#nombre_modopago").val(json.nombre_modopago);
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

function buscarNombreModoPago() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreModo.jsp',
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
                $("#id_modopago").val(id);
                $("#nombre_modopago").focus();
                buscarIdModoPago();
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
function validarFormulario() {
    var valor = true;
    if ($("#numero_factura_compra").val().length < 3) {
        valor = false;
        $("#mensajes").html("N° de factura no puede estar vacio.");
        $("#numero_factura_compra").focus();
    }
    if ($("#nombre_proveedor").val().length < 3) {
        valor = false;
        $("#mensajes").html("Proveedor no puede estar vacio.");
        $("#nombre_proveedor").focus();
    }
    return valor;
}

function limpiarFormulario() {
    $("#id_compra").val("0");
    $("#numero_factura_compra").val("");
    $("#timbrado_compra").val("");
    $("#nombre_proveedor").val("");
    $("#id_proveedor").val("0");
    $("#id_tipoccompra").val("0");
    $("#estado_compra").val("");
}

function agregarLinea() {
    $("#id_detallecompra").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#costo_compra").val("0");
    $("#iva_producto").val("");
    $("#cantidad_productocompra").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detallecompra", "#botonAgregarLinea", true);
}

function editarLinea(id) {
    $("#id_detallecompra").val(id);
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#costo_compra").val("0");
    $("#iva_producto").val("");
    $("#cantidad_productocompra").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdCompraDetalle();
    siguienteCampo("#cantidad_productocompra", "#botonModificarLinea", true);
}
// comprasproductos
function buscarIdCompraDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCompraDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#costo_compra").val(json.costo_compra);
            $("#iva_producto").val(json.iva_producto);
            $("#cantidad_productocompra").val(json.cantidad_productocompra);
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

function agregarCompraDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_compra = $("#id_compra").val();
    datosFormulario += "&id_compra=" + id_compra;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCompraDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCompra();
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

function modificarCompraDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_compra = $("#id_compra").val();
    datosFormulario += "&id_compra=" + id_compra;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarCompraDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCompra();
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

function eliminarCompraDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_compra = $("#id_compra").val();
    datosFormulario += "&id_compra=" + id_compra;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarCompraDetalle.jsp',
        //url: 'jsp/anularCompraDetalle.jsp',
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
            buscarIdCompra();

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
//function eliminarDetalle() {
//    var datosFormulario = $("#formLinea").serialize();
//    var id_compra = $("#id_compra").val();
//    datosFormulario += "&id_compra=" + id_compra;
//    $.ajax({
//        type: 'POST',
//        url: 'jsp/eliminarDetalle.jsp',
//        //url: 'jsp/anularCompraDetalle.jsp',
//                data: datosFormulario,
//        dataType: 'json',
//        beforeSend: function (objeto) {
//            $("#mensajes").html("Enviando datos al Servidor ...");
//        },
//        success: function (json)
//        {
//            $("#mensajes").html(json.mensaje);
//            $("#panelLinea").fadeOut("slow");
//            $("#panelPrograma").fadeIn("slow");
//            buscarIdCompra();
//
//        },
//        error: function (e) {
//            $("#mensajes").html("No se pudo modificar los datos.");
//        },
//        complete: function (objeto, exito, error) {
//            if (exito === "success") {
//            }
//        }
//    });
//}
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
            $("#costo_compra").val(json.costo_compra);
            $("#iva_producto").val(json.iva_producto);


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
function validarFormulario() {
    var valor = true;
    if ($("#numero_factura_compra").val().length < 1) {
        valor = false;
        $("#mensajes").html("N° De factura no puede estar vacio.");
        $("#numero_factura_compra").focus();
    }
    return valor;
}
function validarFormularioCompra() {
    var valor = true;
    var num = $("#numero_factura").val();
    var prov = $("#id_proveedor").val();
    var prod = $("#id_producto").val();
    var cant = $("#cantidad_productocompra").val();
    if (num === "") {
        valor = false;
        $("#numero_factura").val("");
        $("#numero_factura").focus();
        $("#mensajes").html("Numero de Factura No puede estar Vacio.");
    } else {
        if (prov === "0") {
            valor = false;
            $("#id_proveedor").val("");
            $("#id_proveedor").focus();
            $("#mensajes").html("Proveedor no puede estar vacio.");
        } else {
            if (prod === "0") {
                valor = false;
                $("#id_producto").val("");
                $("#id_producto").focus();
                $("#mensajes").html("Producto no puede estar vacio.");
            } else {
                if (cant === "0") {
                    valor = false;
                    $("#cantidad_productocompra").val("");
                    $("#cantidad_productocompra").focus();
                    $("#mensajes").html("Cantidad no puede estar vacia.");
                }
            }
        }
    }
    return valor;
}
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}