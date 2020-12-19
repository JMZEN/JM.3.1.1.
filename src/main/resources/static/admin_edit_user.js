function getUserForUpdate(userId) {
    console.log(userId)

    fetch('rest/users/' + userId).then(value => {
        return value.json();
    }).then((user => {
        $('#user-id-edit').attr('value', `${user.userId}`)
        $('#user-first-name-edit').attr('value', `${user.firstName}`)
        $('#user-last-name-edit').attr('value', `${user.lastName}`)
        $('#user-age-edit').attr('value', `${user.age}`)
        $('#user-roles-edit').attr('value', `${user.rolesAsString}`)
        $('#button-user-edit').attr('onclick', editUser(userId))
        console.log('edit activated')
        console.log(user)
    }))
}

function editUser(userId) {
    const editUserURL = 'rest/users/' + userId
    $('#user-roles-edit').attr('value', [])
    console.log("editUser activated")
    fetch(editUserURL, {
        method: 'PUT',
        body: JSON.stringify({
            email: $('#userEmailEdit').val(),
            firstName: $('#user-first-name-edit').val(),
            lastName: $('#user-last-name-edit').val(),
            age: $('#user-age-edit').val(),
            password: $('#user-password-edit').val(),
            roles: [
                {
                    nameOfRole: $('#user-roles-edit').val()
                }
            ]
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'},

    })
}
