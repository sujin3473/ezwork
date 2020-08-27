	$(function() {
		//검색 클릭 후 응답화면에는 검색시 선택한 필드가 선택되도록 합니다
		var selectedValue = '${search_field}'
		if (selectedValue != '-1')
			$("#viewcount").val(selectedValue);
		
		
		
		$("#viewcount").change(function() {
			selectedValue = $(this).val();
			$("input").val('');
 
			message = [ "이름을", "부서를", "연락처를" ]
			$("input").attr("placeholder", message[selectedValue] + " 입력하세요");

		})

		$("tr > td:nth-child(3) > a").click(function(event) {
			var answer = confirm("정말 삭제하시겠습니까?");
			console.log(answer); //취소를 클릭한 경우-false;
			console.log("test"); //취소를 클릭한 경우-false;
			if (!answer) { //취소를 클릭한 경우
				event.preventDefault();

			}
		})
	});
	
	
