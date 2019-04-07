function show(param){
    /*var length=${lenth};*/
    var length=param;
    if(length>0){
        $("#td").html(sToHms(length));
    }else{
        $("#td").html("00:00:00");
    }

}
//秒转换成时分秒
function sToHms(s){
    s = Math.floor(s);  //如果输入的是浮点数，则舍弃小数位
    var h = Math.floor(s/3600);  //计算得出小时数
    if(h<10){  //调整为两位数的格式
        h = '0'+h;
    }
    var m = Math.floor(s/60-h*60);  //计算得出分钟数
    if(m<10){  //调整为两位数的格式
        m = '0'+m;
    }
    var s = s%60;  //计算得出剩下的秒数
    if(s<10){  //调整为两位数的格式
        s = '0'+s;
    }
    return h+':'+m+':'+s;  //最后连接成字符串并返回
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

