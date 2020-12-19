const url = "http://localhost:8088/getUser"

function getUser() {

    fetch(url).then((res) => res.json()).then((user) => {
            let userRoles = "";
            let output3 = "";
            let output4 = "";
            for (let i = 0; i < user.rolesSet.length; i++) {
                userRoles += `${user.rolesSet[i].role} `
            }

            if (userRoles.includes("ROLE_ADMIN")) {
                output3 +=
                    `<li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#adminPanel">Admin</a>
                    </li>`
                ;
            }
            if (userRoles.includes("ROLE_USER")) {
                output3 +=
                    `<li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#userAdmin" id="clickWhenRoleUser">User</a>
                    </li>`
                ;
            }
            if (userRoles.includes("ROLE_USER") && userRoles === "ROLE_USER ") {
                output3 =
                    `<li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#user" id="clickWhenRoleUser">User</a>
                    </li>`
                ;
                output4 =
                    `<div class="tab-pane fade show active" id="user">
                        <h2 class="text-left">User information page</h2>
                        <div class="card">
                            <div class="card-header font-weight-bolder">
                                About user
                            </div>
                            <div class="card-body">
                                <table class="table table-striped w-100">
                                    <thead>
                                        <tr>
                                            <th class="p-2">Id</th>
                                            <th class="p-2">Name</th>
                                            <th class="p-2">Character</th>
                                            <th class="p-2">Iq</th>
                                            <th class="p-2">Role</th>
                                        </tr>
                                    </thead>
                                    <tbody id="getUser">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>`
            }

            let output = "<tr>";
            output += `
                <td class="p-2">${user.id}</td>
                <td class="p-2">${user.name}</td>
                <td class="p-2">${user.character}</td>
                <td class="p-2">${user.iq}</td>
                <td class="p-2">${userRoles}</td>
            `;
            output += "<tr>";

            let output2 = "";
            output2 += `${user.name}  with roles: ${user.roles}`;

            document.getElementById("header").innerHTML = output2;
            document.getElementById("tabsAdminUser").innerHTML = output3;
            if (userRoles === "ROLE_USER ") {
                document.getElementById("tabsContent").innerHTML = output4;
                document.getElementById("clickWhenRoleUser").click();
            }
            document.getElementById("getUser").innerHTML = output;
        })
}

getUser()
