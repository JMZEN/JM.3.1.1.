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