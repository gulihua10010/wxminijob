layui.use(['form','layer','jquery','element','upload'],function(){
  var form = layui.form
  ,layer = layui.layer
  ,element = layui.element
  ,$ = layui.jquery;
  
  
  $('.logi_logout').click(function(){
	    loading = layer.load(2, {
	      shade: [0.2,'#000']
	    });
	    var url=$(this).data('url');
	    var locationurl=$(this).attr('location-url');
	    $.getJSON(url,function(data){
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
	  });


})