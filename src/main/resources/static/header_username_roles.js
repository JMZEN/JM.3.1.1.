fetch("/rest/users/principal")
    .then(response => response.json())
    .then(user => {
        $('#header_username').append(user.email)
        $('#header_user_roles').append(`${user.rolesAsString}`)
    });