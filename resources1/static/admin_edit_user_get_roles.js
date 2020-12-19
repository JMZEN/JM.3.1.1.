fetch("/rest/users/roles")
    .then(response => response.json())
    .then(result => {
        for (role of result) {
            let temp = `<option value=${role.nameOfRole}> ${role.nameOfRole} </option>`
            $('#user-roles-edit').append(temp)
            $('#newUserRole').append(temp)
        }
    })