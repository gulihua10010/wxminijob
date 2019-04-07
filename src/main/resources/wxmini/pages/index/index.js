//index.js
//获取应用实例
const app = getApp()   

var util = require("../../utils/util.js"), e = util.formatTime(new Date(new Date().getTime() + 6048e5)).split(" ")[0], s = (util.formatTime(new Date()).split(" ")[0],
  util.formatTime(new Date(new Date().getTime() + 53568e5)).split(" ")[0]), i = 1, o = new Array(), n = new Array(), d = new Array(), r = new Array();

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    all: "act",   
    sex: ["男女不限", "只看男生", "只看女生"],  
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    gender: 0,
    start: "",
    over: "",
    adList: [],
    lists:[],
    images: {},
    tips: 0, 

  }, 
  onLoad: function () { 
    var o = this;
 o.getList()
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  setSex: function (t) {
    this.setData({
      gender: t.detail.value
    }), 
      this.getList(this.data.start, this.data.gender);  
  },
  getList: function (school,gender) {
    var o = this;
    console.log(school)
    console.log(gender)
    if (school == undefined) school = ""
    if (gender == undefined) gender=0
    util.ajaxByJson("/getTaskList?school="+school+"&gender="+gender, "post", {}
      , function (r1) {
        if (r1.status == 500) {
          return util.isError(r1.msg, o);
        } else {
          o.setData({
            lists: r1.data
          })
        }

      })
  },
  onPullDownRefresh: function () {
    this.getList(this.data.start, this.data.gender);   
     wx.stopPullDownRefresh();
  },
  onShow:function(){
    this.getList(this.data.start, this.data.gender);   
  },
  chooseStart: function () {  
    var t = this;
    wx.chooseLocation({ 
      success: function (res) {
        var d = util.isLocationValid(res.name) 
        t.setData({
          start: d== undefined ? '' :d
        }),   
          t.getList(t.data.start, t.data.gender);
      },
      fail: function () {
        t.setData({
          start: ""
        }), t.getList(t.data.date, "", t.data.over, t.data.gender);
      }
    });
  },
})
