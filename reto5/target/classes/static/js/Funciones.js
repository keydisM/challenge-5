function consultarCloud() {

    $.ajax({
        url:"http://localhost:8080/api/Cloud/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            CrearConsultaNube(respuesta);
        }

    });
}
function CrearConsultaNube(){

    let myTable =  `<table border="1" class="table">
                    <thead>
                    <tr>
                        <th>ID &nbsp;</th>
                        <th>NAME &nbsp;</th>
                        <th>BRAND &nbsp;</th>
                        <th>YEAR &nbsp;</th>
                        <th>DESCRIPTION &nbsp;</th>
                        <th>CATEGORY ID &nbsp;</th>
                        <th>CLIENTS &nbsp;</th>
                        <th>MESSAGES &nbsp;</th>
                    </tr>
                    </thead>`;

    for(let i=0; i<length;i++ ){
        myTable+="<tr>";
        myTable+="<td>"+[i].id+"</td>" + "&nbsp;";
        myTable+="<td>"+[i].name+"</td>" + "&nbsp;";
        myTable+="<td>"+[i].brand+"</td>"+"&nbsp;";
        myTable+="<td>"+[i].year+"</td>" +"&nbsp;";
        myTable+="<td>"+[i].description+"</td>"+"&nbsp;";
        myTable+="<td>"+[i].category+"</td>"+"&nbsp;";
        myTable+="<td>"+[i].client+"</td>"+"&nbsp;";
        myTable+="<td>"+[i].message+"</td>"+"&nbsp;";

        myTable+="<td> <button onclick='delete-cloud("+[i].id+")' class='btn btn-danger'>Eliminar</button> &nbsp;";
        myTable+="</tr>";
    }
    myTable+="<br></table>";
    $("#result-cloud").append(myTable);
}

function GuardarNube(){
    let Datos={
        id:$("#id").val(),
        name:$("#name").val(),
        brand:$("#brand").val(),
        year:$("#year").val(),
        category:$("#category").val(),
        description:$("#description").val(),
    };
    let dataToSend=JSON.stringify(Datos);
    $.ajax({
        url:"http://localhost:8080/api/Cloud/save",
        type:"POST",
        data: Datos,
        datatype:"JSON"
        success:function(respuesta){
            $("#result-cloud").empty();
            $("#id")val("");
            $("#name")val("");
            $("#brand")val("");
            $("#year")val("");
            $("#category")val("");
            $("#description")val("");
            consultarCloud();
            alert("Se ha guardado el registro con exito :)")
        }
    });
}