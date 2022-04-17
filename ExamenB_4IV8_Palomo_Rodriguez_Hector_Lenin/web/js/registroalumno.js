
function validarDatos(){
    var boleta = document.getElementById("boleta");
    var nombre = document.getElementById("name");
    var primerApellido = document.getElementById("apellido1");
    var segundoApellido = document.getElementById("apellido2");
    var semestre = document.getElementById("semestre");
    var turno = document.getElementById("turno");
    var contraseña = document.getElementById("password");
    
    if(boleta.value.trim()== ""){
        alert("Debes de ingresar tu boleta");
        return false; 
    }else{
        if(nombre.value.trim()== ""){
            alert("Debes de ingresar tu nombre");
            return false;
        }else{
            if(primerApellido.value.trim()==""){
                alert("Debes de ingresar tu primer apellido");
                return false;
            }else{
                    if(semestre.value.trim()==""){
                        alert("Debes de seleccionar tu semestre");
                        return false;
                    }else{
                        if(turno.value.trim()==""){
                            alert("Debes de seleccionar tu turno");
                            return false;
                        }else{
                            if(contraseña.value.trim()==""){
                                alert("Debes de crear una contraseña");
                                return false;
                        }
                    }
                }
            }
        }
    }
}
