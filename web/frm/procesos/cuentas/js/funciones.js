function buscarIdCuenta() {
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
            $("#id_cuenta").val(json.id_cuenta);
            $("#fecha_apertura").val(json.fecha_apertura);
            $("#estado_cuenta").val(json.estado_cuenta);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);

                siguienteCampo("#id_tipocuenta", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
               // $("#fecha_apertura").prop('disabled', true);
                siguienteCampo("#id_tipocuenta", "#botonModificar", true);
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
function buscarNombreCuenta() {
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
                $("#id_cuenta").val(id);
                //    $("#nombre_proveedor").focus();
                buscarIdCuenta();
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
function agregarCuenta() {
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
            $("#id_cuenta").val(json.id_cuenta);
            buscarIdCuenta();
            $("#id_cuenta").focus;
            $("#id_cuenta").select();

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
function modificarCuenta() {
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
            $("#id_cuenta").focus;
            $("#id_cuenta").select();
            buscarIdCuenta();
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
function eliminarCuenta() {
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
            // eliminarCuentaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_cuenta").focus;
            $("#id_cuenta").select();
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
function validarFormularioCuenta() {

    var valor = true;
    var num = $("#id_compra").val();

    var prov = $("#id_modopago").val();
    
    var total = $("#total").val();
    
    var prod = $("#importe").val();
    
    var im = $("#importe").val();
    // var cant = $("#cantidad_productocuenta").val();

    if (prov === "0") {
        valor = false;

        $("#id_modopago").val("");
        $("#id_modopago").focus();
        $("#mensajes").html("Tipo pago no puede estar vacio.");

    } else {

        if (num === "0") {
            valor = false;

            $("#id_compra").val("");
            $("#id_compra").focus();
            $("#mensajes").html("Compra no puede estar vacio.");

        } else {
            if (prod === "0") {
                valor = false;

                $("#importe").val("");
                $("#importe").focus();
                $("#mensajes").html("Importe no puede estar vacia.");
            }else{
                 if (im < total ) {
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

function limpiarFormulario() {
    $("#id_cuenta").val("0");

   // $("#id_usuario").val("0");





}
function agregarLinea() {
    $("#id_detallecuenta").val("0");
    $("#id_compra").val("0");
   // $("#numero_factura_compra").val("");
    // $("#costo_producto").val("0");
    //  $("#iva_producto").val("");
    //  $("#cantidad_productocuenta").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_compra").focus();
    $("#id_compra").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detallecuenta", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detallecuenta").val(id);
    $("#id_compra").val("0");
 //   $("#numero_factura_compra").val("");
//    $("#costo_producto").val("");
//    $("#iva_producto").val("");
//    $("#cantidad_productocuenta").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_compra").focus();
    $("#id_compra").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdCuentaDetalle();
    siguienteCampo("#cantidad_productocuenta", "#botonModificarLinea", true);
}
// cuentasproductos
function buscarIdCuentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCuentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_compra").val(json.id_compra);
          
            $("#id_modopago").val(json.id_modopago);
            $("#nombre_modopago").val(json.nombre_modopago);
           // $("#total").val(json.total);
            $("#importe").val(json.importe);


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
function agregarCuentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_cuenta = $("#id_cuenta").val();
    datosFormulario += "&id_cuenta=" + id_cuenta;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCuentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCuenta();
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
function modificarCuentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_cuenta = $("#id_cuenta").val();
    datosFormulario += "&id_cuenta=" + id_cuenta;

    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarCuentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCuenta();
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
function eliminarCuentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_cuenta = $("#id_cuenta").val();
    datosFormulario += "&id_cuenta=" + id_cuenta;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarCuentaDetalle.jsp',
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
            buscarIdCuenta();

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
function buscarIdCompra() {
    var datosFormulario = $("#formLinea").serialize();
  //  alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCompra.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_compra").val(json.id_compra);
         
            $("#total").val(json.total);
//            $("#fecha_compra").val(json.fecha_compra);
//           // $("#id_modopago").val(json.id_modopago);
//            $("#estado_compra").val(json.estado_compra);
//            $("#id_cliente").val(json.id_cliente);
//            $("#nombre_cliente").val(json.nombre_cliente);
//            $("#ruc_cliente").val(json.ruc_cliente);
//            $("#contenidoDetalle").html(json.contenido_detalle);

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
        url: 'jsp/buscarNombreCompra.jsp',
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
               
                buscarIdCompra();
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
//function validarFormularioCuenta() {
//
//    var valor = true;
//    var num = $("#numero_factura_cuenta").val();
//
//
//    var prov = $("#id_proveedor").val();
//
//    var prod = $("#id_producto").val();
//    var cant = $("#cantidad_productocuenta").val();
//
//        if (prov === "0") {
//            valor = false;
//
//            $("#id_proveedor").val("");
//            $("#id_proveedor").focus();
//            $("#mensajes").html("Proveedor no puede estar vacio.");
//
//        } else {
//
//            if (prod === "0") {
//                valor = false;
//
//                $("#id_producto").val("");
//                $("#id_producto").focus();
//                $("#mensajes").html("Producto no puede estar vacio.");
//
//            } else {
//                if (cant === "0") {
//                    valor = false;
//
//                    $("#cantidad_productocuenta").val("");
//                    $("#cantidad_productocuenta").focus();
//                    $("#mensajes").html("Cantidad no puede estar vacia.");
//                }
//
//
//
//            }
//
//
//        }
//        return valor;
//    }

function buscarIdModoPago() {
    var datosFormulario = $("#formLinea").serialize();

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
    if ($("#nombre_categoria").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre categorias no puede estar vacio.");
        $("#nombre_categoria").focus();
    }
    return valor;
}