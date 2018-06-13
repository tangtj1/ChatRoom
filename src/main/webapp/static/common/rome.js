$(function () {

    createWebSocket();

    //设置send点击事件
    $("#send").click(function () {
        let msgInput = $("#msgInput");
        let str = msgInput.val();
        if (str === ""){
            return;
        }
        ws.sendMessage(str, ChatType.Chat);

        addMessage(str, MessagePosition.Right);
        scrollToLast();
        msgInput.val("");
    });

    //按键监听 回车键也可出发send事件
    $("#msgInput").keydown(function (key) {
        if (key.keyCode === 13) {
            $("#send").click();
        }
    });


});

function addMessage(msg, position = MessagePosition.Left) {
    let msgBox = $(".messages");
    if (position === MessagePosition.Left) {
        msgBox.append('<div class="message left">' +
            '<h5 class="message-title">' + msg["author"] + '</h5>' +
            '<div class="message-text">' + msg["msg"] + '</div>'
            + '</div>')
    } else if (position === MessagePosition.Right) {
        msgBox.append('<div class="message right">' +
            '<div class="message-text">' + msg + '</div>'
            + '</div>');
    }
}


let ws;
let lockReconnect = false;

function createWebSocket() {
    ws = new WebSocket(socketUrl);
    initWebSocketEvent();
}

function initWebSocketEvent() {
    ws.onclose = function () {
        reconnect(socketUrl);
    };
    ws.onerror = function () {
        reconnect(socketUrl);
    };
    ws.onopen = function () {
        //心跳检测重置
        heartCheck.reset().start();
        setTimeout(getRomeInfo(), 5000);
    };
    ws.onmessage = function (event) {
        //如果获取到消息，心跳检测重置
        //拿到任何消息都说明当前连接是正常的
        heartCheck.reset().start();
        console.log("on message" + event.data);
        let msg = JSON.parse(event.data);

        if (msg === null)
            return;
        // if (msg[MessageField.msgType] === 2) {
        //     //收到广播的行为
        //     return;
        // }

        switch (msg[MessageField.msgType]) {
            case ChatType.Chat:
                if (msg[MessageField.author] !== ownUserName) {
                    addMessage(msg);
                }
                break;
            case ChatType.Broadcast:
                if (msg[MessageField.msg] === 'UP_INFO') {
                    getRomeInfo();
                }
                break;
        }
    };
    ws.sendMessage = function sendMessage(msg, type) {
        const m = Msg(msg, type);
        console.log("send message" + JSON.stringify(m));
        ws.send(JSON.stringify(m))
    }
}

function getRomeInfo() {
    $.post({
        url: baseUrl + "/chat/room/info",
        data: {groupKey: groupKey},
        success: function (info) {
            //dialog.close();
            $("#groupUserInfo").text(info['liveUserCount'] + '/' + info['maxSizeofUser']);
            let lists = $("#userList");
            lists.empty();
            info['users'].forEach((value, index, array) => {
                lists.append('<li class="mdui-list-item"><span>' + value + '</span></li><li class="mdui-divider mdui-m-y-0"></li>');
            });
        },
        error: function () {
            mdui.dialog({
                title: "进入失败",
                buttons: [
                    {
                        text: '退出',
                        onclick: function () {
                            window.location.href = "//" + window.location.host + baseUrl + "/chat";
                        },

                    }
                ],
            });
        }
    });
}

let heartCheck = {
    timeout: 60000,//60秒
    timeoutObj: null,
    serverTimeoutObj: null,
    reset: function () {
        clearTimeout(this.timeoutObj);
        clearTimeout(this.serverTimeoutObj);
        return this;
    },
    start: function () {
        let self = this;
        this.timeoutObj = setTimeout(function () {
            //这里发送一个心跳，后端收到后，返回一个心跳消息，
            //onmessage拿到返回的心跳就说明连接正常
            ws.sendMessage(Heart, ChatType.Broadcast);
            getRomeInfo();
            self.serverTimeoutObj = setTimeout(function () {//如果超过一定时间还没重置，说明后端主动断开了
                ws.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
            }, self.timeout)
        }, this.timeout)
    }
};

const Heart = "HEART";

const MessageField = {
    msg: "msg",
    author: "author",
    time: "time",
    msgType: "msgType"
};

const MessagePosition = {
    Left: 1,
    Right: 2
};

const ChatType = {
    System: 1,
    Broadcast: 2,
    Chat: 10
};

function Msg(msg, type) {
    let obj = {};
    obj.msg = msg;
    obj.type = type;
    return obj;
}

function reconnect(url) {
    if (lockReconnect) return;
    lockReconnect = true;
    setTimeout(function () {
        createWebSocket(url);
        lockReconnect = false;
    }, 2000);
}

function scrollToLast() {
    let nodes = document.getElementsByClassName("message");
    nodes.item(nodes.length - 1).scrollIntoView({
        black: "end",
        behavior: "smooth"
    });
}