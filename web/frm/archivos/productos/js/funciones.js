function agregarProducto() {
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
            $("#id_producto").focus();
            $("#id_producto").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_producto").focus();
        }
    });
}
function buscarIdProducto() {
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
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#costo_venta").val(json.costo_venta);
            $("#costo_compra").val(json.costo_compra);
            $("#iva_producto").val(json.iva_producto);
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);
            $("#id_categoria").val(json.id_categoria);
            $("#nombre_categoria").val(json.nombre_categoria);
            $("#cantidad_min").val(json.cantidad_min);
            $("#cantidad_max").val(json.cantidad_max);
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
function buscarNombreProducto() {
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
                $("#id_producto").val(id);
                $("#nombre_producto").focus();

                buscarIdProducto();
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
function modificarProducto() {
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
            $("#id_producto").focus();
            $("#id_producto").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarProducto() {
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
            $("#id_producto").focus();
            $("#id_producto").select();
            if (exito === "success") {

            }
        }
    });
}
function limpiarCampos() {
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#costo_venta").val("");
    $("#costo_compra").val("");
    $("#iva_producto").val("");
    $("#id_categoria").val("");
    $("#nombre_categoria").val("");
    $("#id_marca").val("");
    $("#nombre_marca").val("");
    $("#id_producto").focus();
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
function buscarIdCategoria(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCategoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_categoria").val(json.id_categoria);
            $("#nombre_categoria").val(json.nombre_categoria);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', false);
                $("#botonEliminar").prop('disable', false);
                siguienteCampo("#nombre_categoria", "#botonmodificar", true);
                
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {              
            }
        }
    });
}
function buscarNombreCategoria(){
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCategoria.jsp',
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
            $("tbody tr").on("click", function (){
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_categoria").val(id);
                $("#nombre_categoria").focus();
                buscarIdCategoria();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e){
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success"){
            }
        }
    });
    
}
function buscarIdMarca(){
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdMarca.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', false);
                $("#botonEliminar").prop('disable', false);
                siguienteCampo("#nombre_marca", "#botonmodificar", true);
                
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {              
            }
        }
    });
}
function buscarNombreMarca(){
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreMarca.jsp',
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
            $("tbody tr").on("click", function (){
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_marca").val(id);
                $("#nombre_marca").focus();
                buscarIdMarca();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e){
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success"){
            }
        }
    });
    
}
function soloNumeros(e){
	var key = window.Event ? e.which : e.keyCode();
	return (key >= 48 && key <= 57);
}
function validar(formulario){
      if (formulario.dato.value.length !== 10);
           { alert("Debe introducir una contraseña de 10 dígitos");
           }
}




