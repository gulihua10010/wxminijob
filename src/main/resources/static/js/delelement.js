layui.use(['layer','jquery','form'],function(){
  var layer = layui.layer
  , form = layui.form
  ,jq = layui.jquery;
  
  form.on('checkbox(checkAll)', function(data){
    if(data.elem.checked){
      jq("input[lay-filter='checkOne']").prop('checked',true);
    }else{
      jq("input[lay-filter='checkOne']").prop('checked',false);
    }
    form.render('checkbox');
  });  

  form.on('checkbox(checkOne)', function(data){
    var is_check = true;
    if(data.elem.checked){
      jq("input[lay-filter='checkOne']").each(function(){
        if(!jq(this).prop('checked')){ is_check = false; }
      });
      if(is_check){
        jq("input[lay-filter='checkAll']").prop('checked',true);
      }
    }else{
      jq("input[lay-filter='checkAll']").prop('checked',false);
    } 
    form.render('checkbox');
  });
  jq('.elementdel').click(function(){
	  
	//  var id= jq(this).data('id');
	  var url= jq(this).data('url');
	  var page= jq('.pagination li.active span').html();
  var length= jq('.admin-table #content tr').length;
 
  //alert(locationurl);
 // alert(window.location.href);
  layer.confirm('你确定要删除该条数据吗?', {icon: 3, title:'删除提示'}, function(index){
		    loading = layer.load(2, {
			      shade: [0.2,'#000']
			    });
		   
			  
			    jq.getJSON(url,function(data){
			    	
			      if(data.code == 200){
			        layer.close(loading);
			        layer.msg(data.msg, {icon: 1, time: 1000}, function(){
			         // 
			        	 if(length-1>0){
			        		 location.reload();
			        	  }else{
			        		  if(page>1){
			        			  page=page-1;
			        			  
			        		  }
			        		  location.href = window.location.href+'?page='+page;// '{:url("admin_user/index")}'+page;
			        	  }
			          
			        });
			      }else{
			        layer.close(loading);
			        layer.msg(data.msg, {icon: 2, anim: 6, time: 1000});
			      }
			    });
		 
		}); 
	  
	  

	  });


  form.on('submit(alldelete)', function(data){
    var is_check = false;
    jq("input[lay-filter='checkOne']").each(function(){
      if(jq(this).prop('checked')){ is_check = true; }
    });
    if(!is_check){
      layer.msg('请选择数据', {icon: 2,anim: 6,time: 1000});
      return false;
    }
    var url= jq(this).data('url');
   
    layer.confirm('确定批量删除数据吗?', function(index){
      loading = layer.load(2, {
        shade: [0.2,'#000']
      });
      var param = data.field;
    
      jq.post(url,param,function(data){
        if(data.code == 200){
          layer.close(loading);
          layer.msg(data.msg, {icon: 1, time: 1000}, function(){
            location.reload();
          });
        }else{
          layer.close(loading);
          layer.msg(data.msg, {icon: 2,anim: 6, time: 1000});
        }
      });
    });
    return false;
  });


	  
  });