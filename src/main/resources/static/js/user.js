// const showUserURL = '/rest/users/principal';

fetch("/rest/users/principal")
    .then(response => response.json())
    .then(user => {
        let temp = `<tr>
                    <td>${user.userId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.rolesAsString}</td>
                </tr>`
        $('#mainTableBodyUser').append(temp)
    });


// fetch(showUserURL)
//     .then(response => response.json())
//     .then(user => {
//             $('#footerEmail').append(`${user.email}`)
//             $('#footerRoles').append(`${user.rolesToString}`)
//         if(!`${user.rolesToString}`.toLowerCase().includes('admin')){
//             hiddenLinkAdmin()
//         }
//             let temp = `<tr>
//                     <td>${user.id}</td>
//                     <td>${user.firstName}</td>
//                     <td>${user.lastName}</td>
//                     <td>${user.department}</td>
//                     <td>${user.email}</td>
//                     <td>${user.rolesToString}</td>
//                 </tr>`
//             $('#mainTableBodyUser').append(temp)
//     })
//
// function hiddenLinkAdmin(){
//     $('#adminLinkPanel').attr('hidden', `true`)
// }