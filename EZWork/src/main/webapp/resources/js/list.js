function go(page) {
   var limit = $('#viewcount').val();
   var data = "limit=" + limit + "&state=ajax&page=" + page;
   ajax(data);
}

//총 2페이지 페이징 처리된 경우
//이전 1 2 다음
//현재 페이지가 1페이지인 경우 아래와 같은 페이징 코드가 필요
//<li class="page-item"><a class="page-link gray">이전&nbsp;</a></li>
//<li class="page-item"><a class="page-link gray">1</a></li>
//<li class="page-item"><a class="page-link" href="javascript:go(2)">2</a></li>
//<li class="page-item"><a class="page-link" href="javascript:go(2)">다음&nbsp;</a></li>

//현재 페이지가 2페이지인 경우 아래와 같은 페이징 코드가 필요
//<li class="page-item"><a class="page-link" href="javascript:go(1)">이전&nbsp;</a></li>
//<li class="page-item"><a class="page-link" href="javascript:go(1)">1</a></li>
//<li class="page-item"><a class="page-link gray">2</a></li>
//<li class="page-item"><a class="page-link gray">다음&nbsp;</a></li>
function setPaging(href, digit){
   output += "<li class=page-item>";
   gray="";
   if(href=="") {
      gray=" gray";
   }
   anchor = "<a class='page-link" + gray + "'"  + href + ">"+ digit + "</a></li>";
   output += anchor;
}

function ajax(sdata) {
   // 1줄보기 선택시 리턴된 데이터
   /*
    {"page":1,"maxpage":6,"startpage":1,"endpage":6,"listcount":6,"limit":1,
     "boardlist":[{"BOARD_NUM":6,"BOARD_NAME":"admin","BOARD_SUBJECT":"1","BOARD_CONTENT":"1","BOARD_RE_REF":6,"BOARD_RE_LEV":0,"BOARD_RE_SEQ":0,"BOARD_READCOUNT":0,"BOARD_DATE":"2019-05-26"}]}
    */
   console.log(sdata)
   output = "";
   $.ajax({
      type : "POST",
      data : sdata,
      //url : "BoardList.bo"
      //보드리스트에이젝스.bo로 주소 바꿔주고 Network 확인해보자
         //"board_NAME":"jaeout2"이런식으로 값 받아온다
         //(라이브러리가 자동으로 대신 받아다줌) 
         //근데 나는 BOARD_NAME 이런식으로 설정을 해놨단 말여,,,
         //그래서 그거 대소문자 수정 안 해주면 값들이 다 undefined로 뜰거야! 
      url : "EventBoardListAjax.ev", //요청이 들어오면
      dataType : "json",
      cache : false,
      success : function(data) {
         $("#viewcount").val(data.limit);
         $("table").find("font").text("글 개수 : " + data.listcount);

         if (data.listcount > 0) { // 총갯수가 0보다 큰 경우
            $("tbody").remove();
            var num = data.listcount - (data.page - 1) * data.limit;
            console.log(num)
            output = "<tbody>";
            $(data.boardlist).each(
               function(index, item) {
                  output += '<tr><td>' + (num--) + '</td>'
                  blank_count = item.ev_RE_LEV * 2 + 1;
                  blank = '&nbsp;';
                  for (var i = 0; i < blank_count; i++) {
                     blank += '&nbsp;&nbsp;';
                  }
                  img="";
                  if (item.ev_RE_LEV > 0) {
                     img="<img src='resources/image/answerLine.gif'>";
                  }
                     
                  output +=  "<td><div>" + blank + img
                  output += ' <a href="BoardDetailAction.bo?num='
                          + item.ev_NO + '&page='
                         + data.page + '">'
                  output += item.ev_TITLE + '</a></div></td>'
                  output += '<td><div>' + item.ev_NAME+'</div></td>'
                  output += '<td><div>' + item.ev_DATE+'</div></td>'
                  output += '<td><div>' + item.ev_READCOUNT
                        + '</div></td></tr>'
               })
            output += "</tbody>"
            $('table').append(output)//table 완성
            
            $(".pagination").empty(); //페이징 처리 영역 내용 제거
            output = "";
            
            digit = '이전&nbsp;'
            href="";   
            if (data.page > 1) {
               href = 'href=javascript:go(' + (data.page - 1) + ')';
            }
            setPaging(href, digit);
            
            for (var i = data.startpage; i <= data.endpage; i++) {
               digit = i;
               href="";
               if (i != data.page) {
                  href = 'href=javascript:go(' + i + ')';
               } 
               setPaging( href, digit);
            }
            
            digit = '다음&nbsp;';
            href="";
            if (data.page < data.maxpage) {
               href = 'href=javascript:go(' + (data.page + 1) + ')';
            } 
            setPaging( href, digit);

            $('.pagination').append(output)
         }//if(data.listcount) end
         
      }, //success end
      error : function() {
         console.log('에러')
      }
   })// ajax end
 } // fucntion ajax end



$(function(){
   
   $("#viewcount").change(function(){
      
      go(1); //보여줄 페이지를 1페이지로 설정합니다
   }); //change end
   
   $("button").click(function(){
      
      location.href="BoardWrite.ev"; 
   })
   
})