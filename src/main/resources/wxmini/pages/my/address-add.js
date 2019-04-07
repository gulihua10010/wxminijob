// pages/my/address-edit.js
getApp();

var util = require("../../utils/util.js"), loca = require("../../utils/location.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userId: 0,
    id: 0,
    info: "",
    lat: 0,
    lng: 0,
    name: "",
    address: ''
  },
  formSubmit: function (a) {
    var o = this;
    if (a.detail.value.name == '') return util.isError("请输入位置信息", o);
    if (a.detail.value.address == '') return util.isError("请输入详细地址信息", o);
    this.setData({
      name: a.detail.value.name,
      address: a.detail.value.address
    });
    var user = wx.getStorageSync("userinfodetail")
    util.ajaxByJson("/addAddress/", "POST",
      {
        name: o.data.name,
        address: o.data.address,
        uid: user.id

      }
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          wx.showToast({
            title: '添加成功',
            icon: 'success',
            duration: 1000
          })

          setTimeout(function () {
            wx.redirectTo({
              url: "/pages/my/address"
            });
          }, 1000)


        }

      })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },
  setPlace: function () {
    var a = this;
    loca.choose(a.data.lat, a.data.lng, "postion", function () {
      var t = wx.getStorageSync("postion_cn");
      a.setData({
        place: t.name,
        info: t.address,
        lat: t.lat,
        lng: t.lng
      });
    });
  },
})