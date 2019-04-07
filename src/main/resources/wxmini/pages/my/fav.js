// pages/my/fav.js
var util = require("../../utils/util.js");

Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var user = wx.getStorageSync("userinfodetail")
var o=this;
    util.ajaxByJson("/getfavlist/"  + "?uid=" + user.id, "post", {}
      , function (r1) {
        if (r1.status == 500) {
          // return util.isError(r1.msg, o);

        } else {
          o.setData({
            list: r1.data
          })
          if(r1.data==""||r1.data==null){
            o.setData({
              isnull: true
            })
          }

        }


      })
  },

del:function(op){
  var o=this;
  var idx = op.target.dataset.id;
  var id =op.target.dataset.item;
  var user = wx.getStorageSync("userinfodetail")
  util.ajaxByJson("/delfav/" +  id + "?uid=" + user.id, "post", {}
    , function (r1) {
      if (r1.status == 500) {
        return util.isError(r1.msg, o);
      } else { 
        wx.showToast({
          title: '取消收藏成功',
          icon: 'success',
          duration: 1000
        })

        o.data.list.splice(idx, 1)
        o.setData({
          list: o.data.list
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