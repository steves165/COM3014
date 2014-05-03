
function deleteRow(r)
{
        var i=r.parentNode.parentNode.rowIndex;
        document.getElementById('skillsTable').deleteRow(i);
}
function deleteRow1(r)
{
        var i=r.parentNode.parentNode.rowIndex;
        document.getElementById('desiredSkillsTable').deleteRow(i);
}

function loaded() {
    document.getElementById("salary").value === null;
    // Commented so the form can be submitted until new validation is wired in
    // document.getElementById('submit').disabled = true;
}

var j = 0;

function validate() {
    if (document.getElementById("title").value === null) {
        document.getElementById("title").setAttribute("class", "wrong");
        return false;
    } else if (/^[a-zA-Z 0-9 ]+$/.test(document.getElementById("title").value)) {
        document.getElementById("title").setAttribute("class", "correct");
        return true;
    } else {
        document.getElementById("title").setAttribute("class", "wrong");
        return false;
    }

}

function validateSalary() {

    if (document.getElementById("salary").value === 0.0) {
        document.getElementById("salary").setAttribute("class", "wrong");
        return false;
    } else if (/^[0-9.]+$/.test(document.getElementById("salary").value)) {
        document.getElementById("salary").setAttribute("class", "correct");
        return true;
    } else {
        document.getElementById("salary").setAttribute("class", "wrong");
        return false;
    }

}

function validateCompany() {
    if (document.getElementById("company").value === null) {
        document.getElementById("company").setAttribute("class", "wrong");
        return false;
    } else if (/^[a-zA-Z 0-9 ]+$/.test(document.getElementById("company").value)) {
        document.getElementById("company").setAttribute("class", "correct");
        return true;
    } else {
        document.getElementById("company").setAttribute("class", "wrong");
        return false;
    }

}
function validateDept() {
    if (document.getElementById("dept").value === null) {
        document.getElementById("dept").setAttribute("class", "wrong");
        return false;
    } else if (/^[a-zA-Z 0-9 ]+$/.test(document.getElementById("dept").value)) {
        document.getElementById("dept").setAttribute("class", "correct");
        return true;
    } else {
        document.getElementById("dept").setAttribute("class", "wrong");
        return false;
    }

}
function validateDesc() {
    if (document.getElementById("desc").value === null) {
        document.getElementById("desc").setAttribute("class", "wrong");
        return false;
    } else if (/^[a-zA-Z 0-9 ]+$/.test(document.getElementById("desc").value)) {
        document.getElementById("desc").setAttribute("class", "correct");
        return true;
    } else {
        document.getElementById("desc").setAttribute("class", "wrong");
        return false;
    }

}
function validateLoc() {
    if (document.getElementById("loc").value === null) {
        document.getElementById("loc").setAttribute("class", "wrong");
        return false;
    } else if (/^[a-zA-Z 0-9 ]+$/.test(document.getElementById("loc").value)) {
        document.getElementById("loc").setAttribute("class", "correct");
        return true;
    } else {
        document.getElementById("loc").setAttribute("class", "wrong");
        return false;
    }

}
function validateReq() {
    if (document.getElementById("REQ").value === null) {
        document.getElementById("REQ").setAttribute("class", "wrong");
        return false;
    } else if (/^[a-zA-Z 0-9 ]+$/.test(document.getElementById("REQ").value)) {
        document.getElementById("REQ").setAttribute("class", "correct");
        return true;
    } else {
        document.getElementById("REQ").setAttribute("class", "wrong");
        return false;
    }

}
function validateDes() {
    if (document.getElementById("DES").value === null) {
        document.getElementById("DES").setAttribute("class", "wrong");
        return false;
    } else if (/^[a-zA-Z 0-9 ]+$/.test(document.getElementById("DES").value)) {
        document.getElementById("DES").setAttribute("class", "correct");
        return true;
    } else {
        document.getElementById("DES").setAttribute("class", "wrong");
        return false;
    }

}




function validateForSubmit() {


   if (validate() && validateSalary() && validateCompany() && validateDept() && validateDesc() && validateLoc() && validateSalary() === true) {
            document.getElementById('submit').disabled = false;
    } else {
        document.getElementById('submit').disabled = true;
    }

}