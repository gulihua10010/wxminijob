// pages/my/address.js
var util = require("../../utils/util.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userId: "",
    lists: [],
    redirect: !1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   this.getlist();
  },
getlist:function(){
var o=this;
  var user = wx.getStorageSync("userinfodetail")
  util.ajaxByJson("/getAddress/" + user.id, "POST",
    {  }
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
  deleteAddress:function(t){
    var i = this, id = t.target.dataset.id, idx = t.target.dataset.item;
    return wx.showModal({
      title: "提示",
      content: "确定删除?",
      success: function (res) {
        if (res.confirm) {
          util.ajaxByJson("/delAddress/" + id, "post", {}
            , function (r1) {
              if (r1.status == 500) {
                return util.isError(r1.msg, i);
              } else {
                wx.showToast({
                  title: '删除成功',
                  icon: 'success',
                  duration: 1000
                })
console.log(id)
                i.data.lists.splice(idx, 1)
                i.setData({
                  lists: i.data.lists
                })

              }


            })
        } else if (res.cancel) {

        }

      }
    });


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