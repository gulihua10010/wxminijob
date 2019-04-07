// pages/task_detail/task_detail.js
var util = require("../../utils/util.js");
const app = getApp()  
Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasUserInfo: false,
    taskstatus: false,
    myName: '',
    myNickName:'',
    btntip:'',
    data:'',
    ismy:false,
    id:0,
    fav:'收藏',
    price:0,
    isget:false,
    fullnum:false,
    isjin:false,
    ordertip:'接单'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var ts = wx.getStorageSync('tast_status_' + options.id);
    console.log(ts)
    if(ts=='end'){
      this.setData({
        taskstatus: true, 
      })
      this.setData({   
        btntip: '订单过期',
      })

    } else if (ts =='finish'){  

      this.setData({ 
        taskstatus: true,  
      })
      this.setData({
        btntip: '订单完成',
      })
    }
    console.log(util.uuid())
    var user = wx.getStorageSync('userinfodetail')
    if (user.status <0) {
      this.setData({
        isjin: true,
        btntip:'您已经被管理员禁用'
      });
    }
    var o = this;
    o.setData({
      id: options.id
    })
    o.setData({
      uuid: util.uuid()
    }) 
    util.ajaxByJson("/getTaskById?id=" + options.id  , "post", {}
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          o.setData({
            data: r1.data
          })
          o.setData({
            price: r1.data.payment,
          })
          console.log('ddddd')   
          if (r1.data.publishUserid == user.id){
            o.setData({
              ismy: true,
            })
          } 
          if (r1.data.exeNum >= r1.data.personNum ) {
            o.setData({
              fullnum: true
            })
            o.setData({
              ordertip: '人数已满',
            })   
          }
          // console.log(o.data.data)
          if (r1.data.status==4){
            console.log('rrr')
            wx.setStorageSync('tast_status_'+r1.data.id, 'finish')
            o.setData({
              taskstatus: true,  
            })
            o.setData({
              btntip: '订单完成',
            })
          }else{
            // console.log(new Date(o.data.data.finshTime).getTime() < new Date().getTime())
            if (ts == '' &&new Date(r1.data.finshTime).getTime()<new Date().getTime()){
              wx.setStorageSync('tast_status_' + r1.data.id, 'end')
              o.setData({
                taskstatus: true, 
              })
              o.setData({
                btntip: '订单过期',
              })

            }
          }
          
        }

      })

    util.ajaxByJson("/getfav/" + this.data.id + "?uid=" + user.id, "post", {}
      , function (r1) {
        if (r1.status == 500) {
          // return util.isError(r1.msg, o);
        } else {
          o.setData({
            fav: '取消收藏',
          })

        }


      })

    util.ajaxByJson("/getGettaskbyUidAndTid?uid=" + user.id + "&tid=" +this.data.id, "post", {}
        , function (r1) {
          if (r1.status == 500) {
            return util.isError(r1.msg, o);
          } else {
              if(r1.data!=""&&r1.data!=null){

            o.setData({
              isget: true,
            })
                o.setData({
                  ordertip: "已接单",
                })
      
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
            if (parseInt(res.data.status) <= 3&&res.data.status>0) {
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
  fav:function(){ 
var o=this;
    var user = wx.getStorageSync("userinfodetail")
    if(this.data.fav=="收藏"){
      util.ajaxByJson("/fav/" + this.data.id + "?uid=" + user.id, "post", {}
        , function (r1) {
          if (r1.status == 500) {
            return util.isError('r1.msg', o);
          } else {
            o.setData({
              fav: '取消收藏',
            })
            wx.showToast({
              title: '收藏成功',
              icon: 'success',
              duration: 1000
            })

          }


        })

    }else{
      util.ajaxByJson("/delfav/" + this.data.id + "?uid=" + user.id, "post", {}
        , function (r1) {
          if (r1.status == 500) {
            return util.isError(r1.msg, o);
          } else {
            o.setData({
              fav: '收藏',
            })
            wx.showToast({
              title: '取消收藏成功',
              icon: 'success',
              duration: 1000
            })

          }


        })

    }

  },
  getorder:function(){
    var o = this;

      if(this.data.ismy){
        // wx.navigateTo({
        //   url: "/pages/order/getorder"
        // });
        
        return util.isError("不能接自己的单", o);
      }else if(this.data.isget){
        return util.isError("您已经接单了", o);
      } else if (this.data.fullnum) {
        return util.isError("人数已经满了", o);
      }   else{
        var user = wx.getStorageSync("userinfodetail")

          wx.showModal({
            title: '提示',
            content: '确定要接单嘛，意味着您将要执行这些任务',
            success: function (res) {
              if (res.confirm) {

                util.ajaxByJson("/addorder?tid=" + o.data.id + "&uid=" + user.id + "&token=" + o.data.uuid, "post", {payment:o.data.price}
                  , function (r1) {
                    if (r1.status == 500) {
                      return util.isError(r1.msg, o);
                    } else  if(r1.status==200){
                      wx.showLoading({
                        title: '正在生成订单...',
                      })
                      o.setData({
                        isget: true,
                      })
                      o.setData({
                        ordertip: '已接单',
                      })
                      setTimeout(function () {
                        wx.hideLoading()
                        wx.navigateTo({
                          url: "/pages/order/getorder?id="+r1.data
                        });


                      }, 2000)

                    }


                  })
              }  else if(res.cancel) {

            }

            }
          })
      

      }

  },
  into_room: function (event) {
    var o = this;

    if (this.data.ismy) {
      return util.isError("不能和自己聊", o);
    }

    var your= event.target.dataset.username.toLowerCase(),
        myName=this.data.myName.toLowerCase();
    var nameList = {
      myName: myName,
      myNickName: this.data.myNickName,
      your: your,
      yourNickName: event. target.dataset.nickname
    }; 
    var useravatarurl= event.target.dataset.useravatarurl;
    // app.getmsg_historychatMsg(your + myName)
    // app.getmsg_ChatMsgs(your+myName)
    wx.setStorageSync(your + "_",
      {
        nick: event.target.dataset.nickname,
        avatarurl: useravatarurl
      }) 
 
    wx.navigateTo({
      url: "../chatroom/chatroom?username=" + JSON.stringify(nameList)
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