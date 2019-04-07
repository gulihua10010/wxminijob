let disp = require("../../webim/utils/broadcast");

Page({
  data: {
    search_btn: true,
    search_chats: false,
    show_mask: false,
    yourname: "",
    unReadSpot: false,
    arr: [],
     hasuerinfo: true,
    isjin: false,
  },

  onLoad() {
    var user = wx.getStorageSync('userinfodetail')
    if (user.status < 0) {
      this.setData({
        isjin: true,
      });
    }
    if (user == "" || user == null || user == undefined) {
      o.setData({
        hasuerinfo: false
      })

    }
    let me = this;
    // console.log(me.getChatList())

    disp.on("em.xmpp.unreadspot", function (count) {
      console.log('count')
      console.log(count)
      // console.log(me.getChatList())
      console.log('me.getChatList()')
      if(count>0){
        wx.showTabBarRedDot({
          index: 1,
        })
        wx.setTabBarBadge({
          index: 1,
          text: String(count)
        })
      }else{ 
        wx.hideTabBarRedDot({
          index: 1,
        })
        wx.removeTabBarBadge({
          index: 1,
        })
      }
      me.setData({
        arr: me.getChatList(), 
      });
    });
  },

  getChatList() {
    var array = [];
    var member = wx.getStorageSync("member");
    
    // xoan
    console.log('chatmember')
    console.log(member) 
    // setStorage
    var myName = wx.getStorageSync("myUsername");
    for (let i = 0; i < member.length; i++) {
      let newChatMsgs = wx.getStorageSync(member[i].name + myName) || [];
      console.log(member[i].name + myName)
      console.log(newChatMsgs)
      let historyChatMsgs = wx.getStorageSync("rendered_" + member[i].name + myName) || [];
      // console.log(historyChatMsgs)

      let curChatMsgs = historyChatMsgs.concat(newChatMsgs);

      if (curChatMsgs.length) {
        let lastChatMsg = curChatMsgs[curChatMsgs.length - 1];
        // console.log(lastChatMsg)
        lastChatMsg.unReadCount = newChatMsgs.length;
        if (lastChatMsg.unReadCount > 99) {
          lastChatMsg.unReadCount = "...";
        }
        array.push(lastChatMsg);
      }
    }
    return array;
  },

  onShow: function () {
    this.setData({
      arr: this.getChatList(),
      unReadSpot: getApp().globalData.unReadSpot,
    });
    if (getApp().globalData.unReadSpot==true) {
      wx.showTabBarRedDot({
        index: 1
      })  
  }else{
    wx.hideTabBarRedDot({
      index: 1,
    })
      wx.removeTabBarBadge({
        index: 1,
      })
  }
  },

  openSearch: function () {
    this.setData({
      search_btn: false,
      search_chats: true,
      show_mask: true
    });
  },

  cancel: function () {
    this.setData({
      search_btn: true,
      search_chats: false,
      show_mask: false
    });
  },
 

  close_mask: function () {
    this.setData({
      search_btn: true,
      search_chats: false,
      show_mask: false
    });
  },

 
  into_chatRoom: function (event) { 

    console.log(wx.getStorageSync(event.currentTarget.dataset.username)+"_")
    // if (wx.getStorageSync(event.currentTarget.dataset.username + "_")==undefined||
    //   wx.getStorageSync(event.currentTarget.dataset.username + "_")==null){
    //   getApp().getuserbase(event.currentTarget.dataset.username)
    //   }
    getApp().getuserbase(event.currentTarget.dataset.username)
    var my = wx.getStorageSync("myUsername");
    var nameList = {
      myName: my,
      your: event.currentTarget.dataset.username,
      yourNickName: wx.getStorageSync(event.currentTarget.dataset.username + "_").nick
    };
    console.log('dddd')
    console.log(event.currentTarget.dataset.username + "_")
    console.log(wx.getStorageSync(event.currentTarget.dataset.username + "_"))
    wx.navigateTo({
      url: "/pages/chatroom/chatroom?username=" + JSON.stringify(nameList)
    });
  },

  del_chat: function (event) {
    var nameList = {
      your: event.currentTarget.dataset.username
    };
    var myName = wx.getStorageSync("myUsername");
    var currentPage = getCurrentPages();
    wx.showModal({
      title: "删除该聊天记录",
      confirmText: "删除",
      success: function (res) {
        if (res.confirm) {
          wx.setStorageSync(nameList.your + myName, "");
          wx.setStorageSync("rendered_" + nameList.your + myName, "");
          if (currentPage[0]) {
            currentPage[0].onShow();
          }
          disp.fire("em.chat.session.remove");
        }
      },
      fail: function (err) {
      }
    });
  },

});
