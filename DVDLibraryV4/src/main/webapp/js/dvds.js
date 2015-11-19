/// TO DO: implement checkForChanges()

$(document).ready(function () {

    loadDvds();

    $("#add-button").click(function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "dvd",
            data: JSON.stringify({
                title: $("#add-title").val(),
                releaseDate: $("#add-releaseDate").val(),
                mpaaRating: $("#add-mpaaRating").val(),
                director: $("#add-director").val(),
                studio: $("#add-studio").val(),
                note: $("#add-note").val(),
                imgSrc: $("#add-imgSrc").val()
            }),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            dataType: "json"
        }).done(function (data, status) {
            $("#add-title").val("");
            $("#add-releaseDate").val("");
            $("#add-mpaaRating").val("");
            $("#add-director").val("");
            $("#add-studio").val("");
            $("#add-note").val("");
            $("#add-imgSrc").val("");
            $('#validationErrors').empty();
            loadDvds();
        }).fail(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $("#validationErrors");
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });
    });

    $("#edit-button").click(function () {
        var modal = $("#detailsModal");
        var dvdId = $("#edit-dvd-id").val();
        if ($("#edit-button").html() === "Cancel") {
            populateModalFields(dvdId);
        }

        //toggles disabled property of input fields
        modal.find("input[type='text']").prop("disabled", function (i, val) {
            return !val;
        });
        toggleEditButtonText();
    });

    $("#save-button").click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'PUT',
            url: 'dvd/' + $("#edit-dvd-id").val(),
            data: JSON.stringify({
                title: $("#edit-title").val(),
                releaseDate: $("#edit-releaseDate").val(),
                mpaaRating: $("#edit-mpaaRating").val(),
                director: $("#edit-director").val(),
                studio: $("#edit-studio").val(),
                note: $("#edit-note").val(),
                imgSrc: $("#edit-imgSrc").val()
            }),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            dataType: 'json'
        }).success(function () {
            loadDvds();
            $('#editValidationErrors').empty();
        }).error(function (data, status) {
            $('#editValidationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $("#editValidationErrors");
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });
    });

    $("#delete-button").click(function (event) {
        var answer = confirm("Are you sure you want to delete?");
        if (answer === true) {
            $.ajax({
                type: "DELETE",
                url: "dvd/" + $("#edit-dvd-id").val()
            }).success(function () {
                loadDvds();
            });
        }
    });

    $("#close-button").click(function () {
        var dvdId = $("#edit-dvd-id").val();
//        if (checkForChanges(dvdId)) {
//
//        } else {
        $("#detailsModal").modal("hide");
        //}
    });

    $("#search-button").click(function (event) {
        event.preventDefault();

        $.ajax({
            type: "POST",
            url: "search/dvds",
            data: JSON.stringify({
                title: $("#search-title").val(),
                releaseDate: $("#search-releaseDate").val(),
                mpaaRating: $("#search-mpaaRating").val(),
                director: $("#search-director").val(),
                studio: $("#search-studio").val()
            }),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            dataType: "json"
        }).success(function (data, status) {
            $("#search-title").val("");
            $("#search-releaseDate").val("");
            $("#search-mpaaRating").val("");
            $("#search-director").val("");
            $("#search-studio").val("");
            fillDvds(data, status);
        });
    });

});

/// load main page

function fillDvds(data, status) {
    clearDvdContent();
    var contentDiv = $("#contentDiv");

    $.each(data, function (index, dvd) {
        contentDiv.append($("<a>")
                .attr({
                    "data-dvd-id": dvd.id,
                    "data-toggle": "modal",
                    "data-target": "#detailsModal"
                })
                .append($("<img>")
                        .attr({
                            "src": dvd.imgSrc,
                            "class": "img-responsive",
                            "style": "max-width: 150px; display:inline-block; padding:10px",
                            "alt": dvd.title
                        })));
    });

}

function loadDvds() {
    $.ajax({
        url: "dvds"
    }).success(function (data, status) {
        fillDvds(data, status);
    });
}

function clearDvdContent() {
    $("#contentDiv").empty();
}


/// modal functions

function disableEditFields() {
    var modal = $("#detailsModal");
    modal.find("input[type='text']").prop("disabled", function (i, val) {
        if (val === false) {
            return !val;
        }
    });
}

function toggleEditButtonText() {
    var button = $("#edit-button");
    if ($(button).html() === "Edit") {
        $(button).html("Cancel");
    } else {
        $(button).html("Edit");
    }
}

function populateModalFields(dvdId) {
    var modal = $("#detailsModal");
    $.ajax({
        type: "GET",
        url: "dvd/" + dvdId

    }).success(function (dvd) {
        modal.find("#dvd-id").text(dvd.id);
        modal.find("#modal-image").attr({
            "src": dvd.imgSrc,
            "class": "img-responsive"});
        modal.find("#edit-title").val(dvd.title);
        modal.find("#edit-releaseDate").val(dvd.releaseDate);
        modal.find("#edit-mpaaRating").val(dvd.mpaaRating);
        modal.find("#edit-director").val(dvd.director);
        modal.find("#edit-studio").val(dvd.studio);
        modal.find("#edit-note").val(dvd.note);
        modal.find("#edit-imgSrc").val(dvd.imgSrc);
        modal.find("#edit-dvd-id").val(dvd.id);
    });
}

//function checkForChanges(dvdId) {
//
//}

$("#detailsModal").on("show.bs.modal", function (event) {
    var element = $(event.relatedTarget);
    var dvdId = element.data("dvd-id");
    disableEditFields();
    $("#edit-button").html("Edit");
    populateModalFields(dvdId);
});