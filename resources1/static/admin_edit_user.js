function func(userId) {
    const uId = userId;
    const editUserURL = 'rest/users/' + uId
    console.log(userId)

    fetch(editUserURL)
        .then(response => response.json())
        .then((user => {
            $('#user-id-edit').attr('value', `${user.userId}`)
            $('#user-first-name-edit').attr('value', `${user.firstName}`)
            $('#user-last-name-edit').attr('value', `${user.lastName}`)
            $('#user-age-edit').attr('value', `${user.age}`)
            // $('#user-roles-edit').attr('value', `${user.rolesAsString}`)
            $('#button-user-edit').attr('onclick', editUser())
            console.log('edit activated')
            console.log(user)
        }))

    function editUser() {
        $('#user-roles-edit').attr('value', [])

        console.log("editUser activated")

        fetch(editUserURL, {
            method: 'PUT',
            body: JSON.stringify({
                email: "12345678987654321",
                password: "pass",
                firstName: $('#user-first-name-edit').val(),
                lastName: "201@mail.ru",
                age: 18,
                roles: [
                    {
                        nameOfRole: "ADMIN"
                    }
                ]
            }),
            headers: {'Content-type': 'application/json; charset=UTF-8'},
        })
    }


    // function editUser(userId) {
    //     const editUserURL = 'rest/users/' + userId
    //     $('#user-roles-edit').attr('value', [])
    //     console.log("editUser activated")
    //     fetch(editUserURL, {
    //         method: 'PUT',
    //         body: JSON.stringify({
    //             email: $('#userEmailEdit').val(),
    //             password: $('#user-password-edit').val(),
    //             firstName: $('#user-first-name-edit').val(),
    //             lastName: $('#user-last-name-edit').val(),
    //             age: $('#user-age-edit').val(),
    //             roles: [
    //                 {
    //                     nameOfRole: "ADMIN"
    //                 }
    //             ]
    //         }),
    //         headers: {'Content-type': 'application/json; charset=UTF-8'},
    //     })
    // }
}


