const url = '/api/admin'
const urlAdmin = '/admin'
const urlHead = '/api/header'
const header = document.getElementById('head')
const tBody = document.querySelector('tbody')
const delModal = document.getElementById('deleteModal')
const editModal = document.getElementById('editModal')
const newUser = document.getElementById('newuser')

const firstName = document.getElementById('firstName')
const lastName = document.getElementById('lastName')
const age = document.getElementById('age')
const email = document.getElementById('email')
const password = document.getElementById('password')
const roles = document.getElementById('roles')

const delId = document.getElementById('deleteId')
const delFirstName = document.getElementById('deleteFirstName')
const delLastName = document.getElementById('deleteSurName')
const delAge = document.getElementById('deleteAge')
const delEmail = document.getElementById('deleteEmail')
const delRoles = document.getElementById('deleteRoles')

const editId = document.getElementById('editId')
const editFirstName = document.getElementById('editFirstName')
const editLastName = document.getElementById('editSurName')
const editAge = document.getElementById('editAge')
const editEmail = document.getElementById('editEmail')
const editPassword = document.getElementById('editPassword')
const editRoles = document.getElementById('editRoles')


fetch(urlHead)
    .then(response => response.text())
    .then(data => header.innerText = data)
    .catch(err => console.log(err))


let res = ''
const fillingUserTable = (allUsers) => {
    allUsers.forEach(user => {
        res += `<tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.age}</td>
                            <td>${user.username}</td>
                            <td>${user.roles.map(r=>r.role.replace('ROLE_', ' '))}</td>
                            <td class="text-center"><button type="submit" class="btnEdit btn-info active" 
                                data-bs-toggle="modal" data-bs-target="#editModal">Изменить</button></td>
                            <td class="text-center"><button type="submit" class="btnDel btn-danger active" 
                                data-bs-toggle="modal" data-bs-target="#deleteModal">Удалить</button></td>
                        </tr>`
    })
    tBody.innerHTML = res
}

fetch(url)
    .then(response => response.json())
    .then(data => fillingUserTable(data))
    .catch(error => console.log(error))


const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}


let headers = new Headers();
headers.append('Content-Type', 'application/json; charset=utf-8');

let roleList = () => {
    let array = []
    let options = document.querySelector('#editRoles').options
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            let role = options[i].value
            array.push(role)
        }
    }
    return array;
}

on(document, 'click', '.btnEdit', e => {

    e.preventDefault()
    let target = e.target.parentNode.parentNode

    editId.value = target.children[0].innerHTML
    editFirstName.value = target.children[1].innerHTML
    editLastName.value = target.children[2].innerHTML
    editAge.value = target.children[3].innerHTML
    editEmail.value = target.children[4].innerHTML
    editRoles.value = roleList()
})


on(document, 'click', '.btnDel', e => {
    e.preventDefault()
    let target = e.target.parentNode.parentNode
    id =target.children[0].innerHTML

    delId.value = target.children[0].innerHTML
    delFirstName.value = target.children[1].innerHTML
    delLastName.value = target.children[2].innerHTML
    delAge.value = target.children[3].innerHTML
    delEmail.value = target.children[4].innerHTML
    delRoles.value = roleList()
})


delModal.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch(`${url}/${id}`, {
        method: 'DELETE',
    })
        .then(() => window.location.href = urlAdmin)
        .catch(error => console.log(error))
})


editModal.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch(`${url}`, {
        method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({
            id: editId.value,
            name: editFirstName.value,
            surname: editLastName.value,
            age: editAge.value,
            username: editEmail.value,
            password: editPassword.value,
            roles: roleList()
        })
    })
        .then(response => response.json())
        .then(() => window.location.href = urlAdmin)
        .catch(error => console.log(error))
})

let roleListNewUser = () => {
    let array = []
    let options = document.querySelector('#roles').options
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                let role = options[i].value
                array.push(role)
            }
        }
    return array;
}
newUser.addEventListener('submit', (e) => {
        e.preventDefault()
        fetch(`${url}`, {
            method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({
                name: firstName.value,
                surname: lastName.value,
                age: age.value,
                username: email.value,
                password: password.value,
                roles: roleListNewUser()
            })
        })
            .then(response => response.json())
            .then(() => window.location.href = urlAdmin)
            .catch(error => console.log(error))
    })