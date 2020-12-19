function getUsers() {
    fetch("http://localhost:8088/allUsers")
        .then((res) => res.json())
        .then((data) => {
            let output = "";
            data.forEach(function (user) {

                let userRoles = "";
                for (let i = 0; i < user.rolesSet.length; i++) {
                    userRoles += `${user.rolesSet[i].role} `
                }

                output += `
                <tr id="${user.id}">
                <td class="p-2" id="id${user.id}">${user.id}</td>
                <td class="p-2" id="name${user.id}">${user.name}</td> 
                <td class="p-2" id="character${user.id}">${user.character}</td>
                <td class="p-2" id="iq${user.id}">${user.iq}</td>
                <td class="p-2" id="roles${user.id}">${userRoles}</td>
                <td class="p-2" style="width: 9%">
                <a class="btn btn-primary text-white"
                data-toggle="modal" data-target="#editUser" id="callModalEdit"
                onclick="modalWindowEdit(${user.id})">Edit</a>
                </td>
                <td class="p-2" style="width: 9%">
                <a class="btn btn-danger text-white" role="button"
                data-toggle="modal" data-target="#deleteUser" id="delete-post"
                onclick="modalWindowDelete(${user.id})">Delete</a>
                </td>
              </tr>
          `;
            });
            document.getElementById("allUsers").innerHTML = output;
        })
}

getUser()
getUsers()


