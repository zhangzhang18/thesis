var a = false;
$(document).ready(function() {
	
	var length = $("tr[id^='recommend_']").length;
	/* alert(length); */
	h();
	for (var i = 1; i <= length; i++) {
		var p = $("p").text();
		var stuid = $(".stu_" + i).text();
		var flag = $(".flag_" + i).text();
		$(".stu_" + i).hide();
		/*
		 * alert(p); alert(length); alert(stuid);
		 */
		if (p == stuid) {
			a = true;
			$(".nothesis" + i).show();

		} else {
			$(".nothesis" + i).hide();
		}

	}
});
function h() {

	if (a) {
		//alert(a);
		$(".thesis").hide();
	}

}
