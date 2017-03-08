$(document).ready(function () {
	$("#all_city_container").hide();


	$("a").click(function () {
		$.post("/select_city",
			{
				"name": $(this).html(),
			},
			function (resp) {
				window.location.href = "/home";
			}
		);
	});

	$("#all_city").on("click", function () {
		if ($("#all_city_container").is(":hidden")) {
			$("#all_city_container").show();
		} else {
			$("#all_city_container").hide();
		}
	});

});