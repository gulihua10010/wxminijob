// pages/index/add.js
var util = require("../../utils/util.js"),  
t = getApp(),
  n = util.formatTime(new Date(new Date().getTime() + 864e5)).split(" ")[0],
  date = util.formatTime(new Date()).split(" ")[0], 
  i = util.formatTime(new Date(new Date().getTime() + 53568e5)).split(" ")[0],
   r = !1;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    sex: ["请选择性别", "男", "女"],
    nums: [1,2,3,4,5,6,7,8,9,10],
    type: 1,
    gender: 0,
    date: date,
    start: date,
    end: i,
    phone:"",
    time: "请选择时间", 
    username:'',
    usernametip:'请先进行用户注册'
,    isAgree: !1,
    vehicle: "",
    departure: "出发地(点击获取位置)",
    destination: "目的地",
    goods: 0,
    price: "",
    remark: "",
    hidden_remark: "",
    paid: 0,
    hasUserInfo:false,
    pic1:'',
    pic2:'',
    pic3:'',
    num:1,
    task: {},
    uuid:0,
    isjin:false,


  },
  // tips:function(){
  //   wx.showToast({
  //     title: '请先登录或认证',
  //     icon: 'none',
  //     duration: 2000
  //   })
  // },
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var t = this;
    wx.getStorage({
      key: "userInfo",
      success: function (res) { 
        t.setData({ 
          hasUserInfo: true
        })
        wx.getStorage({
          key: "userinfodetail",
          success: function (res) {//
            console.log('sss')
            t.setData({
              hasUserInfo: true
            })
            t.setData({
              uuid: res.data.id,
            })
            t.setData({
              phone: res.data.phone,
            })
            // console.log(res.data.phone)
            t.setData({
              username: res.data.realName,
            })

            t.setData({
              gender: res.data.gender,
            })
            // t.setData({
            //   usernametip: '请输入名字',
            // })
            if (parseInt(res.data.status) <= 3&&res.data.status>0) {
              t.setData({
                usernametip: '请先完成个人资料认证',
              })
              t.setData({
                hasUserInfo: false,
              })
            }else if(res.data.status<0){

              t.setData({
                isjin:true,
                usernametip:'你已经被管理员禁用'
              })
            }

          },
          fail: function () {
            t.setData({
              usernametip: '请先登录',
            })
            
          }
        })
      },
      fail: function () { 
        t.setData({
          usernametip: '请先登录',
        })

      }
    })
   
 
  },
  formSubmit: function (a) {
    var o = this;
    if (a.detail.value.type=='') return util.isError("请输入类型", o);
    if (a.detail.value.title == '') return util.isError("请输入标题", o);
    if (this.data.departure == '出发地(点击获取位置)') return util.isError("请选择出发地", o);
    if (a.detail.value.date == '') return util.isError("请选择截止日期", o);
    if (this.data.num == '') return util.isError("请输入人数", o);
    if (a.detail.value.time == '请选择时间') return util.isError("请选择截止时间", o);
    if  (a.detail.value.price == '') return util.isError("请输入赏金", o);
    if (parseInt(a.detail.value.price) > 2000) return util.isError("建议悬赏金额不要多于2000元", o);
    if (isNaN(a.detail.value.price)) return util.isError("赏金请输入数字！！", o);
    if (a.detail.value.remark == '') return util.isError("请输入介绍", o);   
    if(new Date(a.detail.value.date + a.detail.value.time + ":00").getTime()-new Date().getTime()<0){
      return util.isError("截止时间不能小于当前时间", o); 
    }
    console.log(parseInt(a.detail.value.price))
    this.setData({
      "task.title": a.detail.value.title,
      "task.type": a.detail.value.type,
      "task.place": this.data.departure,
      "task.personNum": this.data.num,
      "task.payment": a.detail.value.price,
      "task.desc": a.detail.value.remark,
      "task.pic1": this.data.pic1,
      "task.pic2": this.data.pic2,
      "task.pic3": this.data.pic3, 
      "task.finshTime": a.detail.value.date + a.detail.value.time+":00", 
      "task.publishUserid": this.data.uuid, 
      "task.privateInfo": a.detail.value.hidden_remark  , 
    });
    var i = o.data.task;
 
    util.ajaxByJson("/publishTask"  , "POST",
      JSON.stringify(i)
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          if (r1.status == 200) {

            wx.showToast({
              title: '发布成功',
              icon: 'success',
              duration: 1000
            })

            setTimeout(function () {
              wx.switchTab({
                url: "/pages/index/index"
              });
            }, 2000)

          }
  


        }

      })

  },
  emptyImg: function (t) { 
    var a = this, 
    e = t.target.dataset.type;
      console.log(e)
   var  o = a.data; 
   switch(e){
     case 'pic1': 
     a.setData({
       pic1: a.data.pic2,
     });
       a.setData({
         pic2: a.data.pic3,
       });  
     break;
     case 'pic2':
       a.setData({
         pic2: a.data.pic3,
       });  
       a.setData({
         pic3  :'',
       }); 
        break;
     case 'pic3':
       a.setData({
         pic3: '',
       }); break;
   } 
  },
  chooseimage: function (t) {
    var e = this, o = t.currentTarget.dataset.type;
    e.data[o];
    wx.chooseImage({
      count: 3,
      sizeType: ["original", "compressed"],
      sourceType: ["album", "camera"],
      success: function (t) {
        util.img(t.tempFilePaths, e, o);
      }
    });
  },
  sexDeparture: function () { 

    var a = this;
    wx.chooseLocation({
      success: function (e) {
        a.setData({
          departure: e.name + "-" + e.address
        });
      },
      fail: function () {
        util.modal("错误", "请检查是否开启手机定位");
      }
    });
  }, 
  setSex: function (e) {
    this.setData({
      gender: e.detail.value
    });
  },
  setNum: function (e) {
    this.setData({
      num: e.detail.value
    });
  },
  bindDateChange: function (e) {
    console.log('dd')
    this.setData({
      date: e.detail.value
    });
  },
  bindTimeChange: function (e) {
    this.setData({
      time: e.detail.value
    });
  },
 
 
})