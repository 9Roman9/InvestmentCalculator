//Determine url with name of project
let form = document.querySelector('#form_delete');
let submit = document.querySelector("button");

function setURL(){
    form.action = "/investor/delete/" + document.querySelector("#name").value;
}

submit.onclick = setURL;