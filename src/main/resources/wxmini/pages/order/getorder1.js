// pages/task_detail/task_detail.js
var util = require("../../utils/util.js");
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasUserInfo: false,
    myName: '',
    myNickName: '',
    btntip: '',
    data: '',
  }, 

  /**
   * 生命周期函数--监听页面加载
   */ 
  onLoad: function (options) { 
    console.log(options.id) 
    var o = this;
    var user = wx.getStorageSync("userinfodetail")

    util.ajaxByJson("/getOrderByorderid?id=" + options.id, "post", { }
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          o.setData({
            data: r1.data
          })

        }

      })


  },

  finish:function(){




  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

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