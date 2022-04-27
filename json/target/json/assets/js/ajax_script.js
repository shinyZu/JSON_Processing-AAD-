// loadAllCustomers();

// function loadAllCustomers() {
//     $("#tblCustomer tbody").empty();
//     $.ajax({
//         // url: "https://localhost:8080/apptwo/customer",
//         url: "customer",
//         method: "GET",

//         success: function (response) {
//             for (const customer of response) {
//                 let row =
//                     `<tr>
//                         <td>${customer.id}</td>
//                         <td>${customer.name}</td>
//                         <td>${customer.address}</td>
//                         <td>${customer.salary}</td>
//                     </tr>`;
//                 $("#tblCustomer tbody").append(row);
//             }
//         },
//         error: function (xhr) {
//             console.log(xhr);
//         }
//     });
// }

// // Save Customer
// $("#saveCustomer").click(function (e) {

//     var data = $("#customer_form").serialize(); // creates a query string using the "name" attribute and value we input
//     console.log(data);
//     // customerID=C001&customerName=Kamal&customerAddress=Galle&customerSalary=1500 <-- Query String

//     $.ajax({
//         url: "customer",
//         method: "POST",
//         data: data,

//         success: function (res) {
//             alert(res);
//             loadAllCustomers();
//             // console.log(res);
//         },
//         error: function (xhr) {
//             console.log(xhr);
//         }
//     });
// });

// //Get All Customers
// $("#btn1").click(function (e) {
//     loadAllCustomers();
// });

// //Get All Items
// $("#btn2").click(function (e) {
//     $("#tblItem tbody").empty();
//     $.ajax({
//         url: "http://localhost:8080/apptwo/items",
//         method: "GET",
//         dataType: "json",
//         success: function (response) {
//             for (const item of response) {
//                 let row =
//                     `<tr>
//                         <td>${item.code}</td>
//                         <td>${item.description}</td>
//                         <td>${item.unitPrice}</td>
//                         <td>${item.qtyOnHand}</td>
//                     </tr>`;

//                 $("#tblItem tbody").append(row);
//             }
//         },
//         error: function (xhr) {
//             console.log(xhr);
//         }
//     });
// });




loadAllCustomers();

function loadAllCustomers() {
    $("#tblCustomer tbody").empty();
    $.ajax({
        url: "customer",
        method: "GET",
        // dataType:"json", // please convert the response into JSON
        success: function (resp) {
            console.log(typeof resp);
            for (const customer of resp) {
                let row = `<tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.salary}</td></tr>`;
                $("#tblCustomer tbody").append(row);
            }
        }
    });
}

$("#btn1").click(function () {
    loadAllCustomers();
});


$("#btnSaveCustomer").click(function () {

    var data = $("#customer_form").serialize(); // return query string of form with name:type-value

    console.log(data);

    $.ajax({
        url: "customer",
        method: "POST",
        data: data,// if we send data with the request
        success: function (res) {
            //alert(res);
            loadAllCustomers();
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });
});

$("#btnDeleteCustomer").click(function (e) {

    var data = $("#customer_form").serialize();
    console.log(data); // creates a query string using the "name" attribute and value we input

    /* gather customer information */
    // customerID = $("#txtCusID").val();
    // customerName = $("#txtCusName").val();
    // customerAddress = $("#txtCusAddress").val();
    // customerSalary = $("#txtCusSalary").val();

    $.ajax({
        url: "http://localhost:8080/apptwo/customer",
        method: "DELETE",
        data: data, // if we want send data with the request

        success: function (res) {

            loadAllCustomers();
            alert("Customer Deleted Successfully..!");
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });
});