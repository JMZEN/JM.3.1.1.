function cleanModal() {
    console.log('clean')
    $("#updateUserModal").trigger("reset");
}

function func(userId) {
    console.log(userId)
    const userByIdURL = `rest/users/` + userId
    fetch(userByIdURL)

        .then(response => response.json())
        .then(user => {
            $('#userIdEdit').attr('value', `${user.userId}`)
            $('#userNameEdit').attr('value', `${user.firstName}`)
            $('#userLastnameEdit').attr('value', `${user.lastName}`)
            $('#userAgeEdit').attr('value', `${user.age}`)
            $('#userEmailEdit').attr('value', `${user.email}`)
            $('#editUserRole').attr('value', `${user.rolesToString}`)

            $('#btnEdit').attr('onclick', `editUser('${user.userId}')`)
        })
}

function editUser(userId) {
    const editUserURL = `rest/users/` + userId
    $('#editUserRole').attr('value', [])
    console.log("editUser activated")
    fetch(editUserURL, {
        method: 'PUT',
        body: JSON.stringify({
            email: $('#userEmailEdit').val(),
            password: $('#userPasswordEdit').val(),
            firstName: $('#userNameEdit').val(),
            lastName: $('#userLastnameEdit').val(),
            age: $('#userAgeEdit').val(),
            roles: [
                {
                    nameOfRole: $('#editUserRole').val()[0]
                }
            ]
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'},
    })

    // updateUserTableRow(userId)
}

function updateUserTableRow(userId) {
    const userByIdURL = `rest/users/` + userId
    fetch(userByIdURL)

        .then(response => response.json())
        .then(user => {
            let temp = `<tr id="row-${userId}">
                    <td id="idEdit">${user.userId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.rolesAsString}</td>
                    
                    <td> 
                        <button class="btn btn-info" onclick="func('${user.userId}')" 
                        type="button" data-toggle="modal" data-target=#modalEditView>Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" onclick="showDeleteUser('${user.userId}')" 
                        type="button" data-toggle="modal" data-target=#modalDeleteView>Delete</button>
                    </td>
                </tr>`
            $('#mainTableBodyUsers').append(temp)
            console.log('done row')
        })
}