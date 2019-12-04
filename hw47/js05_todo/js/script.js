let btnCreate = document.querySelector(".btn-create");
let table = document.querySelector(".table");
let tbText = document.querySelector(".text");

btnCreate.addEventListener("click", function (e) {
    let description = tbText.value;
    //create main item
    let newItem = document.createElement("div");
    newItem.classList.add("item");

    //append checkbox
    let divCheckbox = document.createElement("div");
    divCheckbox.classList.add("check");
    let checkbox = document.createElement("input");
    checkbox.setAttribute("type", "checkbox");
    checkbox.addEventListener("change", function (e){
        let item = e.target.parentElement.parentElement;
        item.classList.toggle("ready");
    });
    divCheckbox.appendChild(checkbox);
    newItem.appendChild(divCheckbox);

    //append description
    let divDesc = document.createElement("div");
    divDesc.classList.add("description");
    let textNode = document.createTextNode(description);
    divDesc.appendChild(textNode);
    newItem.appendChild(divDesc);

    //append delete btn
    let divDelete = document.createElement("div");
    divDelete.classList.add("delete");
    let deleteBtn = document.createElement("a");
    deleteBtn.appendChild(document.createTextNode("Delete"));
    deleteBtn.addEventListener("click", function (e) {
        let item = e.target.parentElement.parentElement;
        item.remove();
    });
    divDelete.appendChild(deleteBtn);
    newItem.appendChild(divDelete);

    //append new item
    table.appendChild(newItem);
});