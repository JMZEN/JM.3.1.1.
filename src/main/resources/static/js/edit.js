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
            id: $('#userIdEdit').val(),
            firstName: $('#userNameEdit').val(),
            lastName: $('#userLastnameEdit').val(),
            department: $('#userDepartmentEdit').val(),
            email: $('#userEmailEdit').val(),
            username: $('#userLoginEdit').val(),
            password: $('#userPasswordEdit').val(),
            roles: [
                {
                    nameOfRole: $('#editUserRole').val()[0]
                }
            ]
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'},

    })
}

// function showUpdateUser(userId) {
//     const userByIdURL = `rest/users/` + userId
//     fetch(userByIdURL)
//         .then(response => response.json())
//         .then(user => {
//             $('#userIdEdit').attr('value', `${user.userId}`)
//             $('#userNameEdit').attr('value', `${user.firstName}`)
//             $('#userLastnameEdit').attr('value', `${user.lastName}`)
//             $('#userAgeEdit').attr('value', `${user.age}`)
//             $('#userEmailEdit').attr('value', `${user.email}`)
//             $('#editUserRole').attr('value', `${user.rolesToString}`)
//             $('#btnEdit').attr('onclick', `editUser('${user.userId}')`)
//         })
// }
//
// function editUser(userId) {
//     const userByIdURL = `rest/users/` + userId
//     $('#editUserRole').attr('value', [])
//     console.log("editUser activated")
//     fetch(userByIdURL, {
//         method: 'PUT',
//         body: JSON.stringify({
//             email: $('#userEmailEdit').val(),
//             password: $('#userPasswordEdit').val(),
//             firstName: $('#userNameEdit').val(),
//             lastName: $('#userLastnameEdit').val(),
//             age: $('#userAgeEdit').val(),
//             roles: [
//                 {
//                     nameOfRole: $('#newUserRole').val()[0]
//                 }
//             ]
//         }),
//         headers: {'Content-type': 'application/json; charset=UTF-8'},
//     }).then(function (response) {
//         return response.text();
//     }).then(function (text) {
//         console.log(text);
//     })
// }


// function func(userId){
//     console.log(userId)
// const userByIdURL = `rest/users/`+ userId
// .finally(() => showAllUsers())
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

// fetch(editPost('z'), {
//     method: 'PUT',
//     body: JSON.stringify({
//         password: "pass",
//         firstName: $('#userNameEdit').val(),
//         lastName: "201@mail.ru",
//         age: 18,
//         roles: [
//             {
//                 nameOfRole: "ADMIN"
//             }
//         ]
//     }),
//     headers: {'Content-type': 'application/json; charset=UTF-8'},
// }).then(value => value.text())
//     .then(value => console.log(value))

// const myForm = document.getElementById("updateUserModal");
// myForm.addEventListener("submit", e => {
//     e.preventDefault();
//
//     console.log('fetch for update')
//     $('#user-roles-edit').attr('value', [])
//     console.log("editUser activated")

// const formData = new FormData(this);
//
// fetch(editPost('z'), {
//     method: 'PUT',
//     body: formData,
//     headers: {'Content-type': 'application/json; charset=UTF-8'},
// }).then(function (response) {
//     return response.text();
// }).then(function (text) {
//     console.log(text);
// })
// })
