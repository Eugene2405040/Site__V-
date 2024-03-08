const button = document.querySelector("form button")
button.innerText = "Delete"
button.onclick = (event) =>{
    event.preventDefault();
    const categoryId = document.querySelector("#categoryId").value
    if (categoryId == "null"){
        alert('Choose Page !')
        return
    }
    fetch('/admin/delete/' + categoryId, {
        method: 'DELETE'
    }).then(response => {
        console.log("Status: " + response.status + ", Ok: " + response.ok)
        alert("Status: " + response.status + ", Ok: " + response.ok)
        location.reload()
    }).catch(error => {
        alert(error);
    });

}