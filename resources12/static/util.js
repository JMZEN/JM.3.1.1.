function setRoles(someRoles) {
    let roles = [];
    if (someRoles.indexOf("ROLE_ADMIN") >= 0) {
        roles.push({"id": 1});
    }
    if (someRoles.indexOf("ROLE_USER") >= 0) {
        roles.push({"id": 2});
    }
    return roles;
}

function inputUserAfterAdd(name) {
    fetch(url + "/" + name).then((res) => res.json())
        .then((user) => {
            let userRoles = "";
            for (let i = 0; i < user.rolesSet.length; i++) {
                userRoles += `${user.rolesSet[i].role} `
            }

            let output = `<tr id="${user.id}">`;
            output +=
                    `<td class="p-2" id="id${user.id}">${user.id}</td>
                    <td class="p-2" id="name${user.id}">${user.name}</td> 
                    <td class="p-2" id="character${user.id}">${user.character}</td>
                    <td class="p-2" id="iq${user.id}">${user.iq}</td>
                    <td class="p-2" id="roles${user.id}">${userRoles}</td>
                    <td class="p-2" style="width: 9%">
                            <a class="btn btn-primary text-white"
                        data-toggle="modal" data-target="#editUser" id="callModalEdit"
                        onclick="modalWindowEdit(${user.id})">Edit
                        </a>
                    </td>
                    <td class="p-2" style="width: 9%">
                        <a class="btn btn-danger text-white" role="button"
                        data-toggle="modal" data-target="#deleteUser" id="delete-post"
                        onclick="modalWindowDelete(${user.id})">Delete
                        </a>
                    </td>
                </tr>`
            ;
            $('#allUsers').append(output);
        })
}

function inputUserAfterEdit(name) {
    fetch(url + "/" + name).then((res) => res.json())
        .then((user) => {
            let userRoles = "";
            for (let i = 0; i < user.rolesSet.length; i++) {
                userRoles += `${user.rolesSet[i].role} `
            }

            let output = ``;
            output +=
                `<td class="p-2" id="id${user.id}">${user.id}</td>
                <td class="p-2" id="name${user.id}">${user.name}</td> 
                <td class="p-2" id="character${user.id}">${user.character}</td>
                <td class="p-2" id="iq${user.id}">${user.iq}</td>
                <td class="p-2" id="roles${user.id}">${userRoles}</td>
                <td class="p-2" style="width: 9%">
                        <a class="btn btn-primary text-white"
                    data-toggle="modal" data-target="#editUser" id="callModalEdit"
                    onclick="modalWindowEdit(${user.id})">Edit
                    </a>
                </td>
                <td class="p-2" style="width: 9%">
                    <a class="btn btn-danger text-white" role="button"
                    data-toggle="modal" data-target="#deleteUser" id="delete-post"
                    onclick="modalWindowDelete(${user.id})">Delete
                    </a>
                </td>`
            ;
            document.getElementById(user.id).innerHTML = output;
        })
}