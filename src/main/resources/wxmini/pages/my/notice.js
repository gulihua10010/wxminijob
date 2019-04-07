// pages/my/notice.js

var util = require("../../utils/util.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[ ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('dd')
    
this.getlist();
  },
 getlist:function(){
   console.log(2)
   var user = wx.getStorageSync("userinfodetail");
var o=this;
   util.ajax("/getarts?sid="+user.id, "POST",
   {}
     , function (r1) {
       if (r1.status == 500) {
         return util.isError(r1.msg, o);
       } else {
       o.setData({
         list:r1.data
       }) 

       }

     })
 },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {this.getlist()
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