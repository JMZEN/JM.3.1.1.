fetch('rest/users').then(value => {
    return value.json();
}).then((data) => console.log(data));