$(document).ready(function (){
    // alert("yo");
    $(".editButton").on('click', function (e){
        console.log(e.target.dataset.id);
        // $.get(`/posts/${$(this).attr("data-id")}/edit`)
        window.location.replace(`/posts/${$(this).attr("data-id")}/edit`);
    });
});

$(document).ready(function (){
    // alert("Yooor!");
    $(".deleteButton").on('click', function (e){
        // console.log(e.target.dataset.delButton);
        // console.log("Delete button works!!! :D");
        window.location.replace(`/posts/${$(this).attr("data-id")}/delete`);
    });
});