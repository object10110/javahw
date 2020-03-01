function loadData() {
    let tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    let xhr = new XMLHttpRequest();
    let url = document.getElementById("baseUrl").getAttribute("value");
    url += "api/students";

    fetch(url, {method: 'GET'})
        .then(resp => {
            return resp.json();
        })
        .then(data => {
            let students = data; //JSON.parse(data);
            console.log(students);
            students.forEach((s) => {
                tbody.appendChild(createStudentTr(s));
            });
        })
        .catch(err => {
            // Do something for an error here
        });
}

function createStudentTr(s) {
    let tr = document.createElement("tr");
    for (let val in s) {
        let td = document.createElement("td");
        let textNodeVal = s[val];
        if (val === "group")
            textNodeVal = s[val]["name"];
        if (val !== "groupName") {
            td.appendChild(document.createTextNode(textNodeVal));
            if (val !== "id") {
                td.setAttribute("contentEditable", "true");
            }
            tr.appendChild(td);
        }
    }
    let td = document.createElement("td");
    let btnDel = document.createElement("button");
    btnDel.setAttribute('data-id', s.id);
    btnDel.appendChild(document.createTextNode('Delete'));
    addDeleteHandler(btnDel);

    let btnUpd = document.createElement("button");
    btnUpd.setAttribute('data-id', s.id);
    btnUpd.appendChild(document.createTextNode('Update'));
    addUpdateHandler(btnUpd);

    td.appendChild(btnUpd);
    td.appendChild(btnDel);
    tr.appendChild(td);
    return tr;
}

loadData();
addCreateHandler(document.querySelector("#create"));

function addDeleteHandler(btn) {
    let url = document.getElementById("baseUrl").getAttribute("value");
    btn.addEventListener("click", function () {
        let id = this.getAttribute('data-id');
        console.log("DELETE: " + id);
        url = url + "api/students/" + id;
        fetch(url, {method: 'DELETE'})
            .then(resp => {
                console.log("Ok");
                let tr = btn.parentElement.parentElement;
                tr.remove();
            }).catch(error => {
            console.error("Error remove: " + error);
        });
    });
}

function addCreateHandler(btn) {
    btn.addEventListener("click", function () {
        let url = document.getElementById("baseUrl").getAttribute("value");

        url = url + "api/students";
        let firstName = document.querySelector("#firstName").value;
        let lastName = document.querySelector("#lastName").value;
        let age = document.querySelector("#age").value;
        let group = document.querySelector("#group").value;

        fetch(url, {
            method: 'POST',
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Accept": "application/json"
            },
            body: "{" +
                `  \"firstName\": \" ${firstName}\",` +
                `  \"lastName\": \"${lastName}\",` +
                `  \"age\": ${age},` +
                "  \"group\": {" +
                "    \"id\": 0," +
                `  \"name\": \"${group}\"` +
                "  }" +
                "}"
        })
            .then(resp => {
                return resp.json();
            })
            .then(data => {
                let tbody = document.querySelector("tbody");
                tbody.appendChild(createStudentTr(data));
            })
            .catch(error => {
                console.error("Error remove: " + error);
            });
    });
}

function addUpdateHandler(btn) {
    btn.addEventListener("click", function () {
        let url = document.getElementById("baseUrl").getAttribute("value");
        url = url + "api/students";

        let tr = btn.parentElement.parentElement;

        tr.childNodes[0].value;

        let userId = parseInt(tr.childNodes[0].innerText);
        let firstName = tr.childNodes[1].innerText;
        let lastName = tr.childNodes[2].innerText;
        let age = parseInt(tr.childNodes[3].innerText);
        let group = tr.childNodes[4].innerText;

        fetch(url, {
            method: 'PUT',
            headers: {
                "Content-type": "application/json; charset=UTF-8",
                "Accept": "application/json"
            },
            body: "{" +
                `  \"id\": \" ${userId}\",` +
                `  \"firstName\": \" ${firstName}\",` +
                `  \"lastName\": \"${lastName}\",` +
                `  \"age\": ${age},` +
                "  \"group\": {" +
                "    \"id\": 0," +
                `  \"name\": \"${group}\"` +
                "  }" +
                "}"
        })
            .then(resp => {
                return resp.json();
            })
            .then(data => {
/*                let tbody = document.querySelector("tbody");
                tbody.appendChild(createStudentTr(data));*/
            })
            .catch(error => {
                console.error("Error remove: " + error);
            });
    });
}