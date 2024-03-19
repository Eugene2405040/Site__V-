const formButton = document.querySelector("form button")
formButton.innerText = "Choose"
formButton.onclick = (e) => {
    e.preventDefault()
    const newLocation = document.querySelector(".form-data").value
    newLocation != 'null' ?
        window.location.href += '/' + newLocation :
        alert('Select Page!')
}