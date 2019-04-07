let Disp = require("../../webim/utils/Dispatcher");
let msgPackager = require("msgpackager");
let msgType = require("msgtype");
let msgStorage = new Disp();
let disp = require("../../webim/utils/broadcast");
let util = require("../../utils/util.js");
msgStorage.saveReceiveMsg = function(receiveMsg, type) {
  let sendableMsg;
  if (type == msgType.IMAGE) {
    sendableMsg = {
      id: receiveMsg.id,
      type: type,
      body: {
        id: receiveMsg.id,
        from: receiveMsg.from,
        to: receiveMsg.to,
        type: type,
        ext: receiveMsg.ext,
        chatType: receiveMsg.chatType,
        toJid: "",
        body: {
          type: type,
          url: receiveMsg.url,
          filename: receiveMsg.filename,
          filetype: receiveMsg.filetype,
          size: {
            width: receiveMsg.width,
            height: receiveMsg.height
          },
        },
      },
    };
  } else if (type == msgType.TEXT || type == msgType.EMOJI) {
    sendableMsg = {
      id: receiveMsg.id,
      type: type,
      body: {
        id: receiveMsg.id,
        from: receiveMsg.from,
        to: receiveMsg.to,
        type: type,
        ext: receiveMsg.ext,
        chatType: receiveMsg.chatType,
        toJid: "",
        body: {
          type: type,
          msg: receiveMsg.data,
        },
      },
      value: receiveMsg.data
    };
  } else if (type == msgType.AUDIO) {
    sendableMsg = {
      id: receiveMsg.id,
      type: type,
      body: {
        id: receiveMsg.id,
        from: receiveMsg.from,
        to: receiveMsg.to,
        type: type,
        ext: receiveMsg.ext,
        chatType: receiveMsg.chatType,
        toJid: "",
        body: {
          type: type,
          url: receiveMsg.url,
          filename: receiveMsg.filename,
          filetype: receiveMsg.filetype,
        },
      },
    };
  } else {
    return;
  }
  this.saveMsg(sendableMsg, type, receiveMsg);
};
msgStorage.saveMsg = function(sendableMsg, type, receiveMsg) {
  let fromid = sendableMsg.body.from;
  let toid = sendableMsg.body.to;
  let myName = wx.getStorageSync("myUsername");
  let member = wx.getStorageSync("member") || [];;

  // console.log('sendableMsg')
  // console.log(member)
  // console.log(sendableMsg)
  if (myName == fromid) {
    var data = {
      name: toid,
    }
    // console.log(member.find(function (x) {

    //   return x.name != data.name
    // }) )

    if (member.find(function(x) {

        return x.name == data.name
      }) === undefined) {
      member.push(data)
    }
  } else if (myName == toid) {
    // console.log('fromid')
    // console.log(fromid)
  
    var data = {
      name: fromid,
    }

    if (member.find(function(x) {
        return x.name == data.name
      }) === undefined) {
      member.push(data)
    }
  }
  wx.setStorageSync('member', member)
  util.ajaxByJson("/savmember/" + myName, "POST", { member }, function (r) {
    console.log(r)

  }) 
  // console.log(member) 


  let me = this;
  let sessionKey;
  // 仅用作群聊收消息，发消息没有 receiveMsg
  if (receiveMsg && receiveMsg.type == "groupchat") {
    sessionKey = receiveMsg.to + myName;
  }
  // 群聊发 & 单发 & 单收
  else {
    sessionKey = sendableMsg.body.from == myName ?
      sendableMsg.body.to + myName :
      sendableMsg.body.from + myName;
  }
  let curChatMsg = wx.getStorageSync(sessionKey) || [];
  let renderableMsg = msgPackager(sendableMsg, type, myName);
  // console.log(renderableMsg)
  curChatMsg.push(renderableMsg);
  if (type == msgType.AUDIO) {
    // 如果是音频则请求服务器转码
    wx.downloadFile({
      url: sendableMsg.body.body.url,
      header: {
        "X-Requested-With": "XMLHttpRequest",
        Accept: "audio/mp3",
        Authorization: "Bearer " + sendableMsg.accessToken
      },
      success(res) {
        // wx.playVoice({
        // 	filePath: res.tempFilePath
        // });
        renderableMsg.msg.url = res.tempFilePath;
        save();
      },
      fail(e) {
        console.log("downloadFile failed", e);
      }
    });
  } else {
    save();
  }

  function save() {
    console.log('sessionKey')
    console.log(sessionKey)
    console.log(curChatMsg)
    util.ajaxByJson("/saveMsg/1/" + sessionKey, "POST", { curChatMsg }, function (r) {
      console.log(r)

    }) 

    wx.setStorage({
      key: sessionKey,
      data: curChatMsg,
      success() {
        disp.fire('em.chat.audio.fileLoaded');
        me.fire("newChatMsg", renderableMsg, type, curChatMsg);
      }
    });
  }
};

module.exports = msgStorage;