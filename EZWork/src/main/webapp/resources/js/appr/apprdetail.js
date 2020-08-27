$(function() {

	var chkcode = $("#chkCode").val();
	console.log(chkcode);
	if (chkcode == 1) {
		$("#apbtn").attr("disabled",true);
	}
	
	var stat_f="";
	var stat_s="";
	var stat_t="";	
	
	if($("#first_stat").length){
		stat_f = $("#first_stat").val();
		switch (stat_f) {
		case "승인":
			$("#f_valid").addClass('is-valid');
			break;
		case "반려":
			$("#f_valid").addClass('is-invalid');
			break;
		}
	}
	if($("#second_stat").length){
		stat_s = $("#second_stat").val();
		switch (stat_s) {
		case "승인":
			$("#s_valid").addClass('is-valid');
			break;
		case "반려":
			$("#s_valid").addClass('is-invalid');
			break;
		}		
	}
	if($("#third_stat").length){
		stat_t = $("#third_stat").val();
		switch (stat_t) {
		case "승인":
			$("#t_valid").addClass('is-valid');
			break;
		case "반려":
			$("#t_valid").addClass('is-invalid');
			break;
		}
	}
	

	
	
	
	
	
	

})


	