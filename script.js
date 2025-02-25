let tableData = [];

function cloneArray(data) {
    data.forEach(item => tableData.push(item));
}

window.fetch('data.json')
    .then(response => response.json())
    .then(data => populateTable(data))
    .catch(error => console.error('Error loading JSON:', error));

function populateTable(data) {
    if (tableData.length == 0) cloneArray(data);
    const tableBody = document.querySelector('tbody');

    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }

    tableData.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.company}</td>
            <td>${item.contact}</td>
            <td>${item.country}</td>
        `;
        tableBody.appendChild(row);
    });
}

function addRow(company, contact, country) {
    const newId = tableData.length ? tableData[tableData.length - 1].id + 1 : 1;
    const newRow = { "company": company, "contact": contact, "country": country };
    tableData.push(newRow);
}

document.addForm.onsubmit = function (event) {
    const company = this.company.value;
    const contact = this.contact.value;
    const country = this.country.value;

    addRow(company, contact, country);
    populateTable();
    event.preventDefault();
};

function sortTableByColumn(column) {
    tableData.sort((a, b) => {
        let valA = a[column].toLowerCase();
        let valB = b[column].toLowerCase();

        if (valA < valB) {
            return sortOrder[column] ? -1 : 1;
        } else if (valA > valB) {
            return sortOrder[column] ? 1 : -1;
        } else {
            return 0;
        }
    });

    sortOrder[column] = !sortOrder[column];
    populateTable(tableData);
}

document.querySelectorAll('thead th').forEach(th => {
    th.addEventListener('click', function () {
        const column = this.getAttribute('data-column');
        sortTableByColumn(column);
    });
});

let sortOrder = {
    company: true,
    contact: true,
    country: true
};
