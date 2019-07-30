$(function () {
    $("#selectCity").change(function () {
        var id = $("#selectCity").val();
        var url = "/report/cityid/" + id;
        window.location.href = url;
    });
});