// const formatTime = date => {
//   const year = date.getFullYear()
//   const month = date.getMonth() + 1
//   const day = date.getDate()
//   const hour = date.getHours()
//   const minute = date.getMinutes()
//   const second = date.getSeconds()

//   return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
// }
var baseurl = "http://127.0.0.1:8090/api";
var imgurl = "http://127.0.0.1:8090/";
// var baseurl = "http://127.0.0.1:8090/api";
// var imgurl = "http://127.0.0.1:8090/";
const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}
function t(t) {
  return (t = t.toString())[1] ? t : "0" + t;
}
 
module.exports = {
  formatTime: function (date) {
    var year = date.getFullYear(), 
      month = date.getMonth() + 1,
      day = date.getDate(),
      hour =date.getHours(), 
      min = date.getMinutes(), 
      sec = date.getSeconds();
    return [year, month, day].map(t).join("-") + " " + [hour, min, sec].map(t).join(":");
  },
  req: function (url, data, fun) {
    wx.getStorage({
      key: "sk",
      success: function (r) {
        data || (data = {}), data.sk = r.data, wx.request({
          url: baseurl + url,
          data: data,
          method: "post",
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          success: function (res) {
            return console.log(res.data), "function" == typeof fun && fun(res.data);
          },
          fail: function () {
            return "function" == typeof fun && fun(!1);
          }
        });
      },
      fail: function () {
        console.log("没有获取到sk"), wx.request({
          url: baseurl + url,
          data: data,
          method: "post",
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          success: function (res) {
            return console.log(res.data), "function" == typeof fun && fun(res.data);
          },
          fail: function () {
            return "function" == typeof fun && fun(!1);
          }
        });
      }
    });
  },
  ajax: function (url, method, data, r) {
     wx.request({
       url: baseurl + url,
       method: method,
       data: data,
      header: {
        "content-type": "application/x-www-form-urlencoded",
        Accept: "application/json"
      },
       success: function (res) {
         r(res.data);
      },
      error: function (res) {
        console.log(res.statusCode + "," + res.errMsg), console.log(res.data);
      }
    });
  },
  ajaxByJson: function (url, method, data, r) {
    wx.request({
      url: baseurl + url,
      method: method,
      data: data,
      header: {
        "content-type": "application/json",
        Accept: "application/json"
      },
      success: function (res) {
        r(res.data);
      },
      error: function (res) {
        console.log(res.statusCode + "," + res.errMsg), console.log(res.data);
      }
    });
  },
  getDeviceInfo: function () {
    return wx.getSystemInfoSync();
  },
  S4: function () {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
  },
  uuid: function () {
    return (this.S4() + this.S4() + "-" + this.S4() + "-" + this.S4() + "-" + this.S4() + "-" + this.S4() + this.S4() + this.S4());
  },
  wxPay: function (e, t) {
    wx.requestPayment({
      timeStamp: e.timeStamp,
      nonceStr: e.nonceStr,
      package: e.package,
      signType: "MD5",
      paySign: e.paySign,
      success: t,
      fail: function (e) { },
      complete: function (e) { }
    });
  },

  trim: function (str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
  },
  getDateDiff: function (date) {
    var e = date - new Date().getTime();
    if (!(e < 0)) {
      var mon = e / 2592e6, week = e / 6048e5, day = e / 864e5,hour = e / 36e5, min= e / 6e4;
      return mon >= 1 ? parseInt(mon) + "月后" : week >= 1 ? parseInt(week) + "周后" : day >= 1 ? parseInt(day) + "天后" : hour >= 1 ? parseInt(hour) + "小时后" : min>= 1 ? parseInt(min) + "分钟后" : "刚刚";
    }
  },
  modal: function (t, e, n) {
    wx.showModal({
      title: t,
      content: e,
      success: function (t) {
        return "function" == typeof n && n(t.data);
      }
    });
  },
  logout: function () {
    wx.removeStorage({
      key: "encrypted_data"
    }), wx.removeStorage({
      key: "vi"
    }), wx.removeStorage({
      key: "userInfo"
    }), wx.removeStorage({
      key: "pass"
    }), wx.removeStorageSync("userId"), 
      wx.removeStorageSync("userinfodetail"), 
      wx.showToast({
        title: '退出成功',
        icon: 'success',
        duration: 2000
      })
      setTimeout(function(){

  wx.switchTab({ 
      url: "/pages/index/index"
    });
      },2000)
  
      
    // wx.switchTab({ 
    //   url: "/pages/index/index"
    // });
  },
  logoutwithNoToast: function () {
    wx.removeStorage({
      key: "encrypted_data"
    }), wx.removeStorage({
      key: "vi"
    }), wx.removeStorage({
      key: "userInfo"
    }), wx.removeStorage({
      key: "pass"
    }), wx.removeStorageSync("userId");
    
   
    wx.switchTab({ 
      url: "/pages/index/index"
    });
  },
  parseLocation: function (t) {
    console.log(t)
    var e = t.split("-")[0];
    return e.match("大学") ? e.split("大学")[0] + "大学" : e.match("学院") ? e.split("学院")[0] + "学院" : t.match("-") ? t.split("-")[1].split(/[县区]/)[0] : e.split(/[县区]/)[0];
  },
  isLocationValid: function (t) {
    console.log(t)
    //大厦
    var e = t;
    if ("" == e || null == e){
      return "请选择您的学校" 
    }else{
      if (e.match("校区") && (e.match("大学") || e.match("学院"))){
        return e.split("校区)")[0] + "校区)" 
      }else{
        if (e.match("大学")){
          return e.split("大学")[0] + "大学"
        } else if (e.match("学院") ){
          return e.split("学院")[0] + "学院" 
        }

      }

    }
    // return "" == e || null == e ? "请选择您的学校" 
    // : e.match("大学") ? e.split("大学")[0] + "大学" : e.match("学院") ? e.split("学院")[0] + "学院" : "请选择您的学校";
    // return "" == e || null == e ? "请选择您的学校" : e.match("大厦") ? e.split("大厦")[0] + "大厦" : e.match("学院") ? e.split("学院")[0] + "学院" : "请选择您的学校";
  }, 
  isError: function (t, e) {
    e.setData({
      showTopTips: true,
      errorMsg: t
    });
  },
  objToArray: function (t) {
    var e = new Array();
    for (var n in t) e[n] = t[n];
    return e;
  },
  img: function (tempFilePaths, _this, typeName, i, l) {
    var url= baseurl+'/uploadImg'
    console.log(typeName);
    for (var s = 0; s < tempFilePaths.length; s++) wx.uploadFile({
      url: url,
      filePath: tempFilePaths[s],
      name: "file",
      header: {
        "Content-Type": "multipart/form-data",
        accept: "application/json",
        Authorization: "Bearer .."
      },
      success: function (o) {
        var e = JSON.parse(o.data);
        console.log(e)
        if (e.status==200) {  
          // var tt = _this.data;
          //   tt[typeName] = baseurl + e.data.url 
          // _this.setData(tt) 
          switch (typeName) {
            case 'idcard1':
              _this.setData({
                idcard1: imgurl + e.data.url,
              }); break;
            case 'idcard2':
              _this.setData({
                idcard2: imgurl + e.data.url,
              }); break;
            case 'stucard':
              _this.setData({
                // stucard: baseurl + e.data.url,
                stucard: imgurl + e.data.url,
              }); break;
          } 
          if(typeName=='pic'){ 
            if(_this.data.pic1==''){  
              _this.setData({
                pic1: imgurl + e.data.url,
              });
            } else if (_this.data.pic2 == '') { 
              _this.setData({
                pic2: imgurl + e.data.url,
              });
            } else if (_this.data.pic3 == '') { 
              _this.setData({
                pic3: imgurl + e.data.url,
              });
            }
          }
        }
      },
      fail: function (a) {
        console.log("-------------------"), console.log(a);
      }
    });
  }
}
