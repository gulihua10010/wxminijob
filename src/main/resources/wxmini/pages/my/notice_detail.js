// pages/my/notice_detail.js
getApp();
var util = require("../../utils/util.js"), t= require("../../wxParse/wxParse.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageInfo: "",
    'detail':{ 
    }

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var o=this;
    util.ajax("/getartsbyid?id=" + options.id  , "post", {}
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          o.setData({
            detail: r1.data
          })
t.wxParse("pageInfo","html",r1.data.content,o)
        }

      })

    // t.wxParse("pageInfo", "html", ,this);
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