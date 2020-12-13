$(document).ready(function () {
    getAllUsers();
});

function getAllUsers() {
    fetch("rest/users").then(function (response) {
        response.json().then(function (data) {
            var userTable = $('#fillTableAllUsers tbody');
            userTable.empty();
            $(data).each(function (i, user) {
                userTable.append(
                    `<tr> 
                    <td class="font-weight-normal">${user.userId}</td> 
                    <td class="font-weight-normal">${user.firstName}</td> 
                    <td class="font-weight-normal">${user.lastName}</td> 
                    <td class="font-weight-normal">${user.age}</td> 
                    <td class="font-weight-normal">${user.email}</td> 
                    </tr>`);
            })
        });
    });
}