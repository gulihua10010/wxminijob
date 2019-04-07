// pages/my/my.js
const app = getApp()
var util  = require("../../utils/util.js");
Page({

  /**
   * 页面的初始数据
   */
  data: { 
    canIUse: wx.canIUse('button.open-type.getUserInfo'), 
    hasUserInfo: false,
    userInfo: {},
    // userInfo: {
    //   'avatarUrl': 'https://ss0.bdstatic.com/7Ls0a8Sm1A5BphGlnYG/sys/portrait/item/45b6e6a2a8e88ab1e4b88de5b8a6e99ba85f5f3233.jpg?1549963112',
    //   'nickName': 'name'

    // }
  },
  getUserInfo: function (e) {
    console.log(e.detail.errMsg)
    console.log(e.detail.userInfo)
    wx.setStorageSync("userInfo", e.detail.userInfo)
    console.log(e.detail.rawData)
    // app.globalData.userInfo = e.detail.userInfo
    app.login();
    this.setData({
      userInfo: e.detail.userInfo, 
      hasUserInfo: true
    })
  },
  logout:function(){
    util.logout();
  },
  getuserinfos:function(){
    var o = this, user = wx.getStorageSync('userinfodetail');
    util.ajax("/getUserbyid?id=" + user.id, "POST",
      {}
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          if (r1.status == 200) {
            wx.setStorageSync('userinfodetail', r1.data)

          }


        }

      })


  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var t=this;
    var user=wx.getStorageSync('userinfodetail')
    if (user != null && user != '') {
      this.getuserinfos()
    }
    wx.getStorage({
      key: "userInfo",
      success: function (res) {
        console.log(res.data) 
        t.setData({
          userInfo: res.data,
          hasUserInfo: true
        })
      },
      fail: function () { 
        // if (t.data.canIUse) {
        //   // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
        //   // 所以此处加入 callback 以防止这种情况
        //   app.userInfoReadyCallback = res => {
        //     // app.login();
        //     t.setData({
        //       userInfo: res.userInfo,
        //       hasUserInfo: true
        //     })
        //   }
        // } else {
        //   // 在没有 open-type=getUserInfo 版本的兼容处理
        //   wx.getUserInfo({
        //     success: res => {
        //       // app.globalData.userInfo = res.userInfo
        //       // app.login();
        //       wx.setStorageSync("userInfo", res.userInfo)
        //       t.setData({
        //         userInfo: res.userInfo,
        //         hasUserInfo: true
        //       })
        //     }
        //   })
        // }
      }
    });
    // console.log(this.data.canIUse);
    // if (app.globalData.userInfo) {  
    //   // app.login();
    //   this.setData({
    //     userInfo: app.globalData.userInfo,
    //     hasUserInfo: true
    //   })
    // } else if (this.data.canIUse) {
    //   // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //   // 所以此处加入 callback 以防止这种情况
    //   app.userInfoReadyCallback = res => {
    //     // app.login();
    //     this.setData({
    //       userInfo: res.userInfo,
    //       hasUserInfo: true
    //     })
    //   }
    // } else {
    //   // 在没有 open-type=getUserInfo 版本的兼容处理
    //   wx.getUserInfo({
    //     success: res => {
    //       app.globalData.userInfo = res.userInfo
    //       // app.login();
    //       this.setData({
    //         userInfo: res.userInfo,
    //         hasUserInfo: true
    //       })
    //     }
    //   })
    // }
  },
  tips:function(){
    wx.showToast({
      title: '请先登录',
      icon: 'none',
      duration: 2000
    })
  },
  call:function(){

    wx.makePhoneCall({
      phoneNumber: '18360866171' // 仅为示例，并非真实的电话号码
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var user = wx.getStorageSync('userinfodetail')
    console.log('user')
    console.log(user)
    if (user != null &&user != '') {
      this.getuserinfos()
    }
  },
 
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var t=this
    var user = wx.getStorageSync('userinfodetail')
    if (user != null && user!='') { 
      this.getuserinfos()
    }
    wx.getStorage({
      key: "userInfo",
      success: function (res) {
        // console.log(res.data)  
        t.setData({
          userInfo: res.data,
          hasUserInfo: true
        })
      },
      fail: function () {
        t.setData({
          userInfo:"",
          hasUserInfo: false
        })
      }
      });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})