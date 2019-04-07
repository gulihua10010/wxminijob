// pages/my/address.js
var util = require("../../utils/util.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userId: "",
    lists: [],
    redirect:  '',
    name:'',
    address:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getlist();
  },
  getlist: function () {
    var o = this;
    var user = wx.getStorageSync("userinfodetail")
    util.ajaxByJson("/getAddress/" + user.id, "POST",
      {}
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          o.setData({
            lists: r1.data
          });


        }

      })
  },
  selectthis: function (t) {
    var i = this, id = t.target.dataset.id 
   
    var pages = getCurrentPages();
    var currPage = pages[pages.length - 1];
    var prevPage = pages[pages.length - 2]; 
    prevPage.setData({
      departure: i.data.lists[id].name + "-" + i.data.lists[id].address
         
    })
    wx.navigateBack();
          

  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.getlist();
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getlist();
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