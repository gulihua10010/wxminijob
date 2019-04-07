let disp = require("../../webim/utils/broadcast");

Page({
	data: {
		username: {
			your: "",
		},
	},

	// options = 系统传入的 url 参数
	onLoad(options){
		let username = JSON.parse(options.username);
    console.log(username)
		this.setData({ username: username });
		wx.setNavigationBarTitle({
      title: username.yourNickName
		});
	},

	onUnload(){
		disp.fire("em.chatroom.leave");
	},

});
