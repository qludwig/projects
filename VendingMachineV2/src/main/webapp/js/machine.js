$(document).ready(function () {

    loadItems();
    loadMoney();

    $("#nickel-button").click(function () {
        $.ajax({
            url: "nickel",
            type: "PUT"
        }).success(function () {
            loadMoney();
        });
    });

    $("#dime-button").click(function () {
        $.ajax({
            url: "dime",
            type: "PUT"
        }).success(function () {
            loadMoney();
        });
    });

    $("#quarter-button").click(function () {
        $.ajax({
            url: "quarter",
            type: "PUT"
        }).success(function () {
            loadMoney();
        });
    });

    $("#dollar-button").click(function () {
        $.ajax({
            url: "dollar",
            type: "PUT"
        }).success(function () {
            loadMoney();
        });
    });

    $("#change-return-button").click(function () {
        var currentTotal = $("#totalDiv").children().text();

        $.ajax({
            url: "money",
            type: "PUT"
        }).success(function () {
            loadMoney();
            loadChangeReturn(currentTotal);
        });
    });

});

function loadItems() {
    clearItems();
    var itemDiv = $("#itemContent");
    $.ajax({
        url: "items"
    }).success(function (data, status) {
        $.each(data, function (index, item) {
            itemDiv.append($("<div>")
                    .attr({
                        "class": "col-md-4 item-container"
                    })
                    .append($("<div>")
                    .attr({
                        "class": "item"
                    })
                    .append($("<div>")
                    .attr({
                        "class": "item-name"
                    }).text(item.name))
                    .append($("<div>")
                    .attr({
                        "class": "item-cost"
                    }).text("$" + item.cost))
                    .append($("<div>")
                    .attr({
                        "class": "item-inventory"
                    }).text("x" + item.inventory))
                    .append($("<div>")
                    .attr({
                        "class": "button-div"
                    })
                            .append($("<button>")
                                    .attr({
                                        "class": "btn btn-default buy-button",
                                        "data-item-id": item.id,
                                        "data-item-cost": item.cost
                                    })
                                    .text("Buy").click(function (event) {
                                        vend(item, event);

                                        })
                                    ))));
        });
    });
}

function clearItems() {
    $("#itemContent").empty();
}

function loadMoney() {
    clearMoney();
    var moneyDiv = $("#totalDiv");

    $.ajax({
        url: "money"
    }).success(function (total) {
        moneyDiv.append($("<p>").text("$ " + total));
    });
}

function clearMoney() {
    $("#totalDiv").empty();
}

function loadChangeReturn(currentTotal) {
    var div = $("#changeReturnDiv");
    div.append($("<p>").text(currentTotal));
    div.children().fadeOut(3000, function () {
        div.empty();
    });
}

function vend(item, event) {
    console.log("clicked");

    $.ajax({
        url: "money/" + item.id,
        type: "PUT",
        data: JSON.stringify({
            id: item.id,
            name: item.name,
            cost: item.cost,
            inventory: item.inventory
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).success(function (vendSuccess) {
        if (vendSuccess === true) {
            loadItems();
            loadMoney();
            displaySuccessMessage(item.name);
        } else {
            var totalDivText = $("#totalDiv").children();
            totalDivText.css({
                "color": "red",
                "text-shadow": "0 0 25px #800000"
            });
            window.setTimeout(restoreTotalDivTextColor, 400);
        }
    });
}

function restoreTotalDivTextColor() {
    var totalDivText = $("#totalDiv").children();
    totalDivText.delay(1000).css({
        "color": "#00E600",
        "text-shadow": "0 0 25px #008000"
    });
}

function displaySuccessMessage(itemName) {
    $("body").append($("<div>")
            .attr({
                "class": "success-div"
            })
                    .append($("<p>").attr({"class": "success-p embiggen"}).text("YEAH!"))
                    .append($("<p>").attr({"class": "success-p"}).text("You got a " + itemName + "!"))
            );
    window.setTimeout(removeSuccessMessage, 700);
}

function removeSuccessMessage() {
    $(".success-div").fadeOut("fast");
}