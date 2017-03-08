$(document).ready(function () {

	$("#popover").popover({
		html: true,
		content: function () {
			if (Cookies.get('token')) {
				return $("#popover-content").html();
			} else {
				window.location.href = "/login";
			}
		}
	});

	$(document).on("click", "#logout", function () {
		$.post("/logout", {},
			function (resp) {
				window.location.href = "/home";
			}
		);
	});
});