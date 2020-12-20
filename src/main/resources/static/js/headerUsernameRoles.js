fetch("/rest/users/principal")
    .then(response => response.json())
    .then(user => {
        $('#headerUsername').append(user.email)
        $('#headerUserRoles').append(`${user.rolesAsString}`)
        if (!`${user.rolesAsString}`.includes('ADMIN')) {
            $('#adminLinkPanel').attr('hidden', 'true')
        }
    });