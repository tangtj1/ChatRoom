$(function () {
    $("#create").click(function () {
        $("#myModel").modal("show");
    });
    $("#change").click(function () {
        let name = $("#nicename").val();
        if (name.length < 3 || name.length > 30){
            alert("修改失败,昵称不符合规范");
            return false;
        }
        $.post({
            url: baseUrl + "/chat/changename",
            data: {name: name},
            success: function (msg) {
                alert(msg['body']['msg']);
                window.location.reload();
            },
            error: function () {
                alert("修改失败");
            }
        })
    });

    $("#createGroup").click(function () {
        let name = $("#groupName").val();
        if (name.length < 3 || name.length > 30) {
            alert("创建失败,房间名不符合规范");
            return false;
        }
        $.post({
            url: baseUrl + "/chat/create",
            data: {name: name},
            success: function (msg) {
                alert(msg['body']['msg']);
                window.location.reload();
            },
            error: function () {
                alert("创建失败");
            }
        });
    });

    $("#changeBtn").click(function () {
       $("#changeNicename").modal("show");
    });

    $("#searchRomeBtn").click(function () {
        let str = $("#searchRome").val();
        search(str);
    });
    $('#searchRome').bind('keypress', function (event) {
        if (event.keyCode === 13) {
            let str = $(this).val();
            search(str);
        }

    });


});

function search(str) {
    let url = baseUrl + "/chat/search/" + str;
    if (str === "") {
        url = baseUrl + "/chat/rooe"
    }
    $.get({
        url: url,
        success: (data) => {
            addRomeCard(data);
        },
        error: () => {

        }
    })
}

function addRomeCard(data) {
    const div = '<div class="col-md-6">' +
        '<div class="small-box bg-green">' +
        '<div class="inner">' +
        '<h3 id="rome_name"></h3>' +
        '<div class="h4" id="rome_info"></div>' +
        '</div>' +
        '<div class="icon">' +
        '<i class="ion ion-person-add"></i>' +
        '</div>' +
        '<a href="" class="small-box-footer">' +
        '加 入 <i class="fa fa-arrow-circle-right"></i>' +
        '</a>' +
        '</div>' +
        '</div>';

    let romes = $("#romeCards");
    romes.empty();
    for (let i = 0, block; i < data.length; i++) {
        block = $(div);
        block.find("a").attr("href", baseUrl + "/chat/room/" + data[i]['key']);
        block.find("#rome_name").html(data[i]['name']);
        block.find("#rome_info").html("在线人数 :" + data[i]['user'].size() + " / " + data[i]['maxUserSize']);
        romes.append(block);
    }
}
