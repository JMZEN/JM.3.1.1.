

function editPost(userId) {
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

            document.getElementById("updateUserModal").addEventListener("submit", fetchPatch(userId))
        })



    function fetchPatch(userId) {
        console.log(userId)
        const userByIdURL = `rest/users/` + userId

        $('#user-roles-edit').attr('value', [])

        console.log("editUser activated")

        fetch(userByIdURL, {
            method: 'PUT',
            body: JSON.stringify({
                password: "pa1ss",
                firstName: "he1llo",
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
}


// function func(userId){
//     console.log(userId)
// const userByIdURL = `rest/users/`+ userId
//
//     fetch(userByIdURL)
//         .then(response => response.json())
//         .then(user => {
//             $('#userIdEdit').attr('value', `${user.id}`)
//             $('#userNameEdit').attr('value', `${user.firstName}`)
//             $('#userLastnameEdit').attr('value', `${user.lastName}`)
//             $('#userDepartmentEdit').attr('value', `${user.department}`)
//             $('#userEmailEdit').attr('value', `${user.email}`)
//             $('#userLoginEdit').attr('value', `${user.username}`)
//             $('#userPasswordEdit').attr('value', `${user.password}`)
//             $('#editUserRole').attr('value', `${user.rolesToString}`)
//             $('#btnEdit').attr('onclick', editUser())
//         })
//
//     function editUser(){
//         $('#editUserRole').attr('value', [])
//         console.log("editUser activated")
//         fetch(userByIdURL,{
//             method: 'PUT',
//             body: JSON.stringify({
//                 id: $('#userIdEdit').val(),
//                 firstName: $('#userNameEdit').val(),
//                 lastName: $('#userLastnameEdit').val(),
//                 department: $('#userDepartmentEdit').val(),
//                 email: $('#userEmailEdit').val(),
//                 username: $('#userLoginEdit').val(),
//                 password: $('#userPasswordEdit').val(),
//                 roles: [
//                     {
//                         nameOfRole: "USER"
//                     }
//                 ]
//             }),
//             headers: {'Content-type': 'application/json; charset=UTF-8'},
//
//         })
//     }
// }
