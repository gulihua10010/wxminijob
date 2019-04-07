let msgStorage = require("../msgstorage");
let util = require("../../../utils/util.js");

let LIST_STATUS = {
	SHORT: "scroll_view_change",
	NORMAL: "scroll_view"
};

Component({
	properties: {
		username: {
			type: Object,
			value: {},
		},
	},
	data: {
		view: LIST_STATUS.NORMAL,
		toView: "",
		chatMsg: [],
		__visibility__: false,
	},
	methods: {
		normalScroll(){
			this.setData({
				view: LIST_STATUS.NORMAL
			});
		},

		shortScroll(){
			this.setData({
				view: LIST_STATUS.SHORT
			});
		},

		onTap(){
			this.triggerEvent("msglistTap", null, { bubbles: true });
		},

		previewImage(event){
			var url = event.target.dataset.url;
			wx.previewImage({
				urls: [url]		// 需要预览的图片 http 链接列表
			});
		},

		renderMsg(renderableMsg, type, curChatMsg, sessionKey){
			var historyChatMsgs = wx.getStorageSync("rendered_" + sessionKey) || []; 
      // if (historyChatMsgs == "" || historyChatMsgs == [] || historyChatMsgs == null) {
      //   util.ajax("/getMsg/0/" + sessionKey, "POST", {}, function (r) {
      //     console.log(r)
      //     historyChatMsgs = r.historyChatMsgs;
      //     wx.setStorageSync("rendered_" + sessionKey, historyChatMsgs)
      //   })
 
      // }

			historyChatMsgs = historyChatMsgs.concat(curChatMsg);
			if(!historyChatMsgs.length) return;
			this.setData({
				chatMsg: historyChatMsgs,
				// 跳到最后一条
				toView: historyChatMsgs[historyChatMsgs.length - 1].mid,
			});
			wx.setStorageSync("rendered_" + sessionKey, historyChatMsgs);
      console.log('historyChatMsgs')
      // console.log(historyChatMsgs)
      util.ajaxByJson("/saveMsg/0/" + sessionKey, "POST", { historyChatMsgs} , function (r) {
        console.log(r)
    
      }) 
      util.ajaxByJson("/saveMsg/2/" + sessionKey, "POST", {  }, function (r) {
        console.log(r)

      }) 

      console.log(historyChatMsgs)
			wx.setStorageSync(sessionKey, null);
		},
	},

	// lifetimes
	created(){},
	attached(){
		this.__visibility__ = true;
	},
	moved(){},
	detached(){
		this.__visibility__ = false;
	},
	ready(){
		let me = this;
		let username = this.data.username;
		let myUsername = wx.getStorageSync("myUsername");
    let sessionKey = username.your + myUsername 
		let chatMsg = wx.getStorageSync(sessionKey) || []; 
    // if (chatMsg == "" ||  chatMsg == [] || chatMsg == null){
  
    //   util.ajax("/getMsg/1/" + sessionKey, "POST", {}, function (r) {
    //     console.log(r)
    //     chatMsg = r.chatMsg;
    //     wx.setStorageSync( sessionKey, chatMsg)

    //   }) 
    // }

		this.renderMsg(null, null, chatMsg, sessionKey);
		msgStorage.on("newChatMsg", function(renderableMsg, type, curChatMsg){
			if(!me.__visibility__) return;
			// 判断是否属于当前会话
			if(username.groupId){
				// 群消息的 to 是 id，from 是 name
				if(renderableMsg.info.from == username.groupId || renderableMsg.info.to == username.groupId){
					me.renderMsg(renderableMsg, type, curChatMsg, sessionKey);
				}
			}
			else if(renderableMsg.info.from == username.your || renderableMsg.info.to == username.your){
				me.renderMsg(renderableMsg, type, curChatMsg, sessionKey);
			}
		});
	},
});
