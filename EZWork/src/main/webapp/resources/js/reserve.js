$(function(){
	
	$("form").submit(function(){
		/*
		var stime = document.getElementById("stime").value;
		var etime = document.getElementById("etime").value;
		console.log($('form').serialize());
		if(parseInt(stime)>=parseInt(etime)){
			alert('예약 시간을 확인하세요.');
			return false;
		}*/
	})
	
	var date = new Date();
	var today = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
	document.getElementById("today").innerHTML=today;
	ajax();
})
	function ajax(){
		$.ajax({
			type : "GET",
			url  : "meetingAjax.res",
			dataType : "json",
			cache : false,
			success : function(data){
				$(data.list).each(
					function(index, item){
						var info = "회의내용 : "+ item.MSUBJECT + " / 전화번호 : " + item.USER_TEL;
						if(item.RNAME=="Forest"){
							var output = "<span class='label label-success label-inline mr-2'" +
									"data-container='body' data-toggle=" +
									"'tooltip' data-placement='top' title='" +
									info + "'>"
							output += item.USER_NAME + '</span>'
							$('#f'+item.MTIME).append(output);
						} else if(item.RNAME=="Ocean"){
							var output = "<span class='label label-light-dark label-inline mr-2'" +
							"data-container='body' data-toggle=" +
							"'tooltip' data-placement='top' title='" +
							info + "'>"
							output += item.USER_NAME + '</span>'
							$('#o'+item.MTIME).append(output);
						} else if(item.RNAME=="Desert"){
							var output = "<span class='label label-danger label-inline mr-2'" +
							"data-container='body' data-toggle=" +
							"'tooltip' data-placement='top' title='" +
							info + "'>"
							output += item.USER_NAME + '</span>'
							$('#d'+item.MTIME).append(output);
						} else if(item.RNAME=="Mountain"){
							var output = "<span class='label label-warning label-inline mr-2'" +
							"data-container='body' data-toggle=" +
							"'tooltip' data-placement='top' title='" +
							info + "'>"
							output += item.USER_NAME + '</span>'
							$('#m'+item.MTIME).append(output);
						} else if(item.RNAME=="Sky"){
							var output = "<span class='label label-info label-inline mr-2'" +
							"data-container='body' data-toggle=" +
							"'tooltip' data-placement='top' title='" +
							info + "'>"
							output += item.USER_NAME + '</span>'
							$('#s'+item.MTIME).append(output);
						}
						$('[data-toggle="tooltip"]').tooltip();
					})
			},
			error : function(request, error){
				console.log('meetingAjax.res : ajax 에러'+
						'\n'+request.responseText+'\n'+request.status+error)
			}
		})
	}


