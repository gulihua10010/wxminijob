// pages/order.js

var util = require("../../utils/util.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: [ ],
    type: {
      0: "pay",
      1: "get"
    },
    tab: 0,
    p: 0,
    winWidth: 0,
    winHeight: 0,
    hasuerinfo:true, 
    isjin:false,
  },
  swichNav: function (t) {
    var e = this;
    if (this.data.tab === t.target.dataset.current) return false;
    this.getlist(t.target.dataset.current); 
    e.setData({ 
      p: 0,
      tab: t.target.dataset.current
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var user = wx.getStorageSync('userinfodetail')
    if(user.status<0){
      this.setData({
        isjin: true, 
      });
    }else{
      this.getlist(this.data.tab); 

    }


  },

getlist:function(type){ 
  var o = this;
  o.setData({
    hasuerinfo: true
  })
  var user = wx.getStorageSync('userinfodetail') 
  console.log(o.data.hasuerinfo)
  if(user==""||user==null||user==undefined){
    console.log(11111)
    o.setData({ 
      hasuerinfo: false
    })

  }else{
    if (type == 0) {
      util.ajaxByJson("/getTaskListByuid/" + user.id, "post", {}
        , function (r1) {
          if (r1.status == 500) {
            return util.isError(r1.msg, o);
          } else {
            o.setData({
              list: r1.data
            })
          }

        })
    } else {
      util.ajaxByJson("/getOrderByuid?uid=" + user.id, "post", {}
        , function (r1) {
          if (r1.status == 500) {
            return util.isError(r1.msg, o);
          } else {
            o.setData({
              list: r1.data
            })
          }

        })

    }
  }

  console.log(o.data.hasuerinfo)

},
  del: function (t) {
    var i = this, id = t.currentTarget.id, idx = t.target.dataset.item; 
    return wx.showModal({
      title: "提示",
      content: "确定删除?",
      success: function (res) {
        if (res.confirm) {
          util.ajaxByJson("/delTaskByid/" + id, "post", {}
            , function (r1) {
              if (r1.status == 500) {
                return util.isError(r1.msg, i);
              } else {
                wx.showToast({
                  title: '删除成功',
                  icon: 'success',
                  duration: 1000
                })
                i.data.list.splice(idx, 1)
                i.setData({
                  list: i.data.list
                })
                // for (var k = 0; k < i.data.list.length; k++) {
                //   if (i.data.list[k].id == id) {
                //     i.data.list.splice(k, 1)
                //     console.log(i.data.list)
                //   }
                //   i.setData({
                //     list: i.data.list
                //   })
                // }

              }
              

            })
        } else if (res.cancel) {
         
        }
       
      }
    }) ;
  
  },
  delorder: function (t) {
    var i = this, id = t.currentTarget.id, idx = t.target.dataset.item,
      uid =   t.target.dataset.uid, tid =  t.target.dataset.tid;
    return wx.showModal({
      title: "提示",
      content: "确定删除?",
      success: function (res) {
        if (res.confirm) {
          util.ajaxByJson("/delOrder?id=" + id+"&tid="+tid+"&uid="+uid, "post", {}
            , function (r1) {
              if (r1.status == 500) {
                return util.isError(r1.msg, i);
              } else  if(r1.status==200){
                wx.showToast({
                  title: '删除成功',
                  icon: 'success',
                  duration: 1000
                })
                i.data.list.splice(idx, 1)
                i.setData({
                  list: i.data.list
                })
                // for (var k = 0; k < i.data.list.length; k++) {
                //   if (i.data.list[k].id == id) {
                //     i.data.list.splice(k, 1)
                //     console.log(i.data.list)
                //   }
                //   i.setData({
                //     list: i.data.list
                //   })
                // }

              }else{
                return util.isError(r1.msg, i);
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

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    if(this.data.isjin==false){
      this.getlist(this.data.tab); 

    }
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