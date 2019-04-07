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
    ismy: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.id)
    var o = this;
    var user = wx.getStorageSync("userinfodetail")

    util.ajaxByJson("/getTaskById?id=" + options.id, "post", {}
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          o.setData({
            data: r1.data
          })
          if (r1.data.publishUserid == user.id) {
            o.setData({
              ismy: true,
            })
          }
          console.log(o.data.ismy)
          if (o.data.data.status == 4) {
            o.setData({
              hasUserInfo: false
            })
            o.setData({
              btntip: '订单完成',
            })
          } else {
            if (new Date(o.data.data.finshTime).getTime() < new Date().getTime()) {
              o.setData({
                hasUserInfo: false
              })
              o.setData({
                btntip: '订单过期',
              })

            }
          }

        }

      })
    wx.getStorage({
      key: "userInfo",
      success: function (res) {
        o.setData({
          hasUserInfo: true
        })
        wx.getStorage({
          key: "userinfodetail",
          success: function (res) {
            if (parseInt(res.data.status) <= 2) {
              o.setData({
                hasUserInfo: false,
              })
              o.setData({
                btntip: '请先完成个人认证',
              })
            } else {

              o.setData({
                hasUserInfo: true,
              })
              o.setData({
                myNickName: res.data.nickName,
                myName: res.data.openId,
              })
            }



          },
          fail: function () {
            console.log('f')//
            o.setData({
              btntip: '请先登录',
            })
          }
        })
      }, fail: function (res) {
        o.setData({
          btntip: '请先登录',
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