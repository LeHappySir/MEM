<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机械设备管理系统</title>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/icon.css" />
<script src="<%=request.getContextPath()%>/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',split:true,border:false" style="width: 20%" title="机械管理系统">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'north',split:true,border:false" style="height: 80%">
					<div  style="height: 50px;width:100%;background-color: gray;"><label id="centerHead" style="font-weight: bold;font-size: xx-large;color: white;"></label></div>
					<div>
						<ul id="tree"></ul>
					</div>
				</div>
				<div data-options="region:'center',split:true,border:false" style="height: 20%">
					<a id="machineManageBtn" style="width: 99%;height: 49%;"><label style="font-size: large;">设备管理</label></a>
					<a id="maintainBtn"   style="width: 99%;height: 48%;"><label style="font-size: large;">保养/维修</label></a>
				</div>
			</div>
		</div>
		<div data-options="region:'east',split:true,border:false" style="width:80%">
			 <div style="height: 78px;width:100%;background-color: gray;"><label id="eastHead" style="font-weight: normal;font-size: 60px;color: white;"></label></div>
			 <div id="maintainDiv">
			 	<table id="maintainTable"></table>
			 	
			 </div>
			<div id="manageDiv">
				<table id="manageTable"></table>
				
			</div>
		</div>
	</div>
	<div id="manageDlg">
		<form id="mgeForm" method="post">
			<div>
				<label style="font-size: xx-large;font-weight: bold;margin-left: 20px;color: gray;">机械设备信息</label>
			</div>
			<div style="margin: 20px">
				<label  class="label-top">设备编号:</label>
				<input id="equipmentNo" name="equipmentNo" class="easyui-textbox" data-options="required:true"  style="width:100%;height:30px"><br><br>
				<label  class="label-top">设备名称 :</label>
				<input id="equipmentName" name="equipmentName" class="easyui-textbox" data-options="required:true" style="width:100%;height:30px"><br><br>
				<input id="specification" name="specification" class="easyui-textbox" label="规格型号:" labelPosition="top" style="width:100%;height:52px"><br><br>
				<input id="manufacturer" name="manufacturer" class="easyui-textbox" label="生产厂商:" labelPosition="top" style="width:100%;height:52px"><br><br>
				<input id="usingDepartment" name="usingDepartment" class="easyui-textbox" label="所在部门:" labelPosition="top" style="width:100%;height:52px"><br><br>
				<input id="usingState" name="usingState" class="easyui-textbox" label="使用情况:" labelPosition="top" style="width:100%;height:52px"><br><br>
				<input id="acquisitionTime" name="acquisitionTime" class="easyui-datetimebox" label="购置时间:" data-options="editable:false,panelWidth:300" labelPosition="top" style="width:100%;height:52px"><br><br>
				<input id="equipmentType" name="equipmentType" class="easyui-textbox" label="设备类型:" labelPosition="top" style="width:100%;height:52px">
				<input name="uid" type="hidden">
			</div>
		</form>
	</div>
	<div id="maintainDlg">
		<form id="mtaForm" method="post">
			  <div>
				<label style="font-size: xx-large;font-weight: bold;margin-left: 20px;color: gray;">设备维修信息</label>
			</div>
			<div style="margin: 20px">
				<label  class="label-top">维修单号:</label>
				<input id="repairNo" name="repairNo" class="easyui-textbox" data-options="required:true"  style="width:100%;height:30px"><br>
				<label  class="label-top">维修单名称:</label>
				<input id="repairName" name="repairName" class="easyui-textbox" data-options="required:true"  style="width:100%;height:30px"><br>
				<label  class="label-top">设备名称 :</label>
				<input id="eqmName" name="eqmName" style="width:100%;height:30px"><br>
				<input id="eqmNo" name="eqmNo" class="easyui-textbox" label="设备编号:" labelPosition="top" data-options="readonly:true" style="width:100%;height:52px"><br>
				<input id="eqmDep" name="eqmDep" class="easyui-textbox" label="使用部门:" labelPosition="top" data-options="readonly:true" style="width:100%;height:52px"><br>
				<input id="repairProposer" name="repairProposer" class="easyui-textbox" label="维修申请人:" labelPosition="top" style="width:100%;height:52px"><br>
				<input id="repairTime" name="repairTime" class="easyui-datetimebox" label="送修时间:" labelPosition="top" data-options="editable:false,panelWidth:300" style="width:100%;height:52px"><br>
				<input id="startTime" name="startTime" class="easyui-datetimebox" label="维修开始时间:" labelPosition="top" data-options="editable:false,panelWidth:300" style="width:100%;height:52px"><br>
				<input id="endTime" name="endTime" class="easyui-datetimebox" label="维修完成时间:" labelPosition="top" data-options="editable:false,panelWidth:300" style="width:100%;height:52px"><br>
				<input id="repairCharge" name="repairCharge" class="easyui-textbox" label="维修负责人:" labelPosition="top" style="width:100%;height:52px"><br>
				<input id="repairState" name="repairState" class="easyui-textbox" label="状态:" labelPosition="top" style="width:100%;height:52px"><br>
				<input id="fault" name="fault" class="easyui-textbox" label="故障描述:" labelPosition="top" style="width:100%;height:80px"><br>
				<input id="repairReason" name="repairReason" class="easyui-textbox" label="故障分析及处理:" labelPosition="top" style="width:100%;height:80px"><br>
				<input name="uid" type="hidden">
				<input name="host" type="hidden">
			</div>
		</form>
	</div>
</body>

<script type="text/javascript"> 
    var manageEdit;
    var saveEquipmentUrl;
    var saveRepairUrl;
   	var buildEquipmentTreeUrl = '<%=request.getContextPath()%>/equipment/buildEquipmentTree';
   	var buildRepairTreeUrl = '<%=request.getContextPath()%>/repair/buildRepairTree';
	$(function(){
		
		//var equData=getAllEquipment();
		//console.log(equData);
		$('#tree').tree({
			url:'<%=request.getContextPath()%>/equipment/buildEquipmentTree',
			onClick:function(node){
				
				if(node.children){
					$('#manageTable').datagrid('loadData',node.children);
				}else{
					var eqmData=[];
					eqmData.push(node);
					$('#manageTable').datagrid('loadData',eqmData);
				}
			}
		});
		$('#centerHead').text('设备管理');
		$('#eastHead').text('设备管理');
		$('#machineManageBtn').linkbutton({
			iconCls:'icon-large-chart',
			size:'large',
			iconAlign:'left',
			onClick:function(){
				$('#centerHead').text('设备管理');
				$('#eastHead').text('设备管理');
				$('#manageDiv').show();
				$('#maintainDiv').hide();
				$('#tree').tree({
					url:'<%=request.getContextPath()%>/equipment/buildEquipmentTree',
					onClick:function(node){
						
						if(node.children){
							$('#manageTable').datagrid('loadData',node.children);
						}else{
							var eqmData=[];
							eqmData.push(node);
							$('#manageTable').datagrid('loadData',eqmData);
						}
					}
				});
			}
			
		});
		
		$('#maintainBtn').linkbutton({
			iconCls:'icon-large-smartart',
			size:'large',
			iconAlign:'left',
			onClick:function(){
				$('#tree').tree({
					url:'<%=request.getContextPath()%>/repair/buildRepairTree',
					onClick:function(node){
						
						if(node.children){
							$('#maintainTable').datagrid('loadData',node.children);
						}else{
							var repairData=[];
							repairData.push(node);
							$('#maintainTable').datagrid('loadData',repairData);
						}
					}
				});
				$('#centerHead').text('保修/维修');
				$('#eastHead').text('维修记录');
				$('#manageDiv').hide();
				$('#maintainDiv').show();
				$('#maintainTable').datagrid({
					striped:true,
					fit:false,
					fitColumns:true,
					singleSelect:true,
					pagination:true,
					rownumbers:true,
					url:'<%=request.getContextPath()%>/repair/getAllRepair',
					columns:[[
						{ field:'repairState',title:'状态',width:100,align:'center'},
						{ field:'repairNo',title:'维修单号',width:100,align:'center'},
						{ field:'repairName',title:'维修单名称',width:100,align:'center'},
						{ field:'eqmName',title:'设备名称',width:100,align:'center'},
						{ field:'eqmNo',title:'设备编号',width:100,align:'center'},
						{ field:'eqmDep',title:'使用部门',width:100,align:'center'},
						{ field:'repairProposer',title:'维修申请人',width:100,align:'center'},
						{ field:'repairTime',title:'送修时间',width:100,align:'center'},
						{ field:'startTime',title:'维修开始时间',width:100,align:'center'},
						{ field:'endTime',title:'维修完成时间',width:100,align:'center'},
						{ field:'fault',title:'故障描述',width:100,align:'center'},
						{ field:'repairCharge',title:'维修负责人',width:100,align:'center'},
						{ field:'repairReason',title:'故障分析及处理',width:100,align:'center'},
					]],
					toolbar:[
						{width:'80px',height:'30px',iconCls:'icon-edit',text:'报修',handler:function(){
								$('#maintainDlg').dialog('open').dialog('setTitle','设备维修信息');
								$('#maintainDlg').form('clear');
								saveRepairUrl='<%=request.getContextPath()%>/repair/saveNewRepair'
							}
						},'-',
						{width:'120px',height:'30px',iconCls:'icon-reload',text:'执行保养或维修',handler:function(){
								var row=getSelectedRow('maintainTable');
								if(!row){
									$.messager.show({
										title:'提示',
										msg:'请选择一行！'
									})
								}else{
									$('#maintainDlg').dialog('open').dialog('setTitle','查看维修信息');
									$('#mtaForm').form('load',row);
									saveRepairUrl='<%=request.getContextPath()%>/repair/updateRepair';
								}
							}
						},'-',
						{width:'80px',height:'30px',iconCls:'icon-remove',text:'删除',handler:function(){
									deleteConfirm('maintainTable','<%=request.getContextPath()%>/repair/deleteRepair',buildRepairTreeUrl);
							}
						},'-',
						{width:'80px',height:'30px',iconCls:'icon-search',text:'查看详细',handler:function(){
								var row=getSelectedRow('maintainTable');
								if(!row){
									$.messager.show({
										title:'提示',
										msg:'请选择一行！'
									})
								}else{
									$('#maintainDlg').dialog('open').dialog('setTitle','查看维修信息');
									$('#mtaForm').form('load',row);
									saveRepairUrl='<%=request.getContextPath()%>/repair/updateRepair';
								}
							}
						},'-',
						{width:'120px',height:'30px',iconCls:'icon-save',text:'输出维修单据',handler:function(){
								var row=getSelectedRow('maintainTable');
								if(!row){
									$.messager.show({
										title:'提示',
										msg:'请选择一行！'
									})
								}else{
									
								}
							}
						}
					],
					onDblClickRow:function(index,row){
						$('#maintainDlg').dialog('open').dialog('setTitle','查看维修信息');
						$('#mtaForm').form('load',row);
						saveRepairUrl='<%=request.getContextPath()%>/repair/updateRepair';
					}
				});
			}
		});
		
		
		
		$('#manageTable').datagrid({
			striped:true,
			fit:false,
			fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			url:'<%=request.getContextPath()%>/equipment/getAllEquipment',
			columns:[[
				{ field:'equipmentNo',title:'设备编号',width:100,align:'center'},
				{ field:'equipmentName',title:'设备名称',width:100,align:'center'},
				{ field:'specification',title:'规格型号',width:100,align:'center'},
				{ field:'manufacturer',title:'生产厂商',width:100,align:'center'},
				{ field:'usingDepartment',title:'所在部门',width:100,align:'center'},
				{ field:'usingState',title:'使用状况',width:100,align:'center'},
				{ field:'acquisitionTime',title:'购置时间',width:100,align:'center'},
				{ field:'equipmentType',title:'设备类型',width:100,align:'center'}
			]],
			toolbar:[
					{width:'80px',height:'30px',iconCls:'icon-add',text:'新增',handler:function(){
								$('#manageDlg').dialog('open').dialog('setTitle','添加机械设备')
								$('#mgeForm').form('clear');
								saveEquipmentUrl='<%=request.getContextPath()%>/equipment/saveNewEquipment';
							}
					},'-',
					{width:'80px',height:'30px',iconCls:'icon-remove',text:'删除',handler:function(){
							deleteConfirm('manageTable','<%=request.getContextPath()%>/equipment/deleteEquipment',buildEquipmentTreeUrl);
							
						}
					},'-',
					{width:'80px',height:'30px',iconCls:'icon-search',text:'查看详细',handler:function(){
							var row=getSelectedRow('manageTable');
							if(!row){
								$.messager.show({
									title:'提示',
									msg:'请选择一行！'
								})
							}else{
								$('#manageDlg').dialog('open').dialog('setTitle','查看或编辑用户信息');
								$('#mgeForm').form('load',row);
								saveEquipmentUrl='<%=request.getContextPath()%>/equipment/updateEquipment';
							}
							
							<%-- $.ajax({
								type:'post',
								dataType:'json',
								url:'<%=request.getContextPath()%>/equipment/getAllEquipment',
								success:function(msg){
									console.log(msg);
									$('#manageTable').datagrid('loadData',msg);
								}
							}); --%>
						}
					}
			],
			onDblClickRow:function(index,row){
				$('#manageDlg').dialog('open').dialog('setTitle','查看或编辑用户信息');
				$('#mgeForm').form('load',row);
				saveEquipmentUrl='<%=request.getContextPath()%>/equipment/updateEquipment';
			}
		});
		
		$('#manageDlg').dialog({
			width:1000,
			height:720,
			closed:true,
			cache:false,
			shadow:true,
			openAnimation:'show',
			modal:'true',
			buttons:[
				{iconCls:'icon-ok',width:'90',height:'30',text:'保存',handler:function(){
						if($('#mgeForm').form('validate')){
							var data=$('#mgeForm').serializeObject();
						  $.ajax({
						    type:"post",
						    dataType: "json",
						    url:saveEquipmentUrl,
						    data:JSON.stringify(data),
						    contentType: "application/json;charset=utf-8",
						    success:function(result){
										if(result.success){
											$('#manageDlg').dialog('close');
											$('#manageTable').datagrid('reload');
											$.messager.show({
												title:'提示',
												msg:'成功更新'
											});
											loadTree(buildEquipmentTreeUrl);
										}else{
											$.messager.show({
												title:'提示',
												msg:'更新失败'
											});
										}
									}
						    	});
						}else{
							$.messager.show({
								title:'提示',
								msg:'请填写必要信息'
							})
							
						}
					
					} 
					  
					
				},
				{iconCls:'icon-cancel',width:'90',height:'30',text:'关闭',handler:function(){
						$('#manageDlg').dialog('close');
					}
				
				}
			]
		});
		$('#maintainDlg').dialog({
			width:1000,
			height:800,
			closed:true,
			cache:false,
			shadow:true,
			openAnimation:'show',
			modal:'true',
			buttons:[
				{iconCls:'icon-ok',width:'90',height:'30',text:'保存',handler:function(){
					if($('#mtaForm').form('validate')){
						var data=$('#mtaForm').serializeObject();
					  $.ajax({
					    type:"post",
					    dataType: "json",
					    url:saveRepairUrl,
					    data:JSON.stringify(data),
					    contentType: "application/json;charset=utf-8",
					    success:function(result){
									if(result.success){
										$('#maintainDlg').dialog('close');
										$('#maintainTable').datagrid('reload');
										$.messager.show({
											title:'提示',
											msg:'成功更新'
										});
										loadTree(buildRepairTreeUrl);
									}else{
										$.messager.show({
											title:'提示',
											msg:'更新失败'
										});
									}
								}
					    	});
					}else{
						$.messager.show({
							title:'提示',
							msg:'请填写必要信息'
						})
						
					}
					}
				},
				{iconCls:'icon-cancel',width:'90',height:'30',text:'关闭',handler:function(){
						$('#maintainDlg').dialog('close');
					}
				
				}
			]
		});
		
		 $('#eqmName').combogrid({
			url:'<%=request.getContextPath()%>/equipment/getAllEquipment',
			//method:'post',
			textField:'equipmentName',
			idField:'uid',
			editable :false,
			required:true,
			fit:false,
			fitColumns:true,
			columns:[[
				{ field:'equipmentNo',title:'设备编号',width:100,align:'center'},
				{ field:'equipmentName',title:'设备名称',width:100,align:'center'},
				{ field:'specification',title:'规格型号',width:100,align:'center'},
				{ field:'manufacturer',title:'生产厂商',width:100,align:'center'},
				{ field:'usingDepartment',title:'所在部门',width:100,align:'center'},
				{ field:'usingState',title:'使用状况',width:100,align:'center'},
				{ field:'acquisitionTime',title:'购置时间',width:100,align:'center'},
				{ field:'equipmentType',title:'设备类型',width:100,align:'center'}
			]],
			
			onSelect:function(rec){
				var g=$('#eqmName').combogrid('grid');
				var data=g.datagrid('getSelected');
				$('#eqmNo').textbox('setValue',data.equipmentNo);
				$('#eqmDep').textbox('setValue',data.usingDepartment);
			}
		});
		
	
	});
	
	//删除
	function deleteConfirm(tableId,url,treeUrl){
		var row=$('#'+tableId).datagrid('getSelected');
		if(row){
			
			$.messager.confirm('确认信息','你确定删除该条信息！',function(r){
				if(r){
					$.post(url,{uid:row.uid},function(result){
						if(result.success){
							$('#'+tableId).datagrid('reload');
							$.messager.show({
								title:'错误提示',
								msg:'删除成功'
							});
							loadTree(treeUrl);
						}else{
							$.messager.show({
								title:'错误提示',
								msg:result.errorMsg
								
							});
						}
					})
				}
			});
			
		}else{
			$.messager.show({
				title:'提示',
				msg:'请选中一行'
			});
		}
	}
	//加载左侧树
	function loadTree(treeUrl){
		var treeData=[];
		$.ajax({
			type:'post',
			dataType:'json',
			url:treeUrl,
			success:function(data){
				treeData=data;
			}
		});
		$('#tree').tree({
			data:treeData
		});
	}
	
	//得到选定的行
	function getSelectedRow(tableId){
		var row=$('#'+tableId).datagrid('getSelected');
		return row;
	}
	
	//判断是否为空对象
	function isEmptyObject(e){
		var t;  
	    for (t in e)  
	        return !1;  
	    return !0
	}
	//序列化表单
	$.fn.serializeObject = function(){    
	   var o = {};    
	   var a = this.serializeArray();    
	   $.each(a, function() {    
	       if (o[this.name]) {    
	           if (!o[this.name].push) {    
	               o[this.name] = [o[this.name]];    
	           }    
	           o[this.name].push(this.value || '');    
	       } else {    
	           o[this.name] = this.value || '';    
	       }    
	   });    
	   return o;    
	};  
</script>

</html>