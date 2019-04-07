// pages/my/info.js
var util = require("../../utils/util.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sex: ["保密", "男", "女"],
    provinces: [], 
    citys: [],
    city: "",
    idcard1:'',
    idcard2: '', 
    stucard: '', 
    school:'请选择你的学校',
    countys: [],
    county: "",
    value: [0, 0, 0],
    values: [0, 0, 0],
    condition: !1,
    verification: "",
    getCodeText: "获取验证码",
    waitCode: !1,
    bootflag: 0,
    file:'',
    existSchool: !1,
    existPhone: false,
    disabled:false,
    isjin:false,
    phone: '',
    usertips:'请完善您的个人信息',
    userInfo:  {
     
    },
    btninfo:'提交审核'
  },
  chooseStart: function () {
    var t = this;
    wx.chooseLocation({
      success: function (a) {
        t.setData({
          school: util.isLocationValid(a.name)
        });
      },
      fail: function () { 
        t.setData({
          school: '请选择你的学校'
        });
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     
    console.log('on')
    var t=this;
    wx.getStorage({
      key: "userinfodetail",
      success: function (res) {
        console.log('s') 
        console.log(res.data) 
        t.setData({
          userInfo: res.data, 
        })
        t.setData({
          phone: res.data.phone,
        })
        t.setData({
          idcard1: res.data.idCard1,
        })
        t.setData({
          idcard2: res.data.idCard2,
        })
        t.setData({
          stucard: res.data.stuCard,
        })
        // if (res.data.phone !== "" || res.data.phone!==null){
        //   t.setData({
        //     existSchool: true
        //   })

        // }
        if(res.data.status<0){
          t.setData({
            isjin:true,
          })
         t.setData({
           usertips : '你已经被管理员禁用',
          })
        } else if(res.data.status==3) {
          t.setData({
            isjin:true
          })
          t.setData({
            usertips:'审核未通过'
          })
        }else if(res.data.status==2){
t.setData({
  isjin:true,
  usertips:'请完成信息认证'
})

        }else if(res.data.status==4){
          t.setData({
            isjin:false
          })
        }
        if (res.data.school !== ""&&res.data.school !== null) {
          t.setData({
            existPhone:true,
          })

        }
       
        console.log((t.data.stucard).length)

        if (res.data.school == '') {
          t.setData({
            school: '请选择你的学校',
          }) 
        }else{ 
          t.setData({
            school: res.data.school,
          })
          
        }
        if (res.data.phone != '' && res.data.phone!=null){
          console.log(res.data.phone)
          t.setData({
            existPhone: true
          });
        }
       
      },
      fail: function () { 
        console.log('f')
      }
    })
  },
  emptyImg: function (t) {
    console.log('em')
    var a = this, 
    e = t.target.dataset.type;
      console.log(e)
   var  o = a.data; 
   switch(e){
     case 'idcard1': 
     a.setData({
       idcard1: [],
     });break;
     case 'idcard2':
       a.setData({
         idcard2: [],
       }); break;
     case 'stucard':
       a.setData({
         stucard: [],
       }); break;
   } 
  },
  chooseimage: function (t) {
    var e = this, o = t.currentTarget.dataset.type;
    e.data[o];
    wx.chooseImage({
      count: 1,
      sizeType: ["original", "compressed"],
      sourceType: ["album", "camera"],
      success: function (t) {
        util.img(t.tempFilePaths, e, o);
      }
    });
  },
  updatePhone: function () {
    this.setData({
      existPhone: false
    });
  },
  formSubmit: function (a) {
    var o = this;
    if ("请选择您的学校" == util.isLocationValid(this.data.school)) return util.isError("请选择您的学校", o);
       
    this.setData({
      "userInfo.school": this.data.school, 
      "userInfo.nickName": a.detail.value.nickName,
      "userInfo.realName": a.detail.value.realName,
      "userInfo.phone": a.detail.value.phone,
      "userInfo.gender": a.detail.value.gender,
      "userInfo.college": a.detail.value.college,
      "userInfo.className": a.detail.value.className,
      "userInfo.stuNo": a.detail.value.stuno,
      "userInfo.idCard1": this.data.idcard1,
      "userInfo.idCard2": this.data.idcard2,
      "userInfo.stuCard": this.data.stucard,  
    });
    var i = o.data.userInfo;
   
      if ("" == a.detail.value.verification) 
      return util.isError("请输入验证码", o);
      i.verification = a.detail.value.verification; 
    
    util.ajaxByJson("/saveUserData/" + a.detail.value.verification, "POST",
      JSON.stringify(i)
       , function (r1) {
         if(r1.status==500){
           return util.isError(r1.msg, o);
         }else{
           if(r1.status==200){
             wx.showToast({
               title: '提交成功',
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
  getPhoneValue: function (e) {
    this.setData({
       phone: e.detail.value
    })
  },
  //获取验证码
  getVerificationCode() {
    this.getCode();
    var _this = this
    _this.setData({
      disabled: true
    })
  },
  getCodeValue: function (e) {
    this.setData({
      code: e.detail.value
    })
  },
  getCode: function () {
    var a = this.data.phone;
    var _this = this;
    var myreg = /^(14[0-9]|13[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$$/;
    if (this.data.phone == "") {
      wx.showToast({
        title: '手机号不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    } else if (!myreg.test(this.data.phone)) {
      wx.showToast({
        title: '请输入正确的手机号',
        icon: 'none',
        duration: 1000
      })
      return false;
    } else {
      util.ajax("/sendCode", "POST", { phone: this.data.phone}, function (r) {
        console.log(r)
        var num = 61;
        var timer = setInterval(function () {
          num--;
          if (num <= 0) {
            clearInterval(timer);
            _this.setData({
              getCodeText: '重新发送',
              disabled: false
            })

          } else {
            _this.setData({
              getCodeText: num + "s"
            })
          }
        }, 1000)
      })
    

    }


  },
 
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var t = this;
    wx.getStorage({
      key: "userinfodetail",
      success: function (res) { 
 
        console.log(res.data)
        console.log(res)
        t.setData({
          userInfo: res.data,
        })

      },
      fail: function () {
        console.log('f')
      }
    })
  
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