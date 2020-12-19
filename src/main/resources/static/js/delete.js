function showDeleteUser(userId) {
    const userByIdURL = `rest/users/` + userId
    fetch(userByIdURL)
        .then(response => response.json())
        .then(user => {
            $('#userIdDelete').attr('value', `${user.userId}`)
            $('#userFirstNameDelete').attr('value', `${user.firstName}`)
            $('#userLastnameDelete').attr('value', `${user.lastName}`)
            $('#userAgeDelete').attr('value', `${user.age}`)
            $('#userEmailDelete').attr('value', `${user.email}`)
            $('#userRoleDelete').attr('value', `${user.rolesToString}`)
            $('#btnDelete').attr('onclick', `deleteUser('${user.userId}')`)
        })
}

function deleteUser(userId) {
    const userByIdURL = `rest/users/` + userId
    console.log('deleteUser activated')
    fetch(userByIdURL, {
        method: 'DELETE'
    })
    $(`#row-${userId}`).remove()
}