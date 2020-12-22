function cleanModal() {
    console.log('clean')
    document.getElementById('updateUserModal').reset();
    $("#updateUserModal").trigger("reset");
}

function updateUserModal(userId) {
    console.log(userId)

    document.getElementById('updateUserModal').reset();
    $("#updateUserModal").trigger("reset");
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
    }).then(function () {
        showAllUsers()
    })
}