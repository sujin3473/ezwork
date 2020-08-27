$(function(){
	go(1);
	
	$("#upfile").change(function(){
		var inputfile = $(this).val().split('\\');
		$("#filevalue").text(inputfile[inputfile.length-1]);
	})
	
	$("#allcheck").click(function(){
		$('input:checkbox').prop('checked',this.checked);
	})

	

	var timeout = null
	$('.fmem').on('keyup', function() {
		var text = this.value
		clearTimeout(timeout)
		timeout = setTimeout(function() {
			var key = $(".fmem").val()
			var str = "keyword="+key;
			//console.log(str);
			fmemajax(str);
			console.log(text)
		}, 500)
	})
	$('.smem').on('keyup', function() {
		var text = this.value
		clearTimeout(timeout)
		timeout = setTimeout(function() {
			var key = $(".smem").val()
			var str = "keyword="+key;
			//console.log(str);
			smemajax(str);
			console.log(text)
		}, 500)
	})
	$('.tmem').on('keyup', function() {
		var text = this.value
		clearTimeout(timeout)
		timeout = setTimeout(function() {
			var key = $(".tmem").val()
			var str = "keyword="+key;
			//console.log(str);
			tmemajax(str);
			console.log(text)
		}, 500)
	})
	
	$("#search_btn").on('click',function(){
		search(1);
		
	});
	
	
})

function go(page){
	console.log("go");
	var limit = 10;
	//var id = document.getElementById('SENDER').value;
	var data = "limit=" + limit + "&start=ajax&page=" + page  ;
	ajax(data);
}

function search(page){
	console.log("searchajax");
	var limit = 10;
	//var id = document.getElementById('SENDER').value;
	var number=$("#search_number").val();
	var name = $("#search_name").val();
	var contentitle = $("#search_contentitle").val();
	var start = $("#search_start").val();
	var end = $("#search_end").val();
	var appr_stat = $("#search_appr_stat").val();
	var appr_val = $("#search_appr_val").val();
	
	var data = "limit=" + limit + "&page=" + page + "&number="+number+"&name="
				+name+"&contentitle="+contentitle+"&start="+start+"&end="+end
				+"&appr_stat="+appr_stat+"&appr_val="+appr_val  ;
	console.log(data);
	searchajax(data);
}

function setPaging(href, digit){
	output += '';
	active="";
	if(href==""){
		active=" btn-hover-info active";
	}
	anchor = "<a class='btn btn-icon btn-sm btn-light mr-2 my-1"+ active + "'" + href + ">" + digit + "</a></li>";
	output += anchor;
}

var output = "";
function fmemajax(str) {
	output = "";
	$.ajax({
		type : "POST",
		data : str,
		url : "SearchMemAjax.appr",
		dataType : "json",
		cache : false,
		success : function(data) {
			var totalData = data.memcount;
			console.log(totalData);
			if(totalData>0){
			$('#fmemlist').children('option').remove();
			$(data.memberlist).each(
					function(index, item) {
						//console.log(item.m_PART_C + ' ' + item.m_NAME);
						
						output+='<option value="'+ item.m_PART_C+' '+item.m_NAME  +'">' +'</option>'

			})
			
			$("#fmemlist").append(output);
			}else if(totalData==0){
				output+='<option value="일치하는 정보가 없습니다. "</option>';
				$("#fmemlist").append(output);
			}

		}, // success end
		error : function() {
			console.log('에러')
		}
	})
}
function smemajax(str) {
	output = "";
	$.ajax({
		type : "POST",
		data : str,
		url : "SearchMemAjax.appr",
		dataType : "json",
		cache : false,
		success : function(data) {
			var totalData = data.memcount;
			console.log(totalData);
			if(totalData>0){
			$('#smemlist').children('option').remove();
			$(data.memberlist).each(
					function(index, item) {
						//console.log(item.m_PART_C + ' ' + item.m_NAME);
						
						output+='<option value="'+ item.m_PART_C+' '+item.m_NAME  +'">' +'</option>'

			})
			
			$("#smemlist").append(output);
			}else if(totalData==0){
				output+='<option value="일치하는 정보가 없습니다. "</option>';
				$("#fmemlist").append(output);
			}

		}, // success end
		error : function() {
			console.log('에러')
		}
	})
}
function tmemajax(str) {
	output = "";
	$.ajax({
		type : "POST",
		data : str,
		url : "SearchMemAjax.appr",
		dataType : "json",
		cache : false,
		success : function(data) {
			var totalData = data.memcount;
			console.log(totalData);
			if(totalData>0){
			$('#tmemlist').children('option').remove();
			$(data.memberlist).each(
					function(index, item) {
						//console.log(item.m_PART_C + ' ' + item.m_NAME);
						
						output+='<option value="'+ item.m_PART_C+' '+item.m_NAME  +'">' +'</option>'

			})
			
			$("#tmemlist").append(output);
			}else if(totalData==0){
				output+='<option value="일치하는 정보가 없습니다. "</option>';
				$("#fmemlist").append(output);
			}

		}, // success end
		error : function() {
			console.log('에러')
		}
	})
}


function searchajax(sdata){
	output="";
	$.ajax({
		type : "POST",
		data : sdata,
		url : "Search.appr",
		dataType : "json",
		cache : false,
		success : function(data){
			var totalData = data.listcount;	
			console.log(totalData);
			$("tbody").remove();
			if(totalData > 0){
				var num = totalData - (data.nowpage -1) * data.limit;
				output = "<tbody>";
				$(data.apprlist).each(
					function(index, item){
						var stat="",val="";
						switch(item.appr_STAT){
						case 0:
							stat = '업무';
							break;
						case 1:
							stat = '휴가';
							break;
						}
						
						switch(item.appr_VAL){
						case 0:
							val = '승인대기';
							break;
						case 1:
							val = '승인(1차)';
							break;
						case 2:
							val = '승인(2차)';
							break;
						case 3:
							val = '승인(최종)';
							break;
						case 4:
							val = '반려';
							break;
						}
						var comp_date='';
						if(item.appr_COMP_DATE==null){
							comp_date='-';
						}else{
							comp_date = item.appr_COMP_DATE.substring(0,10);
						}
						
						
						output += '<tr><td><div><p class="font-size-lg">'+item.appr_CODE + '</p></div></td>'
						output += '<td><div><p class="font-size-lg">'+'<span class="label label-lg font-weight-bold label-light-info label-inline">'
						output += item.m_PART+ '</span>'+ item.appr_NAME + '</p></div></td>'
						
						output += '<td><div><p class="font-size-lg">' + '<a href="ApprDetailAction.appr?num='
								+item.appr_CODE+'&page=' + data.nowpage+'">'+ item.appr_TITLE +'</a></p></div></td>'
						output += '<td><div><p class="font-size-lg">'+stat+'</p></div></td>'
						output += '<td><div><p class="font-size-lg">' + item.appr_DATE.substring(0,10)+'</p></div></td>'
						output += '<td><div><p class="font-size-lg">' + comp_date +'</p></div></td>'
						output += '<td><div><p class="font-size-lg">'+val + '</p></div></td>'
					})
				output += "</tbody>"
				$('table').append(output)//table 완성
				
				$(".pagination").empty(); //페이징 처리 영역 내용 제거
				output = "";
				
				digit = '<i class="ki ki-bold-arrow-back icon-xs"></i>'; //이전 버튼							
				href="";	
				if (data.nowpage > 1) {
					href = 'href=javascript:go(' + (data.nowpage - 1) + ')';
				}
				setPaging(href, digit);
				
				for (var i = data.startpage; i <= data.endpage; i++) {
					digit = i;
					href="";
					if (i != data.nowpage) {
						href = 'href=javascript:search(' + i + ')';
					} 
					setPaging( href, digit);
				}
				
				digit = '<i class="ki ki-bold-arrow-next icon-xs"></i>'; //다음 버튼
				href="";
				if (data.page < data.maxpage) {
					href = 'href=javascript:search(' + (data.nowpage + 1) + ')';
				} 
				setPaging( href, digit);
				$('.pagination').append(output)
			}//if(data.listcount) end
			else if(totalData==0){
				output = "<tbody><tr><th colspan='7'><h3>검색된 결재가 없습니다.</h3></th></tr></tbody>";
				$('table').append(output)
			}			
		}, //success end
		error : function(){
			console.log('searchajax 에러')
		}
	})
}


function ajax(sdata){
	output="";
	$.ajax({
		type : "POST",
		data : sdata,
		url : "ApprListAjax.appr",
		dataType : "json",
		cache : false,
		success : function(data){
			var totalData = data.listcount;		
			$("tbody").remove();
			if(totalData > 0){
				var num = totalData - (data.nowpage -1) * data.limit;
				output = "<tbody>";
				$(data.apprlist).each(
					function(index, item){
						var stat="",val="";
						switch(item.appr_STAT){
						case 0:
							stat = '업무';
							break;
						case 1:
							stat = '휴가';
							break;
						}
						
						switch(item.appr_VAL){
						case 0:
							val = '승인대기';
							break;
						case 1:
							val = '승인(1차)';
							break;
						case 2:
							val = '승인(2차)';
							break;
						case 3:
							val = '승인(최종)';
							break;
						case 4:
							val = '반려';
							break;
						}
						var comp_date='';
						if(item.appr_COMP_DATE==null){
							comp_date='-';
						}else{
							comp_date = item.appr_COMP_DATE.substring(0,10);
						}
						
						
						output += '<tr><td><div><p class="font-size-lg">'+item.appr_CODE + '</p></div></td>'
						output += '<td><div><p class="font-size-lg">'+'<span class="label label-lg font-weight-bold label-light-info label-inline">'
						output += item.m_PART+ '</span>'+ item.appr_NAME + '</p></div></td>'
						
						output += '<td><div><p class="font-size-lg">' + '<a href="ApprDetailAction.appr?num='
								+item.appr_CODE+'&page=' + data.nowpage+'">'+ item.appr_TITLE +'</a></p></div></td>'
						output += '<td><div><p class="font-size-lg">'+stat+'</p></div></td>'
						output += '<td><div><p class="font-size-lg">' + item.appr_DATE.substring(0,10)+'</p></div></td>'
						output += '<td><div><p class="font-size-lg">' + comp_date +'</p></div></td>'
						output += '<td><div><p class="font-size-lg">'+val + '</p></div></td>'
					})
				output += "</tbody>"
				$('table').append(output)//table 완성
				
				$(".pagination").empty(); //페이징 처리 영역 내용 제거
				output = "";
				
				digit = '<i class="ki ki-bold-arrow-back icon-xs"></i>'; //이전 버튼							
				href="";	
				if (data.nowpage > 1) {
					href = 'href=javascript:go(' + (data.nowpage - 1) + ')';
				}
				setPaging(href, digit);
				
				for (var i = data.startpage; i <= data.endpage; i++) {
					digit = i;
					href="";
					if (i != data.nowpage) {
						href = 'href=javascript:go(' + i + ')';
					} 
					setPaging( href, digit);
				}
				
				digit = '<i class="ki ki-bold-arrow-next icon-xs"></i>'; //다음 버튼
				href="";
				if (data.page < data.maxpage) {
					href = 'href=javascript:go(' + (data.nowpage + 1) + ')';
				} 
				setPaging( href, digit);
				$('.pagination').append(output)
			}//if(data.listcount) end
			else if(totalData==0){
				output = "<tbody><tr><th colspan='7'><h3>등록된 결재가 없습니다.</h3></th></tr></tbody>";
				$('table').append(output)
			}			
		}, //success end
		error : function(){
			console.log('에러')
		}
	})
}