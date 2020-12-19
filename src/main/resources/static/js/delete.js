
function showDeleteUser(userId) {
    const userByIdURL = `rest/users/`+ userId
    fetch(userByIdURL)
        .then(response => response.json())
        .then(user => {
            $('#userIdDelete').attr('value', `${user.id}`)
            $('#userFirstNameDelete').attr('value', `${user.firstName}`)
            $('#userLastnameDelete').attr('value', `${user.lastName}`)
            $('#userDepartmentDelete').attr('value', `${user.department}`)
            $('#userEmailDelete').attr('value', `${user.email}`)
            $('#userLoginDelete').attr('value', `${user.username}`)
            $('#userPasswordDelete').attr('value', `${user.password}`)
            $('#userRoleDelete').attr('value', `${user.rolesToString}`)
            // $('#btnDelete').attr('onclick', `deleteUser(${user.id})`)
        })
}

function deleteUser(userId){
    const userByIdURL = `http://localhost:8080/admin/delete/${userId}`
    console.log('deleteUser activated')
    fetch(userByIdURL,{
        method: 'DELETE'
    })
    $(`#row-${userId}`).remove()
}