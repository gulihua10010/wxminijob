var util = require("utils/util.js");
require("webim/sdk/libs/strophe");
let WebIM = require("webim/utils/WebIM")["default"];
let msgStorage = require("comps/chat/msgstorage");
let msgType = require("comps/chat/msgtype");
let disp = require("webim/utils/broadcast");
App({
  onLaunch: function () { 
 
    var a = this;
    wx.checkSession({
      success: function () {
        wx.getStorage({
          key: "userInfo",
          success: function (o) { 
          },
          fail: function () {
            util.logoutwithNoToast();
          }
        });
      },
      fail: function () {
        util.logoutwithNoToast();
      }
    });
    disp.on("em.main.ready", function () {
      calcUnReadSpot();
    });
    disp.on("em.chatroom.leave", function () {
      calcUnReadSpot();
    });
    disp.on("em.chat.session.remove", function () {
      calcUnReadSpot();
    });
    disp.on('em.chat.audio.fileLoaded', function () {
      calcUnReadSpot()
    });

  
    // 
    WebIM.conn.listen({
      onOpened(message) {
        console.log("onOpened", message);
        WebIM.conn.setPresence(); 
      },
      onClosed() {
        me.conn.closed = true;
      },
      onInviteMessage(message) { 
      },
      onPresence(message) {
    
      },

      onRoster(message) { 
        // let pages = getCurrentPages();
        // if(pages[0]){
        // 	pages[0].onShow();
        // }
      },

   
      onAudioMessage(message) {
        getuserbase(message.from)
        console.log("onAudioMessage", message);
        if (message) {
          if (onMessageError(message)) {
            msgStorage.saveReceiveMsg(message, msgType.AUDIO);
          }
          //calcUnReadSpot();
          ack(message);
        }
      },

      onCmdMessage(message) {
        getuserbase(message.from)
        console.log("onCmdMessage", message);
        if (message) {
          if (onMessageError(message)) {
            msgStorage.saveReceiveMsg(message, msgType.CMD);
          }
          calcUnReadSpot();
          ack(message);
        }
      },

      // onLocationMessage(message){
      // 	console.log("Location message: ", message);
      // 	if(message){
      // 		msgStorage.saveReceiveMsg(message, msgType.LOCATION);
      // 	}
      // },

      onTextMessage(message) {
        console.log("onTextMessage", message);
        if (message) {
          if (onMessageError(message)) {
            msgStorage.saveReceiveMsg(message, msgType.TEXT);
          }
          getuserbase(message.from)
          calcUnReadSpot();
          ack(message);
        }
      },

      onEmojiMessage(message) {
        getuserbase(message.from)
        console.log("onEmojiMessage", message);
        if (message) {
          if (onMessageError(message)) {
            msgStorage.saveReceiveMsg(message, msgType.EMOJI);
          }
          calcUnReadSpot();
          ack(message);
        }
      },

      onPictureMessage(message) {
        getuserbase(message.from)
        console.log("onPictureMessage", message);
        if (message) {
          if (onMessageError(message)) {
            msgStorage.saveReceiveMsg(message, msgType.IMAGE);
          }
          calcUnReadSpot();
          ack(message);
        }
      },

      // 各种异常
      onError(error) {
        // 16: server-side close the websocket connection
        if (error.type == WebIM.statusCode.WEBIM_CONNCTION_DISCONNECTED) {
          if (WebIM.conn.autoReconnectNumTotal < WebIM.conn.autoReconnectNumMax) {
            return;
          }
          wx.showToast({
            title: "server-side close the websocket connection",
            duration: 1000
          }); 
          return;
        }
        // 8: offline by multi login
        if (error.type == WebIM.statusCode.WEBIM_CONNCTION_SERVER_ERROR) {
          wx.showToast({
            title: "offline by multi login",
            duration: 1000
          }); 
        }
        if (error.type == WebIM.statusCode.WEBIM_CONNCTION_OPEN_ERROR) {
          console.log("用户名或密码错误") 
          wx.showToast({
            title: "用户名或密码错误",
            duration: 1000
          }); 
        }
      },
    });
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo
              wx.getStorage({
                key: "userInfo",
                success: function (res) {
                  // console.log(res.data)  
                  a.login();
                },
                fail: function () {
                  
                }
              }); 
               
 
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
    // 登录
  login: function () {
    var _this = this;
    wx.login({
        success: function(t) {
          util.ajaxByJson("/wxLogin?code=" + t.code, "post", {}
            , function (r1) {
              if (r1.status == 500) {
                wx.showToast({
                  title: "登录失败",
                    icon: 'none'
                });
              } else { 
                // 发送 res.code 到后台换取 openId, sessionKey, unionId 
                wx.setStorageSync(r1.data.openId.toLowerCase()+"_", 
                  {
                    nick: r1.data.nickName,
                    avatarurl: r1.data.avatarUrl
                  }) 
                console.log('wx.getStorageInfoSync(r1.data.openId.toLowerCase())')
                console.log(wx.getStorageSync(r1.data.openId.toLowerCase() +"_"))
                wx.getUserInfo({ 
                  success: function (n) { 
                    if (parseInt(r1.data.status)<=1&&r1.data.status>0){
                      var options = {
                        apiUrl: WebIM.config.apiURL,
                        username: r1.data.openId.toLowerCase(),
                        password: r1.data.password,
                        nickname: r1.data.nickName,
                        appKey: WebIM.config.appkey,
                        success: function (res1) {
                          if (res1.statusCode == "200") {
                            console.log("注册成功")
                            var data = {
                              apiUrl: WebIM.config.apiURL,
                              user: r1.data.openId,
                              pwd: r1.data.password,
                              grant_type: "password",
                              appKey: WebIM.config.appkey
                            };
                            // console.log('data',data)
                            wx.setStorage({
                              key: "myUsername",
                              data: r1.data.openId.toLowerCase()
                            });
                          
                                // setTimeout(function(){
                                // 	// WebIM.conn.open(data);
                                // }, 1000);
                          }
                        },
                        error: function (res) {
                          if (res.statusCode !== "200") {
                            console.log("用户名已被占用")
                          
                          }
                        }
                      };
                      WebIM.utils.registerUser(options);

                      util.ajax("/userLogin", "post", {
                        encryptedData:n.encryptedData,
                        iv:n.iv,
                        token: r1.data.wxSession.session_key

                      }
                        , function (r2) { 
                          if (r2.status == 500) {
                            wx.showToast({
                              title: "登录失败",
                              icon:'none'
                            });
                          } else {
                            wx.setStorageSync(r1.data.openId.toLowerCase() + "_",
                              {
                                nick: r1.data.nickName,
                                avatarurl: r1.data.avatarUrl
                              }) 
                           
                            wx.setStorageSync('userinfodetail', r2.data); 
                          }

                        })


                    }else{
                      console.log('r1.data.status') 
                      if (r1.data.status <0) {
                        wx.showModal({
                          title: '提示',
                          content: '你已经被管理员禁用，无法接单和发单！',
                        })
                      }
                      wx.setStorageSync('userinfodetail', r1.data)
                      console.log(wx.getStorageSync('userinfodetail'))
                      _this.conn.open({
                          apiUrl: WebIM.config.apiURL,
                         user: r1.data.openId,
                        pwd: r1.data.password,
                          grant_type: 'password',
                          appKey: WebIM.config.appkey
                        });
                      wx.setStorage({
                        key: "myUsername",
                        data: r1.data.openId.toLowerCase()
                      });

                      var member=  _this.getmember(r1.data.openId.toLowerCase())
                      console.log(member)
                      for (let i = 0; i < member.length; i++) {
                        var sessionkey = member[i].name + r1.data.openId.toLowerCase();
                        _this.getmsg_ChatMsgs(sessionkey)
                        _this.getmsg_ChatMsgs(sessionkey)
                      }
                      
                      wx.setStorage({
                        key: "myUserNick",
                        data: r1.data.nickName
                      });

                    }
                   

                  },
                  fail: function (o) {
                    _this.loginFail();
                  }
                });
               
              }

            })
           
        }
    });
  },

  conn: {
    closed: false,
    curOpenOpt: {},
    open(opt) {
      this.curOpenOpt = opt;
      WebIM.conn.open(opt);
      this.closed = false;
    },
    reopen() {
      if (this.closed) {
        this.open(this.curOpenOpt);
      }
    },
  },
  getuserbase:function(id){
    
    getuserbase(id)
  },
  loginFail: function () {
    var o = this;
    wx.showModal({
      content: "登录失败，请允许获取用户信息,如不显示请删除小程序重新进入",
      showCancel: !1
    }), o.login();
  },
  
  globalData: {
    userInfo: null,
    sk: null,
    bootflag: 0,
    booted: 0,
     unReadSpot: false,
  },

  getmsg_historychatMsg: function (sessionKey){

    var historyChatMsgs = wx.getStorageSync("rendered_" + sessionKey) || [];
    if (historyChatMsgs == "" || historyChatMsgs == [] || historyChatMsgs == null) {
      util.ajax("/getMsg/0/" + sessionKey, "POST", {}, function (r) {
        console.log(r)
        historyChatMsgs = r.historyChatMsgs;
        wx.setStorageSync("rendered_" + sessionKey, historyChatMsgs)
      })

    }

  },
  getmsg_ChatMsgs: function (sessionKey) {
    var historyChatMsgs = wx.getStorageSync("rendered_" + sessionKey) || [];
    if (historyChatMsgs == "" || historyChatMsgs == [] || historyChatMsgs == null) {
      util.ajax("/getMsg/0/" + sessionKey, "POST", {}, function (r) {
        console.log(r)
        historyChatMsgs = r.historyChatMsgs;
        wx.setStorageSync("rendered_" + sessionKey, historyChatMsgs)
      })

    }


  }, getmember: function (sessionKey) {
    var  member = wx.getStorageSync("member")|| [];
    if (member == "" || member == [] || member == null) {
      util.ajax("/getmber/" + sessionKey, "POST", {}, function (r) {
        console.log(r)
        member = r.member;
        wx.setStorageSync("member", member)
      })

    }

    return member;
  }



});

function ack(receiveMsg) {
  // 处理未读消息回执
  var bodyId = receiveMsg.id;         // 需要发送已读回执的消息id
  var ackMsg = new WebIM.message("read", WebIM.conn.getUniqueId());
  ackMsg.set({
    id: bodyId,
    to: receiveMsg.from
  });
  WebIM.conn.send(ackMsg.body);
}

function onMessageError(err) {
  if (err.type === "error") {
    wx.showToast({
      title: err.errorText
    });
    return false;
  }
  return true;
}
 
function getCurrentRoute() {
  let pages = getCurrentPages(); 
  let currentPage = pages[pages.length - 1];
  return currentPage.route;
}
function getuserbase(fromid){ 
  if (wx.getStorageSync(fromid + "_") == "" || wx.getStorageSync(fromid + "_") == null) {
    console.log('fromid')
    util.ajax("/getuser/" + fromid, "POST", {}, function (r) {
      console.log(r)
      wx.setStorageSync(fromid + "_",
        {
          nick: r.data.usernick,
          avatarurl: r.data.avatarurl
        }) 
 
    })  
 
  }

  console.log(wx.getStorageSync(fromid + "_"))


}

function getmsg_historychatMsg(sessionkey){ 
  var historyChatMsgs = wx.getStorageSync("rendered_" + sessionKey) || [];
      if (historyChatMsgs == "" || historyChatMsgs == [] || historyChatMsgs == null) {
        util.ajax("/getMsg/0/" + sessionKey, "POST", {}, function (r) {
          console.log(r)
          historyChatMsgs = r.historyChatMsgs;
          wx.setStorageSync("rendered_" + sessionKey, historyChatMsgs)
        })

      }

}
function getmsg_ChatMsgs(sessionkey) {
let chatMsg = wx.getStorageSync(sessionKey) || []; 
    if (chatMsg == "" ||  chatMsg == [] || chatMsg == null){
  
      util.ajax("/getMsg/1/" + sessionKey, "POST", {}, function (r) {
        console.log(r)
        chatMsg = r.chatMsg;
        wx.setStorageSync( sessionKey, chatMsg)

      }) 
    }


}
function calcUnReadSpot() {
  let myName = wx.getStorageSync("myUsername");
  let members = wx.getStorageSync("member") || [];
  let count = members.reduce(function (result, curMember, idx) {
    let chatMsgs = wx.getStorageSync(curMember.name + myName) || [];
    return result + chatMsgs.length;
  }, 0);
 getApp().globalData.unReadSpot = count > 0;
  console.log('count')
  console.log(count)
  if (count > 0) {
    wx.showTabBarRedDot({
      index: 1,
    })
    wx.setTabBarBadge({
      index: 1,
      text: String(count)
    })
  } else {
    wx.hideTabBarRedDot({
      index: 1,
    })
    wx.removeTabBarBadge({
      index: 1,
    })

  }
  disp.fire("em.xmpp.unreadspot", count);
}