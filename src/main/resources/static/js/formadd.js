layui.use(['form','upload'],function(){
    var form = layui.form
 
  ,jq = layui.jquery;

 var url=jq('form').data('url');
 var locationurl=jq('form').attr('localtion-url');
 
 //jq('.btable-paged').eq(1).hide();
 //
 // var uploadurl=jq('#uploadfile').data('url');
 // layui.upload({
	//     url: uploadurl
	//     ,elem:'#uploadfile'
	// 	,ext: 'jpg|png|gif'
	//
	// 	,area: ['100px', '100px']
	//     ,before: function(input){
	//
	//
	//       loading = layer.load(2, {
	//         shade: [0.2,'#000'] //0.2透明度的白色背景
	//       });
	//     }
	//     ,success: function(res, input){
	//       layer.close(loading);
	//
	//    var eleid=jq(input).data('id');
	//       jq('#'+eleid).val(res.path);
	// 	 // headedit.src = res.headpath;
	//       layer.msg(res.msg, {icon: 1, time: 1000});
	//     }
	//   });
 //
 // jq('.layui-form').append('<input type="hidden" name="token" value="'+11111111+'">');

 form.on('submit(formadd)', function(data){
	    loading = layer.load(2, {
	      shade: [0.2,'#000']
	    });
	
	  
	    var param = data.field;
	   
	    jq.post(url,param,function(data){
	    	
	      if(data.code == 200){
	        layer.close(loading);
	        layer.msg(data.msg, {icon: 1, time: 1000}, function(){
	          location.href = locationurl;
	        });
	      }else{
	        layer.close(loading);
	        layer.msg(data.msg, {icon: 2, anim: 6, time: 1000});
	      }
	    });
	    return false;
	  });

})